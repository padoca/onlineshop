package es.example.onlineshop.it;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import es.example.onlineshop.dto.PricesDto;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.MimeTypeUtils;

@SpringBootTest()
@AutoConfigureMockMvc
@TestPropertySource(
    locations = "classpath:application.properties")
class PricesRestControllerTests {

  private PricesDto prices1 = new PricesDto(35455, 1, 1, "2020-06-14-00.00.00", "2020-12-31-23.59.59", new BigDecimal("35.5"));
  private PricesDto prices2 = new PricesDto(35455, 1, 2, "2020-06-14-15.00.00", "2020-06-14-18.30.00", new BigDecimal("25.45"));
  private PricesDto prices3 = new PricesDto(35455, 1, 3, "2020-06-15-00.00.00", "2020-06-15-11.00.00", new BigDecimal("30.5"));
  private PricesDto prices4 = new PricesDto(35455, 1, 4, "2020-06-15-16.00.00", "2020-12-31-23.59.59", new BigDecimal("38.95"));

  @Autowired
  private MockMvc mvc;

  @Test
  public void test1() throws Exception {
    // Given
    Integer productId = 35455;
    Integer brandId = 1;
    String applicationDate = "2020-06-14-10.00.00";

    // When
    final ResultActions result = mvc.perform(
        MockMvcRequestBuilders.get("/api/prices")
            .param("productId", productId.toString())
            .param("brandId", brandId.toString())
            .param("applicationDate", applicationDate)
            .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

    // Then
    List<PricesDto> pricesResult = List.of(prices1);
    final int expectedSize = pricesResult.size();
    final Double[] expectedLanguageNames = pricesResult.stream().map(PricesDto::getPrice).map(BigDecimal::doubleValue)
        .collect(Collectors.toList()).toArray(new Double[pricesResult.size()]);
    result.andExpect(status().isOk());
    result.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(expectedSize));
    result.andExpect(MockMvcResultMatchers.jsonPath("$[*].price", Matchers.containsInAnyOrder(expectedLanguageNames)));
  }

  @Test
  public void test2() throws Exception {
    // Given
    Integer productId = 35455;
    Integer brandId = 1;
    String applicationDate = "2020-06-14-16.00.00";

    // When
    final ResultActions result = mvc.perform(
        MockMvcRequestBuilders.get("/api/prices")
            .param("productId", productId.toString())
            .param("brandId", brandId.toString())
            .param("applicationDate", applicationDate)
            .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

    // Then
    List<PricesDto> pricesResult = List.of(prices1, prices2);
    final int expectedSize = pricesResult.size();
    final Double[] expectedLanguageNames = pricesResult.stream().map(PricesDto::getPrice).map(BigDecimal::doubleValue)
        .collect(Collectors.toList()).toArray(new Double[pricesResult.size()]);
    result.andExpect(status().isOk());
    result.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(expectedSize));
    result.andExpect(MockMvcResultMatchers.jsonPath("$[*].price", Matchers.containsInAnyOrder(expectedLanguageNames)));
  }

  @Test
  public void test3() throws Exception {
    // Given
    Integer productId = 35455;
    Integer brandId = 1;
    String applicationDate = "2020-06-14-21.00.00";

    // When
    final ResultActions result = mvc.perform(
        MockMvcRequestBuilders.get("/api/prices")
            .param("productId", productId.toString())
            .param("brandId", brandId.toString())
            .param("applicationDate", applicationDate)
            .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

    // Then
    List<PricesDto> pricesResult = List.of(prices1);
    final int expectedSize = pricesResult.size();
    final Double[] expectedLanguageNames = pricesResult.stream().map(PricesDto::getPrice).map(BigDecimal::doubleValue)
        .collect(Collectors.toList()).toArray(new Double[pricesResult.size()]);
    result.andExpect(status().isOk());
    result.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(expectedSize));
    result.andExpect(MockMvcResultMatchers.jsonPath("$[*].price", Matchers.containsInAnyOrder(expectedLanguageNames)));
  }

  @Test
  public void test4() throws Exception {
    // Given
    Integer productId = 35455;
    Integer brandId = 1;
    String applicationDate = "2020-06-15-10.00.00";

    // When
    final ResultActions result = mvc.perform(
        MockMvcRequestBuilders.get("/api/prices")
            .param("productId", productId.toString())
            .param("brandId", brandId.toString())
            .param("applicationDate", applicationDate)
            .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

    // Then
    List<PricesDto> pricesResult = List.of(prices1, prices3);
    final int expectedSize = pricesResult.size();
    final Double[] expectedLanguageNames = pricesResult.stream().map(PricesDto::getPrice).map(BigDecimal::doubleValue)
        .collect(Collectors.toList()).toArray(new Double[pricesResult.size()]);
    result.andExpect(status().isOk());
    result.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(expectedSize));
    result.andExpect(MockMvcResultMatchers.jsonPath("$[*].price", Matchers.containsInAnyOrder(expectedLanguageNames)));
  }

  @Test
  public void test5() throws Exception {
    // Given
    Integer productId = 35455;
    Integer brandId = 1;
    String applicationDate = "2020-06-16-21.00.00";

    // When
    final ResultActions result = mvc.perform(
        MockMvcRequestBuilders.get("/api/prices")
            .param("productId", productId.toString())
            .param("brandId", brandId.toString())
            .param("applicationDate", applicationDate)
            .accept(MimeTypeUtils.APPLICATION_JSON_VALUE));

    // Then
    List<PricesDto> pricesResult = List.of(prices1, prices4);
    final int expectedSize = pricesResult.size();
    final Double[] expectedLanguageNames = pricesResult.stream().map(PricesDto::getPrice).map(BigDecimal::doubleValue)
        .collect(Collectors.toList()).toArray(new Double[pricesResult.size()]);
    result.andExpect(status().isOk());
    result.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(expectedSize));
    result.andExpect(MockMvcResultMatchers.jsonPath("$[*].price", Matchers.containsInAnyOrder(expectedLanguageNames)));
  }
}
