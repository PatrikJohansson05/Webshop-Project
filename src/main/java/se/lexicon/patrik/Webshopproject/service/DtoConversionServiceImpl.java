package se.lexicon.patrik.Webshopproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.patrik.Webshopproject.dto.ProductDto;
import se.lexicon.patrik.Webshopproject.model.Product;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional(readOnly = true)
public class DtoConversionServiceImpl implements DtoConversionService {

    @Override
    public Product dtoToProduct(ProductDto dto) {
        return new Product(dto.getProductId(), dto.getProductName(), dto.getPrice());
    }

    @Override
    public ProductDto productToDto(Product product) {
        return new ProductDto(product.getProductId(), product.getProductName(), product.getPrice());
    }

    @Override
    public Collection<ProductDto> productToDto(Collection<Product> products){
        if(products == null) products = new ArrayList<>();
        Collection<ProductDto> productDtos = new ArrayList<>();
        for (Product product: products) {
            productDtos.add(productToDto(product));
        }

        return productDtos;
    }

}
