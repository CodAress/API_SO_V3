package upc.edu.NerdyNestAPI.inventario.service;

import upc.edu.NerdyNestAPI.inventario.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(String category_id, String brand_id, Product product);
    Product getProductById(String id);
    List<Product> getAllProducts();
    Product updateProduct(String id, Product product);
    Boolean deleteProduct(String id);
}
