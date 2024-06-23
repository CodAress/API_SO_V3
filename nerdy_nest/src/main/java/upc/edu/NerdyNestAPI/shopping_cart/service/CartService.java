package upc.edu.NerdyNestAPI.shopping_cart.service;

import upc.edu.NerdyNestAPI.shopping_cart.dto.CartResponse;
import upc.edu.NerdyNestAPI.shopping_cart.model.Cart;

public interface CartService {
    Cart createCart(String clientId);
    Cart getCart(String clientId);
    Cart addItemToCart(String clientId, String productId, Integer quantity);
    Cart removeItemFromCart(String clientId, String productId);
    Cart updateItemQuantity(String clientId, String productId, Integer quantity);
    Cart clearCart(String clientId);
    void deleteCart(String clientId);
}
