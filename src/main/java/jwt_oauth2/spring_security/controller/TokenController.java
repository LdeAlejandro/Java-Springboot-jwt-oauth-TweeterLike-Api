package jwt_oauth2.spring_security.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jwt_oauth2.spring_security.controller.dto.LoginRequest;
import jwt_oauth2.spring_security.controller.dto.LoginResponse;
import jwt_oauth2.spring_security.entities.Role;
import jwt_oauth2.spring_security.repository.UserRepository;
import jwt_oauth2.spring_security.service.Impl.LoginServiceImpl;
import jwt_oauth2.spring_security.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;


@RestController
public class TokenController {

    private final LoginService loginService;

    public TokenController(LoginService loginService) {

        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
       return loginService.login(loginRequest);
    }
}
