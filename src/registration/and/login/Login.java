/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration.and.login;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

class Task {
    private static int taskCounter = 0;  // Counter to keep track of the number of tasks created
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerFirstName;
    private String developerLastName;
    private int taskDuration;
    private String taskID;
    private String taskStatus;

    public Task(String taskName, String taskDescription, String developerFirstName, String developerLastName, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskCounter++;
        this.taskDescription = taskDescription;
        this.developerFirstName = developerFirstName;
        this.developerLastName = developerLastName;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
    }

    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    public String createTaskID() {
        return (taskName.substring(0, 2) + ":" + taskNumber + ":" + developerLastName.substring(developerLastName.length() - 3)).toUpperCase();
    }

    public String printTaskDetails() {
        return "Task Status: " + taskStatus + "\n" +
               "Developer: " + developerFirstName + " " + developerLastName + "\n" +
               "Task Number: " + taskNumber + "\n" +
               "Task Name: " + taskName + "\n" +
               "Task Description: " + taskDescription + "\n" +
               "Task ID: " + taskID + "\n" +
               "Task Duration: " + taskDuration + " hours";
    }

    public int getTaskDuration() {
        return taskDuration;
    }
    
    public String getDeveloper() {
        return developerFirstName + " " + developerLastName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public String getTaskID() {
        return taskID;
    }
}

public class Login {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private List<Task> tasks;
    private int totalTaskDuration;

    private List<String> developers;
    private List<String> taskNames;
    private List<String> taskIDs;
    private List<Integer> taskDurations;
    private List<String> taskStatuses;

    public Login(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tasks = new ArrayList<>();
        this.totalTaskDuration = 0;

        this.developers = new ArrayList<>();
        this.taskNames = new ArrayList<>();
        this.taskIDs = new ArrayList<>();
        this.taskDurations = new ArrayList<>();
        this.taskStatuses = new ArrayList<>();
    }

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpperCase = true;
            else if (Character.isDigit(c)) hasNumber = true;
            else if ("@#$!%*".indexOf(c) != -1) hasSpecialChar = true;
        }

        return hasUpperCase && hasNumber && hasSpecialChar;
    }

    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted. Please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.";
        }

        // Registration successful
        return "Account created successfully!";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(username) && enteredPassword.equals(password);
    }

    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName + ". It is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public void displayMenu() {
        boolean quit = false;
        while (!quit) {
            String menu = "Welcome to EasyKanban\n\nPlease choose an option:\n1) Add tasks\n2) Show report\n3) Search Task\n4) Delete Task\n5) Quit";
            String choice = JOptionPane.showInputDialog(menu);

            switch (choice) {
                case "1":
                    addTasks();
                    break;
                case "2":
                    showReport();
                    break;
                case "3":
                    searchTask();
                    break;
                case "4":
                    deleteTask();
                    break;
                case "5":
                    quit = true;
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }

    public void addTasks() {
        try {
            int numTasks = Integer.parseInt(JOptionPane.showInputDialog("How many tasks do you want to enter?"));
            for (int i = 0; i < numTasks; i++) {
                String taskName = JOptionPane.showInputDialog("Enter task name:");
                String taskDescription = JOptionPane.showInputDialog("Enter task description:");

                if (taskDescription.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                    continue;
                }

                String developerFirstName = JOptionPane.showInputDialog("Enter developer's first name:");
                String developerLastName = JOptionPane.showInputDialog("Enter developer's last name:");
                int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (in hours):"));
                String[] taskStatuses = {"To Do", "Doing", "Done"};
                String taskStatus = (String) JOptionPane.showInputDialog(null, "Select task status:", "Task Status", JOptionPane.QUESTION_MESSAGE, null, taskStatuses, taskStatuses[0]);

                Task task = new Task(taskName, taskDescription, developerFirstName, developerLastName, taskDuration, taskStatus);
                tasks.add(task);
                totalTaskDuration += task.getTaskDuration();  // Accumulate total task duration

                developers.add(task.getDeveloper());
                taskNames.add(task.getTaskName());
                taskIDs.add(task.getTaskID());
                this.taskStatuses.add(task.getTaskStatus());  // Use this to refer to the taskStatuses instance variable
                this.taskDurations.add(task.getTaskDuration());  // Use this to refer to the taskDurations instance variable

                JOptionPane.showMessageDialog(null, "Task successfully captured:\n" + task.printTaskDetails());
            }
            JOptionPane.showMessageDialog(null, "Total Task Duration: " + totalTaskDuration + " hours");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Please enter an integer.");
            addTasks();  // Retry if invalid input is given
        }
    }

    public void showReport() {
        StringBuilder report = new StringBuilder("Task Report:\n\n");
        for (Task task : tasks) {
            report.append(task.printTaskDetails()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public void searchTask() {
        String[] searchOptions = {"Search by Task Name", "Search by Developer"};
        String searchChoice = (String) JOptionPane.showInputDialog(null, "Choose a search option:", "Search Task", JOptionPane.QUESTION_MESSAGE, null, searchOptions, searchOptions[0]);
        if (searchChoice.equals("Search by Task Name")) {
            String taskName = JOptionPane.showInputDialog("Enter task name to search:");
            boolean found = false;
            for (Task task : tasks) {
                if (task.getTaskName().equalsIgnoreCase(taskName)) {
                    JOptionPane.showMessageDialog(null, "Task Found:\n" + task.printTaskDetails());
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Task not found.");
            }
        } else if (searchChoice.equals("Search by Developer")) {
            String developerName = JOptionPane.showInputDialog("Enter developer name to search:");
            StringBuilder result = new StringBuilder();
            for (Task task : tasks) {
                if (task.getDeveloper().equalsIgnoreCase(developerName)) {
                    result.append("Task Name: ").append(task.getTaskName()).append("\nTask Status: ").append(task.getTaskStatus()).append("\n\n");
                }
            }
            if (result.length() == 0) {
                JOptionPane.showMessageDialog(null, "No tasks found for developer: " + developerName);
            } else {
                JOptionPane.showMessageDialog(null, "Tasks Found:\n" + result.toString());
            }
        }
    }

    public void deleteTask() {
        String taskName = JOptionPane.showInputDialog("Enter task name to delete:");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().equalsIgnoreCase(taskName)) {
                tasks.remove(i);
                developers.remove(i);
                taskNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                JOptionPane.showMessageDialog(null, "Task " + taskName + " has been deleted.");
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(null, "Task not found.");
        }
    }

    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        String firstName = JOptionPane.showInputDialog("Enter firstname:");
        String lastName = JOptionPane.showInputDialog("Enter lastname:");

        Login user = new Login(username, password, firstName, lastName);
        String registrationStatus = user.registerUser();
        JOptionPane.showMessageDialog(null, registrationStatus);

        if (registrationStatus.equals("Account created successfully!")) {
            String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
            String enteredPassword = JOptionPane.showInputDialog("Enter your password:");

            boolean isLoggedIn = user.loginUser(enteredUsername, enteredPassword);
            String loginStatus = user.returnLoginStatus(isLoggedIn);
            JOptionPane.showMessageDialog(null, loginStatus);

            if (isLoggedIn) {
                user.displayMenu();
            }
        }
    }
}
