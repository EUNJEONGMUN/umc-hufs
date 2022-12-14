package umc.crudproject.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class BoardListRes {
    private int idx;
    private String title;
    private String content;
    private String writer;
    private String updateTime;

    public BoardListRes(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "idx: "+idx+" title: "+ title;
    }
}
