package es.example.onlineshop.ut;

import es.example.onlineshop.service.rest.UserService;
import es.example.onlineshop.service.rest.dto.UserDto;
import es.example.onlineshop.service.rest.dto.UserResponseDto;
import io.cucumber.junit.Cucumber;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@RunWith(Cucumber.class)
@ExtendWith({MockitoExtension.class})
class UserServiceJunitTests {

  UserServiceJunitTests() throws ParseException {
  }

  private UserDto user = new UserDto(1, "email","firstname", "lastname", "avatar");

  @InjectMocks
  private UserService userService = new UserService();

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private ResponseEntity<UserResponseDto> response;

  @Test
  public void givenApiRest_when_GetAllUserMethodCalled_then_ReturnsExpected() {
    ReflectionTestUtils.setField(userService,  "resourceUrl",   "url");
    Mockito.when(restTemplate.exchange(Mockito.any(RequestEntity.class), Mockito.eq(UserResponseDto.class))).thenReturn(response);
    Mockito.when(response.getBody()).thenReturn(UserResponseDto.builder().data(List.of(user)).build());
    List<UserDto> result = userService.getAllUsers();
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.get(0).getId(), user.getId());
  }

  @Test
  public void givenApiRest_when_GetUserByIdMethodCalled_then_ReturnsExpected() {
    Mockito.when(restTemplate.getForEntity(Mockito.nullable(String.class), Mockito.eq(UserResponseDto.class))).thenReturn(response);
    Mockito.when(response.getBody()).thenReturn(UserResponseDto.builder().data(List.of(user)).build());
    Optional<UserDto> result = userService.getUserById(1);
    Assertions.assertNotNull(result.get());
    Assertions.assertEquals(result.get().getId(), user.getId());
  }

}
