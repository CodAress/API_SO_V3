package upc.edu.NerdyNestAPI.inventario.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upc.edu.NerdyNestAPI.inventario.model.Category;
import upc.edu.NerdyNestAPI.inventario.repository.CategoryRepository;
import upc.edu.NerdyNestAPI.inventario.service.CategoryService;
import upc.edu.NerdyNestAPI.utils.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        validateCategory(category);
        existCategoryByName(category);
        category.setParent(null);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        if(allCategories.isEmpty()){
            allCategories = null;
            throw new ResourceNotFoundException("Unregistered categories");
        }
        return allCategories;
    }

    @Override
    public Category getCategory(String id) {
        existCategoryById(id);
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category updateCategory(String category_id, Category category) {
        existCategoryById(category_id);
        validateCategory(category);
        existCategoryByName(category);
        Category existingCategory = categoryRepository.findById(category_id).get();
        existingCategory.setName(category.getName());
        existingCategory.setParent(category.getParent());
        return categoryRepository.save(existingCategory);
    }
    @Override
    public Category updateParentCategory(String category_id, String parent_id) {
        existCategoryById(category_id);
        existCategoryById(parent_id);
        Category category = categoryRepository.findById(category_id).get();
        category.setParent(categoryRepository.findById(parent_id).get());
        return categoryRepository.save(category);

    }

    @Override
    public boolean deleteCategory(String id) {
        existCategoryById(id);
        categoryRepository.deleteById(id);
        return true;
    }
    private void existCategoryById(String id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category by id " + id + " not found");
        }
    }

    private void validateCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name is required");
        }
    }
    private void existCategoryByName(Category category) {
        if (categoryRepository.existsCategoryByNameIgnoreCase(category.getName())) {
            throw new IllegalArgumentException("Category already exists");
        }
    }
}
