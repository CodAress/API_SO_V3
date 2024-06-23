package upc.edu.NerdyNestAPI.comments.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {
    private String id;
    private String description;
    private Integer rating;
    private String clientId;
    private String productId;
    private LocalDateTime date;
}
