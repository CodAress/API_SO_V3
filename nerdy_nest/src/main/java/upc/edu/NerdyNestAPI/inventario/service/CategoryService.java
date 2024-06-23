package upc.edu.NerdyNestAPI.inventario.service;

import upc.edu.NerdyNestAPI.inventario.model.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    List<Category> getAllCategories();
    Category getCategory(String id);
    Category updateCategory(String category_id, Category category);
    Category updateParentCategory(String category_id, String parent_id);
    boolean deleteCategory(String id);
}
