package upc.edu.NerdyNestAPI.shopping_cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "carts")
public class Cart {
    @Id
    private String id;
    @Field(name = "client_id")
    private String clientId;
    @Field(name = "items")
    List<CartItem> items;
}
