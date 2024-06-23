package upc.edu.NerdyNestAPI.inventario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Gestión de Productos", description = "Controlador para operaciones relacionadas con productos")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Crea un nuevo producto", description = "Crea un nuevo producto y devuelve el producto creado")
    @Transactional
    @PostMapping(value = "/categories/{category_id}/brands/{brand_id}/products")
    public ResponseEntity<ProductResponse> createProduct(@PathVariable String category_id, @PathVariable String brand_id, @RequestBody ProductRequest productRequest){
        var productCreated = productService.createProduct(category_id, brand_id, ProductMapper.INSTANCE.productRequestToProduct(productRequest));
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productCreated);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene un producto", description = "Obtiene un producto por su ID")
    @Transactional
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id){
        var product = productService.getProductById(id);
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(product);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los productos", description = "Obtiene todos los productos")
    @Transactional
    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        var products = productService.getAllProducts();
        var productsResponse = ProductMapper.INSTANCE.productsToProductsResponse(products);
        return new ResponseEntity<List<ProductResponse>>(productsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un producto", description = "Actualiza un producto por su ID")
    @Transactional
    @PutMapping(value = "/brands/{brand_id}/categories/{category_id}/products/{product_id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String brand_id, @PathVariable String category_id, @PathVariable String product_id, @RequestBody ProductRequest productRequest){
        var productUpdated = productService.updateProduct(brand_id, category_id, product_id, ProductMapper.INSTANCE.productRequestToProduct(productRequest));
        var productResponse = ProductMapper.INSTANCE.productToProductResponse(productUpdated);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un producto", description = "Elimina un producto por su ID")
    @Transactional
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable String id){
        var deleted = productService.deleteProduct(id);
        return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
    }
}
