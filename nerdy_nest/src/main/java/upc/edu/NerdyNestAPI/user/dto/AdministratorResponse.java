package upc.edu.NerdyNestAPI.user.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AdministratorResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private String dni;
    private LocalDate birthDate;
    private String role;
}
