package umc.crudproject.home;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import umc.crudproject.response.BoardListRes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HomeControllerTest {
    @Autowired
    private HomeService homeService;

    @Test
    void 테스트() throws Exception {
        List<BoardListRes> boardList = homeService.getBoardList();
        for (BoardListRes boardListRes : boardList) {
            System.out.println(boardListRes);
        }
    }
}