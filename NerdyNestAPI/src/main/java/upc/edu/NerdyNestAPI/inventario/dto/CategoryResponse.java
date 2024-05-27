package upc.edu.NerdyNestAPI.inventario.dto;

import lombok.Data;

@Data
public class CategoryResponse {
    private String id;
    private String name;
    private CategoryResponse parent;
}
