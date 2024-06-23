package upc.edu.NerdyNestAPI.shopping_cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import upc.edu.NerdyNestAPI.shopping_cart.model.Cart;

public interface CartRepository extends MongoRepository<Cart, String> {
    Cart findByClientId(String clientId);
    Boolean existsByClientId(String clientId);
    void deleteByClientId(String clientId);
}
