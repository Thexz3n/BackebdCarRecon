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

    @Override
    public void inputDetaile() {
        super.inputDetaile();
        System.out.print("Write the type of a car: ");
        String type = user.nextLine();

        System.out.print("Write the plate number of a car: ");
        String platenumber = user.nextLine();

        System.out.print("Write the carID of a car: ");
        int carid = user.nextInt();
        createCar(super.getName(), super.getBrand(), super.getColor(), super.getModel(), super.isPartnership(), super.isFormalleted(), type, platenumber, carid);
    }

    @Override
    public void updateInput() {
        super.updateInput();
        System.out.print("Write the new type of a car: ");
        String type = user.nextLine();

        System.out.print("Write the new plate number of a car: ");
        String platenumber = user.nextLine();

        System.out.print("Write the new carID of a car: ");
        int carid = user.nextInt();

        System.out.print("Write the carID of a car: ");
        int car = user.nextInt();
    }

    @Override
    public void deleteInput() {
        System.out.print("Write the CarID that you want a delete: ");
        int car = user.nextInt();
    }

    public void createCar(String Name, String Brand, String Color, int Model, boolean partnership, boolean formalleted, String type, String platenumber, int carid) {
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
            pst.setInt(9, carid);

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

    public void updateCar(String Name, String Brand, String Color, int Model, boolean partnership, boolean formalleted, String type, String platenumber, int newcarid, int carid) {
        String sql = "UPDATE cars SET CarID=?, name = ?, Brand = ?, Color = ?, Model = ?, partnership = ?, formalleted = ?, Type = ?, plateNumber = ?,  WHERE CarID = ?";
        try {
            pst = con.prepareStatement(sql);

            pst.setInt(1, newcarid);
            pst.setString(2, Name);
            pst.setString(3, Brand);
            pst.setString(4, Color);
            pst.setInt(5, Model);
            pst.setBoolean(6, partnership);
            pst.setBoolean(7, formalleted);
            pst.setString(8, type);
            pst.setString(9, platenumber);
            pst.setInt(10, carid); // CarID is used to identify the row to update

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

    public void deleteCar(int carid) {
        String sql = "DELETE FROM cars WHERE ?";

        try {
            
            pst = con.prepareStatement(sql);
            
            pst.setInt(1, carid);

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
}
