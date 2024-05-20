package upc.edu.NerdyNestAPI.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upc.edu.NerdyNestAPI.user.dto.AdministratorRequest;
import upc.edu.NerdyNestAPI.user.dto.AdministratorResponse;
import upc.edu.NerdyNestAPI.user.dto.Mapper.AdministratorMapper;
import upc.edu.NerdyNestAPI.user.service.AdministratorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v4")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @PostMapping(value = "/administrators")
    public ResponseEntity<AdministratorResponse> createAdministrator(@RequestBody AdministratorRequest administratorRequest){
        var administrator = AdministratorMapper.INSTANCE.administratorRequestToAdministrator(administratorRequest);
        var administratorResponse = AdministratorMapper.INSTANCE.administratorToAdministratorResponse(administratorService.createAdministrator(administrator));
        return new ResponseEntity<AdministratorResponse>(administratorResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/administrators")
    public ResponseEntity<List<AdministratorResponse>> getAllAdministrators(){
        var administratorsResponse = AdministratorMapper.INSTANCE.administratorsToAdministratorsResponse(administratorService.getAllAdministrators());
        return new ResponseEntity<List<AdministratorResponse>>(administratorsResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/administrators/{id}")
    public ResponseEntity<AdministratorResponse> getAdministratorById(@PathVariable("id") String id){
        var administratorResponse = AdministratorMapper.INSTANCE.administratorToAdministratorResponse(administratorService.getAdministratorById(id));
        return new ResponseEntity<AdministratorResponse>(administratorResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/administrators/{id}")
    public ResponseEntity<AdministratorResponse> updateAdministrator(@PathVariable("id") String id, @RequestBody AdministratorRequest administratorRequest){
        var administrator = AdministratorMapper.INSTANCE.administratorRequestToAdministrator(administratorRequest);
        var administratorResponse = AdministratorMapper.INSTANCE.administratorToAdministratorResponse(administratorService.updateAdministrator(id, administrator));
        return new ResponseEntity<AdministratorResponse>(administratorResponse, HttpStatus.OK);
    }

    @DeleteMapping(value = "/administrators/{id}")
    public ResponseEntity<Boolean> deleteAdministrator(@PathVariable("id") String id){
        var valor = administratorService.deleteAdministrator(id);
        return new ResponseEntity<Boolean>(valor, HttpStatus.NO_CONTENT);
    }
}
