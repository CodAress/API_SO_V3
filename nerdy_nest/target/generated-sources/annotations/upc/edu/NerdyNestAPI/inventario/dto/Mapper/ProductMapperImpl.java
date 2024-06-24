package upc.edu.NerdyNestAPI.inventario.dto.Mapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.Generated;
import upc.edu.NerdyNestAPI.inventario.dto.ProductRequest;
import upc.edu.NerdyNestAPI.inventario.dto.ProductResponse;
import upc.edu.NerdyNestAPI.inventario.model.Brand;
import upc.edu.NerdyNestAPI.inventario.model.Category;
import upc.edu.NerdyNestAPI.inventario.model.Product;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-23T18:14:46-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productRequestToProduct(ProductRequest productRequest) {
        if ( productRequest == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( productRequest.getName() );
        product.price( productRequest.getPrice() );
        product.stock( productRequest.getStock() );
        product.description( productRequest.getDescription() );
        List<String> list = productRequest.getImages();
        if ( list != null ) {
            product.images( new ArrayList<String>( list ) );
        }
        Map<String, Object> map = productRequest.getSpecifications();
        if ( map != null ) {
            product.specifications( new LinkedHashMap<String, Object>( map ) );
        }

        return product.build();
    }

    @Override
    public List<ProductResponse> productsToProductsResponse(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponse> list = new ArrayList<ProductResponse>( products.size() );
        for ( Product product : products ) {
            list.add( productToProductResponse( product ) );
        }

        return list;
    }

    @Override
    public ProductResponse productToProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setBrand_id( productMarcaId( product ) );
        productResponse.setCategory_id( productCategoriaId( product ) );
        productResponse.setId( product.getId() );
        productResponse.setName( product.getName() );
        productResponse.setDescription( product.getDescription() );
        productResponse.setPrice( product.getPrice() );
        productResponse.setStock( product.getStock() );
        productResponse.setRating( product.getRating() );
        List<String> list = product.getImages();
        if ( list != null ) {
            productResponse.setImages( new ArrayList<String>( list ) );
        }
        Map<String, Object> map = product.getSpecifications();
        if ( map != null ) {
            productResponse.setSpecifications( new LinkedHashMap<String, Object>( map ) );
        }

        return productResponse;
    }

    private String productMarcaId(Product product) {
        if ( product == null ) {
            return null;
        }
        Brand marca = product.getMarca();
        if ( marca == null ) {
            return null;
        }
        String id = marca.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productCategoriaId(Product product) {
        if ( product == null ) {
            return null;
        }
        Category categoria = product.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        String id = categoria.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
