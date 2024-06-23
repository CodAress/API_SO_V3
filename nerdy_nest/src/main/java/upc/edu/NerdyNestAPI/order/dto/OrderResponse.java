package upc.edu.NerdyNestAPI.order.dto;

import lombok.Data;
import upc.edu.NerdyNestAPI.order.model.OrderItem;
import upc.edu.NerdyNestAPI.user.dto.ClientResponse;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private String id;
    private ClientResponse client;
    private LocalDateTime orderDate;
    private LocalDateTime orderUpdate;
    private String cartId;
    private List<OrderItem> items;
}
