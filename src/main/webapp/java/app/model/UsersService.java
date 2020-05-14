package app.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersService {
    private static final String INSERT_NEW_USER = "insert into users value (null, ?, ?, ?, ?)";
    private static final String GET_USER = "select * from users where login = ? and password = ?";
    private static final String DELETE_USER = "delete from users where id = ?";
    private static final String GET_ALL_USER = "select * from users where admin = false";

    public void add(String name, String login, String password, boolean admin) {
        try {
            PreparedStatement statement = DBManager.getConnection().prepareStatement(INSERT_NEW_USER);
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, password);
            statement.setBoolean(4, admin);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(String login, String password) {
        try {
            PreparedStatement statement = DBManager.getConnection().prepareStatement(GET_USER);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLogin(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setAdmin(resultSet.getBoolean(5));
            }
            if (user.getLogin() == null & user.getPassword() == null)
                return null;
            System.out.println(user);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUsersList() {
        try {
            Statement statement = DBManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USER);
            List<User> usersList = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLogin(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setAdmin(resultSet.getBoolean(5));
                usersList.add(user);
            }
            for (User us: usersList) {
                System.out.println(us);
            }
            return usersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser(int id) {
        try {
            PreparedStatement statement = DBManager.getConnection().prepareStatement(DELETE_USER);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
