package upc.edu.NerdyNestAPI.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.user.dto.ClientRequest;
import upc.edu.NerdyNestAPI.user.dto.ClientResponse;
import upc.edu.NerdyNestAPI.user.dto.Mapper.ClientMapper;
import upc.edu.NerdyNestAPI.user.service.ClientService;

import java.util.List;

@Tag(name = "Gestión de Clientes", description = "Controlador para operaciones relacionadas con clientes")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Operation(summary = "Crea un nuevo cliente", description = "Crea un nuevo cliente y devuelve el cliente creado")
    @PostMapping(value = "/clients")
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest){
        var client = ClientMapper.INSTANCE.clientRequestToClient(clientRequest);
        var clientResponse = ClientMapper.INSTANCE.clientToClientResponse(clientService.createClient(client));
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene todos los clientes", description = "Obtiene todos los clientes")
    @GetMapping(value = "/clients")
    public ResponseEntity<List<ClientResponse>> getAllClients(){
        var clientsResponse = ClientMapper.INSTANCE.clientsToClientsResponse(clientService.getAllClients());
        return new ResponseEntity<List<ClientResponse>>(clientsResponse, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un cliente", description = "Obtiene un cliente por su ID")
    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable("id") String id){
        var clientResponse = ClientMapper.INSTANCE.clientToClientResponse(clientService.getClientById(id));
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un cliente", description = "Actualiza un cliente por su ID")
    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable("id") String id, @RequestBody ClientRequest clientRequest){
        var client = ClientMapper.INSTANCE.clientRequestToClient(clientRequest);
        var clientResponse = ClientMapper.INSTANCE.clientToClientResponse(clientService.updateClient(id, client));
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un cliente", description = "Elimina un cliente por su ID")
    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<Boolean> deleteClient(@PathVariable("id") String id){
        var valor = clientService.deleteClient(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }

}
