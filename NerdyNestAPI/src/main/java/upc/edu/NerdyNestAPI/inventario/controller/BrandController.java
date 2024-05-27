package upc.edu.NerdyNestAPI.inventario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.inventario.dto.BrandRequest;
import upc.edu.NerdyNestAPI.inventario.dto.BrandResponse;
import upc.edu.NerdyNestAPI.inventario.dto.Mapper.BrandMapper;
import upc.edu.NerdyNestAPI.inventario.service.BrandService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Transactional
    @PostMapping(value = "/brands")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest){
        var brandCreated = brandService.createBrand(BrandMapper.INSTANCE.brandRequestToBrand(brandRequest));
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandCreated);
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping(value = "/brands/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable String id){
        var brand = brandService.getBrand(id);
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brand);
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/brands")
    public ResponseEntity<List<BrandResponse>> getAllBrands(){
        var brands = brandService.getAllBrands();
        var brandsResponse = BrandMapper.INSTANCE.brandsToBrandsResponse(brands);
        return new ResponseEntity<List<BrandResponse>>(brandsResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/brands/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable String id, @RequestBody BrandRequest brandRequest){
        var brandUpdated = brandService.updateBrand(id, BrandMapper.INSTANCE.brandRequestToBrand(brandRequest));
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandUpdated);
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/brands/{id}")
    public ResponseEntity<Boolean> deleteBrand(@PathVariable String id){
        var deleted = brandService.deleteBrand(id);
        return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
    }
}
