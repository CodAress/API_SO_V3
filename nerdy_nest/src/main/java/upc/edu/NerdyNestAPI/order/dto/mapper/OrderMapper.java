package upc.edu.NerdyNestAPI.order.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.NerdyNestAPI.order.dto.OrderResponse;
import upc.edu.NerdyNestAPI.order.model.Order;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponse orderToOrderResponse(Order order);
    List<OrderResponse> ordersToOrderResponses(List<Order> orders);
}
