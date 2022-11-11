package umc.crudproject.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRes {
    private int idx;
    private String title;
    private String content;
    private String writer;

    public PostRes(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
