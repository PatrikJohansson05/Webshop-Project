package se.lexicon.patrik.Webshopproject.dto;

public class ProductForm {
    private String productId;
    private String productName;
    private String price;

    public ProductForm(String productId, String productName, String price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public ProductForm(){
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
}
