package upc.edu.NerdyNestAPI.shopping_cart.dto;

import lombok.Data;
import upc.edu.NerdyNestAPI.shopping_cart.model.CartItem;

import java.util.List;

@Data
public class CartResponse {
    private String id;
    private String clientId;
    private List<CartItem> items;
}
