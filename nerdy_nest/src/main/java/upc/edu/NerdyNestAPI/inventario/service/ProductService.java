package upc.edu.NerdyNestAPI.inventario.service;

import upc.edu.NerdyNestAPI.inventario.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(String category_id, String brand_id, Product product);
    Product getProductById(String id);
    List<Product> getAllProducts();
    Product updateProduct(String brand_id, String category_id, String product_id, Product product);
    Boolean deleteProduct(String id);
}
