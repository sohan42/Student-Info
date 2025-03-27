package project11;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Sohan
 */
public class Student_Insert extends JFrame{
    JPanel jp;
    JLabel lRo,lNa,lAd,lGe,lPh,lEm;
    JTextField tRo,tNa,tAd,tPh,tEm;
    JComboBox cGe;
    JButton submit,operation,display; 
    
    Connection c = Database.connect();
    Statement st = null;
    
    void init(){
       setSize(400,400); //size of Jframe
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits while clicking 'X' button
       setLocationRelativeTo(null);
       setResizable(false);
    }
    
    void components(){
        Color bg = new Color(7, 74, 102);
        Color fg = new Color(230, 231, 240);
        
        jp = new JPanel();
        jp.setBackground(bg);
        jp.setSize(400,500);
        jp.setLayout(null);       
        add(jp);
        
        lRo = new JLabel("Roll no: ");
        lRo.setForeground(fg);
        lRo.setBounds(10,10,100, 20);
        jp.add(lRo);
        
        tRo = new JTextField();
        tRo.setBounds(120,10,160,20);
        jp.add(tRo);
        
        lNa = new JLabel("Name: ");
        lNa.setForeground(fg);
        lNa.setBounds(10,60,100, 20);
        jp.add(lNa);
        
        tNa = new JTextField();
        tNa.setBounds(120,60,160,20);
        jp.add(tNa);
         
        lAd = new JLabel("Address: ");
        lAd.setForeground(fg);
        lAd.setBounds(10,110,100, 20);
        jp.add(lAd);
        
        tAd = new JTextField();
        tAd.setBounds(120,110,160,20);
        jp.add(tAd);
        
        lGe = new JLabel("Gender: ");
        lGe.setForeground(fg);
        lGe.setBounds(10,160,100, 20);
        jp.add(lGe);
        
        String[] items = {"Male", "Female"};
        cGe = new JComboBox<>(items);
        cGe.setBounds(120,160,80,20);
        jp.add(cGe);
        
        lPh = new JLabel("Phone no: ");
        lPh.setForeground(fg);
        lPh.setBounds(10,210,100, 20);
        jp.add(lPh);
        
        tPh = new JTextField();
        tPh.setBounds(120,210,160,20);
        jp.add(tPh);
        
        lEm = new JLabel("Email: ");
        lEm.setForeground(fg);
        lEm.setBounds(10,260,100, 20);
        jp.add(lEm);
        
        tEm = new JTextField();
        tEm.setBounds(120,260,160,20);
        jp.add(tEm);
        
        submit = new JButton("Submit");
        submit.setBounds(10,310,80,25);
        jp.add(submit);
        
        display = new JButton("Display");
        display.setBounds(110,310,80,25);
        jp.add(display);
        
        operation = new JButton("Operation");
        operation.setBounds(210,310,100,25);
        jp.add(operation);
        
        
        submit.addActionListener((ActionEvent e) -> {
            submitPerformed(e);
        });
        
        display.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                View v = new View();
                v.loadData();
            }
        });
        
        operation.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Operation op = new Operation();
                op.init();
            }
        });
        setVisible(true);        
    }
    
    void submitPerformed(ActionEvent e){
       String na,ad,ge,ph,em;
       int roll;
       
        try {
            st=c.createStatement();
            if(("".equals(tRo.getText()))&&("".equals(tNa.getText()))
                    &&("".equals(tAd.getText()))&&("".equals(tPh.getText()))&&("".equals(tEm.getText()))){
                JOptionPane.showMessageDialog(new JFrame(), "Fill all the details!",
                        "Dialig", JOptionPane.ERROR_MESSAGE);
            }
            
            else{
                na = tNa.getText();
                ad = tAd.getText();
                ge = (String) cGe.getSelectedItem();
                ph  = tPh.getText();
                em  =tEm.getText();
                roll = Integer.parseInt(tRo.getText());
                String query  = "INSERT INTO student(sroll,sname,saddress,sgender,sphone,semail) "
                        + "VALUES ('"+roll+"','"+na+"','"+ad+"','"+ge+"','"+ph+"','"+em+"')";
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(rootPane, 
                        "Successfully Registered!", "Dialog", JOptionPane.OK_OPTION);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student_Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
