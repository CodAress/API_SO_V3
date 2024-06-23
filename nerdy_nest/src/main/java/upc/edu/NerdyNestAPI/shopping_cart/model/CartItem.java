package upc.edu.NerdyNestAPI.shopping_cart.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
@Builder
@Data
public class CartItem {
    @Field(name = "product_id")
    private String productId;
    @Field(name = "quantity")
    private Integer quantity;
}
