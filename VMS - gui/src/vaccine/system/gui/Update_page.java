package vaccine.system.gui;

import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;

public class Update_page {
    private JFrame frame;

    public Update_page() {
        frame = new JFrame("Update Page");
        frame.setSize(800, 600); // Set the initial size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); // Use null layout
        
        // Load the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mukes\\eclipse-workspace\\VMS - gui\\Images\\updatepage.jpg"); // Replace "path/to/your/image.jpg" with the actual path to your image
        Image image = imageIcon.getImage(); // Transform it 
        Image newImg = image.getScaledInstance(800, 200, java.awt.Image.SCALE_SMOOTH); // Scale the image to fit the frame
        imageIcon = new ImageIcon(newImg); // Convert back to ImageIcon
        
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, 800, 190); // Set bounds for image label
        frame.add(imageLabel);
        
        JLabel instructionLabel = new JLabel("Select field you want to update :-");
        instructionLabel.setBounds(70, 250, 300, 30); // Set bounds for instruction label
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font size to 15 and style to bold
        frame.add(instructionLabel);

        String[] fields = {"Name", "Aadhar Number", "Gender", "Age", "Profession", "Body Temperature", "Address", "Blood Pressure", "Mobile Number", "Vaccine Name"};
        JComboBox<String> fieldComboBox = new JComboBox<>(fields);
        fieldComboBox.setBounds(400, 245, 300, 40); // Set bounds for combo box
        frame.add(fieldComboBox);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(420, 400, 150, 50); // Set bounds for update button
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedField = (String) fieldComboBox.getSelectedItem();
                updateData(selectedField);
            }
        });
        frame.add(updateButton);
        
        JButton backButton = new JButton("Back");
        backButton.setBounds(230, 400, 150, 50); // Set bounds for back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current frame
                new Menu_page().setVisible(true); // Open the menu page
            }
        });
        frame.add(backButton);
        frame.setVisible(true);
    }

    private void updateData(String field) {
        // Show dialog to get Aadhar number input
        String adhar = JOptionPane.showInputDialog(frame, "Enter Aadhar Number:");
        if (adhar == null || adhar.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Aadhar Number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_database_new", "root", "P@y@l123");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM registration_data");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // User found, allow for updating
                String currentValue = rs.getString(field.toLowerCase().replace(" ", "_"));
                String newValue = JOptionPane.showInputDialog(frame, "Enter updated " + field + ":", currentValue);

                // Prepare and execute update statement
                PreparedStatement updatePs = conn.prepareStatement("UPDATE registration_data SET " + field.toLowerCase().replace(" ", "_") + " = ? WHERE aadhar = ?");
                updatePs.setString(1, newValue);
                updatePs.setString(2, adhar);
                updatePs.executeUpdate();

                JOptionPane.showMessageDialog(frame, field + " updated successfully!");
            } else {
                // User not found
                JOptionPane.showMessageDialog(frame, "User with Aadhar Number " + adhar + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Close resources
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "An error occurred while updating user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
