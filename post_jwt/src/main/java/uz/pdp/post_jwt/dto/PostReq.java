package uz.pdp.post_jwt.dto;


import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostReq {

    private String title;
    private String content;
    private String author;
    private UUID commentId;
    private byte file;

}
