package upc.edu.NerdyNestAPI.order.dto.mapper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import upc.edu.NerdyNestAPI.order.dto.OrderResponse;
import upc.edu.NerdyNestAPI.order.model.Order;
import upc.edu.NerdyNestAPI.order.model.OrderItem;
import upc.edu.NerdyNestAPI.user.dto.ClientResponse;
import upc.edu.NerdyNestAPI.user.model.Client;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-23T18:14:46-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponse orderToOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setId( order.getId() );
        orderResponse.setClient( clientToClientResponse( order.getClient() ) );
        orderResponse.setOrderDate( order.getOrderDate() );
        orderResponse.setOrderUpdate( order.getOrderUpdate() );
        orderResponse.setCartId( order.getCartId() );
        List<OrderItem> list = order.getItems();
        if ( list != null ) {
            orderResponse.setItems( new ArrayList<OrderItem>( list ) );
        }

        return orderResponse;
    }

    @Override
    public List<OrderResponse> ordersToOrderResponses(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResponse> list = new ArrayList<OrderResponse>( orders.size() );
        for ( Order order : orders ) {
            list.add( orderToOrderResponse( order ) );
        }

        return list;
    }

    protected ClientResponse clientToClientResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientResponse clientResponse = new ClientResponse();

        clientResponse.setId( client.getId() );
        clientResponse.setFirstName( client.getFirstName() );
        clientResponse.setLastName( client.getLastName() );
        clientResponse.setEmail( client.getEmail() );
        clientResponse.setPhone( client.getPhone() );
        clientResponse.setDni( client.getDni() );
        if ( client.getBirthDate() != null ) {
            clientResponse.setBirthDate( DateTimeFormatter.ISO_LOCAL_DATE.format( client.getBirthDate() ) );
        }
        clientResponse.setPostalCode( client.getPostalCode() );
        clientResponse.setAddress( client.getAddress() );
        if ( client.getRole() != null ) {
            clientResponse.setRole( client.getRole().name() );
        }

        return clientResponse;
    }
}
