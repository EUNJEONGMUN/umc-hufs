package umc.crudproject.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import umc.crudproject.request.NewPostReq;
import umc.crudproject.response.BoardListRes;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class HomeRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<BoardListRes> getBoardList() {
        String query = "SELECT idx, title, content, writer, createdAt\n" +
                "FROM Board\n" +
                "WHERE status='Y'\n" +
                "ORDER BY createdAt DESC;";
        return this.jdbcTemplate.query(query,
                (rs, rowNum) -> new BoardListRes(
                        rs.getInt("idx"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("writer"),
                        rs.getString("createdAt")
                ));
    }

    public BoardListRes getBoardPost(int postIdx) {
        String query = "SELECT idx, title, content, writer, createdAt\n" +
                "FROM Board\n" +
                "WHERE status='Y' AND idx=?;";
        return this.jdbcTemplate.queryForObject(query,
                (rs, rowNum) -> new BoardListRes(
                        rs.getInt("idx"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("writer"),
                        rs.getString("createdAt")
                ), postIdx);
    }

    public void newPost(NewPostReq newPostReq) {
        String query = "INSERT INTO Board(title, content, writer, password) VALUES (?,?,?,?);";
        Object[] parameter = new Object[]{newPostReq.getTitle(), newPostReq.getContent(),
                newPostReq.getWriter(), newPostReq.getPassword()};
        this.jdbcTemplate.update(query, parameter);

    }
}
