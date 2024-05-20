package upc.edu.NerdyNestAPI.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "price")
    private Double price;
    @Field(name = "stock")
    private Integer stock;
    @Field(name = "description")
    private String description;

}
