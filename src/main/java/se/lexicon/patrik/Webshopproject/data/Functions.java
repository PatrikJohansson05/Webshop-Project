package se.lexicon.patrik.Webshopproject.data;

import se.lexicon.patrik.Webshopproject.data.product.Products;
import se.lexicon.patrik.Webshopproject.model.Product;

import java.sql.SQLException;
import java.util.Scanner;

public class Functions {

/*

    public static void connection() throws SQLException{
        System.out.println("Database connection established");
        DataSource.getConnection();
    }

    public static String input(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void createProduct() {
        System.out.print("Enter product name:");
        String pName = input();
        System.out.print("Enter price:");
        String price = input();

        Product product = new Product(pName, price);
        runProduct.create(product);
        System.out.println("Product added to database");
    }



    public static void updateProduct() {
        System.out.print("Enter product ID to change:");
        int findId = Integer.parseInt(input());
        Product product = runProduct.findById(findId);
        System.out.println("Current name: "+product.getProductName());
        System.out.println("Enter new name: ");
        String name = input();
        System.out.println("Current price: "+product.getPrice());
        System.out.println("Enter new price: ");
        String price = input();

        int resultNumber = Integer.parseInt(String.valueOf(product));
        if(runProduct.findById(resultNumber) == null){
            System.out.println("No matches on that number");
        }
    }

    public static void findProductById(int productId){
        if(runProduct.findById(productId) != null){
            System.out.println(runProduct.findById(productId));
        } else {
            System.out.println("Unable to find product by ID");
        }
    }

*/
}
