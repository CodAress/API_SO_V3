package upc.edu.NerdyNestAPI.inventario.dto.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import upc.edu.NerdyNestAPI.inventario.dto.BrandRequest;
import upc.edu.NerdyNestAPI.inventario.dto.BrandResponse;
import upc.edu.NerdyNestAPI.inventario.model.Brand;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-23T18:14:46-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class BrandMapperImpl implements BrandMapper {

    @Override
    public Brand brandRequestToBrand(BrandRequest brandRequest) {
        if ( brandRequest == null ) {
            return null;
        }

        Brand.BrandBuilder brand = Brand.builder();

        brand.name( brandRequest.getName() );
        brand.description( brandRequest.getDescription() );
        brand.logo( brandRequest.getLogo() );

        return brand.build();
    }

    @Override
    public List<BrandResponse> brandsToBrandsResponse(List<Brand> brands) {
        if ( brands == null ) {
            return null;
        }

        List<BrandResponse> list = new ArrayList<BrandResponse>( brands.size() );
        for ( Brand brand : brands ) {
            list.add( brandToBrandResponse( brand ) );
        }

        return list;
    }

    @Override
    public BrandResponse brandToBrandResponse(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandResponse brandResponse = new BrandResponse();

        brandResponse.setId( brand.getId() );
        brandResponse.setName( brand.getName() );
        brandResponse.setDescription( brand.getDescription() );
        brandResponse.setLogo( brand.getLogo() );

        return brandResponse;
    }
}
