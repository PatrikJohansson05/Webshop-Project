package se.lexicon.patrik.Webshopproject.data.user;
import se.lexicon.patrik.Webshopproject.data.DataSource;
import se.lexicon.patrik.Webshopproject.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class UsersRepository implements Users{

    @Override
    public User create(User user){
        if (user.getUserId() !=0)
            throw new IllegalArgumentException("Invalid Entry");

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet keySet = null;
        try{
            connection = DataSource.getConnection();
            statement = connection.prepareStatement("INSERT INTO user (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getname());
            statement.execute();

            keySet = statement.getGeneratedKeys();
            while(keySet.next()){
                user = new User(
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

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException{
        User user = new User(
                resultSet.getInt("user_id"),
                resultSet.getString("name"),
                resultSet.getString("address"),
                resultSet.getString("email")
        );
        return user;
    }

    @Override
    public Collection<User> findAll() {
        Collection<User> result = new ArrayList<>();
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user");
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
    public Optional<User> findById(int personId){
        Optional<User> result = Optional.empty();

        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = createFindByIdStatement(connection, "SELECT * FROM user WHERE user_id = ?", personId);
            ResultSet resultSet = statement.executeQuery()){
            while(resultSet.next()){
                User person = createUserFromResultSet(resultSet);
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
    public Collection<User> findByName(String name) {
        Collection<User> result = new ArrayList<>();
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = createFindByNameStatement(connection, "SELECT * FROM user WHERE name LIKE ?", name);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = createUserFromResultSet(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Changes needed
    @Override
    public User update(User user){
        if (user.getUserId() == 0)
            throw new IllegalArgumentException("Invalid Entry");
        try(Connection connection = DataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE user SET name = ?,  WHERE user_id =?")){
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
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE user_id = ?")){
            statement.setInt(1, userId);
            statement.execute();
            delete = true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return delete;
    }
}
