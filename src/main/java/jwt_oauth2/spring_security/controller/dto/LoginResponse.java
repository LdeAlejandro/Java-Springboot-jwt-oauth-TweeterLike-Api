package jwt_oauth2.spring_security.controller.dto;

public record LoginResponse(String accessToken, long expiresIn) {
}
