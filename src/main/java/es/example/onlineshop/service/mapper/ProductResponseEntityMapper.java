package es.example.onlineshop.service.mapper;

import es.example.onlineshop.dto.ProductDto;
import es.example.onlineshop.repository.entity.Product;
import es.example.onlineshop.util.Utils;
import java.text.ParseException;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseEntityMapper {

  public ProductDto toDto(final Product product) {
    return ProductDto.builder().id(product.getId()).name(product.getName()).attributes(product.getAttributes())
        .creationDate(Utils.formatDate(product.getCreationDate())).inStock(product.getInStock()).build();
  }

  public Product toEntity(final ProductDto dto) throws ParseException {
    Product entity = null;

    if (dto != null) {
      entity = new Product(dto.getId(), dto.getName(), dto.getInStock(), Utils.toTimestamp(dto.getCreationDate()), dto.getAttributes());
    }

    return entity;
  }
}
