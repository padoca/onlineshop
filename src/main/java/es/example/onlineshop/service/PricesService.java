package es.example.onlineshop.service;

import es.example.onlineshop.dto.PricesDto;
import es.example.onlineshop.repository.PricesRepository;
import es.example.onlineshop.repository.entity.Prices;
import es.example.onlineshop.service.mapper.PricesResponseEntityMapper;
import es.example.onlineshop.util.Utils;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
public class PricesService {

  @Autowired
  private PricesRepository pricesRepository;

  @Autowired
  private PricesResponseEntityMapper pricesResponseEntityMapper;

  public List<PricesDto> getAllPrices() {
    List<Prices> result = new ArrayList<>();
    pricesRepository.findAll().forEach(result::add);
    return result.stream().map(pricesResponseEntityMapper::toDto).collect(Collectors.toList());
  }

  public List<PricesDto> getPricesFilter(final String applicationDate, final Integer productId, final Integer brandId) {
    Timestamp applicationDateTimestamp;
    try {
      applicationDateTimestamp = applicationDate != null ? Utils.toTimestamp(applicationDate) : null;
    } catch (ParseException e) {
      log.error("Error to parse applicationDate " + applicationDate);
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
    List<Prices> result = pricesRepository.findByFilter(applicationDateTimestamp, productId, brandId);
    return result.stream().map(pricesResponseEntityMapper::toDto).collect(Collectors.toList());
  }

  public PricesDto createPrices(final PricesDto pricesDto) {
    validate(pricesDto);
    Prices prices = null;
    try {
      prices = pricesResponseEntityMapper.toEntity(pricesDto);
    } catch (ParseException e) {
      log.error("Error to parse dates of pricesDto: {}, {}", pricesDto.getStartDate(), pricesDto.getEndDate());
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
    return pricesResponseEntityMapper.toDto(pricesRepository.save(prices));
  }

  public void deletePrices(final Integer id) {
    pricesRepository.deleteById(id);
  }

  private void validate(final PricesDto prices) {
    if (prices == null || prices.getBrandId() == null || prices.getProductId() == null || prices.getPriceList() == null
        || prices.getEndDate() == null || prices.getPrice() == null) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
  }
}
