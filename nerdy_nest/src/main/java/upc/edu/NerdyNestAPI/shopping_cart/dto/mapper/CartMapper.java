package upc.edu.NerdyNestAPI.shopping_cart.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.NerdyNestAPI.shopping_cart.dto.CartResponse;
import upc.edu.NerdyNestAPI.shopping_cart.model.Cart;

import java.util.List;

@Mapper
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
    CartResponse cartToCartResponse(Cart cart);
    List<CartResponse> cartsToCartsResponse(List<Cart> carts);
}
