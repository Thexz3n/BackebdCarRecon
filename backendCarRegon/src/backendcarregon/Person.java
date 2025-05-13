/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendcarregon;

import java.util.Scanner;
import java.sql.*;
import javax.swing.JOptionPane;


public class Person {
    Scanner user = new Scanner(System.in);
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private String Name;
    private String Phone;
    private String Email;
    private String Addres;
    private String Role;
    
    public Person(){
        getConnection();
        inputDetaile();
        createPerson(getName(), getPhone(), getEmail(), getAddres(), getRole());
    }
    
    
    private void setInformation(String name, String phone, String email, String address, String role){
        this.Name = name;
        this.Phone = phone;
        this.Email = email;
        this.Addres = address;
        this.Role = role;
    }

    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getAddres() {
        return Addres;
    }

    public String getRole() {
        return Role;
    }
    
    public void getConnection(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/carsrecingotion", "root", "");
            if(con != null){
                JOptionPane.showMessageDialog(null, "Connect database successfully");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    
    public void inputDetaile(){
        System.out.print("Write your full name: ");
        String name = user.nextLine();
        
        System.out.print("Write your phone number: ");
        String phone = user.nextLine();
        
        System.out.print("Write your email: ");
        String email = user.nextLine();
        
        System.out.print("Write your address: ");
        String address = user.nextLine();
        
        System.out.print("Write your role: \n   1.Admin\n   2. User\n");
        String role = user.nextLine().toLowerCase();
        
        setInformation(name, phone, email, address, role);
        System.out.println("");
        displayDetaile();
        
        switch (role) {
            case "admin":
                Admin admin = new Admin();
                break;
            case "user":
                break;
            default:
                System.out.println("Tkaya wak xoy binwsa");
        }
        
        
    }
    
    public void updateInputDetaile(){
        System.out.print("Write the EmployeeID that you want change data:  ");
        int employee = user.nextInt();
        
        System.out.print("Write new full name: ");
        String name = user.nextLine();
        
        System.out.print("Write new phone number: ");
        String phone = user.nextLine();
        
        System.out.print("Write new email: ");
        String email = user.nextLine();
        
        System.out.print("Write new address: ");
        String address = user.nextLine();
        
        System.out.print("Write new role: \n   1.Admin\n   2. User\n");
        String role = user.nextLine().toLowerCase();
        
        updatePerson(employee,name, phone, email, address, role);
        System.out.println("");
        
     
    }
    
    public void deleteEmployee(){
        System.out.println("Write your Employee ID that you want delete: ");
         int employee = user.nextInt();
         deletePerson(employee);
    }
    
   public void displayDetaile(){
       System.out.println("Employee Information: ");
       System.out.println("Full name: "+getName());
       System.out.println("Phone number: "+getPhone());
       System.out.println("Email: "+getEmail());
       System.out.println("Address: "+getAddres());
       System.out.println("Role: "+getRole());
   }  
   
   
   private void createPerson(String name, String phone, String email, String address, String role){
        try {
            
    String sql = "INSERT INTO employee(Name, Phone, Email, Address, Role) VALUES (?,?,?,?,?)";
    pst = con.prepareStatement(sql);
    
    pst.setString(1, name);
    pst.setString(2, phone);
    pst.setString(3, email);
    pst.setString(4, address);
    pst.setString(5, role);
    
    pst.executeUpdate(); // Corrected this line
    
    JOptionPane.showMessageDialog(null, "Create account successfully", "Create Account", JOptionPane.INFORMATION_MESSAGE);
} catch (SQLException e) {
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
}
   }
   
   private void updatePerson(int EmployeeID,String name, String phone, String email, String address, String role){
       try{
           String sql = "UPDATE `employee` SET `Name`=?, `Phone`=?, `Email`=?, `Address`=?, `Role`=? WHERE `EmployeeID` = ?";
           pst = con.prepareStatement(sql);
           
           pst.setString(1, name);
           pst.setString(2, phone);
           pst.setString(3, email);
           pst.setString(4, address);
           pst.setString(5, role);
           pst.setInt(6, EmployeeID);

         int rowsUpdated = pst.executeUpdate();
    
         if (rowsUpdated > 0) {
                 JOptionPane.showMessageDialog(null, "Update successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
           } else {
                   JOptionPane.showMessageDialog(null, "No record updated. Please check Employee ID.", "Error", JOptionPane.WARNING_MESSAGE);
    }   

       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   private void deletePerson(int EmployeeID){
       try {
        String sql = "DELETE FROM employee WHERE EmployeeID=?";
        PreparedStatement pst = con.prepareStatement(sql);

        pst.setInt(1, EmployeeID); // Specify the EmployeeID to delete

        int rowsDeleted = pst.executeUpdate();
    
        if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Record deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
                JOptionPane.showMessageDialog(null, "No record found to delete. Check Employee ID.", "Error", JOptionPane.WARNING_MESSAGE);
         }

        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
}
   }
   

}