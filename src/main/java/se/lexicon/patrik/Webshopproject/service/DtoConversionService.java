package se.lexicon.patrik.Webshopproject.service;

import se.lexicon.patrik.Webshopproject.dto.ProductDto;
import se.lexicon.patrik.Webshopproject.dto.ProductForm;
import se.lexicon.patrik.Webshopproject.model.Product;

import java.util.Collection;

public interface DtoConversionService {
    Product dtoToProduct(ProductDto dto);
    ProductDto productToDto(Product product);
    Collection<ProductDto> productToDto(Collection<Product> products);

    Product ProductFormToProduct(ProductForm dto);
}
