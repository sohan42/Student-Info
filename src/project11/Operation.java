package project11;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Operation extends JFrame{
    JTextField search,tRoll,tName,tAddress,tPhone,tEmail;
    JComboBox cGender;
    JButton sBtn,update,delete,back;
    
    Connection c = Database.connect();
    Statement st = null;
    Operation(){
       setSize(380,600); //size of Jframe
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits while clicking 'X' button
       setLocationRelativeTo(null);
       setResizable(false);
       setVisible(true);
    }
    
    void init(){
        JPanel p = new JPanel();
        p.setSize(400,600);
        p.setLayout(null);       
        add(p);
        
        JLabel s = new JLabel("Search by Id:");
        s.setBounds(20,30,80,20);
        p.add(s);
        
        search = new JTextField();
        search.setBounds(100,30,60,25);
        p.add(search);
        
        JButton sBtn = new JButton("Search");
        sBtn.setBounds(170,30,80,30);
        p.add(sBtn);
        
        JLabel lRoll = new JLabel("Roll no:");
        lRoll.setBounds(20,70,100,20);
        p.add(lRoll);
        
        tRoll = new JTextField();
        tRoll.setBounds(20,100,200,25);
        p.add(tRoll);
        
        JLabel lName = new JLabel("Name:");
        lName.setBounds(20,140,100,20);
        p.add(lName);
        
        tName = new JTextField();
        tName.setBounds(20,170,200,25);
        p.add(tName);
        
        JLabel lAddress = new JLabel("Address:");
        lAddress.setBounds(20,210,100,20);
        p.add(lAddress);
        
        tAddress = new JTextField();
        tAddress.setBounds(20,240,200,25);
        p.add(tAddress);
        
        JLabel lGender = new JLabel("Gender:");
        lGender.setBounds(20,280,100,20);
        p.add(lGender);
        
        String[] items = {"Male", "Female"};
        cGender = new JComboBox<>(items);
        cGender.setBounds(20,310,80,25);
        p.add(cGender);
        
        JLabel lPhone = new JLabel("Phone:");
        lPhone.setBounds(20,350,100,20);
        p.add(lPhone);
        
        tPhone = new JTextField();
        tPhone.setBounds(20,380,200,25);
        p.add(tPhone);
        
        JLabel lEmail = new JLabel("Email:");
        lEmail.setBounds(20,420,100,20);
        p.add(lEmail);
        
        tEmail = new JTextField();
        tEmail.setBounds(20,450,200,25);
        p.add(tEmail);
        
        update = new JButton("Update");
        update.setBounds(20, 500, 80, 30);
        p.add(update);
        
        delete = new JButton("Delete");
        delete.setBounds(120, 500, 80, 30);
        p.add(delete);
        
        back = new JButton("Back");
        back.setBounds(220, 500, 80, 30);
        p.add(back);
        
        sBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPerformed();
            }
        });
        
        update.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              updatePerformed();
            }
        });
        
        delete.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              deletePerformed();
            }
        }); 
        
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             dispose();
             Student_Insert s = new Student_Insert();
             s.init();
             s.components();
            }
        });
    }
    
    void updatePerformed(){
        try {
            String id,ro,na,ad,ge,ph,em;
            int flag=0;
            st=c.createStatement();
            id= search.getText();
            
            ro = tRoll.getText();
            na = tName.getText();
            ad = tAddress.getText();
            ge = (String) cGender.getSelectedItem();
            ph = tPhone.getText();
            em = tEmail.getText();
            
            String sql = "UPDATE student SET sroll = ?, sname = ?, saddress = ?, sgender = ?, sphone = ?, semail = ? WHERE sid = ?";
            
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, ro);
            ps.setString(2, na);
            ps.setString(3, ad);
            ps.setString(4, ge);
            ps.setString(5, ph);
            ps.setString(6, em);
            ps.setString(7, id);
            
            flag = ps.executeUpdate();
                    if (flag > 0) {
                        JOptionPane.showMessageDialog(new JFrame(), "Data successfully updated!");
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "No data found for the given ID.");
                    }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void searchPerformed(){
        try {
            String id;
            int flag = 0;
            
            st =c.createStatement();
            
            id = search.getText();
            if("".equals(id)){
                JOptionPane.showMessageDialog(new JFrame(), "Enter ID!",
                        "Dialog", JOptionPane.ERROR_MESSAGE);
            }
            else{
                String sql = "SELECT* FROM student WHERE sid="+id;
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    tRoll.setText(rs.getString("sroll"));
                    tName.setText(rs.getString("sname"));
                    tAddress.setText(rs.getString("saddress"));
                    cGender.setSelectedItem(rs.getString("sgender"));
                    tPhone.setText(rs.getString("sphone"));
                    tEmail.setText(rs.getString("semail"));
                    flag=1;
                }
                if(flag==0){
                    JOptionPane.showMessageDialog(new JFrame(), "ID not Found!",
                        "Dialog", JOptionPane.ERROR_MESSAGE);
                        tRoll.setText("");
                        tName.setText("");
                        tAddress.setText("");
                        tPhone.setText("");
                        tEmail.setText("");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void deletePerformed(){
        String id,sql,sql2;
        int flag=0,r;
        r = JOptionPane.showConfirmDialog(this, "Are you Sure?");
        if(r==0){
        try {
            st = c.createStatement();
            id = search.getText();
            if("".equals(id)){
                 JOptionPane.showMessageDialog(new JFrame(), "Enter Id!", "Dialog", JOptionPane.ERROR_MESSAGE);
            }
            else{
                    sql = "SELECT* FROM student WHERE sid=?";
                    PreparedStatement ps = c.prepareStatement(sql);
                    ps.setString(1,id);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()){                
                        sql2 = "DELETE FROM student WHERE sid="+id;
                        st.executeUpdate(sql2);
                        search.setText("");
                        tRoll.setText("");
                        tName.setText("");
                        tAddress.setText("");
                        tPhone.setText("");
                        tEmail.setText("");
                        flag=1;
                    }
                if(flag==0){
                    JOptionPane.showMessageDialog(new JFrame(), "Id not Found!", "Dialog", JOptionPane.ERROR_MESSAGE);
                }
            }    
            
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        }
    }
}
