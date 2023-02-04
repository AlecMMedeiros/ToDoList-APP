/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

/**
 *
 * @author alecm
 */
public class TaskTableModel extends AbstractTableModel {

    private final String[] columns = {"Task Name", "Task Description", "Task Completed", "Task Deadline", "Edit Task", "Delete Task"};
    private List<Task> tasks = new ArrayList<>();  

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
     @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }
    
    @Override
    public Class<?> getColumnClass (int columnIdex ) {
        if ( tasks.isEmpty()) {
            return Object.class;
        }
        
        return this.getValueAt(0, columnIdex).getClass();
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIdex) {
        tasks.get(rowIndex).setTaskCompleted((boolean) aValue);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return tasks.get(rowIndex).getTaskName();
            }
            case 1 -> {
                return tasks.get(rowIndex).getTaskDescription();
            }
            case 2 -> {
                return tasks.get(rowIndex).isTaskCompleted();
            }
            case 3-> {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return  simpleDateFormat.format(tasks.get(rowIndex).getTaskDeadline());
            }
            case 4 -> {
                return "";
            }
            case 5 -> {
                return "";
            }
            default -> throw new AssertionError("Data Not Found");
        }
    }

    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }   
    
}
