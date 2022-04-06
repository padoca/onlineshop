package es.example.onlineshop.it.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.example.onlineshop.dto.SalesDto;
import es.example.onlineshop.it.context.CucumberContext;
import es.example.onlineshop.repository.SaleRepository;
import es.example.onlineshop.repository.entity.Sale;
import es.example.onlineshop.repository.entity.compositeprimarykey.SaleId;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

public class SalesProcessSteps {

  @Autowired
  private TestRestTemplate restTemplateMock;
  @Autowired
  private CucumberContext context;
  @Autowired
  protected ObjectMapper customObjectMapper;
  @Autowired
  private SaleRepository saleRepository;


  @Given("an authorized request to the sales endpoint with user {}")
  public void createAuthorizedRequest(final String userId) throws URISyntaxException {
    context.requestEntity(new RequestEntity<>(
        new HttpHeaders(),
        HttpMethod.GET,
        new URI("/api/sales/" + userId)
    ));
  }

  @Then("the sale should have been the same that with id {}")
  public void checkSaleStored(final Integer id) throws JsonProcessingException {
    Sale sale = saleRepository.findById(new SaleId(id, id)).get();
    JsonNode resArray = context.responseEntity().getBody();
    Assertions.assertNotNull(resArray);
    SalesDto[] myObjects = customObjectMapper.treeToValue(resArray, SalesDto[].class);
    Assertions.assertTrue(Arrays.asList(myObjects).stream().anyMatch(
        x -> x.getAmount() == sale.getAmount()));
  }

  @Given("an authorized request to the sales byproduct endpoint with user {} and product {}")
  public void createAuthorizedRequest(final String userId, final String productId) throws URISyntaxException {
    context.requestEntity(new RequestEntity<>(
        new HttpHeaders(),
        HttpMethod.GET,
        new URI("/api/sales/" + userId + "/byproduct?productId=" + productId)
    ));
  }


}
