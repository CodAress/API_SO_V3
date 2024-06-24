package upc.edu.NerdyNestAPI.user.service;

import upc.edu.NerdyNestAPI.user.model.Client;

import java.util.List;

public interface ClientService {
    Client createClient(Client client);
    Client getClientById(String id);
    List<Client> getAllClients();
    Client updateClient(String id, Client client);
    Client getClientByEmailAndPassword(String email, String password);
    Boolean deleteClient(String id);
}
