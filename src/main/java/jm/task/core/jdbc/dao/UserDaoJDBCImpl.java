package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Connection connection = getConnection(); Statement statement = getConnection().createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS USERS " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            statement.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = getConnection();Statement statement = getConnection().createStatement()) {

            String sql = "DROP TABLE IF EXISTS USERS";

            statement.executeUpdate(sql);
            System.out.println("Table deleted in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = getConnection(); Statement statement = getConnection().createStatement()) {
            System.out.println("Inserting records into the table USERS");

            String sql = "INSERT INTO USERS(name, lastname, age) VALUE ('"+name+"','"+lastName+"','"+age+"')";
            statement.executeUpdate(sql);

            System.out.println("User with name: " + name + " was added into table users");

//            PreparedStatement preparedStatement = null;
//            String sql = "INSERT INTO USERS (name, lastname, age) VALUES(?, ?, ?)";
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setByte(3, age);
//            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Connection connection = getConnection()) {

            String sql = "DELETE FROM USERS " +
                    "WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM USERS";

        //Statement statement = null;

        try (Statement statement = getConnection().createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                usersList.add(user);
            }
            System.out.println(usersList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        try(Connection connection = getConnection();Statement statement = getConnection().createStatement()) {

            String sql = "TRUNCATE TABLE USERS";


            statement.executeUpdate(sql);
            System.out.println("Cleared table in given database...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
