package upc.edu.NerdyNestAPI.inventario.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import upc.edu.NerdyNestAPI.inventario.model.Brand;

public interface BrandRepository extends MongoRepository<Brand, String>{
    boolean existsBrandByNameIgnoreCase(String name);
}
