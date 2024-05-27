package upc.edu.NerdyNestAPI.inventario.controller;

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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Transactional
    @PostMapping(value = "/categories")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest){
        var categoryCreated = categoryService.createCategory(CategoryMapper.INSTANCE.categoryRequestToCategory(categoryRequest));
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryCreated);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.CREATED);
    }

    @Transactional
    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable String id){
        var category = categoryService.getCategory(id);
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(category);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/categories")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        var categories = categoryService.getAllCategories();
        var categoriesResponse = CategoryMapper.INSTANCE.categoriesToCategoriesResponse(categories);
        return new ResponseEntity<List<CategoryResponse>>(categoriesResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/categories/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String id, @RequestBody CategoryRequest categoryRequest){
        var categoryUpdated = categoryService.updateCategory(id, CategoryMapper.INSTANCE.categoryRequestToCategory(categoryRequest));
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryUpdated);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }

    @Transactional
    @PutMapping(value = "/categories/{category_id}/parent/{parent_id}")
    public ResponseEntity<CategoryResponse> updateParentCategory(@PathVariable String category_id, @PathVariable String parent_id){
        var categoryUpdated = categoryService.updateParentCategory(category_id, parent_id);
        var categoryResponse = CategoryMapper.INSTANCE.categoryToCategoryResponse(categoryUpdated);
        return new ResponseEntity<CategoryResponse>(categoryResponse, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable String id){
        var deleted = categoryService.deleteCategory(id);
        return new ResponseEntity<Boolean>(deleted, HttpStatus.OK);
    }

}
