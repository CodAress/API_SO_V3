package upc.edu.NerdyNestAPI.inventario.dto;

import lombok.Data;

@Data
public class BrandResponse {
    private String id;
    private String name;
    private String description;
    private String logo;
}
