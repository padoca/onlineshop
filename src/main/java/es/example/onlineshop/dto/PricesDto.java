package es.example.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class PricesDto implements Serializable {

  private Integer productId;

  private Integer brandId;

  private Integer priceList;

  private String startDate;

  private String endDate;

  private BigDecimal price;

}
