package uz.pdp.post_jwt.dto;


import com.fasterxml.jackson.core.StreamReadFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentReq {

    private String comments;
    private String author;
}
