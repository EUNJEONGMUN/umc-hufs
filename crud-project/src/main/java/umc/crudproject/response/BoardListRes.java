package umc.crudproject.response;

import lombok.*;

@AllArgsConstructor
@Getter @Setter
public class BoardListRes {
    private int idx;
    private String title;
    private String content;
    private String writer;
    private String updateTime;

    @Override
    public String toString() {
        return "idx: "+idx+" title: "+ title;
    }
}
