package se.lexicon.patrik.Webshopproject.data.product;

import se.lexicon.patrik.Webshopproject.model.Product;

import java.util.Collection;
import java.util.Optional;

public interface Products {
    Product create (Product product);
    Collection<Product> findAll();
    Product findById(int productId);
    Collection<Product> findByName(String productName);
    Product update(Product product);
    boolean deleteById(int productId);
}
