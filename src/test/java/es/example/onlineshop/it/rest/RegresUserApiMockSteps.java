package es.example.onlineshop.it.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.example.onlineshop.it.context.CucumberContext;
import es.example.onlineshop.service.rest.dto.UserResponseDto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RegresUserApiMockSteps {

  @Value("${users.rest.url}")
  private String baseUrl;

  @Autowired
  private RestTemplate regresUserRestTemplateMock;
  @Autowired
  private CucumberContext context;
  @Autowired
  protected ObjectMapper customObjectMapper;

  private HashMap<String, ResponseEntity<?>> responseMap = new HashMap<>();

  @Before
  public void initialize() {
    responseMap.clear();
  }

  @Given("User rest API endpoint will reply with status {int} and data in {}")
  public void mockRsiAPIResponse(final int httpStatus, final String exampleFile) throws IOException {
    responseMap.put(baseUrl, createApiResponseForFile(httpStatus, exampleFile));

    Mockito.doAnswer(this::getResponse).when(regresUserRestTemplateMock).getForEntity(baseUrl, UserResponseDto.class);
    Mockito.doAnswer(this::getResponse)
        .when(regresUserRestTemplateMock).exchange(Mockito.any(RequestEntity.class), Mockito.eq(UserResponseDto.class));
  }

  private ResponseEntity<UserResponseDto> getResponse(InvocationOnMock invocationOnMock) {
    String requestUrl;
    if (invocationOnMock.getArgument(0) instanceof RequestEntity) { //For exchnge method
      final URI uriObject = ((RequestEntity) invocationOnMock.getArgument(0)).getUrl();
      requestUrl = uriObject.toString();
    } else {
      requestUrl = invocationOnMock.getArgument(0);
    }
    final Class<?> requestClass = invocationOnMock.getArgument(1);
    Assertions.assertEquals(requestUrl, baseUrl);
    Assertions.assertEquals(requestClass, UserResponseDto.class);
    return (ResponseEntity<UserResponseDto>) responseMap.remove(baseUrl);
  }

  private ResponseEntity<UserResponseDto> createApiResponseForFile(final int httpStatus, final String fileDescription) throws IOException {
    final InputStream is = getClass().getResourceAsStream("/api/responses/rest/" + fileDescription + ".json");
    final byte[] bytes = is.readAllBytes();
    UserResponseDto response = customObjectMapper.readValue(bytes, UserResponseDto.class);
    return ResponseEntity.status(httpStatus).body(response);
  }


}
