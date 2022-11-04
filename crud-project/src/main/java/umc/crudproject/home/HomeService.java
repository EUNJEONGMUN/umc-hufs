package umc.crudproject.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umc.crudproject.request.NewPostReq;
import umc.crudproject.response.BoardListRes;

import java.util.List;

@Service
public class HomeService {
    @Autowired
    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public List<BoardListRes> getBoardList() throws Exception {
        try {
            return homeRepository.getBoardList();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public BoardListRes getBoardPost(int postIdx) throws Exception {
        try {
            return homeRepository.getBoardPost(postIdx);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void newPost(NewPostReq newPostReq) throws Exception {
        try {
            homeRepository.newPost(newPostReq);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public void editPost(int postIdx, String title, String content) throws Exception {
        try {
            homeRepository.editPost(postIdx, title, content);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public int checkPassword(int postIdx, String password) throws Exception {
        try {
            return homeRepository.checkPassword(postIdx, password);
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
