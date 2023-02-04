/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author alecm
 */
public class ValidateFields {

    private final String taskName;
    private final String taskDescription;
    private final String taskComments;
    private final String taskDeadline;

    public ValidateFields(String taskName, String taskDescription, String taskComments, String taskDeadline) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskComments = taskComments;
        this.taskDeadline = taskDeadline;
    }
 

    public boolean validateAllField() {
        return !(this.taskName.isEmpty() || this.taskDescription.isEmpty() || this.taskComments.isEmpty() || this.taskDeadline.isEmpty());
    }
}
