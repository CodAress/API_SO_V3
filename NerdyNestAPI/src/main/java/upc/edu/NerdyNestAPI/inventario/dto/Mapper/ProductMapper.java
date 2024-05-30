package upc.edu.NerdyNestAPI.inventario.dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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
    @Mapping(source = "marca.id", target = "brand_id")
    @Mapping(source = "categoria.id", target = "category_id")
    ProductResponse productToProductResponse(Product product);
}
