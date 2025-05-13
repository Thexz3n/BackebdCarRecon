/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backendcarregon;


public class tankerTruck extends Truck{
    private double cargoCapacity;
    private String hazardousMaterial;
    private int compartmentCount;
    private String tankMaterial;

    @Override
    public void inputDetail() {
        System.out.print("Write the type of Tunker: ");
        String type = user.nextLine();
        
        System.out.print("Write the plate number of tunker: ");
        String plateNumber = user.nextLine();
        
        System.out.print("Write the tunkerID: ");
        int carID = user.nextInt();
        
        System.out.print("Write the cargo capacity of tunker: ");
        double cargoCapacity = user.nextDouble();
        
        System.out.print("Write the hazardous material of tunker: ");
        String hazardousMaterial = user.nextLine();
        
        System.out.print("Write the compartment count of tunker: ");
        int compartmentCount = user.nextInt();
        
        System.out.print("Write the tank material of tunker: ");
        String tankMaterial = user.next();
        
        super.setInformation(type, plateNumber, carID);
        
    }

    @Override
    public void updateDetail() {
       
    }

    @Override
    public void deleteDetail() {
        
    }

    @Override
    public void displayDetail() {
       
    }
    
    
    public void setInformation( double cargoCapacity, String hazardousMaterial, int compartmentCount, String tankMaterial){
        this.cargoCapacity = cargoCapacity;
        this.hazardousMaterial = hazardousMaterial;
        this.compartmentCount = compartmentCount;
        this.tankMaterial = tankMaterial;
    }

    public double getCargoCapacity() {
        return cargoCapacity;
    }

    public String getHazardousMaterial() {
        return hazardousMaterial;
    }

    public int getCompartmentCount() {
        return compartmentCount;
    }

    public String getTankMaterial() {
        return tankMaterial;
    }
    
    public void createTunker(String Type, String plateNumber, int CarID, double cargoCapacity, String hazardousMaterial, int compartmentCount, String tankMaterial){
        
    }
    
    public void updateTunker(String Type, String plateNumber, int CarID,int newCarID, double cargoCapacity, String hazardousMaterial, int compartmentCount, String tankMaterial){
        
    }
    
    public void deleteTunker(int CarID){
        
    }
    
    
    
}
