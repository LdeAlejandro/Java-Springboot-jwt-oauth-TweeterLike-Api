package jwt_oauth2.spring_security.service.Impl;

import jwt_oauth2.spring_security.controller.dto.LoginRequest;
import jwt_oauth2.spring_security.controller.dto.LoginResponse;
import jwt_oauth2.spring_security.repository.UserRepository;
import jwt_oauth2.spring_security.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }


    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {

        var user = userRepository.findByUsername(loginRequest.username());

        //Validar se login esta correto se o usuario existir e se a senha estiver correta
        if (user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("User or password is invalid!");
        }
        //Gerar e retornar token jwt
        var now = Instant.now();
        var expiresIn = 300L;
        var scopes = user.get().getRoles()
                .stream()
                //.map(Role::getName)
                .map(role -> role.getName().toUpperCase())
                .collect(Collectors.joining(" "));


        var claims = JwtClaimsSet.builder()
                .issuer("myBackend")
                .subject(user.get().getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes) // permissoes do token jwt
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));
    }
}
