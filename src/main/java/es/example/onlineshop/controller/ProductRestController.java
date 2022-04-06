package es.example.onlineshop.controller;


import es.example.onlineshop.dto.ProductDto;
import es.example.onlineshop.service.ProductService;
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
@RequestMapping("/api/product")
public class ProductRestController {

  public static final String API_PRODUCT_ALL = "/all";

  public static final String API_PRODUCT_DELETE = "/{productId}";

  @Autowired
  private ProductService productService;

  @GetMapping(path = API_PRODUCT_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<ProductDto>> getAllProduct(
      @RequestParam(required = false, defaultValue = "1") final Integer pageNo,
      @RequestParam(required = false, defaultValue = "10") final Integer pageSize) {
    return ResponseEntity.ok(productService.getAllProducts(pageNo, pageSize));
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ProductDto> createProduct(final ProductDto product) {
    return ResponseEntity.ok(productService.createProduct(product));
  }

  @DeleteMapping(path = API_PRODUCT_DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteProduct(@PathVariable(name = "productId") final Integer productId) {
    productService.deleteProduct(productId);
    return ResponseEntity.noContent().build();
  }
}
