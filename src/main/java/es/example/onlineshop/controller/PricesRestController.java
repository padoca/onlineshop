package es.example.onlineshop.controller;

import es.example.onlineshop.dto.PricesDto;
import es.example.onlineshop.service.PricesService;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

  @ApiOperation(value = "Get Prices Operation", notes = "Devolvemos La lista de precios respecto a la fecha de aplicación, identificador de producto, identificador de cadena")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PricesDto.class ),
          @ApiResponse(code = 400, message = "Bad Request", response = String.class),
          @ApiResponse(code = 500, message = "Error inesperado del sistema") })
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PricesDto>> getPrices(
          @ApiParam(name =  "applicationDate", type = "LocalDateTime", value = "Fecha de aplicación", example = "2020-06-14-21.00.00", required = false)
          @RequestParam(value = "applicationDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") final LocalDateTime applicationDate,
          @ApiParam(name =  "productId", type = "Integer", value = "Identificador de producto", example = "35455", required = false)
          @RequestParam(value = "productId", required = false) final Integer productId,
          @ApiParam(name =  "brandId", type = "Integer", value = "Identificador de cadena", example = "1", required = false)
          @RequestParam(value = "brandId", required = false) final Integer brandId) {
    return ResponseEntity.ok(pricesService.getPricesFilter(applicationDate, productId, brandId));
  }

  @ApiOperation(value = "Get All Prices Operation", notes = "Devolvemos La lista completa de precios")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PricesDto.class ),
          @ApiResponse(code = 400, message = "Bad Request", response = String.class),
          @ApiResponse(code = 500, message = "Error inesperado del sistema") })
  @GetMapping(path = API_PRICES_ALL, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<PricesDto>> getAllPrices() {
    return ResponseEntity.ok(pricesService.getAllPrices());
  }

  @ApiOperation(value = "Create Price Operation", notes = "Insertamos un nuevo precio de un producto")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PricesDto.class ),
          @ApiResponse(code = 500, message = "Error inesperado del sistema") })
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PricesDto> createPrices(
          @ApiParam(name =  "prices", type = "PricesDto", value = "DTO del precio", example = "{productId:1, brandId:1, priceList:1, price:10, startDate:2020-06-14-21.00.00, endDate:2020-06-15-21.00.00}", required = true)
          final PricesDto prices) {
    return ResponseEntity.ok(pricesService.createPrices(prices));
  }

  @ApiOperation(value = "Delete Price Operation", notes = "Borramos un precio")
  @ApiResponses(value = {
          @ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = PricesDto.class ),
          @ApiResponse(code = 400, message = "Bad Request", response = String.class),
          @ApiResponse(code = 500, message = "Error inesperado del sistema") })
  @DeleteMapping(path = API_PRICES_DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deletePrices(
          @ApiParam(name =  "pricesId", type = "Integer", value = "Identificador de precio", example = "1", required = true)
          @PathVariable(name = "pricesId") final Integer pricesId) {
    pricesService.deletePrices(pricesId);
    return ResponseEntity.noContent().build();
  }
}
