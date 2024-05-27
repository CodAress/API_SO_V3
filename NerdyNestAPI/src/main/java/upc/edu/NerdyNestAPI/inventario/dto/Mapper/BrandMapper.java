package upc.edu.NerdyNestAPI.inventario.dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.NerdyNestAPI.inventario.dto.BrandRequest;
import upc.edu.NerdyNestAPI.inventario.dto.BrandResponse;
import upc.edu.NerdyNestAPI.inventario.model.Brand;

import java.util.List;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
    Brand brandRequestToBrand(BrandRequest brandRequest);
    List<BrandResponse> brandsToBrandsResponse(List<Brand> brands);
    BrandResponse brandToBrandResponse(Brand brand);
}
