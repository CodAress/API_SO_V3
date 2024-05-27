package upc.edu.NerdyNestAPI.inventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.NerdyNestAPI.inventario.model.Brand;
import upc.edu.NerdyNestAPI.inventario.repository.BrandRepository;
import upc.edu.NerdyNestAPI.inventario.service.BrandService;
import upc.edu.NerdyNestAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand createBrand(Brand brand) {
        validateBrand(brand);
        existBrandByName(brand);
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(String brand_id, Brand brand) {
        validateBrand(brand);
        existBrandByName(brand);
        existBrandById(brand_id);
        Brand existingBrand = brandRepository.findById(brand_id).get();
        existingBrand.setName(brand.getName());
        existingBrand.setDescription(brand.getDescription());
        existingBrand.setLogo(brand.getLogo());
        return brandRepository.save(existingBrand);
    }

    @Override
    public Brand getBrand(String id) {
        existBrandById(id);
        return brandRepository.findById(id).get();
    }

    @Override
    public List<Brand> getAllBrands() {
        List<Brand> allBrands = brandRepository.findAll();
        if(allBrands.isEmpty()){
            allBrands = null;
            throw new ResourceNotFoundException("Unregistered brands");
        }
        return allBrands;
    }

    @Override
    public Boolean deleteBrand(String id) {
        return null;
    }

    private void validateBrand(Brand brand) {
        if (brand.getName() == null || brand.getName().isEmpty()) {
            throw new IllegalArgumentException("Brand name is required");
        }
       if(brand.getDescription() == null || brand.getDescription().isEmpty()){
           throw new IllegalArgumentException("Brand description is required");
       }
       if(brand.getLogo() == null || brand.getLogo().isEmpty()){
           throw new IllegalArgumentException("Brand logo is required");
       }
    }
    private void existBrandByName(Brand brand) {
        if(brandRepository.existsBrandByNameIgnoreCase(brand.getName())){
            throw new IllegalArgumentException("Brand already exists");
        }
    }
    private void existBrandById(String id) {
        if(!brandRepository.existsById(id)){
            throw new IllegalArgumentException("Brand by id "+ id +" not found");
        }
    }
}
