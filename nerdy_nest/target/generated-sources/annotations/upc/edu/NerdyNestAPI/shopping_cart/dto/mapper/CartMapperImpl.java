package upc.edu.NerdyNestAPI.shopping_cart.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import upc.edu.NerdyNestAPI.shopping_cart.dto.CartResponse;
import upc.edu.NerdyNestAPI.shopping_cart.model.Cart;
import upc.edu.NerdyNestAPI.shopping_cart.model.CartItem;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-23T18:14:46-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class CartMapperImpl implements CartMapper {

    @Override
    public CartResponse cartToCartResponse(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponse cartResponse = new CartResponse();

        cartResponse.setId( cart.getId() );
        cartResponse.setClientId( cart.getClientId() );
        List<CartItem> list = cart.getItems();
        if ( list != null ) {
            cartResponse.setItems( new ArrayList<CartItem>( list ) );
        }

        return cartResponse;
    }

    @Override
    public List<CartResponse> cartsToCartsResponse(List<Cart> carts) {
        if ( carts == null ) {
            return null;
        }

        List<CartResponse> list = new ArrayList<CartResponse>( carts.size() );
        for ( Cart cart : carts ) {
            list.add( cartToCartResponse( cart ) );
        }

        return list;
    }
}
