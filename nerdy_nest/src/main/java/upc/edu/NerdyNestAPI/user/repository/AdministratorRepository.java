package upc.edu.NerdyNestAPI.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import upc.edu.NerdyNestAPI.user.model.Administrator;

public interface AdministratorRepository extends MongoRepository<Administrator,String> {
    boolean existsAdministratorByEmailIgnoreCase(String email);
    boolean existsAdministratorByDni(String dni);
    boolean existsAdministratorByUsernameIgnoreCase(String email);
    Administrator findAdministratorByEmail(String email);
}
