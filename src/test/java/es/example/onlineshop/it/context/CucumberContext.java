package es.example.onlineshop.it.context;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Accessors(fluent = true)
@Component
public class CucumberContext {

  private ResponseEntity<JsonNode> responseEntity;
  private RequestEntity<?> requestEntity;

  public void clear() {
    responseEntity = null;
    requestEntity = null;
  }

}
