package vaccine.system.gui;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

 class Login_page implements ActionListener { 
	 
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JCheckBox showPasswordCheckbox ;
    private Connection connection;
    private JLabel userLabel,imageLabel;
    private JTextField userTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JButton button;
    private JLabel success; 
    private JProgressBar progressBar;


     Login_page()
    {
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Login Page");
        frame.add(panel);
        panel.setLayout(null);
        
        userLabel = new JLabel("User Name :");
        userLabel.setBounds(200,150, 100, 27);
        userLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Set font size to 15 and style to bold

        panel.add(userLabel);
        
        userTextField = new JTextField();
        userTextField.setBounds(350, 150, 255, 30);
        panel.add(userTextField);
        
        passwordLabel = new JLabel("Password :");
        passwordLabel.setBounds(200, 250, 100,29);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Set font size to 16 and style to bold
        panel.add(passwordLabel);
        
        passwordText = new JPasswordField();
        passwordText.setBounds(350, 250, 255, 30);
        panel.add(passwordText);
        
        button = new JButton("LOGIN");
        button.setBounds(310, 500, 160, 40);
        button.addActionListener(this);
        panel.add(button);
        
        success = new JLabel("");
        success.setBounds(290, 430, 300, 25);
        panel.add(success);
        
     // Progress bar initialization
        progressBar = new JProgressBar(0,100);
        progressBar.setBounds(190, 380, 400, 30);
        progressBar.setStringPainted(true); // To display progress as text
        panel.add(progressBar);
        
     // Load the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mukes\\eclipse-workspace\\VMS - gui\\Images\\loginnew.png"); // Change the path to your image file
        imageLabel = new JLabel(imageIcon);   // Create a label to display the image
        int imagePanelWidth = frame.getWidth();// Calculate the width and height for the image panel
        int imagePanelHeight = 130; // Adjust as needed for the height of the image panel
        // Create a panel to contain the image
        JPanel imagePanel = new JPanel(null); // Use null layout
        imagePanel.setBounds(0, 0, imagePanelWidth, imagePanelHeight);// Set the size and position of the image panel
        Image img = imageIcon.getImage().getScaledInstance(imagePanelWidth, imagePanelHeight, Image.SCALE_SMOOTH);// Resize the image to cover the entire image panel
        ImageIcon scaledImageIcon = new ImageIcon(img); // Create a new ImageIcon with the scaled image
        imageLabel.setIcon(scaledImageIcon);// Set the scaled image to the label       
        imageLabel.setBounds(0, 0, imagePanelWidth, imagePanelHeight);// Set the position of the label to cover the entire image panel        
        imagePanel.add(imageLabel);// Add the label to the image panel      
        panel.add(imagePanel); // Add the image panel to the main panel      
        
     // Declare the checkbox
        showPasswordCheckbox = new JCheckBox("Show Password");        
        showPasswordCheckbox.setBounds(460, 320, 150, 27);// Set the position and size of the checkbox
        showPasswordCheckbox.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(showPasswordCheckbox);// Add the checkbox to the panel
        // Add an ActionListener to the checkbox
        showPasswordCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle password visibility based on the state of the checkbox
                if (showPasswordCheckbox.isSelected()) {
                    passwordText.setEchoChar((char) 0); // Show password
                } else {
                    passwordText.setEchoChar('\u25CF'); // Hide password
                }
            }
        });

        
        frame.setVisible(true);
     
     // Initialize database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_database_new", "root", "P@y@l123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     @Override
     public void actionPerformed(ActionEvent e) {
         try {
             String username = userTextField.getText();
             String password = new String(passwordText.getPassword());

             // Check if username or password fields are empty
             if (username.isEmpty()) {
                 JOptionPane.showMessageDialog(frame, "Please enter username.", "Error", JOptionPane.ERROR_MESSAGE);
                 return; // Return without attempting login
             }
             else if (password.isEmpty()) {
                 JOptionPane.showMessageDialog(frame, "Please enter password.", "Error", JOptionPane.ERROR_MESSAGE);
                 return; 
             }
             
             // Show progress bar while login is in progress
             progressBar.setValue(0); // Reset progress to 0
             progressBar.setIndeterminate(false);

             SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
                 protected Boolean doInBackground() throws Exception {
                	 
                	// Simulate progress by incrementing the progress bar value
                     for (int i = 0; i <= 100; i++) {
                         Thread.sleep(6); // Simulate processing time
                         progressBar.setValue(i);
                         progressBar.setString(i + "%"); // Update progress text
                     }
                     
                     String query = "SELECT * FROM login_data WHERE username = ? AND password = ?";
                     PreparedStatement statement = connection.prepareStatement(query);
                     statement.setString(1, username);
                     statement.setString(2, password);
                     ResultSet resultSet = statement.executeQuery();
                     return resultSet.next();
                 }

                 // To show the result that task is completed or no(status of task)
                 protected void done() {
                     try {
                         if (get()) {
                             success.setText("Successfully Logged In.......");
                             
                             // Dispose login frame
                             frame.dispose();
                             
                             // Create and display the menu frame
                             Menu_page mp = new Menu_page();
                             mp.setVisible(true);
                         } 
                         else {
                             success.setText("*** Invalid username or password ***");
                         }
                     } 
                     catch (Exception ex) {
                         ex.printStackTrace();
                     } 
                     finally {
                         // Reset progress bar after login attempt
                    	 progressBar.setValue(0);
                         progressBar.setString("");
                     }
                 }
             };
             worker.execute();
         } catch (Exception ex) {
             ex.printStackTrace();
         }
     }
}


//---------------------- Main Class ------------------------

 class VaccineGui {           
    public static void main(String arg[]) {
        try {
        		new Login_page();
        } catch(Exception e) {
            System.out.println(e);
        }      
    }
  }

