package jwt_oauth2.spring_security.controller.dto;

public record FeedItemDto(Long tweetId, String content, String username) {
}
