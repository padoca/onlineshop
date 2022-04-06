package es.example.onlineshop.it.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.example.onlineshop.it.context.CucumberContext;
import io.cucumber.java.en.Given;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

public class UserProcessSteps {

  @Autowired
  private TestRestTemplate restTemplateMock;
  @Autowired
  private CucumberContext context;
  @Autowired
  protected ObjectMapper customObjectMapper;


  @Given("an authorized request to the user endpoint")
  public void createAuthorizedtestRequest() throws URISyntaxException {
    context.requestEntity(new RequestEntity<>(
        new HttpHeaders(),
        HttpMethod.GET,
        new URI("/api/user")
    ));
  }


}
