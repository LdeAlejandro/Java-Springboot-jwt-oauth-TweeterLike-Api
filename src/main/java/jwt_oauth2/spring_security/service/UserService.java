package jwt_oauth2.spring_security.service;

import jwt_oauth2.spring_security.controller.dto.CreateUserDTO;
import jwt_oauth2.spring_security.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    public ResponseEntity<Void> newUser(CreateUserDTO dto);
    public ResponseEntity<List<User>> listUsers();
}
