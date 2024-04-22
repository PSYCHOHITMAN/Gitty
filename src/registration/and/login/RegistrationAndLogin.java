// 
package registration.and.login;

import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class RegistrationAndLogin {

    public static void main(String[] args) {
     String username = "";
     String password = "";
     String firstName = "";
     String lastName = "";
      username = JOptionPane.showInputDialog("Enter username:");
      password = JOptionPane.showInputDialog("Enter password:");
      firstName = JOptionPane.showInputDialog("Enter firstname:");
      lastName = JOptionPane.showInputDialog("Enter astname:");
      
     if (checkUsername(username) && checkPasswordComplexity(password)) {
            JOptionPane.showMessageDialog(null, "Account created successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Account creation failed. Please check your username and password format.");
        }
    }

    public static boolean checkUsername(String username) {
        if (username.contains("_") && username.length() <= 5) {
            JOptionPane.showMessageDialog(null, "Username successfully captured");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            return false;
        }
    }
    public static boolean checkPasswordComplexity(String password){
        if(password.length() > 7)
        {
           if(valPassword(password))
           {
               JOptionPane.showMessageDialog(null,"Password successfully captured");
               return true;
           }
           else
           {
               return false;
           }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character." );
            return false;
        }
    }

    public static boolean valPassword(String password) {
        boolean hasNum = false; boolean hasCap = false; boolean hasLow = false; boolean hasSpec = false; char c;
        for (int i = 0; i < password.length(); i++)
        {
            c = password.charAt(i);
            if(Character.isDigit(c))
            {
                hasNum = true;
            }
            else if(Character.isUpperCase(c))
            {
                hasCap = true;
            }
            else if(Character.isLowerCase(c))
            {
                hasLow = true;
            }
            else if(c == '@'|| c == '$' || c == '#' || c == '!' || c == '*' || c == '%')
            {
                hasSpec = true;
            }
            if (hasNum && hasCap && hasLow && hasSpec)
            {
                return true;
            }
        }
        return false; 
     
        }
    }


        
        
    

       