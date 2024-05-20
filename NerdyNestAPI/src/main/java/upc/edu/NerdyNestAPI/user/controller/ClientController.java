package upc.edu.NerdyNestAPI.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.user.dto.ClientRequest;
import upc.edu.NerdyNestAPI.user.dto.ClientResponse;
import upc.edu.NerdyNestAPI.user.dto.Mapper.ClientMapper;
import upc.edu.NerdyNestAPI.user.service.ClientService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/clients")
    public ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest clientRequest){
        var client = ClientMapper.INSTANCE.clientRequestToClient(clientRequest);
        var clientResponse = ClientMapper.INSTANCE.clientToClientResponse(clientService.createClient(client));
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/clients")
    public ResponseEntity<List<ClientResponse>> getAllClients(){
        var clientsResponse = ClientMapper.INSTANCE.clientsToClientsResponse(clientService.getAllClients());
        return new ResponseEntity<List<ClientResponse>>(clientsResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<ClientResponse> getClientById(@PathVariable("id") String id){
        var clientResponse = ClientMapper.INSTANCE.clientToClientResponse(clientService.getClientById(id));
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable("id") String id, @RequestBody ClientRequest clientRequest){
        var client = ClientMapper.INSTANCE.clientRequestToClient(clientRequest);
        var clientResponse = ClientMapper.INSTANCE.clientToClientResponse(clientService.updateClient(id, client));
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<Boolean> deleteClient(@PathVariable("id") String id){
        var valor = clientService.deleteClient(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }

}
