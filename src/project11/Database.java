/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project11;

/**
 *
 * @author Sohan
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class Database {
    final static String DB_URL = "jdbc:mysql://localhost:3306/project11";
    final static String user = "root";
    final static String password = "";
    
    public static Connection connect(){
        try{
        Connection c = DriverManager.getConnection(DB_URL,user,password);
        //System.out.println("Connecting...");
        return c;
       
        }
        
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
            return null;
        }
    }
}
