package project11;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;

public class Components {
    MyFrame m;
    JPanel jp;
    JLabel un, up;
    JTextField tf;
    JPasswordField pf;
    JButton btn;
    
    Connection c = Database.connect();
    Statement st = null;
    
    Components(MyFrame m){
        this.m = m;
        jp = new JPanel();
        jp.setSize(400, 300);
        Color bg = new Color(7, 74, 102);
        Color fg = new Color(230, 231, 240);
        jp.setBackground(bg);
        jp.setLayout(null);
        m.add(jp);
        
        un = new JLabel("User Name: ");
        un.setFont(new Font("Calibri",3,16)); // (Font-Name, style, font-size)
        un.setForeground(fg);
        un.setBounds(20, 60, 100, 20); // (x-axis,y-axis,width,height)
        jp.add(un);
        
        up = new JLabel("Password: ");
        up.setFont(new Font("Calibri",3,16)); // (Font-Name, style, font-size)
        up.setForeground(fg);
        up.setBounds(20, 120, 100, 20); // (x-axis,y-axis,width,height)
        jp.add(up);
        
        tf = new JTextField();
        tf.setBounds(120, 60, 140, 20);
        jp.add(tf);
        
        pf = new JPasswordField();
        pf.setBounds(120, 120, 140, 20);
        jp.add(pf);
        
        btn = new JButton("Submit");
        btn.setBounds(120,160,80,30);
        jp.add(btn);
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPerformed(e);
                //System.out.println("Okay");
            }
        } );
        
       m.setVisible(true);
    }
    
   private void LoginPerformed(ActionEvent e){
       try{
           st = c.createStatement();
           String u = tf.getText();
           String p = pf.getText();
           
           String query = "SELECT* FROM login WHERE user_name = '"+u+"' and password =  '"+p+"'";
           ResultSet rs = st.executeQuery(query);
           if(rs.next()){
               JOptionPane.showMessageDialog(null, "Login Successfull!");
               m.dispose();
               Student_Insert si = new Student_Insert();
               si.init();
               si.components();
           }
           else{
              JOptionPane.showMessageDialog(null, "Enter valid user/password!");
         }
       }
       catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex);
       }
   } 
}
