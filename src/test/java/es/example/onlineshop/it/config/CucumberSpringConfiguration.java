package es.example.onlineshop.it.config;

import es.example.onlineshop.Application;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT, classes = Application.class)
@AutoConfigureMockMvc
public class CucumberSpringConfiguration {
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private MockMvc mvc;

}
