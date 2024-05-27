package upc.edu.NerdyNestAPI.inventario.dto;

import lombok.Data;

import java.util.List;

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
}
