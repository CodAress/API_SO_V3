package upc.edu.NerdyNestAPI.inventario.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import upc.edu.NerdyNestAPI.inventario.model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
    boolean existsCategoryByNameIgnoreCase(String name);
}
