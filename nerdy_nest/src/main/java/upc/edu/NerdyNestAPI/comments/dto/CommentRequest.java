package upc.edu.NerdyNestAPI.comments.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private String description;
    private Integer rating;
}
