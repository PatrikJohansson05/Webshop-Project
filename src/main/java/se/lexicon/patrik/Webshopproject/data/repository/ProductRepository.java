package se.lexicon.patrik.Webshopproject.data.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.patrik.Webshopproject.model.Product;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String>{

    List<Product> findByProductName(String productName);

}
