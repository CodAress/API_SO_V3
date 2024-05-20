package upc.edu.NerdyNestAPI.user.service;

import upc.edu.NerdyNestAPI.user.model.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator createAdministrator(Administrator administrator);
    Administrator getAdministratorById(String id);
    List<Administrator> getAllAdministrators();
    Administrator updateAdministrator(String id, Administrator administrator);
    boolean deleteAdministrator(String id);
}
