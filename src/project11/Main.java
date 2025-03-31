package project11;

import javax.swing.JFrame;

class MyFrame extends JFrame{
    void load(){
       setSize(400,300); //size of Jframe
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exits while clicking 'X' button
       setLocationRelativeTo(null);
       setResizable(false);
    }
   
}

public class Main {
    public static void main(String[] args) {
       MyFrame m = new MyFrame(); //Instance of MyFrame class
       m.load();
       Components c = new Components(m); 
    }
    
}