package upc.edu.NerdyNestAPI.login.service;

import upc.edu.NerdyNestAPI.login.dto.LoginRequest;
import upc.edu.NerdyNestAPI.user.model.Role;

public interface LoginService {
    Role login(LoginRequest loggingRequest);
}
