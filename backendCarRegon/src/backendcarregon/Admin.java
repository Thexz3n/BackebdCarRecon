package backendcarregon;

import java.sql.*;
import javax.swing.JOptionPane;

public class Admin extends Person {

    private int AdminID;
    private int EmployeeID;
    private String securityCode;
    private String Username;
    private String Password;
    private String lastLogin;
    private String history;
    private String sendAssignedTasks;

    public Admin() {
        super.getConnection();
        inputDetaile();
        createAccount(super.getName(), super.getPhone(), super.getEmail(), super.getAddres(), super.getRole(), getEmployeeID(), getSecurityCode(), getUsername(), getPassword());
    }

    private void setInformation(int employeeID, String securitycode, String username, String password) {
        this.EmployeeID = employeeID;
        this.securityCode = securitycode;
        this.Username = username;
        this.Password = password;

    }

    public int getAdminID() {
        return AdminID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
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
        System.out.print("Write the Admin ID that you want change the data");
        this.AdminID = user.nextInt();

        System.out.print("Write new Security Code: ");
        String securitycode = user.nextLine();

        System.out.print("Write new Username");
        String username = user.nextLine();

        System.out.print("Write new password");
        String password = user.nextLine();
        updateAccount(getAdminID(), getEmployeeID(), super.getName(), super.getPhone(), super.getEmail(), super.getAddres(), super.getRole(), securitycode, username, password);
    }

    @Override
    public void deleteEmployee() {
        System.out.print("Write the Admin ID that you want delete the data");
        int adminID = user.nextInt();
        deleteAccount(adminID);
    }

    @Override
    public void displayDetaile() {
        super.displayDetaile();
        System.out.println("Admin ID: " + getAdminID());
        System.out.println("Employee ID: " + getEmployeeID());
        System.out.println("Security Code: " + getSecurityCode());
        System.out.println("Username: " + getUsername());
        System.out.println("Password: " + getPassword());
    }

    public void createAccount(String name, String phone, String email, String address, String role, int employeeID, String securitycode, String username, String password) {
        try {
            String sql = "INSERT INTO admin( `EmployeeID`, `Name`, `Phone`, ,`Address`, `Role`, `SecurityCode`, `Username`, `Password`) VALUES (?,?,?,?,?,?,?,?,?)";
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

    public void updateAccount(int AdminID, int EmployeeID, String name, String phone, String email, String address, String role, String securitycode, String username, String password) {
        try {
            String sql = "UPDATE admin SET Name=?, Phone=?, Address=?, Role=?, SecurityCode=?, Username=?, Password=? WHERE EmployeeID=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, EmployeeID);
            pst.setString(2, name);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, address);
            pst.setString(6, role);
            pst.setString(7, securitycode);
            pst.setString(8, username);
            pst.setInt(9, AdminID);
            int rowsUpdated = pst.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Account updated successfully!", "Update Account", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No record found to update. Check Employee ID.", "Error", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteAccount(int adminID) {
        try {
            String sql = "DELETE FROM admin WHERE EmployeeID=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, adminID); // Make sure EmployeeID is correctly passed

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

}
