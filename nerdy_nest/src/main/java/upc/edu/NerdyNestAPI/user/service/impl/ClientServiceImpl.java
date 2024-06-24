package upc.edu.NerdyNestAPI.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import upc.edu.NerdyNestAPI.user.model.Client;
import upc.edu.NerdyNestAPI.user.model.Role;
import upc.edu.NerdyNestAPI.user.repository.ClientRepository;
import upc.edu.NerdyNestAPI.user.service.ClientService;
import upc.edu.NerdyNestAPI.utils.exception.ResourceNotFoundException;
import upc.edu.NerdyNestAPI.utils.exception.ValidationException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Client createClient(Client client) {
        validateClient(client);
        existClientByDni(client);
        existClientByEmail(client);
        client.setRole(Role.CLIENT);
        client.setDateCreated(LocalDateTime.now());
        client.setDateUpdated(null);
        //client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    @Override
    public Client getClientById(String id) {
        existClientById(id);
        return clientRepository.findById(id).get();
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> allClients = clientRepository.findAll();
        if(allClients.isEmpty())
        {
            throw new ResourceNotFoundException("Unregistered clients");
        }
        return allClients;
    }

    @Override
    public Client updateClient(String id, Client client) {
        existClientById(id);
        validateClient(client);
        Client existingClient = clientRepository.findById(id).get();
        existingClient.setFirstName(client.getFirstName());
        existingClient.setLastName(client.getLastName());
        existingClient.setEmail(client.getEmail());
        //existingClient.setPassword(passwordEncoder.encode(client.getPassword()));
        existingClient.setPhone(client.getPhone());
        existingClient.setDni(client.getDni());
        existingClient.setBirthDate(client.getBirthDate());
        existingClient.setPostalCode(client.getPostalCode());
        existingClient.setAddress(client.getAddress());
        existingClient.setDateUpdated(LocalDateTime.now());
        return clientRepository.save(existingClient);
    }

    @Override
    public Client getClientByEmailAndPassword(String email, String password) {
        existClientByEmail(email);
        Client client = clientRepository.findClientByEmailAndPassword(email, password);
        if(client == null){
            throw new ResourceNotFoundException("Incorrect email or password");
        }
        return client;
    }

    @Override
    public Boolean deleteClient(String id) {
        existClientById(id);
        clientRepository.deleteById(id);
        return true;
    }

    private void validateClient(Client client) {
        if(client.getFirstName() == null || client.getFirstName().isEmpty())
        {
            throw new ValidationException("First name is required");
        }
        if(client.getLastName() == null || client.getLastName().isEmpty())
        {
            throw new ValidationException("Last name is required");
        }
        if(client.getEmail() == null || client.getEmail().isEmpty())
        {
            throw new ValidationException("Email is required");
        }
        if(client.getPassword() == null || client.getPassword().isEmpty())
        {
            throw new ValidationException("Password is required");
        }
        if(client.getPhone() == null || client.getPhone().isEmpty())
        {
            throw new ValidationException("Phone is required");
        }
        if(client.getPhone().length() != 9)
        {
            throw new ValidationException("Phone must have 9 digits");
        }
        if(client.getDni() == null || client.getDni().isEmpty())
        {
            throw new ValidationException("DNI is required");
        }
        if(client.getDni().length() != 8)
        {
            throw new ValidationException("DNI must have 8 digits");
        }
        if(client.getBirthDate() == null)
        {
            throw new ValidationException("Birth date is required");
        }
        if(client.getPostalCode() == null || client.getPostalCode().isEmpty())
        {
            throw new ValidationException("Postal code is required");
        }
        if(client.getPostalCode().length() != 5)
        {
            throw new ValidationException("Postal code must have 5 digits");
        }
        if(client.getAddress() == null || client.getAddress().isEmpty())
        {
            throw new ValidationException("Address is required");
        }
    }
    private void existClientById(String id) {
        if (!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Client by id " + id + " not found");
        }
    }
    private void existClientByEmail(String email) {
        if (!clientRepository.existsClientByEmailIgnoreCase(email)){
            throw new ResourceNotFoundException("Incorrect email or password");
        }
    }
    private void existClientByEmail(Client client) {
        if (clientRepository.existsClientByEmailIgnoreCase(client.getEmail())){
            throw new ValidationException("Email already exists");
        }
    }
    private void existClientByDni(Client client) {
        if (clientRepository.existsClientByDni(client.getDni())){
            throw new ValidationException("DNI already exists");
        }
    }
}
