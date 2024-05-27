package upc.edu.NerdyNestAPI.inventario.dto;

import lombok.Data;

@Data
public class BrandRequest {
    private String name;
    private String description;
    private String logo;
}
