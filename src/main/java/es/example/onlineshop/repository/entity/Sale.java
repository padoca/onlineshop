package es.example.onlineshop.repository.entity;

import es.example.onlineshop.repository.entity.compositeprimarykey.SaleId;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sale")
@IdClass(SaleId.class)
public class Sale implements Serializable {

  @Id
  @Column(name = "PRODUCT_ID")
  private Integer productId;

  @Id
  @Column(name = "USER_ID")
  private Integer userId;

  @Column
  private Integer amount;

}
