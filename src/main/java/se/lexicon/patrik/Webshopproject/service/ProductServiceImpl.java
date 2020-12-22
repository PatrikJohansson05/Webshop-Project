package se.lexicon.patrik.Webshopproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.patrik.Webshopproject.data.repository.ProductRepository;
import se.lexicon.patrik.Webshopproject.dto.ProductDto;
import se.lexicon.patrik.Webshopproject.dto.ProductForm;
import se.lexicon.patrik.Webshopproject.model.Product;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService{
    ProductRepository productRepository;
    DtoConversionService converter;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, DtoConversionService converter){
        this.productRepository = productRepository;
        this.converter = converter;
    }

    @Override
    public Collection<ProductDto> findAll(){
        return converter.productToDto((Collection<Product>) productRepository.findAll());
    }

    @Override
    public ProductDto findByProductId(String productId){
        return converter.productToDto(productRepository.findById(productId).get());
    }

    @Override
    public Collection<ProductDto> findByProductName(String productName){
        return converter.productToDto(productRepository.findByProductName(productName));
    }

    @Override
    public ProductDto createByForm(ProductForm productForm) {
        if(productForm.getProductId() != null){
            throw new IllegalArgumentException("Invalid Product ID: ID should not be specified at creation.");
        }
        return converter.productToDto(productRepository.save(converter.ProductFormToProduct(productForm)));
    }

    @Override
    public ProductDto update(ProductForm productForm){
        if (productForm.getProductId() == null){
            throw new IllegalArgumentException("Product has a invalid ID: " + productForm.getProductId());
        }
        Product product = productRepository.findById(productForm.getProductId()).orElseThrow(() -> new EntityNotFoundException("Could not find Product with ID: " + productForm.getProductId()));

        Product updated = converter.ProductFormToProduct(productForm);

        product.setProductName(updated.getProductName());
        product.setPrice(updated.getPrice());

        return converter.productToDto(productRepository.save(product));
    }


}
