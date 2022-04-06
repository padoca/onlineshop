package es.example.onlineshop.service;

import es.example.onlineshop.dto.ProductDto;
import es.example.onlineshop.repository.ProductRepository;
import es.example.onlineshop.repository.entity.Product;
import es.example.onlineshop.service.mapper.ProductResponseEntityMapper;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private ProductResponseEntityMapper productResponseEntityMapper;

  public List<ProductDto> getAllProducts(final int pageNo, final int pageSize) {
    Sort sort = Sort.by(Sort.Direction.ASC, "name", "attributes");
    Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
    List<Product> result = new ArrayList<>();
    productRepository.findAll(pageable).forEach(result::add);
    return result.stream().map(productResponseEntityMapper::toDto).collect(Collectors.toList());
  }

  public Optional<ProductDto> getProductById(final Integer productId) {
    return productRepository.findById(productId).map(productResponseEntityMapper::toDto);
  }

  public ProductDto createProduct(final ProductDto productDto) {
    validate(productDto);
    Product Products = null;
    try {
      Products = productResponseEntityMapper.toEntity(productDto);
    } catch (ParseException e) {
      log.error("Error to parse dates of ProductsDto: {}", productDto.getCreationDate());
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
    return productResponseEntityMapper.toDto(productRepository.save(Products));
  }

  public void deleteProduct(final Integer id) {
    productRepository.deleteById(id);
  }

  private void validate(final ProductDto product) {
    if (product == null || product.getName() == null) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
  }
}
