package es.example.onlineshop.ut;

import es.example.onlineshop.controller.PricesRestController;
import es.example.onlineshop.dto.PricesDto;
import es.example.onlineshop.service.PricesService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class PricesRestControllerTests {

  private PricesDto prices1 = new PricesDto(35455, 1, 1, "2020-06-14-00.00.00", "2020-12-31-23.59.59", new BigDecimal("35.5"));
  private PricesDto prices2 = new PricesDto(35455, 1, 2, "2020-06-14-15.00.00", "2020-06-14-18.30.00", new BigDecimal("25.45"));
  private PricesDto prices3 = new PricesDto(35455, 1, 3, "2020-06-15-00.00.00", "2020-06-15-11.00.00", new BigDecimal("30.5"));
  private PricesDto prices4 = new PricesDto(35455, 1, 4, "2020-06-15-16.00.00", "2020-12-31-23.59.59", new BigDecimal("38.95"));

  @InjectMocks
  private PricesRestController pricesRestController = new PricesRestController();

  @Mock
  private PricesService pricesService;

  @Test
  public void given_Test1Request_when_GetEndpointCalled_then_ReturnsExpecteed() {
    Mockito.when(pricesService.getPricesFilter("2020-06-14-10.00.00", 35455, 1)).thenReturn(List.of(prices1));
    ResponseEntity<List<PricesDto>> result = pricesRestController.getPrices("2020-06-14-10.00.00", 35455, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getBody().get(0).getPrice(), prices1.getPrice());
  }

  @Test
  public void given_Test2Request_when_GetEndpointCalled_then_ReturnsExpecteed() {
    Mockito.when(pricesService.getPricesFilter("2020-06-14-16.00.00", 35455, 1)).thenReturn(List.of(prices1, prices2));
    ResponseEntity<List<PricesDto>> result = pricesRestController.getPrices("2020-06-14-16.00.00", 35455, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getBody().get(0).getPrice(), prices1.getPrice());
    Assertions.assertEquals(result.getBody().get(1).getPrice(), prices2.getPrice());
  }

  @Test
  public void given_Test3Request_when_GetEndpointCalled_then_ReturnsExpecteed() {
    Mockito.when(pricesService.getPricesFilter("2020-06-14-21.00.00", 35455, 1)).thenReturn(List.of(prices1));
    ResponseEntity<List<PricesDto>> result = pricesRestController.getPrices("2020-06-14-21.00.00", 35455, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getBody().get(0).getPrice(), prices1.getPrice());
  }

  @Test
  public void given_Test4Request_when_GetEndpointCalled_then_ReturnsExpecteed() {
    Mockito.when(pricesService.getPricesFilter("2020-06-15-10.00.00", 35455, 1)).thenReturn(List.of(prices1, prices3));
    ResponseEntity<List<PricesDto>> result = pricesRestController.getPrices("2020-06-15-10.00.00", 35455, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getBody().get(0).getPrice(), prices1.getPrice());
    Assertions.assertEquals(result.getBody().get(1).getPrice(), prices3.getPrice());
  }

  @Test
  public void given_Test5Request_when_GetEndpointCalled_then_ReturnsExpecteed() {
    Mockito.when(pricesService.getPricesFilter("2020-06-16-21.00.00", 35455, 1)).thenReturn(List.of(prices1, prices4));
    ResponseEntity<List<PricesDto>> result = pricesRestController.getPrices("2020-06-16-21.00.00", 35455, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.getBody().get(0).getPrice(), prices1.getPrice());
    Assertions.assertEquals(result.getBody().get(1).getPrice(), prices4.getPrice());
  }

}
