package upc.edu.NerdyNestAPI.inventario.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.inventario.dto.CategoryRequest;
import upc.edu.NerdyNestAPI.inventario.dto.CategoryResponse;
import upc.edu.NerdyNestAPI.inventario.dto.Mapper.CategoryMapper;
import upc.edu.NerdyNestAPI.inventario.service.CategoryService;

import java.util.List;

@Tag(name = "Gestión de Categorías", description = "Controlador para operaciones relacionadas con categorías")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "Crea una nueva categoría", description = "Crea una nueva categoría y devuelve la categoría creada")
    @Transactional
    @PostMapping(value = "/categories")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest){
        var categoryCreated = categoryService.createCategory(CategoryMapper.INSTANCE.categoryRequestToCategory(categoryRequest));
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryCreated);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene una categoría", description = "Obtiene una categoría por su ID")
    @Transactional
    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable String id){
        var category = categoryService.getCategory(id);
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(category);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todas las categorías", description = "Obtiene todas las categorías")
    @Transactional
    @GetMapping(value = "/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        var categories = categoryService.getAllCategories();
        var categoriesResponse = CategoryMapper.INSTANCE.categoriesToCategoriesResponse(categories);
        return new ResponseEntity<List<CategoryResponse>>(categoriesResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza una categoría", description = "Actualiza una categoría por su ID")
    @Transactional
    @PutMapping(value = "/categories/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String id, @RequestBody CategoryRequest categoryRequest){
        var categoryUpdated = categoryService.updateCategory(id, CategoryMapper.INSTANCE.categoryRequestToCategory(categoryRequest));
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryUpdated);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza la categoría padre de una categoría", description = "Actualiza la categoría padre de una categoría por su ID")
    @Transactional
    @PutMapping(value = "/categories/{category_id}/parent/{parent_id}")
    public ResponseEntity<CategoryResponse> updateParentCategory(@PathVariable String category_id, @PathVariable String parent_id){
        var categoryUpdated = categoryService.updateParentCategory(category_id, parent_id);
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryUpdated);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una categoría", description = "Elimina una categoría por su ID")
    @Transactional
    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable String id){
        var deleted = categoryService.deleteCategory(id);
        return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
    }

}
