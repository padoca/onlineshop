package es.example.onlineshop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class KeepAliveRestController {

  public static final String KEEP_ALIVE_PATH = "/api/keep-alive";

  @GetMapping(value = KEEP_ALIVE_PATH)
  public String keepAlive() {
    log.info("Executes keep-alive endpoint");
    return "I´m Alive";
  }
}
