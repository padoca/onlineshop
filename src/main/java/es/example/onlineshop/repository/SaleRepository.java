package es.example.onlineshop.repository;

import es.example.onlineshop.repository.entity.Sale;
import es.example.onlineshop.repository.entity.compositeprimarykey.SaleId;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, SaleId> {

  List<Sale> findByUserId(Integer userId);
}
