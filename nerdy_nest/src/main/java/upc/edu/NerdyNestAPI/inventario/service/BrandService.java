package upc.edu.NerdyNestAPI.inventario.service;

import upc.edu.NerdyNestAPI.inventario.model.Brand;

import java.util.List;

public interface BrandService {
    Brand createBrand(Brand brand);
    Brand updateBrand(String brand_id, Brand brand);
    Brand getBrand(String id);
    List<Brand> getAllBrands();
    Boolean deleteBrand(String id);
}
