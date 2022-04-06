package es.example.onlineshop.service.mapper;

import es.example.onlineshop.dto.PricesDto;
import es.example.onlineshop.repository.entity.Prices;
import es.example.onlineshop.util.Utils;
import java.text.ParseException;
import org.springframework.stereotype.Component;

@Component
public class PricesResponseEntityMapper {

  public PricesDto toDto(final Prices prices) {
    return PricesDto.builder().productId(prices.getProductId()).brandId(prices.getBrandId())
        .startDate(Utils.formatDate(prices.getStartDate())).endDate(Utils.formatDate(prices.getEndDate())).priceList(prices.getPriceList()).price(prices.getPrice()).build();
  }

  public Prices toEntity(final PricesDto dto) throws ParseException {
    Prices entity = null;

    if (dto != null) {
      entity = new Prices(null, dto.getProductId(), dto.getBrandId(), Utils.toTimestamp(dto.getStartDate()), Utils.toTimestamp(dto.getEndDate()), dto.getPriceList(),
          null, dto.getPrice(), null);
    }

    return entity;
  }
}
