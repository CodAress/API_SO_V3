package upc.edu.NerdyNestAPI.inventario.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import upc.edu.NerdyNestAPI.inventario.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{
    boolean existsProductByNameIgnoreCase(String name);
}
