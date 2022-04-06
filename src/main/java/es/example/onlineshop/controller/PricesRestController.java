package es.example.onlineshop.controller;

import es.example.onlineshop.dto.PricesDto;
import es.example.onlineshop.service.PricesService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/prices")
public class PricesRestController {

  public static final String API_PRICES_ALL = "/all";

  public static final String API_PRICES_DELETE = "/{pricesId}";

  @Autowired
  private PricesService pricesService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PricesDto>> getPrices(@RequestParam(value = "applicationDate", required = false) final String applicationDate,
      @RequestParam(value = "productId", required = false) final Integer productId,
      @RequestParam(value = "brandId", required = false) final Integer brandId) {
    return ResponseEntity.ok(pricesService.getPricesFilter(applicationDate, productId, brandId));
  }

  @GetMapping(path = API_PRICES_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PricesDto>> getAllPrices() {
    return ResponseEntity.ok(pricesService.getAllPrices());
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PricesDto> createPrices(final PricesDto prices) {
    return ResponseEntity.ok(pricesService.createPrices(prices));
  }

  @DeleteMapping(path = API_PRICES_DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deletePrices(@PathVariable(name = "pricesId") final Integer pricesId) {
    pricesService.deletePrices(pricesId);
    return ResponseEntity.noContent().build();
  }
}
