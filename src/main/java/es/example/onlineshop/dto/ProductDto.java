package es.example.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class ProductDto implements Serializable {

  private Integer id;

  private String name;

  private String attributes;

  private String creationDate;

  private Boolean inStock;

}
