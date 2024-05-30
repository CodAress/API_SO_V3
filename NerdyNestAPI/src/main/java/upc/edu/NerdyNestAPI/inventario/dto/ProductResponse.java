package upc.edu.NerdyNestAPI.inventario.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private String category_id;
    private String brand_id;
    private Double price;
    private Integer stock;
    private List<String> images;
    private Map<String, Object> specifications;
}
