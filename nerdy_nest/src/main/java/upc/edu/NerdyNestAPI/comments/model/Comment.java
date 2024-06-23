package upc.edu.NerdyNestAPI.comments.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;
    @Indexed
    @Field(name = "client_id")
    private String clientId;
    @Indexed
    @Field(name = "product_id")
    private String productId;
    @Field(name = "description")
    private String description;
    @Field(name = "date")
    private LocalDateTime date;
    @Field(name = "rating")
    private Integer rating;
}
