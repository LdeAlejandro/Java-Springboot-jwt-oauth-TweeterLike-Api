package jwt_oauth2.spring_security.controller;

import jwt_oauth2.spring_security.controller.dto.CreateTweetDto;
import jwt_oauth2.spring_security.controller.dto.FeedDto;
import jwt_oauth2.spring_security.controller.dto.FeedItemDto;
import jwt_oauth2.spring_security.entities.Role;
import jwt_oauth2.spring_security.entities.Tweet;
import jwt_oauth2.spring_security.entities.User;
import jwt_oauth2.spring_security.repository.TweetRepository;
import jwt_oauth2.spring_security.repository.UserRepository;
import jwt_oauth2.spring_security.service.TweetService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDto dto, JwtAuthenticationToken token) {
        return tweetService.createTweet(dto, token);

    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long tweetId, JwtAuthenticationToken token) {
        return tweetService.deleteTweet(tweetId, token);
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedDto> feed(@RequestParam (value = "page", defaultValue = "0") int page,
                                        @RequestParam (value = "pageSize", defaultValue = "10") int pageSize) {
        return tweetService.getFeed(page, pageSize);
    }

}
