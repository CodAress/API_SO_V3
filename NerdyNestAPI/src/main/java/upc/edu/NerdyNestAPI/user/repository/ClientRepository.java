package upc.edu.NerdyNestAPI.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import upc.edu.NerdyNestAPI.user.model.Client;

public interface ClientRepository extends MongoRepository<Client,String> {
    boolean existsClientByEmailIgnoreCase(String email);
    boolean existsClientByDni(String dni);
    Client findClientByEmail(String email);
}
