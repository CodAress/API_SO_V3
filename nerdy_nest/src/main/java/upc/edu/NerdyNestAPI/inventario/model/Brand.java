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
@Document(collection = "brands")
public class Brand {
    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "description")
    private String description;
    @Field(name = "logo")
    private String logo;
}
