package umc.crudproject.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umc.crudproject.request.DelPostReq;
import umc.crudproject.request.EditPostReq;
import umc.crudproject.request.NewPostReq;
import umc.crudproject.response.BoardListRes;

import java.util.List;

@Slf4j
@RestController
public class HomeController {
    
    @Autowired
    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @ResponseBody
    @GetMapping("/home")
    public String start() {
        return "hello!";
    }

    @ResponseBody
    @GetMapping("/board/list")
    public ResponseEntity<List<BoardListRes>> getBoardList() throws Exception {
        List<BoardListRes> list = homeService.getBoardList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/board/{postIdx}")
    public ResponseEntity<BoardListRes> getBoardPost(@PathVariable int postIdx) throws Exception {
        BoardListRes post = homeService.getBoardPost(postIdx);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping("/board/new")
    public ResponseEntity newPost(@RequestBody NewPostReq newPostReq) throws Exception {
        homeService.newPost(newPostReq);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/board/{postIdx}")
    public ResponseEntity editPost(
            @PathVariable int postIdx, @RequestBody EditPostReq editPostReq) throws Exception {
        if (homeService.checkPassword(postIdx, editPostReq.getPassword())!=1) {
            log.info("UNAUTHORIZED");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        homeService.editPost(postIdx, editPostReq.getTitle(), editPostReq.getContent());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/board/{postIdx}/delete")
    public ResponseEntity deletePost(
            @PathVariable int postIdx, @RequestBody DelPostReq delPostReq) throws Exception {
        if (homeService.checkPassword(postIdx, delPostReq.getPassword())!=1) {
            log.info("UNAUTHORIZED");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        homeService.deletePost(postIdx);
        return new ResponseEntity(HttpStatus.OK);
    }
}
