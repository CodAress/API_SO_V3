package upc.edu.NerdyNestAPI.inventario.dto.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import upc.edu.NerdyNestAPI.inventario.dto.CategoryRequest;
import upc.edu.NerdyNestAPI.inventario.dto.CategoryResponse;
import upc.edu.NerdyNestAPI.inventario.model.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category categoryRequestToCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> categoriesToCategoriesResponse(List<Category> categories);
    CategoryResponse categoryToCategoryResponse(Category category);
}
