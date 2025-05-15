package backendcarregon;

import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Truck extends Vehicle{
    Scanner user = new Scanner(System.in);

    private String Type;
    private String plateNumber;
    private String CarID;
    
    

    public void setInformation(String Type, String plateNumber, String CarID){
        this.Type = Type;
        this.plateNumber = plateNumber;
        this.CarID = CarID;
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
    public void firstDisplay() {
        System.out.println("Welcome to the Truck Registration System");
        System.out.println("1. Register a new truck");
        System.out.println("2. Update truck information");
        System.out.println("3. Delete a truck");
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
        System.out.print("Write the type of Truck: ");
        String type = user.nextLine();
        
        System.out.print("Write the plate number of truck: ");
        String plateNumber = user.nextLine();
        
        System.out.print("Write the truckID: ");
        String carID = user.nextLine();
        displayDetaile();
        setInformation(type, plateNumber, carID);
        createTruck(super.getName(), super.getBrand(), super.getColor(), super.getModel(), super.isPartnership(), super.isFormalleted(), Type, plateNumber, CarID);
        
        }
       
    @Override 
    public void updateInput() {
        super.updateInput();
        System.out.print("Enter the new type of truck: ");
        String newType = user.nextLine();
        
        System.out.print("Enter the new plate number of truck: ");
        String newPlateNumber = user.nextLine();
        
        System.out.print("Enter the truckID: ");
        String carID = user.nextLine();
        displayDetaile();
        updateTruck(super.getName(), super.getBrand(), super.getColor(), super.getModel(), super.isPartnership(), super.isFormalleted(), Type, plateNumber,  CarID);
        }

    @Override
    public void deleteInput() {
        System.out.print("Enter the truckID to delete: ");
        String truckID = user.nextLine();
        
        // Logic to delete the truck with the given ID
        deleteTruck(truckID);
        System.out.println("Truck with ID " + truckID + " has been deleted.");
        
    }

    @Override
    public void displayDetaile() {
        super.displayDetaile();
        System.out.println("Truck Type: " + Type);
        System.out.println("Plate Number: " + plateNumber);
        System.out.println("Truck ID: " + CarID);
    }

    public void createTruck(String Name, String Brand, String Color, int Model, boolean partnership, boolean formalleted, String type, String platenumber, String carid) {
        String sql = "INSERT INTO trucks (CarID, name, Brand, Color, Model, partnership, formalleted, Type, plateNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, carid);
            pst.setString(2, Name);
            pst.setString(3, Brand);
            pst.setString(4, Color);
            pst.setInt(5, Model);
            pst.setBoolean(6, partnership);
            pst.setBoolean(7, formalleted);
            pst.setString(8, type);
            pst.setString(9, platenumber);
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Truck registered successfully!");
                JOptionPane.showMessageDialog(null, "Truck registered successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void updateTruck(String Name, String Brand, String Color, int Model, boolean partnership, boolean formalleted, String type, String platenumber, String carid) {
        String sql = "UPDATE trucks SET  name = ?, Brand = ?, Color = ?, Model = ?, partnership = ?, formalleted = ?, Type = ?, plateNumber = ? WHERE CarID = ?";
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
                System.out.println("Truck updated successfully!");
                JOptionPane.showMessageDialog(null, "Truck updated successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteTruck(String carid) {
        String sql = "DELETE FROM trucks WHERE CarID=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, carid);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Truck deleted successfully!");
                JOptionPane.showMessageDialog(null, "Truck deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
