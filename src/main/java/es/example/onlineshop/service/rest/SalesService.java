package es.example.onlineshop.service.rest;

import es.example.onlineshop.dto.ProductDto;
import es.example.onlineshop.dto.SalesDto;
import es.example.onlineshop.repository.SaleRepository;
import es.example.onlineshop.repository.entity.Sale;
import es.example.onlineshop.repository.entity.compositeprimarykey.SaleId;
import es.example.onlineshop.service.ProductService;
import es.example.onlineshop.service.rest.dto.UserDto;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class SalesService {

  @Autowired
  private UserService userService;

  @Autowired
  private ProductService productService;

  @Autowired
  private SaleRepository saleRepository;

  public SalesDto getProductSalesByUser(final Integer productId, final Integer userId) {
    // Call sales to see if exists with future
    CompletableFuture<Optional<Sale>> cfSaleOp = CompletableFuture.supplyAsync(() -> saleRepository.findById(new SaleId(productId, userId)));
    CompletableFuture<Sale> cfSale =
        cfSaleOp.thenApply(sale -> sale.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sales not found")));
    //Call product with future
    CompletableFuture<ProductDto> cfProduct = CompletableFuture.supplyAsync(
        () -> productService.getProductById(productId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product " + productId + "not found")));
    //Call user with future
    CompletableFuture<UserDto> cfUser = CompletableFuture.supplyAsync(
        () -> userService.getUserById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User " + userId + " not found")));

    SalesDto salesDto = cfSale.thenCombine(cfProduct, (saleDto, productDto) -> new SalesDto(productDto, null, saleDto.getAmount()))
        .thenCombine(cfUser, (saleDto, userDto) -> new SalesDto(saleDto.getProduct(), userDto, saleDto.getAmount())).join();
    return salesDto;
  }

  public List<SalesDto> getSalesByUser(final Integer userId) {
    List<Sale> sales = saleRepository.findByUserId(userId);
    return sales.stream().map(this::getSalesDto).collect(Collectors.toList());
  }

  public SalesDto getSalesDto(final Sale sale) {
    CompletableFuture<ProductDto> cfProduct = CompletableFuture.supplyAsync(
        () -> productService.getProductById(sale.getProductId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product " + sale.getProductId() + "not found")));
    CompletableFuture<UserDto> cfUser = CompletableFuture.supplyAsync(
        () -> userService.getUserById(sale.getUserId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User " + sale.getUserId() + " not found")));
    return cfProduct.thenCombine(cfUser, (productDto, userDto) -> new SalesDto(productDto, userDto, sale.getAmount())).join();
  }
}
