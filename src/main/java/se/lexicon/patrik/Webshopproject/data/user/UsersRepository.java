package se.lexicon.patrik.Webshopproject.data.user;
import se.lexicon.patrik.Webshopproject.data.DataSource;
import se.lexicon.patrik.Webshopproject.model.WebUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UsersRepository implements Users{

    @Override
    public WebUser create(WebUser user){
        if (user.getUserId() !=0)
            throw new IllegalArgumentException("Invalid Entry");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet keySet = null;
        try{
            connection = DataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO webUser (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getname());
            statement.execute();

            keySet = statement.getGeneratedKeys();
            while(keySet.next()){
                user = new WebUser(
                        keySet.getInt(1),
                        user.getname(),
                        user.getEmail(),
                        user.getAddress()
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
        return user;
    }

    private WebUser createUserFromResultSet(ResultSet resultSet) throws SQLException{
        WebUser user = new WebUser(
                resultSet.getInt("user_id"),
                resultSet.getString("name"),
                resultSet.getString("address"),
                resultSet.getString("email")
        );
        return user;
    }

    @Override
    public Collection<WebUser> findAll() {
        Collection<WebUser> result = new ArrayList<>();
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM webUser");
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                result.add(createUserFromResultSet(resultSet));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<WebUser> findById(int personId){
        Optional<WebUser> result = Optional.empty();

        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = createFindByIdStatement(connection, "SELECT * FROM webUser WHERE user_id = ?", personId);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                WebUser person = createUserFromResultSet(resultSet);
                result = Optional.of(person);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    private PreparedStatement createFindByIdStatement(Connection connection, String sql, int userId) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        return statement;
    }

    private PreparedStatement createFindByNameStatement(Connection connection, String sql, String name) throws SQLException{
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "%".concat(name).concat("%"));
        return statement;
    }

    @Override
    public Collection<WebUser> findByName(String name) {
        Collection<WebUser> result = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = createFindByNameStatement(connection, "SELECT * FROM webUser WHERE name LIKE ?", name);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                WebUser user = createUserFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Changes needed
    @Override
    public WebUser update(WebUser user){
        if (user.getUserId() == 0)
            throw new IllegalArgumentException("Invalid Entry");
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE webUser SET name = ?,  WHERE user_id =?")){
            statement.setString(1, user.getname());
            statement.setInt(3, user.getUserId());
            statement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteById(int userId){
        boolean delete = false;
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM webUser WHERE user_id = ?")){
            statement.setInt(1, userId);
            statement.execute();
            delete = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return delete;
    }
}
