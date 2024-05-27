package upc.edu.NerdyNestAPI.inventario.dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.NerdyNestAPI.inventario.dto.ProductRequest;
import upc.edu.NerdyNestAPI.inventario.dto.ProductResponse;
import upc.edu.NerdyNestAPI.inventario.model.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product productRequestToProduct(ProductRequest productRequest);
    List<ProductResponse> productsToProductsResponse(List<Product> products);
    ProductResponse productToProductResponse(Product product);
}
