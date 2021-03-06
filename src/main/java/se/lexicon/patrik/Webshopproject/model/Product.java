package se.lexicon.patrik.Webshopproject.model;

import org.hibernate.annotations.GenericGenerator;
import se.lexicon.patrik.Webshopproject.dto.ProductDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")

    private String productId;
    private String productName;
    private String price;

    public Product(String productId, String productName, String price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public Product(String productName, String price){
        this.productName = productName;
        this.price = price;
    }

    public Product() {
    }
    public void setProductId(String productId){
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productId, product.productId) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
