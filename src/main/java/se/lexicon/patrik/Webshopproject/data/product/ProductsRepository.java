package se.lexicon.patrik.Webshopproject.data.product;

import se.lexicon.patrik.Webshopproject.data.DataSource;
import se.lexicon.patrik.Webshopproject.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ProductsRepository implements Products {

    @Override
    public Product create(Product product){
        if (product.getProductId() !=0)
            throw new IllegalArgumentException("Invalid Entry");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet keySet = null;
        try{
            connection = DataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO product (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getProductName());
            statement.execute();

            keySet = statement.getGeneratedKeys();
            while(keySet.next()){
                product = new Product(
                        keySet.getInt(1),
                        product.getProductName(),
                        product.getPrice()
                );
            }
        }catch (SQLException exception){
            exception.printStackTrace();
        }finally {
            try{
                if(keySet != null){
                    keySet.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                exception.printStackTrace();
            }
        }
        return product;
    }

    private Product createProductFromResultSet(ResultSet resultSet) throws SQLException{
        Product product = new Product(
                resultSet.getInt("product_id"),
                resultSet.getString("name"),
                resultSet.getString("price")
        );
        return product;
    }

    @Override
    public Collection<Product> findAll() {
        Collection<Product> result = new ArrayList<>();
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                result.add(createProductFromResultSet(resultSet));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<Product> findById(int productId){
        Optional<Product> result = Optional.empty();

        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = createFindByIdStatement(connection, "SELECT * FROM product WHERE product_id = ?", productId);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                Product person = createProductFromResultSet(resultSet);
                result = Optional.of(person);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private PreparedStatement createFindByIdStatement(Connection connection, String sql, int productId) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, productId);
        return statement;
    }

    private PreparedStatement createFindByNameStatement(Connection connection, String sql, String productName) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%".concat(productName).concat("%"));
        return statement;
    }

    @Override
    public Collection<Product> findByName(String productName) {
        Collection<Product> result = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = createFindByNameStatement(connection, "SELECT * FROM user WHERE name LIKE ?", productName);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = createProductFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Product update(Product product){
        if (product.getProductId() == 0)
            throw new IllegalArgumentException("Invalid Entry");
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE product SET name = ?,  WHERE product_id =?")){
            statement.setString(1, product.getProductName());
            statement.setInt(3, product.getProductId());
            statement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean deleteById(int productId){
        boolean delete = false;
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE product_id = ?")){
            statement.setInt(1, productId);
            statement.execute();
            delete = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return delete;
    }
}
