package backendcarregon;

import java.util.Scanner;
import java.sql.*;
import javax.swing.JOptionPane;

public class Vehicle {

    Scanner user = new Scanner(System.in);
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    private String Name;
    private String Brand;
    private String Color;
    private int Model;
    private boolean Partnership;
    private boolean Formalleted;

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

    public void rigsterCar(String Name, String Brand, String Color, int Model, boolean partnership, boolean formalleted) {
        this.Name = Name;
        this.Brand = Brand;
        this.Model = Model;
        this.Partnership = partnership;
        this.Formalleted = formalleted;

    }

    public String getName() {
        return Name;
    }

    public String getBrand() {
        return Brand;
    }

    public String getColor() {
        return Color;
    }

    public int getModel() {
        return Model;
    }

    public boolean isPartnership() {
        return Partnership;
    }

    public boolean isFormalleted() {
        return Formalleted;
    }

    
    public void inputDetaile() {
        System.out.print("Write the name car: ");
        String Name = user.nextLine();

        System.out.print("Write the Brand of car: ");
        String Brand = user.nextLine();

        System.out.print("Write the color of car: ");
        String Color = user.nextLine();

        System.out.print("Write the color of car: ");
        int Model = user.nextInt();

        System.out.print("The car has a partnership: ");
        boolean Partnership = user.nextBoolean();

        System.out.print("Thw car has a formalleted");
        boolean Formalleted = user.nextBoolean();

        rigsterCar(Name, Brand, Color, Model, Partnership, Formalleted);
    }

    public void updateInput() {
        System.out.println("Write the CarID that you want update the data");
        int carID = user.nextInt();

        System.out.print("Write the new name car: ");
        String name = user.nextLine();

        System.out.print("Write the new Brand of car: ");
        String Brand = user.nextLine();

        System.out.print("Write the new color of car: ");
        String Color = user.nextLine();

        System.out.print("Write the new color of car: ");
        int Model = user.nextInt();

        System.out.print("The car new a partnership: ");
        boolean Partnership = user.nextBoolean();

        System.out.print("Thw car new a formalleted");
        boolean Formalleted = user.nextBoolean();

        updateCar(carID, name, Brand, Color, Model, Partnership, Formalleted);

    }

    public void deleteInput() {
        System.out.println("Write the CarID that you want update the data");
        int carID = user.nextInt();
        deleteCar(carID);
    }

    public void displayDetaile() {
        System.out.println("The information about car: ");
        System.out.println("Car name: " + getName());
        System.out.println("Car model: " + getModel());
        System.out.println("Car brand: " + getBrand());
        System.out.println("Car is partnership? " + isPartnership());
        System.out.println("Car is formalleted? " + isFormalleted());
    }

    public void createCar(String Name, String Brand, String Color, int Model, boolean partnership, boolean formalleted) {

        String sql = "INSERT INTO car(Name, Color, Model, Brand, partnership, formalleted) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            pst = con.prepareStatement(sql);

            pst.setString(1, Name);
            pst.setString(2, Color);
            pst.setInt(3, Model);
            pst.setString(4, Brand);
            pst.setBoolean(5, partnership);
            pst.setBoolean(6, formalleted);

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Account create successfully", "create Account", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No account found with the given UserID", "create Failed", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateCar(int carID, String Name, String Brand, String Color, int Model, boolean partnership, boolean formalleted) {
        String sql = "UPDATE car SET Name=?, Color=?, Model=?, Brand=?, partnership=?, formalleted=? WHERE carID=?";
        try {
            pst = con.prepareStatement(sql);

            pst.setString(1, Name);
            pst.setString(2, Color);
            pst.setInt(3, Model);
            pst.setString(4, Brand);
            pst.setBoolean(5, partnership);
            pst.setBoolean(6, formalleted);
            pst.setInt(7, carID);

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

    public void deleteCar(int carID) {
        String sql = "DELETE FROM car WHERE carID=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, carID);

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Account delete successfully", "delete Account", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No account found with the given UserID", "delete Failed", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
