/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;

import javax.swing.JOptionPane;

public class Tasks {

    private static final String statusType1 = "To Do";
    private static final String statusType2 = "Doing";
    private static final String statusType3 = "Done";
    private static boolean taskAdded=false;//Serves as a flag to track if tasks were added

    private String[] taskName;
    private String[] taskDescription;
    private String[] taskDeveloper;
    private String[] taskID;
    private String[] taskStatus;
    private int[] taskNumber;
    private int[] taskDuration;

    public void displayMenu() {
        String[] options = {"Option 1) Add Tasks", "Option 2) Show report", "Option 3) Quit"};
        int choice;

        String  menu="";
        for(int i=0; i < options.length; i++)
        {
        menu+=options[i] + "\n";
        }
        JOptionPane.showMessageDialog(null, menu, "Menu Options", JOptionPane.PLAIN_MESSAGE);
        choice = Integer.parseInt(JOptionPane.showInputDialog("Enter your choice: "));

switch (choice) {
                case 1:
                    JOptionPane.showMessageDialog(null, "You have decided to add tasks");
                    addTasks();
                    break;
                case 2:
                    if (taskAdded) {
                        displayFeatures();
                    } else {
                        JOptionPane.showMessageDialog(null, "Tasks have not been added yet. Please add tasks first.");
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid Choice. Please enter a number between 1 and 3");
                    break;
                }
        }
   

    public void addTasks() {
        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks you wish to enter: "));

        taskName = new String[numTasks];
        taskDescription = new String[numTasks];
        taskDeveloper = new String[numTasks];
        taskID = new String[numTasks];
        taskStatus = new String[numTasks];
        taskNumber = new int[numTasks];
        taskDuration = new int[numTasks];

        for (int i = 0; i < numTasks; i++) {
            taskName[i] = JOptionPane.showInputDialog("Enter the name of task " + (i + 1));
            taskDescription[i] = promptForTaskDescription();
            taskDeveloper[i] = JOptionPane.showInputDialog("Enter Developer name");
            taskDuration[i] = Integer.parseInt(JOptionPane.showInputDialog("Enter the duration of the task"));
            taskID[i] = createTaskID(i, taskName[i], taskDeveloper[i]);
            taskStatus[i] = promptForTaskStatus();
            
            JOptionPane.showMessageDialog(null, "Task successfully captured:\n" + printTaskDetails(i));
        }

        JOptionPane.showMessageDialog(null, "The total number of hours across all tasks is: " + returnTotalHours() + " hrs");
        displayMenu();
    }

    private String promptForTaskDescription() {
        String description;
        do {
            description = JOptionPane.showInputDialog("Enter task Description (max 50 characters)");
            if (description.isEmpty() || description.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
            }
        } while (description.isEmpty() || description.length() > 50);
        return description;
    }

    private String promptForTaskStatus() {
        String status;
        do {
            status = JOptionPane.showInputDialog("Select task status:\n1) " + statusType1 + "\n2) " + statusType2 + "\n3) " + statusType3);
        } while (!status.equals("1") && !status.equals("2") && !status.equals("3"));

        return switch (status) {
            case "1" -> statusType1;
            case "2" -> statusType2;
            case "3" -> statusType3;
            default -> null;
        }; // This case should not occur due to validation loop
    }

    private String createTaskID(int index, String name, String developer) {
        return name.substring(0, 2).toUpperCase() + ":" + index + ":" + developer.substring(developer.length() - 3);
    }

    private String printTaskDetails(int index) {
        return "Task Number: " + (index + 1) + "\nName: " + taskName[index] + "\nDescription: " + taskDescription[index]
                + "\nDeveloper: " + taskDeveloper[index] + "\nTask ID: " + taskID[index] + "\nDuration: " + taskDuration[index] + " hrs";
    }

    private int returnTotalHours() {
        int total = 0;
        for (int duration : taskDuration) {
            total += duration;
        }
        return total;
    }
    //Main method for displaying the programs newly added features
    public void displayFeatures() {
        String[] features = {"1. Display done Tasks", "2. Display the Longest task", "3. Search for a specific task",
                "4. Search for specific Developer's tasks", "5. Delete a task", "6. Display report","7. Quit"};
        //choice variable used to help access presented tasks
        int choice;

        String featureMenu = String.join("\n", features);
        JOptionPane.showMessageDialog(null, featureMenu, "Features", JOptionPane.PLAIN_MESSAGE);
        choice = Integer.parseInt(JOptionPane.showInputDialog("Enter your choice: "));
        //switch function used in place of multiple if statements
        switch (choice) {
            case 1 -> displayDoneTasks();
            case 2 -> displayLongestTask();
            case 3 -> searchForTask();
            case 4 -> searchDeveloperTasks();
            case 5 -> deleteTask();
            case 6 -> displayReport();
            case 7 -> System.exit(0);
            default -> JOptionPane.showMessageDialog(null, "Invalid Choice. Please enter a number between 1 and 6");
        }
    }
    //Method for Displaying Done Tasks
    public void displayDoneTasks() {
        for (int i = 0; i < taskStatus.length; i++) {
            if (taskStatus[i].equals(statusType3)) {
                JOptionPane.showMessageDialog(null, "Task: " + taskName[i] + "\nDeveloper: " + taskDeveloper[i] + "\nDuration: " + taskDuration[i] + " hrs");
            }
        }
    }
    //Method for Displaying the longest task
    public void displayLongestTask() {
        int longestIndex = 0;
        for (int i = 1; i < taskDuration.length; i++) {
            if (taskDuration[i] > taskDuration[longestIndex]) {
                longestIndex = i;
            }
        }
        JOptionPane.showMessageDialog(null, "Longest task: " + taskName[longestIndex] + ", " + taskDuration[longestIndex] + " hrs, developed by " + taskDeveloper[longestIndex]);
    }
    //Method for searching for a task
    public void searchForTask() {
        String searchTask = JOptionPane.showInputDialog("Enter the name of the task you wish to search for:");
        boolean found = false;
        for (int i = 0; i < taskName.length; i++) {
            if (taskName[i].equalsIgnoreCase(searchTask)) {
                JOptionPane.showMessageDialog(null, "Task found:\n" + printTaskDetails(i));
                found = true;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }
    //Method for searching for a developers tasks
    public void searchDeveloperTasks() {
        String searchDev = JOptionPane.showInputDialog("Enter the name of the Developer whose tasks you want to search for:");
        boolean found = false;
        for (int i = 0; i < taskDeveloper.length; i++) {
            if (taskDeveloper[i].equalsIgnoreCase(searchDev)) {
                JOptionPane.showMessageDialog(null, "Task Name: " + taskName[i] + "\nTask Status: " + taskStatus[i]);
                found = true;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "No tasks found for developer: " + searchDev);
        }
    }
    //Method for completing delete task
    public void deleteTask() {
        String searchTask = JOptionPane.showInputDialog("Enter the name of the task you want to delete:");
        boolean deleted = false;
        for (int i = 0; i < taskName.length; i++) {
            if (taskName[i].equalsIgnoreCase(searchTask)) {
                taskName[i] = null; // Marking for deletion
                deleted = true;
                JOptionPane.showMessageDialog(null, "Task '" + searchTask + "' deleted successfully.");
                break; // Exit loop after first deletion
            }
        }
        if (!deleted) {
            JOptionPane.showMessageDialog(null, "Task not found for deletion.");
        }
    }
    //Method for Displaying the report
    public void displayReport() {
        StringBuilder report = new StringBuilder("Task Report:\n");
        for (int i = 0; i < taskName.length; i++) {
            report.append(printTaskDetails(i)).append("\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void main(String[] args) {
        Tasks taskManager = new Tasks();
        taskManager.displayMenu();
    }
}
