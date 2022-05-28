package uz.pdp.dao;

import uz.pdp.model.Result;
import uz.pdp.model.User;

import java.sql.*;

import static uz.pdp.util.DbConnect.getConnection;

public class UserDao {

    public static Result registerUser(User user){

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            String checkUsername = "select count(*) from users where username = '"+user.getUsername()+"'";
            ResultSet resultSet = statement.executeQuery(checkUsername);
            int userCount = 0;
            while (resultSet.next()) {
                userCount = resultSet.getInt(1);
            }

            if (userCount>0){
                return new Result("This user already exist", false);
            }

            String addQuery = "INSERT into users(username, first_name, last_name, password)\n" +
                    " VALUES('"+user.getUsername()+"', '"+user.getFirst_name()+"', '"+user.getLast_name()+"', '"+user.getPassword()+"')";

            statement.execute(addQuery);
            return new Result("Successfully registered!", true);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Result("Error in server", false);
    }



    public static User getUserByUsername(String username, String password){
        User user = new User();

        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM\n" +
                    "users where username = '"+username+"' and password = '"+password+"'");

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String newUsername = resultSet.getString("username");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String newPassword = resultSet.getString("password");

                user.setId(id);
                user.setUsername(newUsername);
                user.setFirst_name(first_name);
                user.setLast_name(last_name);
                user.setPassword(newPassword);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    public static User getUserByUsername(String username){
        User user = new User();

        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM\n" +
                    "users where username = '"+username+"'");

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String newUsername = resultSet.getString("username");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String newPassword = resultSet.getString("password");

                user.setId(id);
                user.setUsername(newUsername);
                user.setFirst_name(first_name);
                user.setLast_name(last_name);
                user.setPassword(newPassword);
                return user;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }






}
