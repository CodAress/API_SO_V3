package upc.edu.NerdyNestAPI.inventario.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private List<String> images;
}
