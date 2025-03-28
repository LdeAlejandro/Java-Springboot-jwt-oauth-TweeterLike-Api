package jwt_oauth2.spring_security.service;

import jwt_oauth2.spring_security.controller.dto.LoginRequest;
import jwt_oauth2.spring_security.controller.dto.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest);
}