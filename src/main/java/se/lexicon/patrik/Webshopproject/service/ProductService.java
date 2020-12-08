package se.lexicon.patrik.Webshopproject.service;

import se.lexicon.patrik.Webshopproject.dto.ProductDto;
import se.lexicon.patrik.Webshopproject.dto.ProductForm;
import se.lexicon.patrik.Webshopproject.model.Product;

import java.util.Collection;

public interface ProductService {
    Collection<ProductDto> findAll();
    ProductDto findByProductId(String productId);
    Collection<ProductDto> findByProductName(String productName);
    ProductDto createByForm(ProductForm productForm);
    ProductDto update(ProductForm productForm);
    
}
