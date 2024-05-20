package upc.edu.NerdyNestAPI.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import upc.edu.NerdyNestAPI.login.dto.LoginRequest;
import upc.edu.NerdyNestAPI.login.service.LoginService;
import upc.edu.NerdyNestAPI.user.model.Administrator;
import upc.edu.NerdyNestAPI.user.model.Client;
import upc.edu.NerdyNestAPI.user.model.Role;
import upc.edu.NerdyNestAPI.user.repository.AdministratorRepository;
import upc.edu.NerdyNestAPI.user.repository.ClientRepository;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AdministratorRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Role login(LoginRequest loggingRequest) {
        /*
        // Verifica las credenciales para el administrador
        Administrator admin = adminRepository.findAdministratorByEmail(loggingRequest.getEmail());
        if (admin != null && passwordEncoder.matches(loggingRequest.getPassword(), admin.getPassword())) {
            // Las credenciales son correctas
            return admin.getRole();
        }
        // Verifica las credenciales para el cliente
        Client client = clientRepository.findClientByEmail(loggingRequest.getEmail());
        if (client != null && passwordEncoder.matches(loggingRequest.getPassword(), client.getPassword())) {
            // Las credenciales son correctas
            return client.getRole();
        }
        return Role.UNAUTHORIZED;
    }

         */
        return Role.UNAUTHORIZED;
    }
}
