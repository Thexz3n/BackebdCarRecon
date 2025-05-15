package backendcarregon;
import java.sql.*;
import javax.swing.JOptionPane;


public class Database {
    Connection con;
    
    public Database() {
        try {
            
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/carsrecingotion", "root", "");
            if (con != null) {
                System.out.println("Connection Successful");
                JOptionPane.showMessageDialog(null, "Connection Successful");
            }
            
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Connection Failed");
        }
    }
}
