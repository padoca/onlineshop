package es.example.onlineshop.repository.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "prices")
public class Prices implements Serializable {

  @Id
  private Integer id;

  @Column(name = "PRODUCT_ID")
  private Integer productId;

  @Column(name = "BRAND_ID")
  private Integer brandId;

  @Column(name = "START_DATE")
  private Timestamp startDate;

  @Column(name = "END_DATE")
  private Timestamp endDate;

  @Column(name = "PRICE_LIST")
  private Integer priceList;

  @Column(name = "PRIORITY")
  private Integer priority;

  @Column(name = "PRICE")
  private BigDecimal price;

  @Column(name = "CURR")
  private String currency;

}
