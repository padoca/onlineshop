package es.example.onlineshop.service.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Con esta clase no tenemos que intanciar un restTemplate con cada llamada.
 */
@Configuration
public class RestTemplateConfig {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
