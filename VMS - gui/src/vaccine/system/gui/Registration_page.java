package vaccine.system.gui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Registration_page {
    private JFrame frame;
    private JTextField nameField, aadharField, ageField, professionField, bpField, bodyTempField, addressField, phoneField, vaccineField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JButton submitButton, backButton;
    private Menu_page menuPage;

    public Registration_page() {
        frame = new JFrame("Registration Page");
        frame.setSize(800, 600); // Adjusted height to accommodate more components
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // Set layout to null for manual positioning

        // Load the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mukes\\eclipse-workspace\\VMS - gui\\Images\\registrationpage.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(455, 0, 340, 564); // Adjust position and size
        frame.add(imageLabel);

        // Create JLabels
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(80, 20, 80, 25); // Adjust position and size
        frame.add(nameLabel);
        
        // Create JTextFields
        nameField = new JTextField();
        nameField.setBounds(250, 20, 200, 25); // Adjust position and size
        frame.add(nameField);


        JLabel aadharLabel = new JLabel("Aadhar No:");
        aadharLabel.setBounds(80, 50, 80, 25); // Adjust position and size
        frame.add(aadharLabel);
        
        aadharField = new JTextField();
        aadharField.setBounds(250, 50, 200, 25); // Adjust position and size
        frame.add(aadharField);


        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(80, 80, 80, 25); // Adjust position and size
        frame.add(genderLabel);

        // Create JRadioButtons
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(250, 80, 70, 25); // Adjust position and size
        frame.add(maleRadioButton);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(345, 80, 80, 25); // Adjust position and size
        frame.add(femaleRadioButton);

        // Create ButtonGroup
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Add more components similarly
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(80, 110, 80, 25); // Adjust position and size
        frame.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(250, 110, 200, 25); // Adjust position and size
        frame.add(ageField);

        JLabel professionLabel = new JLabel("Profession:");
        professionLabel.setBounds(80, 140, 80, 25); // Adjust position and size
        frame.add(professionLabel);

        professionField = new JTextField();
        professionField.setBounds(250, 140, 200, 25); // Adjust position and size
        frame.add(professionField);

        JLabel bpLabel = new JLabel("Blood Pressure:");
        bpLabel.setBounds(80, 170, 120, 25); // Adjust position and size
        frame.add(bpLabel);

        bpField = new JTextField();
        bpField.setBounds(250, 170, 160, 25); // Adjust position and size
        frame.add(bpField);

        JLabel bodyTempLabel = new JLabel("Body Temperature:");
        bodyTempLabel.setBounds(80, 200, 120, 25); // Adjust position and size
        frame.add(bodyTempLabel);

        bodyTempField = new JTextField();
        bodyTempField.setBounds(250, 200, 160, 25); // Adjust position and size
        frame.add(bodyTempField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(80, 230, 80, 25); // Adjust position and size
        frame.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(250, 230, 200, 25); // Adjust position and size
        frame.add(addressField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(80, 260, 100, 25); // Adjust position and size
        frame.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(250, 260, 180, 25); // Adjust position and size
        frame.add(phoneField);

        JLabel vaccineLabel = new JLabel("Vaccine Injected:");
        vaccineLabel.setBounds(80, 290, 120, 25); // Adjust position and size
        frame.add(vaccineLabel);

        vaccineField = new JTextField();
        vaccineField.setBounds(250, 290, 160, 25); // Adjust position and size
        frame.add(vaccineField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(220, 360, 100, 30); // Adjust position and size
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                set_data();
            }
        });
        frame.add(submitButton);
        
        backButton = new JButton("Back");
        backButton.setBounds(100, 360, 100, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                // Show the Menu_page
                menuPage = new Menu_page();
                menuPage.setVisible(true);
            }
        });
        frame.add(backButton);


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    void set_data() {
        String name = nameField.getText();
        String citznum = aadharField.getText();
        String gender = maleRadioButton.isSelected() ? "Male" : "Female";
        int age = Integer.parseInt(ageField.getText());
        String prof = professionField.getText();
        float bp = Float.parseFloat(bpField.getText());
        float bodytemp = Float.parseFloat(bodyTempField.getText());
        String addrs = addressField.getText();
        String ph_num = phoneField.getText();
        String vaccine_name = vaccineField.getText();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_database_new", "root", "P@y@l123");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO registration_data (name, aadhar, gender, age, profession, temp, address, vaccineName, bloodPressure, contactNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, citznum);
            ps.setString(3, gender);
            ps.setInt(4, age);
            ps.setString(5, prof);
            ps.setFloat(6, bodytemp);
            ps.setString(7, addrs);
            ps.setString(8, vaccine_name);
            ps.setFloat(9, bp);
            ps.setString(10, ph_num);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Data inserted successfully!");
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error inserting data: " + ex.getMessage());
        }
    }

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
