package upc.edu.NerdyNestAPI.login.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.login.dto.LoginRequest;
import upc.edu.NerdyNestAPI.login.service.LoginService;
import upc.edu.NerdyNestAPI.user.dto.ClientResponse;
import upc.edu.NerdyNestAPI.user.dto.Mapper.ClientMapper;
import upc.edu.NerdyNestAPI.user.model.Role;
import upc.edu.NerdyNestAPI.user.service.ClientService;

@Tag(name = "Inicio de Sesión", description = "Controlador para operaciones relacionadas con el inicio de sesión")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class LoginController {
    @Autowired
    private LoginService loggingService;
    @Autowired
    private ClientService clientService;

    @Operation(summary = "Inicia sesión", description = "Inicia sesión, devuelve el rol del usuario")
    @PostMapping("/loggin")
    public ResponseEntity<Role> login(@RequestBody LoginRequest loginRequest) {
        Role role = loggingService.login(loginRequest);
        return switch (role) {
            case UNAUTHORIZED -> new ResponseEntity<Role>(role, HttpStatus.UNAUTHORIZED);
            case ADMIN, CLIENT -> new ResponseEntity<Role>(role, HttpStatus.ACCEPTED);
        };
    }


    @Operation(summary = "Obtiene un cliente por su email y contraseña", description = "Obtiene un cliente por su email y contraseña")
    @GetMapping(value = "/clients/login/{email}/{password}")
    public ResponseEntity<ClientResponse> getClientByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password){
        var clientResponse = ClientMapper.INSTANCE.clientToClientResponse(clientService.getClientByEmailAndPassword(email, password));
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.OK);
    }
}
