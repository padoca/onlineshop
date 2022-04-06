package es.example.onlineshop.repository.entity;

import java.io.Serializable;
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
@Table(name = "product")
public class Product implements Serializable {

  @Id
  private Integer id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "IN_STOCK")
  private Boolean inStock;

  @Column(name = "CREATION_DATE")
  private Timestamp creationDate;

  @Column(name = "ATTRIBUTES")
  private String attributes;

}
