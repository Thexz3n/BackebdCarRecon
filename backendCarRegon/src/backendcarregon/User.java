package backendcarregon;

import java.sql.*;
import javax.swing.JOptionPane;

public class User extends Person {

    private int UserID;
    private int EmployeeID;
    private String securityCode;
    private String Username;
    private String Password;
    private String lastLogin;
    private String history;

    public User() {
        super.getConnection();
        inputDetaile();
        createAccount(super.getName(), super.getPhone(), super.getEmail(), super.getAddres(), super.getRole(), getEmployeeID(), getSecurityCode(), getUsername(), getPassword());
    }

    public void setInformation(int employeeID, String securitycode, String username, String password) {
        this.EmployeeID = employeeID;
        this.securityCode = securitycode;
        this.Username = username;
        this.Password = password;
    }

    public int getUserID() {
        return UserID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    @Override
    public void inputDetaile() {
        super.inputDetaile();
        System.out.println("Write your EmployeeID: ");
        int employeeID = user.nextInt();

        System.out.println("Write your Security Code: ");
        String securitycode = user.nextLine();

        System.out.println("Write your Username: ");
        String username = user.nextLine();

        System.out.println("Write your Password");
        String password = user.nextLine();

        setInformation(employeeID, securitycode, username, password);
        displayDetaile();
    }

    @Override
    public void updateInputDetaile() {
        super.updateInputDetaile();
        System.out.print("Write the User ID that you want change the data");
        this.UserID = user.nextInt();

        System.out.print("Write new Security Code: ");
        String securitycode = user.nextLine();

        System.out.print("Write new Username");
        String username = user.nextLine();

        System.out.print("Write new password");
        String password = user.nextLine();
        updateAccount(getUserID(), super.getName(), super.getPhone(), super.getEmail(), super.getAddres(), super.getRole(), getEmployeeID(), securitycode, username, password);
    }

    @Override
    public void deleteEmployee() {
        System.out.print("Write the User ID that you want delete the data");
        int userID = user.nextInt();
        deleteAccount(userID);
    }

    @Override
    public void displayDetaile() {
        super.displayDetaile();
        System.out.println("User ID: " + getUserID());
        System.out.println("Employee ID: " + getEmployeeID());
        System.out.println("Security Code: " + getSecurityCode());
        System.out.println("Username: " + getUsername());
        System.out.println("Password: " + getPassword());
    }

    public void createAccount(String name, String phone, String email, String address, String role, int employeeID, String securitycode, String username, String password) {
        try {
            String sql = "INSERT INTO user (EmployeeID, Name, Phone, Email, Address, Role, SecurityCode, Username, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setInt(1, employeeID);
            pst.setString(2, name);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, address);
            pst.setString(6, role);
            pst.setString(7, securitycode);
            pst.setString(8, username);
            pst.setString(9, password);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Create account successfully", "Create Account", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateAccount(int userID, String name, String phone, String email, String address, String role, int employeeID, String securitycode, String username, String password) {
        try {
            String sql = "UPDATE user SET EmployeeID = ?, Name = ?, Phone = ?, Email = ?, Address = ?, Role = ?, SecurityCode = ?, Username = ?, Password = ? WHERE UserID = ?";

            pst = con.prepareStatement(sql);
            pst.setInt(1, employeeID);
            pst.setString(2, name);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, address);
            pst.setString(6, role);
            pst.setString(7, securitycode);
            pst.setString(8, username);
            pst.setString(9, password);
            pst.setInt(10, userID); // This specifies which user to update

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Account updated successfully", "Update Account", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No account found with the given UserID", "Update Failed", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteAccount(int userID) {
        try {
            String sql = "DELETE FROM user WHERE UserID=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, userID); // Make sure userID is correctly passed

            int rowsDeleted = pst.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Record deleted successfully!", "Delete Account", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No record found to delete. Check Employee ID.", "Error", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void searchingByID(){
        System.out.print("Enter the User ID to search: ");
        int userID = user.nextInt();
        try {
            String sql = "SELECT * FROM user WHERE UserID=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, userID);
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "User found: " + rs.getString("Name"), "Search Result", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No user found with the given UserID", "Search Result", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void searchingByFullname(){
        System.out.print("Enter the User Fullname to search: ");
        String name = user.nextLine();
        String sql = "SELECT * FROM admin WHERE Name=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, name);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Admin found:");
                System.out.println("Admin ID: " + rs.getInt("AdminID"));
                System.out.println("Employee ID: " + rs.getInt("EmployeeID"));
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Phone: " + rs.getString("Phone"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Address: " + rs.getString("Address"));
                System.out.println("Role: " + rs.getString("Role"));
                System.out.println("Security Code: " + rs.getString("SecurityCode"));
                System.out.println("Username: " + rs.getString("Username"));
            } else {
                System.out.println("No admin found with the given Fullname.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void searchingByPhone(){
    System.out.print("Enter the User Phone to search: ");
        String phone = user.nextLine();
        String sql = "SELECT * FROM admin WHERE Phone=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, phone);
            rs = pst.executeQuery();
            if (rs.next()) {
                System.out.println("Admin found:");
                System.out.println("Admin ID: " + rs.getInt("AdminID"));
                System.out.println("Employee ID: " + rs.getInt("EmployeeID"));
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Phone: " + rs.getString("Phone"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Address: " + rs.getString("Address"));
                System.out.println("Role: " + rs.getString("Role"));
                System.out.println("Security Code: " + rs.getString("SecurityCode"));
                System.out.println("Username: " + rs.getString("Username"));
            } else {
                System.out.println("No admin found with the given Phone.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
