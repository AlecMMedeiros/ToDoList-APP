package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Alecsandro Medeiros
 */
public class ProjectController {

    public void save(Project project) throws SQLException {
        String sql = "INSERT INTO PROJECTS ("
                + "projectName,"
                + "projectDescription,"
                + "createdAt,"
                + "updatedAt ) VALUES (?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getProjectDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();

        } catch (SQLException exception) {
            throw new SQLException("Connection Error On Saving Project " + exception.getMessage(), exception);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Project project) throws SQLException {
        String sql = "UPDATE PROJECTS SET "
                + "projectName = ?, "
                + "projectDescription = ?, "              
                + "updatedAt = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();

            statement = connection.prepareStatement(sql);

            statement.setString(1, project.getProjectName());
            statement.setString(2, project.getProjectDescription());           
            statement.setDate(3, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(4, project.getId());
            statement.execute();

        } catch (SQLException exception) {
            throw new SQLException("Connection Error On Updating Project " + exception.getMessage(), exception);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void removeById(int projectid) throws SQLException {

        String sql = "DELETE FROM PROJECTS WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Connecting with DB
            connection = ConnectionFactory.getConnection();
            //Preparing query
            statement = connection.prepareStatement(sql);

            statement.setInt(1, projectid);
            statement.execute();

        } catch (SQLException exception) {
            throw new SQLException("Connection Error On Deleting Project: " + exception.getMessage(), exception);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Project> getAll() throws SQLException {
        String sql = "SELECT * FROM PROJECTS";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        //Creating task list 
        List<Project> projectsList = new ArrayList<>();

        try {
            //Connecting with DB
            connection = ConnectionFactory.getConnection();
            //Preparing query
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Project project = new Project();
                project.setId(resultSet.getInt("id"));
                project.setProjectName(resultSet.getString("projectName"));
                project.setProjectDescription(resultSet.getString("projectDescription"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));

                projectsList.add(project);
            }

        } catch (SQLException exception) {
            throw new SQLException("Connection Error On Listing Tasks: " + exception.getMessage(), exception);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        return projectsList;
    }
}
