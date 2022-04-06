package es.example.onlineshop.repository;

import es.example.onlineshop.repository.entity.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

}
