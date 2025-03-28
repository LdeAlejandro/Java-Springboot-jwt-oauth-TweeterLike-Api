package jwt_oauth2.spring_security.service;

import jwt_oauth2.spring_security.controller.dto.CreateTweetDto;
import jwt_oauth2.spring_security.controller.dto.FeedDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface TweetService {

    ResponseEntity<Void> createTweet(CreateTweetDto dto, JwtAuthenticationToken token);
    ResponseEntity<Void> deleteTweet(Long tweetId, JwtAuthenticationToken token);
    ResponseEntity<FeedDto> getFeed(int page, int pageSize);
}
