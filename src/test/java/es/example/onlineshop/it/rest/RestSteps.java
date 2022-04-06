package es.example.onlineshop.it.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.example.onlineshop.it.context.CucumberContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;


public class RestSteps {

  @Autowired
  private TestRestTemplate restTemplateMock;
  @Autowired
  private CucumberContext context;
  @Autowired
  protected ObjectMapper customObjectMapper;

  @When("the endpoint is executed")
  public void executeHttpRequest() {
    context.responseEntity(restTemplateMock.exchange(context.requestEntity(), JsonNode.class));
  }

  @Then("the request ended with http status {int}")
  public void assertHttpRequestResult(final int httpStatus) {
    Assertions.assertEquals(httpStatus, context.responseEntity().getStatusCodeValue());
  }

  @Then("the body of the response is null")
  public void assertResponseBodyIsNull() throws IOException {
    Assertions.assertNull(context.responseEntity().getBody());
  }

  @Then("the body of the response contains {}")
  public void assertResponseBodyIsJsonExample(final String fileDescription) throws IOException {
    final InputStream is = getClass().getResourceAsStream("/api/responses/" + fileDescription + ".json");
    final byte[] bytes = is.readAllBytes();
    final JsonNode expectedJson = customObjectMapper.readTree(bytes);
    Assertions.assertNotNull(context.responseEntity().getBody());
    Assertions.assertEquals(expectedJson, context.responseEntity().getBody());
  }
}
