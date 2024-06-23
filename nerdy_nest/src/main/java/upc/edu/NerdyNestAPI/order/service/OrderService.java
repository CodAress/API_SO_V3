package upc.edu.NerdyNestAPI.order.service;

import upc.edu.NerdyNestAPI.order.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(String clientId);
    List<Order> getOrdersByClientId(String clientId);
    Order addItemToOrder(String orderId, String productId, Integer quantity);
    Order removeItemFromOrder(String orderId, String productId);
    Order updateItemQuantity(String orderId, String productId, Integer quantity);
    Order clearOrder(String orderId);
    void deleteOrder(String orderId);
}
