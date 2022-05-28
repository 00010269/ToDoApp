package uz.pdp.dao;

import uz.pdp.model.Task;
import uz.pdp.util.DbConnect;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static uz.pdp.util.DbConnect.getConnection;

public class TaskDao {

    public static List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();

        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from tasks");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setTitle(resultSet.getString(2));
                task.setDescription(resultSet.getString(3));
                task.setStatus(resultSet.getBoolean(4));
                Timestamp deadline = resultSet.getTimestamp(5);
                if (deadline != null){
                    task.setDeadline(deadline.toLocalDateTime());
                }
                task.setCreatedAt(resultSet.getTimestamp(6).toLocalDateTime());
                task.setCreatedAt(resultSet.getTimestamp(7).toLocalDateTime());
                task.setUserId(resultSet.getInt(8));

                taskList.add(task);
            }
            return taskList;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return taskList;
        }
    }



// for pageable
    public static List<Task> getAllTasksFromTo(int start, int total) {
        List<Task> taskList = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT title, description, deadline\n" +
                            "FROM tasks\n" +
                            "limit "+total+" offset "+(start-1));

            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setTitle(resultSet.getString(2));
                task.setDescription(resultSet.getString(3));
                task.setStatus(resultSet.getBoolean(4));
                Timestamp deadline = resultSet.getTimestamp(5);
                if (deadline != null){
                    task.setDeadline(deadline.toLocalDateTime());
                }
                task.setCreatedAt(resultSet.getTimestamp(6).toLocalDateTime());
                task.setCreatedAt(resultSet.getTimestamp(7).toLocalDateTime());
                task.setUserId(resultSet.getInt(8));
                taskList.add(task);
            }
            resultSet.close();
            statement.close();
            return taskList;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return taskList;
        }
    }





    public static Task getTaskById(int id) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from tasks where id = " + id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt(1));
                task.setTitle(resultSet.getString(2));
                task.setDescription(resultSet.getString(3));
                task.setStatus(resultSet.getBoolean(4));
                Timestamp deadline = resultSet.getTimestamp(5);
                if (deadline != null){
                    task.setDeadline(deadline.toLocalDateTime());
                }
                task.setCreatedAt(resultSet.getTimestamp(6).toLocalDateTime());
                task.setCreatedAt(resultSet.getTimestamp(7).toLocalDateTime());
                task.setUserId(resultSet.getInt(8));
                return task;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static boolean editTask(int id, String title, String description, LocalDateTime deadline){

        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update tasks\n" +
                    "set title = '"+title+"',\n" +
                    " description = '"+description+"',\n" +
                    "    deadline = '"+deadline+"'\n" +
                    " where id = " + id);

            preparedStatement.execute();
            preparedStatement.close();
        return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public static boolean deleteTaskById(int id){
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from tasks where id = " + id);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean addNewTasks(String title, String description, boolean status, LocalDateTime deadline, int userId){
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into tasks (title, description, status, deadline, \"userId\")\n" +
                            "VALUES ('"+title+"', '"+description+"', "+status+", '"+deadline+"', "+userId+")");
             preparedStatement.execute();
             preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean addNewTasks(String title, String description, boolean status, int userId){
        Connection connection = getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into tasks (title, description, status, deadline, \"userId\")\n" +
                            "VALUES ('"+title+"', '"+description+"', "+status+", "+userId+")");
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static int getAllTasksCount() {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT count(*) as countTask\n" +
                    "FROM tasks");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("countTask");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }









}
