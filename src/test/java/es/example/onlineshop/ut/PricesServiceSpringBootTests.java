package es.example.onlineshop.ut;

import es.example.onlineshop.dto.PricesDto;
import es.example.onlineshop.repository.PricesRepository;
import es.example.onlineshop.repository.entity.Prices;
import es.example.onlineshop.service.PricesService;
import es.example.onlineshop.service.mapper.PricesResponseEntityMapper;
import es.example.onlineshop.util.Utils;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PricesServiceSpringBootTests {

  PricesServiceSpringBootTests() throws ParseException {
  }

  private Prices prices1 = new Prices(1, 35455, 1, Utils.toTimestamp("2020-06-14-00.00.00"), Utils.toTimestamp("2020-12-31-23.59.59"),
      1, null, new BigDecimal("35.5"), "EUR");
  private Prices prices2 = new Prices(2, 35455, 1, Utils.toTimestamp("2020-06-14-15.00.00"), Utils.toTimestamp("2020-06-14-18.30.00"),
      2, null, new BigDecimal("25.45"), "EUR");
  private Prices prices3 = new Prices(3, 35455, 1, Utils.toTimestamp("2020-06-15-00.00.00"), Utils.toTimestamp("2020-06-15-11.00.00"),
      3, null, new BigDecimal("30.5"), "EUR");
  private Prices prices4 = new Prices(4, 35455, 1, Utils.toTimestamp("2020-06-15-16.00.00"), Utils.toTimestamp("2020-12-31-23.59.59"),
      4, null, new BigDecimal("38.95"), "EUR");

  @InjectMocks
  private PricesService pricesService = new PricesService();

  @Mock
  private PricesRepository pricesRepository;

  @Mock
  private PricesResponseEntityMapper pricesResponseEntityMapper;

  @Test
  public void given_Test1Request_when_GetEndpointCalled_then_ReturnsExpecteed() throws ParseException {
    Mockito.when(pricesResponseEntityMapper.toDto(Mockito.any())).thenCallRealMethod();
    Mockito.when(pricesRepository.findByFilter(Utils.toTimestamp("2020-06-14-10.00.00"), 35455, 1)).thenReturn(List.<Prices>of(prices1));
    List<PricesDto> result = pricesService.getPricesFilter("2020-06-14-10.00.00", 35455, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.get(0).getPrice(), prices1.getPrice());
  }

  @Test
  public void given_Test2Request_when_GetEndpointCalled_then_ReturnsExpecteed() throws ParseException {
    Mockito.when(pricesResponseEntityMapper.toDto(Mockito.any())).thenCallRealMethod();
    Mockito.when(pricesRepository.findByFilter(Utils.toTimestamp("2020-06-14-16.00.00"), 35455, 1)).thenReturn(List.of(prices1, prices2));
    List<PricesDto> result = pricesService.getPricesFilter("2020-06-14-16.00.00", 35455, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.get(0).getPrice(), prices1.getPrice());
    Assertions.assertEquals(result.get(1).getPrice(), prices2.getPrice());
  }

  @Test
  public void given_Test3Request_when_GetEndpointCalled_then_ReturnsExpecteed() throws ParseException {
    Mockito.when(pricesResponseEntityMapper.toDto(Mockito.any())).thenCallRealMethod();
    Mockito.when(pricesRepository.findByFilter(Utils.toTimestamp("2020-06-14-21.00.00"), 35455, 1)).thenReturn(List.of(prices1));
    List<PricesDto> result = pricesService.getPricesFilter("2020-06-14-21.00.00", 35455, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.get(0).getPrice(), prices1.getPrice());
  }

  @Test
  public void given_Test4Request_when_GetEndpointCalled_then_ReturnsExpecteed() throws ParseException {
    Mockito.when(pricesResponseEntityMapper.toDto(Mockito.any())).thenCallRealMethod();
    Mockito.when(pricesRepository.findByFilter(Utils.toTimestamp("2020-06-15-10.00.00"), 35455, 1)).thenReturn(List.of(prices1, prices3));
    List<PricesDto> result = pricesService.getPricesFilter("2020-06-15-10.00.00", 35455, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.get(0).getPrice(), prices1.getPrice());
    Assertions.assertEquals(result.get(1).getPrice(), prices3.getPrice());
  }

  @Test
  public void given_Test5Request_when_GetEndpointCalled_then_ReturnsExpecteed() throws ParseException {
    Mockito.when(pricesResponseEntityMapper.toDto(Mockito.any())).thenCallRealMethod();
    Mockito.when(pricesRepository.findByFilter(Utils.toTimestamp("2020-06-16-21.00.00"), 35455, 1)).thenReturn(List.of(prices1, prices4));
    List<PricesDto> result = pricesService.getPricesFilter("2020-06-16-21.00.00", 35455, 1);
    Assertions.assertNotNull(result);
    Assertions.assertEquals(result.get(0).getPrice(), prices1.getPrice());
    Assertions.assertEquals(result.get(1).getPrice(), prices4.getPrice());
  }

}
