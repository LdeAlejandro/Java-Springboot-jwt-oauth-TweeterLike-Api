package jwt_oauth2.spring_security.service.Impl;

import jwt_oauth2.spring_security.controller.dto.CreateUserDTO;
import jwt_oauth2.spring_security.entities.Role;
import jwt_oauth2.spring_security.entities.User;
import jwt_oauth2.spring_security.repository.RoleRepository;
import jwt_oauth2.spring_security.repository.UserRepository;
import jwt_oauth2.spring_security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDTO dto){

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var userFromDb = userRepository.findByUsername(dto.username());

        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<User>> listUsers(){

        var users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }


}
