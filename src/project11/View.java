package project11;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame {
    
    Connection c = Database.connect();
    Statement st = null;
    
    public View(){
       setSize(800,600); //size of Jframe
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits while clicking 'X' button
       setLocationRelativeTo(null);
       setResizable(false);
       
       setVisible(true);
    }
    
    public void loadData(){
        try {
            st = c.createStatement();
            DefaultTableModel model = new DefaultTableModel(new String[]
            {"Id","Roll no.","Name","Address","Gender","Phone","Email"},0);
            JTable table = new JTable(model); 
            
            JScrollPane sp = new JScrollPane(table);
            
            JButton b = new JButton("Back");
            
            JPanel p = new JPanel(new BorderLayout());
            p.add(sp,BorderLayout.CENTER);
            p.add(b,BorderLayout.SOUTH);
            
            add(p);
            
            String sql = "SELECT* FROM student";
            ResultSet rs = st.executeQuery(sql);
            
            String id, ro, na, ad, ge, ph, em;
            while(rs.next()){
                id = rs.getString("sid");
                ro = rs.getString("sroll");
                na = rs.getString("sname");
                ad = rs.getString("saddress");
                ge = rs.getString("sgender");
                ph = rs.getString("sphone");
                em = rs.getString("semail");
                model.addRow(new Object[]{id,ro,na,ad,ge,ph,em});
            }
            
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    Student_Insert s = new Student_Insert();
                    s.init();
                    s.components();
                }
            } );
            
        } catch (SQLException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
