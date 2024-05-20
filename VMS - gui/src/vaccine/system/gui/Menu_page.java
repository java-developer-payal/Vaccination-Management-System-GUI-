package vaccine.system.gui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu_page implements ActionListener {	
    private JFrame frame = new JFrame();
    private JButton button1, button2, button3, button4, button5, button6; // Added updateButton
    private JLabel imageLabel;
    
    Menu_page()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setTitle("Menu Page");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        frame.setVisible(true);
        
        button1= new JButton("Update Data"); // Add update button
        button1.setBounds(90, 200, 600, 40);
        button1.setFont(new Font("Arial", Font.BOLD, 18));
        button1.setFocusable(false);
        button1.addActionListener(this);
         
        button2 = new JButton("Add New Record");
        button2.setBounds(90, 250, 600, 40);
        button2.setFont(new Font("Arial", Font.BOLD, 18));
        button2.setFocusable(false);
        button2.addActionListener(this);
        
        button3 = new JButton("View Vaccine Data");
        button3.setBounds(90, 300, 600, 40);
        button3.setFont(new Font("Arial", Font.BOLD, 18));
        button3.setFocusable(false);
        button3.addActionListener(this);
        
        button4 = new JButton("View All Data");
        button4.setBounds(90, 350, 600, 40);
        button4.setFont(new Font("Arial", Font.BOLD, 18));
        button4.setFocusable(false);
        button4.addActionListener(this);
        
        button5 = new JButton("Search");
        button5.setBounds(90, 400, 600, 40);
        button5.setFont(new Font("Arial", Font.BOLD, 18));
        button5.setFocusable(false);
        button5.addActionListener(this); 
        
        button6 = new JButton("Exit");
        button6.setBounds(90, 450, 600, 40);
        button6.setFont(new Font("Arial", Font.BOLD, 18));
        button6.setFocusable(false);
        button6.addActionListener(this);
               
        // Load the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mukes\\eclipse-workspace\\VMS - gui\\Images\\menupage.jpg");
        imageLabel = new JLabel(imageIcon);
        int imagePanelWidth = 800;
        int imagePanelHeight = 170;
        imageLabel.setBounds(0, 0, imagePanelWidth, imagePanelHeight);
        imageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(imagePanelWidth, imagePanelHeight, Image.SCALE_SMOOTH)));
        frame.add(imageLabel);
        
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	
       if(e.getSource() == button1) {
            frame.dispose();
            Update_page up = new Update_page();
            up.setVisible(true);
         }else if(e.getSource() == button2){
            frame.dispose();
            Registration_page rg = new Registration_page();
            rg.setVisible(true);
         }else if(e.getSource() == button3){
    	   	frame.dispose();
    	   	VaccineData_page vdp = new VaccineData_page();
    	   	vdp.setVisible(true);
         }else if(e.getSource() == button4){
           frame.dispose();
           ViewAllData_page vap = new  ViewAllData_page();
           vap.showData();
         }else if(e.getSource() == button5){
            frame.dispose();
            Search_page sp = new Search_page();
            sp.setVisible(true);
         } else if (e.getSource() == button6) {  
            System.exit(0);
        }     
    }

    public void setVisible(boolean b) {
        // TODO Auto-generated method stub        
    }    
}






