package umc.crudproject.request;

import lombok.Getter;

@Getter
public class EditPostReq {
    private String title;
    private String content;
    private String password;
}
