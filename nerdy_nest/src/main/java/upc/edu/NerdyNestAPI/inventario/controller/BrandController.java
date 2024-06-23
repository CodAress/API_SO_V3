package upc.edu.NerdyNestAPI.inventario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Gestión de Marcas", description = "Controlador para operaciones relacionadas con marcas")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class BrandController {
    @Autowired
    private BrandService brandService;
    @Operation(summary = "Crea una nueva marca", description = "Crea una nueva marca y devuelve la marca creada")
    @Transactional
    @PostMapping(value = "/brands")
    public ResponseEntity<BrandResponse> createBrand(@RequestBody BrandRequest brandRequest){
        var brandCreated = brandService.createBrand(BrandMapper.INSTANCE.brandRequestToBrand(brandRequest));
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandCreated);
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.CREATED);
    }
    @Operation(summary = "Obtiene una marca", description = "Obtiene una marca por su ID")
    @Transactional
    @GetMapping(value = "/brands/{id}")
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable String id){
        var brand = brandService.getBrand(id);
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brand);
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.OK);
    }
    @Operation(summary = "Obtiene todas las marcas", description = "Obtiene todas las marcas")
    @Transactional
    @GetMapping(value = "/brands")
    public ResponseEntity<List<BrandResponse>> getAllBrands(){
        var brands = brandService.getAllBrands();
        var brandsResponse = BrandMapper.INSTANCE.brandsToBrandsResponse(brands);
        return new ResponseEntity<List<BrandResponse>>(brandsResponse, HttpStatus.OK);
    }
    @Operation(summary = "Actualiza una marca", description = "Actualiza una marca por su ID")
    @Transactional
    @PutMapping(value = "/brands/{id}")
    public ResponseEntity<BrandResponse> updateBrand(@PathVariable String id, @RequestBody BrandRequest brandRequest){
        var brandUpdated = brandService.updateBrand(id, BrandMapper.INSTANCE.brandRequestToBrand(brandRequest));
        var brandResponse = BrandMapper.INSTANCE.brandToBrandResponse(brandUpdated);
        return new ResponseEntity<BrandResponse>(brandResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una marca", description = "Elimina una marca por su ID")
    @Transactional
    @DeleteMapping(value = "/brands/{id}")
    public ResponseEntity<Boolean> deleteBrand(@PathVariable String id){
        var deleted = brandService.deleteBrand(id);
        return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
    }
}
