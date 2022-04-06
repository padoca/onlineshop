package es.example.onlineshop.repository.entity.compositeprimarykey;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SaleId implements Serializable {

  private Integer productId;

  private Integer userId;

}
