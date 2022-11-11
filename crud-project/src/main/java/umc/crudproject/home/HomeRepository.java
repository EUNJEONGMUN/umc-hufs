package umc.crudproject.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import umc.crudproject.response.BoardListRes;
import umc.crudproject.response.PostRes;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class HomeRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
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

    public PostRes newPost(PostRes post, String password) {
        String query = "INSERT INTO Board(title, content, writer, password) VALUES (?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(c -> {
            PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setString(3, post.getWriter());
            ps.setString(4, password);
            return ps;
        }, keyHolder);
        int idx = keyHolder.getKey().intValue();
        post.setIdx(idx);
        return post;

    }

    public void editPost(PostRes post) {
        String query = "UPDATE Board SET title=?, content=? WHERE idx=?;";
        Object[] parameter = new Object[]{post.getTitle(), post.getContent(), post.getIdx()};
        this.jdbcTemplate.update(query, parameter);
    }

    public int checkPassword(int postIdx, String writer, String password) {
        String query = "SELECT EXISTS(SELECT * FROM Board WHERE idx=? AND writer=? AND password=?);";
        Object[] parameter = new Object[]{postIdx, writer, password};
        return this.jdbcTemplate.queryForObject(query, int.class, parameter);
    }

    public void deletePost(int postIdx) {
        String query = "UPDATE Board SET status='N' WHERE idx=?;";
        this.jdbcTemplate.update(query, postIdx);
    }
}
