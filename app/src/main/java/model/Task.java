package model;

import java.util.Date;

/**
 *
 * @author Alecsandro Medeiros
 */
public class Task {

    private int id;
    private int idProject;
    private String taskName;
    private String taskDescription;
    private boolean taskCompleted;
    private String taskComments;
    private Date taskDeadline;
    private Date createdAt;
    private Date updatedAt;

    public Task(
            int id,
            int idProject,
            String taskName,
            String taskDescription,
            boolean taskCompleted,
            String taskComments,
            Date taskDeadline,
            Date createdAt,
            Date updatedAt) {
        this.id = id;
        this.idProject = idProject;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskCompleted = taskCompleted;
        this.taskDeadline = taskDeadline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Task() {
        this.taskCompleted = false;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    public String getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(String taskComments) {
        this.taskComments = taskComments;
    }

    public Date getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", idProject=" + idProject + ", taskName=" + taskName + ", taskDescription=" + taskDescription + ", taskCompleted=" + taskCompleted + ", taskComments=" + taskComments + ", taskDeadline=" + taskDeadline + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
