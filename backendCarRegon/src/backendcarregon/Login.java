/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendcarregon;

import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Login {

    Scanner user = new Scanner(System.in);
    PreparedStatement pst;
    Connection con;
    ResultSet rs;
    private String mainUsername;
    private String mainPassword;
    private String Role;

    public Login() {
        getConnection();
        inputDetaile();
        actionLogin(getMainUsername(), getMainPassword());
    }

    private void setInformation(String mainUsername, String mainPassword) {
        this.mainUsername = mainUsername;
        this.mainPassword = mainPassword;
        this.Role = Role;
    }

    public String getMainUsername() {
        return mainUsername;
    }

    public String getMainPassword() {
        return mainPassword;
    }

    public String getRole() {
        return Role;
    }

    public void actionLogin(String mainUsername, String mainPassword, String Role) {
        switch (Role) {
            case "Admin":
                System.out.println("Welcome Admin");
                
                break;
            case "User":
                System.out.println("Welcome User");
                break;
            default:
                System.out.println("Invalid Role");
        }

        try {
            pst = con.prepareStatement(mainUsername);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void inputDetaile() {
        System.out.print("Enter your username: ");
        String username = user.nextLine();
        System.out.print("Enter your password: ");
        String password = user.nextLine();
        actionLogin(username, password);
    }

    public void outputDetaile() {
        System.out.println("Your username is: " + getUsername());
        System.out.println("Your password is: " + getPassword());
    }

    public void getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/carsrecingotion", "root", "");
            if (con != null) {
                JOptionPane.showMessageDialog(null, "Connect database successfully");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

}
