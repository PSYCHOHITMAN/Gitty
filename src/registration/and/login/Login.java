/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration.and.login;

import javax.swing.JOptionPane;

public class Login {
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Login(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName() {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password.length() < 8)
            return false;

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
        }
    }
}
