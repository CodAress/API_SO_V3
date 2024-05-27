package upc.edu.NerdyNestAPI.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import upc.edu.NerdyNestAPI.user.model.Administrator;
import upc.edu.NerdyNestAPI.user.model.Role;
import upc.edu.NerdyNestAPI.user.repository.AdministratorRepository;
import upc.edu.NerdyNestAPI.user.service.AdministratorService;
import upc.edu.NerdyNestAPI.utils.exception.ResourceNotFoundException;
import upc.edu.NerdyNestAPI.utils.exception.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;
    @Override
    public Administrator createAdministrator(Administrator administrator) {
        validateAdministrator(administrator);
        existAdministratorByDni(administrator);
        existAdministratorByEmail(administrator);
        existAdministratorByUsername(administrator);
        administrator.setRole(Role.ADMIN);
        administrator.setDateCreated(LocalDateTime.now());
        administrator.setDateUpdated(null);
        //administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        return administratorRepository.save(administrator);
    }

    @Override
    public Administrator getAdministratorById(String id) {
        existAdministratorById(id);
        return administratorRepository.findById(id).get();
    }

    @Override
    public List<Administrator> getAllAdministrators() {
        List<Administrator> allAdministrators = administratorRepository.findAll();
        if(allAdministrators.isEmpty())
        {
            allAdministrators = null;
            throw new ValidationException("Unregistered administrators");
        }
        return allAdministrators;
    }

    @Override
    public Administrator updateAdministrator(String id, Administrator administrator) {
        existAdministratorById(id);
        validateAdministrator(administrator);
        existAdministratorByDni(administrator);
        existAdministratorByEmail(administrator);
        existAdministratorByUsername(administrator);
        Administrator existingAdministrator = administratorRepository.findById(id).get();
        existingAdministrator.setFirstName(administrator.getFirstName());
        existingAdministrator.setLastName(administrator.getLastName());
        existingAdministrator.setUsername(administrator.getUsername());
        existingAdministrator.setEmail(administrator.getEmail());
        //existingAdministrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        existingAdministrator.setPhone(administrator.getPhone());
        existingAdministrator.setDni(administrator.getDni());
        existingAdministrator.setBirthDate(administrator.getBirthDate());
        existingAdministrator.setDateUpdated(LocalDateTime.now());
        return administratorRepository.save(existingAdministrator);
    }

    @Override
    public boolean deleteAdministrator(String id) {
        existAdministratorById(id);
        administratorRepository.deleteById(id);
        return true;
    }
    private void validateAdministrator(Administrator administrator) {
        if(administrator.getFirstName() == null || administrator.getFirstName().isEmpty())
        {
            throw new ValidationException("First name is required");
        }
        if(administrator.getLastName() == null || administrator.getLastName().isEmpty())
        {
            throw new ValidationException("Last name is required");
        }
        if(administrator.getUsername() == null || administrator.getUsername().isEmpty())
        {
            throw new ValidationException("Username is required");
        }
        if(administrator.getEmail() == null || administrator.getEmail().isEmpty())
        {
            throw new ValidationException("Email is required");
        }
        if(administrator.getPassword() == null || administrator.getPassword().isEmpty())
        {
            throw new ValidationException("Password is required");
        }
        if(administrator.getDni() == null || administrator.getDni().isEmpty())
        {
            throw new ValidationException("DNI is required");
        }
        if(administrator.getDni().length() != 8)
        {
            throw new ValidationException("DNI must have 8 digits");
        }
        if(administrator.getPhone() == null || administrator.getPhone().isEmpty())
        {
            throw new ValidationException("Phone is required");
        }
        if(administrator.getPhone().length() != 9)
        {
            throw new ValidationException("Phone must have 9 digits");
        }
        if(administrator.getBirthDate() == null)
        {
            throw new ValidationException("Birth date is required");
        }
    }

    private void existAdministratorByEmail(Administrator administrator) {
        if (administratorRepository.existsAdministratorByEmailIgnoreCase(administrator.getEmail())){
            throw new ValidationException("Email already exists");
        }
    }
    private void existAdministratorByUsername(Administrator administrator) {
        if (administratorRepository.existsAdministratorByUsernameIgnoreCase(administrator.getUsername())){
            throw new ValidationException("Username already exists");
        }
    }
    private void existAdministratorByDni(Administrator administrator) {
        if (administratorRepository.existsAdministratorByDni(administrator.getDni())){
            throw new ValidationException("DNI already exists");
        }
    }
    private void existAdministratorById(String id) {
        if (!administratorRepository.existsById(id)){
            throw new ResourceNotFoundException("Administrator by id " + id + " not found");
        }
    }
}
