package upc.edu.NerdyNestAPI.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "administrators")
public class Administrator {
    @Id
    private String id;
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    @Field(name = "username")
    private String username;
    @Field(name = "email")
    private String email;
    @Field(name = "password")
    private String password;
    @Field(name = "role")
    private Role role;
    @Field(name = "phone")
    private String phone;
    @Field(name = "dni")
    private String dni;
    @Field(name = "birth_date")
    private LocalDate birthDate;
    @Field(name = "date_created")
    private LocalDateTime dateCreated;
    @Field(name = "date_updated")
    private LocalDateTime dateUpdated;
}
