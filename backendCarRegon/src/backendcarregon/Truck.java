package backendcarregon;

import java.util.Scanner;

public abstract class Truck extends Vehicle{
    Scanner user = new Scanner(System.in);
    private String Type;
    private String plateNumber;
    private int CarID;
    
    
    public void setInformation(String Type, String plateNumber, int CarID){
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

    public int getCarID() {
        return CarID;
    }
    
    public abstract void inputDetail();
    
    public abstract void updateDetail();
    
    public abstract void deleteDetail();
    
    public abstract void displayDetail();
    
}
