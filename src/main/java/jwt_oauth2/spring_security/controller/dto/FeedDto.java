package jwt_oauth2.spring_security.controller.dto;

import java.util.List;

public record FeedDto(List<FeedItemDto> feedItens, int page,
                      int pageSize,
                      int totalPages,
                      long totalElements) {
}
