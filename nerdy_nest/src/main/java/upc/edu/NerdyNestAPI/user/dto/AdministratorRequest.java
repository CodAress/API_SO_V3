package upc.edu.NerdyNestAPI.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdministratorRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Integer phone;
    private Integer dni;
    private LocalDate birthDate;
}
