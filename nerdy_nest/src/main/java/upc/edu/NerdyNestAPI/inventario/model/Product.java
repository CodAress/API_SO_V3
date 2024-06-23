package upc.edu.NerdyNestAPI.inventario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

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
    @Field(name = "marca")
    private Brand marca;
    @Field(name = "categoria")
    private Category categoria;
    @Field(name = "images")
    private List<String> images;
    @Field(name = "specifications")
    private Map<String, Object> specifications;
    @Field(name = "rating")
    private Double rating;
}
