package upc.edu.NerdyNestAPI.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.login.dto.LoginRequest;
import upc.edu.NerdyNestAPI.login.service.LoginService;
import upc.edu.NerdyNestAPI.user.model.Role;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class LoginController {
    @Autowired
    private LoginService loggingService;

    @PostMapping("/loggin")
    public ResponseEntity<Role> login(@RequestBody LoginRequest loginRequest) {
        Role role = loggingService.login(loginRequest);
        return switch (role) {
            case UNAUTHORIZED -> new ResponseEntity<Role>(role, HttpStatus.UNAUTHORIZED);
            case ADMIN, CLIENT -> new ResponseEntity<Role>(role, HttpStatus.ACCEPTED);
        };
    }
}
