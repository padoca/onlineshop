package es.example.onlineshop.controller;

import es.example.onlineshop.dto.SalesDto;
import es.example.onlineshop.service.rest.SalesService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/sales")
public class SaleRestController {

  public static final String API_USER = "/{userId}";

  public static final String API_PRODUCT = API_USER + "/byproduct";

  @Autowired
  private SalesService salesService;

  @GetMapping(path = API_PRODUCT, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SalesDto> getProductSalesByUser(@PathVariable(name = "userId") final Integer userId,
      @RequestParam(value = "productId") final Integer productId) {
    return ResponseEntity.ok(salesService.getProductSalesByUser(productId, userId));
  }

  @GetMapping(path = API_USER, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<SalesDto>> getSalesByUser(@PathVariable(value = "userId") final Integer userId) {
    return ResponseEntity.ok(salesService.getSalesByUser(userId));
  }

}
