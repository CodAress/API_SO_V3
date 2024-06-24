package upc.edu.NerdyNestAPI.inventario.dto.Mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import upc.edu.NerdyNestAPI.inventario.dto.CategoryRequest;
import upc.edu.NerdyNestAPI.inventario.dto.CategoryResponse;
import upc.edu.NerdyNestAPI.inventario.model.Category;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-24T17:45:10-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        if ( categoryRequest == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( categoryRequest.getName() );

        return category.build();
    }

    @Override
    public List<CategoryResponse> categoriesToCategoriesResponse(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResponse> list = new ArrayList<CategoryResponse>( categories.size() );
        for ( Category category : categories ) {
            list.add( categoryToCategoryResponse( category ) );
        }

        return list;
    }

    @Override
    public CategoryResponse categoryToCategoryResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setName( category.getName() );
        categoryResponse.setParent( categoryToCategoryResponse( category.getParent() ) );

        return categoryResponse;
    }
}
