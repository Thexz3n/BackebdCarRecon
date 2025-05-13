
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
        actionLogin(getMainUsername(), getMainPassword(), getRole());
    }

    private void setInformation(String mainUsername, String mainPassword, String Role) {
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
                String sql = "SELECT * FROM Admin WHERE username=? AND password=?";
                try {
                    pst = con.prepareStatement(sql);
                    pst.setString(1, mainUsername);
                    pst.setString(2, mainPassword);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login successful");
                        System.out.println("Welcome Admin: " + rs.getString("name"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                System.out.println("Welcome Admin");

                break;
            case "User":
                String sql1 = "SELECT * FROM User WHERE username=? AND password=?";
                try {
                    pst = con.prepareStatement(sql1);
                    pst.setString(1, mainUsername);
                    pst.setString(2, mainPassword);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Login successful");
                        System.out.println("Welcome User: " + rs.getString("name"));
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                break;
            default:
                System.out.println("Invalid Role");
        }


    }

    public void inputDetaile() {
        System.out.print("Enter your username: ");
        String username = user.nextLine();
        System.out.print("Enter your password: ");
        String password = user.nextLine();
        System.out.print("Enter your role: \n   1.Admin\n   2. User\n");
        String role = user.nextLine();
        setInformation(username, password, role);
    }

    public void outputDetaile() {
        System.out.println("Your username is: " + getMainUsername());
        System.out.println("Your password is: " + getMainPassword());
        System.out.println("Your role is: " + getRole());
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
