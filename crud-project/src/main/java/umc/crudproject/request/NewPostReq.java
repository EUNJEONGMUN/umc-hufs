package umc.crudproject.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NewPostReq {
    private String title;
    private String content;
    private String writer;
    private String password;
}
