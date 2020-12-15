package se.lexicon.patrik.Webshopproject.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.patrik.Webshopproject.dto.ProductDto;
import se.lexicon.patrik.Webshopproject.dto.ProductForm;
import se.lexicon.patrik.Webshopproject.service.ProductService;

import java.util.Collection;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    public static final String ALL = "ALL";
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> find(@RequestParam(name = "type", defaultValue = ALL) final String type, @RequestParam(name = "value", defaultValue = ALL) final String value){
        switch (type.toUpperCase().trim()){
            case ALL:
                Collection<ProductDto> found = productService.findAll();
                return found.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(found);

            default: return ResponseEntity.badRequest().body("Not a valid type: " + type);
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> createByForm(@RequestBody ProductForm dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createByForm(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateByForm(@PathVariable String id, @RequestBody ProductForm updated){
        if (!id.equals(updated.getProductId())){
            throw new IllegalArgumentException("Id's need to match");
        }
        return ResponseEntity.ok(productService.update(updated));
    }
}
