package es.example.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import es.example.onlineshop.service.rest.dto.UserDto;
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
public class SalesDto implements Serializable {

  private ProductDto product;

  private UserDto user;

  private Integer amount;

}
