package es.example.onlineshop.service.rest;

import es.example.onlineshop.service.rest.dto.UserDto;
import es.example.onlineshop.service.rest.dto.UserResponseDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class UserService {

  /**
   * Url rest for user.
   */
  @Value("${users.rest.url}")
  protected String resourceUrl;

  @Autowired
  private RestTemplate restTemplate;

  public List<UserDto> getAllUsers() {
    UserResponseDto response = callGetRestApiWithHeaders();
    return response.getData();
  }

  public Optional<UserDto> getUserById(final Integer userId) {
    UserResponseDto response = callGetRestApi();
    return response.getData().stream().filter(user -> user.getId().equals(userId)).findAny();
  }

  private UserResponseDto callGetRestApi() {
    log.debug("Request host:{}", resourceUrl);
    ResponseEntity<UserResponseDto> response = restTemplate.getForEntity(resourceUrl, UserResponseDto.class);
    return response.getBody();
  }

  /**
   * Idem to callGetRestApi but more general (get, post, put, etc.)
   * @return UserResponseDto
   */
  private UserResponseDto callGetRestApiWithHeaders() {
    log.debug("Request host:{}", resourceUrl);
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    final RequestEntity<Void> requestEntity = new RequestEntity<>(
        headers,
        HttpMethod.GET,
        createUri(resourceUrl));
    ResponseEntity<UserResponseDto> response = restTemplate.exchange(requestEntity, UserResponseDto.class);
    return response.getBody();
  }

  private URI createUri(final String uriPath) {
    try {
      return new URI(uriPath);
    } catch (URISyntaxException e) {
      final String errMsg = "Unexpected exception, tried to create an invalid URI";
      log.error(errMsg + ": " + uriPath);
      throw new RuntimeException(errMsg, e);
    }
  }


}
