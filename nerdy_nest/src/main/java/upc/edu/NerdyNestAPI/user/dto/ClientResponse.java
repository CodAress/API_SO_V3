package upc.edu.NerdyNestAPI.user.dto;

import lombok.Data;

@Data
public class ClientResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dni;
    private String birthDate;
    private String postalCode;
    private String address;
    private String role;
}
