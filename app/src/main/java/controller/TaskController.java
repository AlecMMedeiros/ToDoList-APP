package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Alecsandro Medeiros
 */
public class TaskController {

    public void save(Task task) throws SQLException, ParseException {

        String sql = "INSERT INTO TASKS ("
                + "idProject, "
                + "taskName, "
                + "taskDescription, "
                + "taskCompleted, "
                + "taskComments, "
                + "taskDeadline, "
                + "createdAt, "
                + "updatedAt) VALUES (?, ? , ? , ? , ? , ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Connecting with DB
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getTaskName());
            statement.setString(3, task.getTaskDescription());
            statement.setBoolean(4, task.isTaskCompleted());
            statement.setString(5, task.getTaskComments());
            statement.setDate(6, new java.sql.Date(task.getTaskDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();

        } catch (SQLException exception) {
            throw new SQLException("Connection Error On Saving Task: " + exception.getMessage(), exception);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Task task) throws SQLException, ParseException {

        String sql = "UPDATE TASKS SET "
                + "idProject = ?, "
                + "taskName = ?, "
                + "taskDescription = ?, "
                + "taskCompleted = ?, "
                + "taskComments = ?, "
                + "taskDeadline = ?, "
                + "updatedAt = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Connecting with DB
            connection = ConnectionFactory.getConnection();
            //Preparing query
            statement = connection.prepareStatement(sql);

            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getTaskName());
            statement.setString(3, task.getTaskDescription());
            statement.setBoolean(4, task.isTaskCompleted());
            statement.setString(5, task.getTaskComments());
            statement.setDate(6, (Date) task.getTaskDeadline());
            statement.setDate(7, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(8, task.getId());

            statement.execute();

        } catch (SQLException exception) {
            throw new SQLException("Connection Error On Update Task: " + exception.getMessage(), exception);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void removeById(int taskid) throws SQLException {

        String sql = "DELETE FROM TASKS WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Connecting with DB
            connection = ConnectionFactory.getConnection();
            //Preparing query
            statement = connection.prepareStatement(sql);

            statement.setInt(1, taskid);
            statement.execute();

        } catch (SQLException exception) {
            throw new SQLException("Connection Error On Deleting Task: " + exception.getMessage(), exception);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Task> getAll(int idProject) throws SQLException {
        String sql = "SELECT * FROM TASKS WHERE idProject = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //Creating task list 
        List<Task> taskList = new ArrayList<>();

        try {
            //Connecting with DB
            connection = ConnectionFactory.getConnection();
            //Preparing query
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
            

                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setTaskName(resultSet.getString("taskName"));
                task.setTaskDescription(resultSet.getString("taskDescription"));
                task.setTaskCompleted(resultSet.getBoolean("taskCompleted"));
                task.setTaskComments(resultSet.getString("taskComments"));
                task.setTaskDeadline(resultSet.getDate("taskDeadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));

                taskList.add(task);
            }

        } catch (SQLException exception) {
            throw new SQLException("Connection Error On Listing Tasks: " + exception.getMessage(), exception);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }

        return taskList;

    }
}
