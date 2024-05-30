package upc.edu.NerdyNestAPI.inventario.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private List<String> images;
    private Map<String, Object> specifications;
}
