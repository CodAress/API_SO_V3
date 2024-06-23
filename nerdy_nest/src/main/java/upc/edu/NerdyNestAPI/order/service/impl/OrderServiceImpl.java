package upc.edu.NerdyNestAPI.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.NerdyNestAPI.inventario.model.Product;
import upc.edu.NerdyNestAPI.inventario.repository.ProductRepository;
import upc.edu.NerdyNestAPI.order.model.Order;
import upc.edu.NerdyNestAPI.order.model.OrderItem;
import upc.edu.NerdyNestAPI.order.repository.OrderRepository;
import upc.edu.NerdyNestAPI.order.service.OrderService;
import upc.edu.NerdyNestAPI.shopping_cart.model.Cart;
import upc.edu.NerdyNestAPI.shopping_cart.model.CartItem;
import upc.edu.NerdyNestAPI.shopping_cart.repository.CartRepository;
import upc.edu.NerdyNestAPI.user.model.Client;
import upc.edu.NerdyNestAPI.user.repository.ClientRepository;
import upc.edu.NerdyNestAPI.utils.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Order createOrder(String clientId) {
        notExistClientById(clientId);
        validateCartItems(clientId);
        notExistCartByClientId(clientId);
        existOrderByCartId(clientId);
        Cart cart = cartRepository.findByClientId(clientId);
        Client client = clientRepository.findById(clientId).get();
        List<CartItem> items = cart.getItems();

        //Verificar si la cantidad de productos en el carrito es menor al stock del producto
        validateQuantityMustBeLessThanStock(items);

        List<OrderItem> orderItems = new ArrayList<>();
        items.forEach(item -> {
            orderItems.add(OrderItem.builder()
                    .productId(productRepository.findById(item.getProductId()).get())
                    .quantity(item.getQuantity())
                    .build());
        });
        Order order = Order.builder()
                .client(client)
                .orderDate(LocalDateTime.now())
                .orderUpdate(null)
                .cartId(cart.getId())
                .items(orderItems)
                .build();

        //actualizar la cantidad de productos en el inventario
        items.forEach(item -> {
            Product productToUpdate = productRepository.findById(item.getProductId()).get();
            productToUpdate.setStock(productToUpdate.getStock() - item.getQuantity());
            productRepository.save(productToUpdate);
        });

        //limpiar el carrito
        cart.setItems(null);
        cartRepository.save(cart);
        
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByClientId(String clientId) {
        notExistClientById(clientId);
        notExistOrderByClientId(clientId);
        return orderRepository.findAllByClientId(clientId);
    }

    @Override
    public Order addItemToOrder(String orderId, String productId, Integer quantity) {
        notExistOrderById(orderId);
        notExistProductById(productId);
        validateQuantity(quantity);
        Order order = orderRepository.findById(orderId).get();
        OrderItem orderItem = OrderItem.builder()
                .productId(productRepository.findById(productId).get())
                .quantity(quantity)
                .build();
        order.getItems().add(orderItem);
        order.setOrderUpdate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order removeItemFromOrder(String orderId, String productId) {
        notExistOrderById(orderId);
        notExistProductById(productId);
        Order order = orderRepository.findById(orderId).get();
        order.getItems().removeIf(item -> item.getProductId().getId().equals(productId));
        order.setOrderUpdate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order updateItemQuantity(String orderId, String productId, Integer quantity) {
        notExistOrderById(orderId);
        notExistProductById(productId);
        validateQuantity(quantity);
        Order order = orderRepository.findById(orderId).get();
        order.getItems().forEach(item -> {
            if(item.getProductId().getId().equals(productId)) {
                item.setQuantity(quantity);
            }
        });
        order.setOrderUpdate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order clearOrder(String orderId) {
        notExistOrderById(orderId);
        Order order = orderRepository.findById(orderId).get();
        order.setItems(null);
        order.setOrderUpdate(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        notExistOrderById(orderId);
        //actualizar la cantidad de productos en el inventario
        Order order = orderRepository.findById(orderId).get();
        order.getItems().forEach(item -> {
            Product productToUpdate = productRepository.findById(item.getProductId().getId()).get();
            productToUpdate.setStock(productToUpdate.getStock() + item.getQuantity());
            productRepository.save(productToUpdate);
        });
        orderRepository.deleteById(orderId);
    }
    private void notExistClientById(String clientId) {
        if(!clientRepository.existsById(clientId)) {
            throw new ResourceNotFoundException(String.format("Client with id %s not found", clientId));
        }
    }
    private void notExistCartByClientId(String clientId) {
        if(!cartRepository.existsByClientId(clientId)) {
            throw new ResourceNotFoundException(String.format("Cart for client with id %s not found", clientId));
        }
    }
    private void validateCartItems(String clientId) {
        if(cartRepository.findByClientId(clientId).getItems() == null || cartRepository.findByClientId(clientId).getItems().isEmpty()){
            throw new ResourceNotFoundException("Cart items required to create order");
        }
    }
    private void existOrderByCartId(String clientId) {
        if(orderRepository.existsByCartId(clientId)) {
            throw new ResourceNotFoundException("Order already exists for this cart");
        }
    }
    private void notExistOrderByClientId(String clientId) {
        if(!orderRepository.existsByClientId(clientId)) {
            throw new ResourceNotFoundException(String.format("Order for client with id %s not found", clientId));
        }
    }
    private void notExistOrderById(String orderId) {
        if(!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException(String.format("Order with id %s not found", orderId));
        }
    }
    private void notExistProductById(String productId) {
        if(!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(String.format("Product with id %s not found", productId));
        }
    }
    private void validateQuantity(Integer quantity) {
        if(quantity <= 0) {
            throw new ResourceNotFoundException("Quantity must be greater than 0");
        }
    }
    private void validateQuantityMustBeLessThanStock(List<CartItem> items) {
        items.forEach(item -> {
            if(item.getQuantity() > productRepository.findById(item.getProductId()).get().getStock()) {
                throw new ResourceNotFoundException("Quantity must be less than stock");
            }
        });
    }
}
