package backendcarregon;

import java.sql.*;
import javax.swing.JOptionPane;

public class Cars extends Vehicle {

    private String Type;
    private String plateNumber;
    private String CarID;

    public void setInformation(String type, String platenumber, String carid) {
        this.Type = type;
        this.plateNumber = platenumber;
        this.CarID = carid;
    }

    public String getType() {
        return Type;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getCarID() {
        return CarID;
    }

    public void firstDisplay() {
        System.out.println("Welcome to the Car Registration System");
        System.out.println("1. Register a new car");
        System.out.println("2. Update car information");
        System.out.println("3. Delete a car");
        System.out.println("4. Exit");
        System.out.print("Please select an option: ");
        int choice = user.nextInt();

        switch (choice) {
            case 1:
                inputDetaile();
                break;
            case 2:
                updateInput();
                break;
            case 3:
                deleteInput();
                break;
            case 4:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    @Override
    public void inputDetaile() {
        super.inputDetaile();
        System.out.print("Write the type of a car: ");
        String type = user.nextLine();

        System.out.print("Write the plate number of a car: ");
        String platenumber = user.nextLine();

        System.out.print("Write the carID of a car: ");
        String carid = user.nextLine();
        createCar(super.getName(), super.getBrand(), super.getColor(), super.getModel(), super.isPartnership(), super.isFormalleted(), type, platenumber, carid);
    }

    @Override
    public void updateInput() {
        super.updateInput();
        System.out.print("Write the new type of a car: ");
        String type = user.nextLine();

        System.out.print("Write the new plate number of a car: ");
        String platenumber = user.nextLine();


        System.out.print("Write the carID of a car: ");
        String car = user.nextLine();
        updateCar(super.getName(), super.getBrand(), super.getColor(), super.getModel(), super.isPartnership(), super.isFormalleted(), type, platenumber,  car);
    }

    @Override
    public void deleteInput() {
        System.out.print("Write the CarID that you want a delete: ");
        String car = user.nextLine();

        deleteCar(car);
        System.out.println("Car with ID " + car + " has been deleted.");
    }

    public void createCar(String Name, String Brand, String Color, int Model, boolean partnership, boolean formalleted, String type, String platenumber, String carid) {
        String sql = "INSERT INTO cars (CarID, name, Brand, Color, Model, partnership, formalleted, Type, plateNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {

            pst = con.prepareStatement(sql);

            pst.setString(1, Name);
            pst.setString(2, Brand);
            pst.setString(3, Color);
            pst.setInt(4, Model);
            pst.setBoolean(5, partnership);
            pst.setBoolean(6, formalleted);
            pst.setString(7, type);
            pst.setString(8, platenumber);
            pst.setString(9, carid);

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

    public void updateCar(String Name, String Brand, String Color, int Model, boolean partnership, boolean formalleted, String type, String platenumber, String carid) {
        String sql = "UPDATE cars SET name = ?, Brand = ?, Color = ?, Model = ?, partnership = ?, formalleted = ?, Type = ?, plateNumber = ?,  WHERE CarID = ?";
        try {
            pst = con.prepareStatement(sql);

        
           
            pst.setString(1, Brand);
            pst.setString(2, Color);
            pst.setInt(3, Model);
            pst.setBoolean(4, partnership);
            pst.setBoolean(5, formalleted);
            pst.setString(6, type);
            pst.setString(7, platenumber);
            pst.setString(8, carid); // CarID is used to identify the row to update

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Car record updated successfully", "Update Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No car found with the given CarID", "Update Failed", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void deleteCar(String carid) {
    String sql = "DELETE FROM cars WHERE CarID = ?";

    try {
        pst = con.prepareStatement(sql);
        pst.setString(1, carid);

        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Car record deleted successfully", "Delete Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No car found with the given CarID", "Delete Failed", JOptionPane.WARNING_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}
}
