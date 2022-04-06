package es.example.onlineshop.it.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestApiMocksConfig {

  @Bean
  @Primary
  public RestTemplate regresUserRestTemplateMock() {
    return Mockito.mock(RestTemplate.class);
  }

}
