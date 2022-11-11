package umc.crudproject.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umc.config.BaseResponse;
import umc.crudproject.request.DelPostReq;
import umc.crudproject.request.EditPostReq;
import umc.crudproject.request.NewPostReq;
import umc.crudproject.response.BoardListRes;
import umc.crudproject.response.DeleteRes;
import umc.crudproject.response.PostRes;

import java.util.List;

import static umc.config.BaseResponseStatus.UNAUTHORIZED;

@Slf4j
@RestController
public class HomeController {

    @Autowired
    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/home")
    public String start() {
        return "hello!";
    }

    @GetMapping("/board/list")
    public BaseResponse<List<BoardListRes>> getBoardList() throws Exception {
        List<BoardListRes> list = homeService.getBoardList();
        return new BaseResponse<>(list);
    }

    @GetMapping("/board/{postIdx}")
    public BaseResponse<BoardListRes> getBoardPost(@PathVariable int postIdx) throws Exception {
        BoardListRes post = homeService.getBoardPost(postIdx);
        return new BaseResponse<>(post);
    }

    @PostMapping("/board/new")
    public BaseResponse<PostRes> newPost(@RequestBody NewPostReq newPostReq) throws Exception {
        PostRes post = new PostRes(newPostReq.getTitle(), newPostReq.getContent(), newPostReq.getWriter());
        homeService.newPost(post, newPostReq.getPassword());
        return new BaseResponse<>(post);
    }

    @PutMapping("/board/{postIdx}")
    public BaseResponse<PostRes> editPost(
            @PathVariable int postIdx, @RequestBody EditPostReq editPostReq) throws Exception {
        if (homeService.checkPassword(postIdx, editPostReq.getWriter(), editPostReq.getPassword()) != 1) {
            log.info("UNAUTHORIZED");
            return new BaseResponse(UNAUTHORIZED);
        }
        PostRes post =
                new PostRes(postIdx, editPostReq.getTitle(), editPostReq.getContent(), editPostReq.getWriter());
        homeService.editPost(post);
        return new BaseResponse<>(post);
    }

    @PatchMapping("/board/{postIdx}/delete")
    public BaseResponse<DeleteRes> deletePost(
            @PathVariable int postIdx, @RequestBody DelPostReq delPostReq) throws Exception {
        if (homeService.checkPassword(postIdx, delPostReq.getWriter(), delPostReq.getPassword()) != 1) {
            log.info("UNAUTHORIZED");
            return new BaseResponse(UNAUTHORIZED);
        }
        homeService.deletePost(postIdx);
        DeleteRes deleteRes = new DeleteRes("삭제되었습니다.");
        return new BaseResponse<>(deleteRes);
    }
}
