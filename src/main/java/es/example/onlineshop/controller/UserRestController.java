package es.example.onlineshop.controller;

import es.example.onlineshop.service.rest.UserService;
import es.example.onlineshop.service.rest.dto.UserDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserRestController {

  @Autowired
  private UserService userService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<UserDto>> getUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

}
