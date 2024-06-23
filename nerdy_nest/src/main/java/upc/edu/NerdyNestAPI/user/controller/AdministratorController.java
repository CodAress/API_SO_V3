package upc.edu.NerdyNestAPI.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.user.dto.AdministratorRequest;
import upc.edu.NerdyNestAPI.user.dto.AdministratorResponse;
import upc.edu.NerdyNestAPI.user.dto.Mapper.AdministratorMapper;
import upc.edu.NerdyNestAPI.user.service.AdministratorService;

import java.util.List;

@Tag(name = "Gestión de Administradores", description = "Controlador para operaciones relacionadas con administradores")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Operation(summary = "Crea un nuevo administrador", description = "Crea un nuevo administrador y devuelve el administrador creado")
    @PostMapping(value = "/administrators")
    public ResponseEntity<AdministratorResponse> createAdministrator(@RequestBody AdministratorRequest administratorRequest) {
        var administrator = AdministratorMapper.INSTANCE.administratorRequestToAdministrator(administratorRequest);
        var administratorResponse = AdministratorMapper.INSTANCE.administratorToAdministratorResponse(administratorService.createAdministrator(administrator));
        return new ResponseEntity<AdministratorResponse>(administratorResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene todos los administradores", description = "Obtiene todos los administradores")
    @GetMapping(value = "/administrators")
    public ResponseEntity<List<AdministratorResponse>> getAllAdministrators() {
        var administratorsResponse = AdministratorMapper.INSTANCE.administratorsToAdministratorsResponse(administratorService.getAllAdministrators());
        return new ResponseEntity<List<AdministratorResponse>>(administratorsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un administrador", description = "Obtiene un administrador por su ID")
    @GetMapping(value = "/administrators/{id}")
    public ResponseEntity<AdministratorResponse> getAdministratorById(@PathVariable("id") String id){
        var administratorResponse = AdministratorMapper.INSTANCE.administratorToAdministratorResponse(administratorService.getAdministratorById(id));
        return new ResponseEntity<AdministratorResponse>(administratorResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un administrador", description = "Actualiza un administrador por su ID")
    @PutMapping(value = "/administrators/{id}")
    public ResponseEntity<AdministratorResponse> updateAdministrator(@PathVariable("id") String id, @RequestBody AdministratorRequest administratorRequest){
        var administrator = AdministratorMapper.INSTANCE.administratorRequestToAdministrator(administratorRequest);
        var administratorResponse = AdministratorMapper.INSTANCE.administratorToAdministratorResponse(administratorService.updateAdministrator(id, administrator));
        return new ResponseEntity<AdministratorResponse>(administratorResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un administrador", description = "Elimina un administrador por su ID")
    @DeleteMapping(value = "/administrators/{id}")
    public ResponseEntity<Boolean> deleteAdministrator(@PathVariable("id") String id){
        var valor = administratorService.deleteAdministrator(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}
