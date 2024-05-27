package upc.edu.NerdyNestAPI.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.inventario.dto.Mapper.ProductMapper;
import upc.edu.NerdyNestAPI.inventario.dto.ProductRequest;
import upc.edu.NerdyNestAPI.inventario.dto.ProductResponse;
import upc.edu.NerdyNestAPI.inventario.model.Product;
import upc.edu.NerdyNestAPI.inventario.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Transactional
    @PostMapping(value = "/category/{category_id}/brand/{brand_id}/products")
    public ResponseEntity<ProductResponse> createProduct(@PathVariable String category_id, @PathVariable String brand_id, @RequestBody ProductRequest productRequest){
        var productCreated = productService.createProduct(category_id, brand_id, ProductMapper.INSTANCE.productRequestToProduct(productRequest));
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productCreated);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id){
        var product = productService.getProductById(id);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        var products = productService.getAllProducts();
        var productsResponse = ProductMapper.INSTANCE.productsToProductsResponse(products);
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductRequest productRequest){
        var productUpdated = productService.updateProduct(id, ProductMapper.INSTANCE.productRequestToProduct(productRequest));
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productUpdated);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable String id){
        var deleted = productService.deleteProduct(id);
        return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
    }
}
