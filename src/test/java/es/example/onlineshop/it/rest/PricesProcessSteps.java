package es.example.onlineshop.it.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.example.onlineshop.dto.PricesDto;
import es.example.onlineshop.it.context.CucumberContext;
import es.example.onlineshop.repository.PricesRepository;
import es.example.onlineshop.repository.entity.Prices;
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

public class PricesProcessSteps {

  @Autowired
  private TestRestTemplate restTemplateMock;
  @Autowired
  private CucumberContext context;
  @Autowired
  protected ObjectMapper customObjectMapper;
  @Autowired
  private PricesRepository pricesRepository;


  @Given("an authorized request to the prices endpoint with date {}")
  public void createAuthorizedtestRequest(final String date) throws URISyntaxException {
    context.requestEntity(new RequestEntity<>(
        new HttpHeaders(),
        HttpMethod.GET,
        new URI("/api/prices?applicationDate="+date)
    ));
  }

  @Then("the price should have been the same price that with id {}")
  public void checkPriceStored(final Integer id) throws JsonProcessingException {
    Prices price = pricesRepository.findById(id).get();
    JsonNode resArray = context.responseEntity().getBody();
    Assertions.assertNotNull(resArray);
    PricesDto[] myObjects = customObjectMapper.treeToValue(resArray, PricesDto[].class);
    Assertions.assertTrue(Arrays.asList(myObjects).stream().anyMatch(
        x -> x.getPrice().doubleValue() == price.getPrice().doubleValue()));
  }





}
