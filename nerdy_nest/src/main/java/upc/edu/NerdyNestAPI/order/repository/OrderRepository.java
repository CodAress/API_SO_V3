package upc.edu.NerdyNestAPI.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import upc.edu.NerdyNestAPI.order.model.Order;
import upc.edu.NerdyNestAPI.user.model.Client;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    Boolean existsByCartId(String cartId);
    Boolean existsByClientId(String clientId);
    List<Order> findAllByClientId(String clientId);
}
