package umc.crudproject.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umc.config.BaseException;
import umc.crudproject.response.BoardListRes;
import umc.crudproject.response.PostRes;

import java.util.List;

import static umc.config.BaseResponseStatus.DATABASE_ERROR;

@Slf4j
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
            log.error(e.getMessage(), e);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public BoardListRes getBoardPost(int postIdx) throws Exception {
        try {
            return homeRepository.getBoardPost(postIdx);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public PostRes newPost(PostRes post, String password) throws Exception {
        try {
            return homeRepository.newPost(post, password);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void editPost(PostRes post) throws Exception {
        try {
            homeRepository.editPost(post);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkPassword(int postIdx, String writer, String password) throws Exception {
        try {
            return homeRepository.checkPassword(postIdx, writer, password);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deletePost(int postIdx) throws Exception {
        try {
            homeRepository.deletePost(postIdx);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
