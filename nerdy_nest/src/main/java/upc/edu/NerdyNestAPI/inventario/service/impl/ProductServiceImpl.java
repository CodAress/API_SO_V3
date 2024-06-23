package upc.edu.NerdyNestAPI.inventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.NerdyNestAPI.inventario.model.Brand;
import upc.edu.NerdyNestAPI.inventario.model.Product;
import upc.edu.NerdyNestAPI.inventario.repository.CategoryRepository;
import upc.edu.NerdyNestAPI.inventario.repository.BrandRepository;
import upc.edu.NerdyNestAPI.inventario.repository.ProductRepository;
import upc.edu.NerdyNestAPI.inventario.service.ProductService;
import upc.edu.NerdyNestAPI.utils.exception.ResourceNotFoundException;
import upc.edu.NerdyNestAPI.utils.exception.ValidationException;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Product createProduct(String category_id, String brand_id, Product product) {
        existCategoryById(category_id);
        existBrandById(brand_id);
        validateProduct(product);
        //existProductByName(product);
        product.setCategoria(categoryRepository.findById(category_id).get());
        product.setMarca(brandRepository.findById(brand_id).get());
        product.setRating(0.0);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(String id) {
        existProductById(id);
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        if(allProducts.isEmpty())
        {
            allProducts = null;
            throw new ResourceNotFoundException("Unregistered products");
        }
        return allProducts;
    }

    @Override
    public Product updateProduct(String brand_id, String category_id, String product_id, Product product) {
        existProductById(product_id);
        existBrandById(brand_id);
        existCategoryById(category_id);
        validateProduct(product);
        //existProductByName(product);
        Product existingProduct = productRepository.findById(product_id).get();
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setMarca(brandRepository.findById(brand_id).get());
        existingProduct.setCategoria(categoryRepository.findById(category_id).get());
        return productRepository.save(existingProduct);
    }

    @Override
    public Boolean deleteProduct(String id) {
        existProductById(id);
        productRepository.deleteById(id);
        return true;
    }

    private void validateProduct(Product product) {
        if(product.getName() == null || product.getName().isEmpty())
            throw new ValidationException("Product name is required");
        if(product.getPrice() == null)
            throw new ValidationException("Product price is required");
        if(product.getPrice() < 0)
            throw new ValidationException("Product price must be greater than zero");
        if(product.getDescription() == null || product.getDescription().isEmpty())
            throw new ValidationException("Product description is required");
        if(product.getStock() == null)
            throw new ValidationException("Product stock is required");
        if(product.getStock() < 0)
            throw new ValidationException("Product stock must be greater than or equal to zero");
        if(product.getImages() == null || product.getImages().isEmpty())
            throw new ValidationException("Product images are required");
        if(product.getSpecifications() == null || product.getSpecifications().isEmpty())
            throw new ValidationException("Product specifications are required");
    }

    private void existProductByName(Product product) {
        if(productRepository.existsProductByNameIgnoreCase(product.getName()))
            throw new ValidationException("Product by name " + product.getName() + " already exists");
    }
    private void existProductById(String id) {
        if(!productRepository.existsById(id))
            throw new ResourceNotFoundException("Product by id " + id + " not found");
    }
    private void existBrandById(String id) {
        if(!brandRepository.existsById(id))
            throw new ResourceNotFoundException("Brand by id " + id + " not found");
    }
    private void existCategoryById(String id) {
        if(!categoryRepository.existsById(id))
            throw new ResourceNotFoundException("Category by id " + id + " not found");
    }
}
