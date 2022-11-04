package umc.crudproject.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import umc.crudproject.response.BoardListRes;

import java.util.List;

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

}
