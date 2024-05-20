package vaccine.system.gui;

import javax.swing.*;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class Search_page extends JFrame implements ActionListener {
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultArea;
    private JButton goBackButton; // Added Go Back Button
    private Menu_page menuPage; // Added Menu_page instance
    private JRadioButton aadharRadio;
    private JRadioButton nameRadio;
    private JRadioButton vaccineRadio;

    public Search_page() {
        setTitle("Search Patient");
        setSize(800, 600); // Set the initial size
        setLocationRelativeTo(null); // Center the window initially
        setLayout(null); // Use null layout

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(190, 230, 100, 30); // Set bounds for search label
        searchLabel.setFont(new Font("Arial", Font.BOLD, 15)); // Set font size to 15 and style to bold
        add(searchLabel);

        searchField = new JTextField(15);
        searchField.setBounds(280, 230, 150, 30); // Set bounds for search field
        add(searchField);

        // Create a JPanel for radio buttons
        JPanel radioPanel = new JPanel(new GridLayout(3, 1)); // GridLayout with 3 rows and 1 column
        radioPanel.setBounds(520, 200, 150, 100); // Set bounds for the radio button panel
        aadharRadio = new JRadioButton("Aadhar Number");
        nameRadio = new JRadioButton("Name");
        vaccineRadio = new JRadioButton("Vaccine Name");

        // Add radio buttons to the panel
        radioPanel.add(aadharRadio);
        radioPanel.add(nameRadio);
        radioPanel.add(vaccineRadio);
        add(radioPanel);

        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(aadharRadio);
        searchGroup.add(nameRadio);
        searchGroup.add(vaccineRadio);

        searchButton = new JButton("Search");
        searchButton.setBounds(400, 320, 100, 30); // Set bounds for search button
        searchButton.addActionListener(this);
        add(searchButton);

        resultArea = new JTextArea(30, 80);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(190, 360, 400, 200); // Set bounds for result area
        add(scrollPane);
        
        // Load the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\mukes\\eclipse-workspace\\VMS - gui\\Images\\searchpage.jpg");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, 800, 170); // Adjust position and size
        add(imageLabel);


        // Go Back Button
        goBackButton = new JButton("Go Back");
        goBackButton.setBounds(280, 320, 100, 30); // Adjust position and size
        goBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Define action to go back
                dispose(); // Close current window
                menuPage = new Menu_page(); // Show the Menu_page
                menuPage.setVisible(true);
            }
        });
        add(goBackButton);
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            // Check if the search field is empty
            if (searchField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a search value.", "Message", JOptionPane.INFORMATION_MESSAGE);
                return; // Exit the method if the search field is empty
            }
            search();
        }
    }

    private void search() {
        if (!aadharRadio.isSelected() && !nameRadio.isSelected() && !vaccineRadio.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please choose a search criteria.", "Message", JOptionPane.INFORMATION_MESSAGE);
            return; // Exit the method if no option is selected
        }

        String searchCriteria = "";
        String searchCriteriaName = "";
        if (aadharRadio.isSelected()) {
            searchCriteria = "aadhar";
            searchCriteriaName = "Aadhar Number";
            // Check if the entered value is a valid Aadhar number
            if (!searchField.getText().matches("\\d{12}")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid 12-digit Aadhar number.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method if the Aadhar number format is invalid
            }
        } else if (nameRadio.isSelected()) {
            searchCriteria = "name";
            searchCriteriaName = "Name";
        } else if (vaccineRadio.isSelected()) {
            searchCriteria = "vaccine_name";
            searchCriteriaName = "Vaccine Name";
        }

        String searchValue = searchField.getText();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_database_new", "root", "P@y@l123");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM registration_data WHERE " + searchCriteria + " LIKE ?");
            ps.setString(1, "%" + searchValue + "%");

            ResultSet rs = ps.executeQuery();
            resultArea.setText(""); // Clear previous results

            boolean found = false;
            while (rs.next()) {
                found = true;
                resultArea.append("\n\t---Patient Data According To Search---\n\n");
                resultArea.append("\n\t Name :-  " + rs.getString("name"));
                resultArea.append("\n\t Aadhar Number :-  " + rs.getString("aadhar"));
                resultArea.append("\n\t Gender :-  " + rs.getString("gender"));
                resultArea.append("\n\t Age :-  " + rs.getString("age"));
                resultArea.append("\n\t Profession :-  " + rs.getString("profession"));
                resultArea.append("\n\t Body Temperature :-  " + rs.getString("temp"));
                resultArea.append("\n\t Address :-  " + rs.getString("address"));
                resultArea.append("\n\t Blood Pressure :-  " + rs.getString("vaccineName"));
                resultArea.append("\n\t Blood Pressure :-  " + rs.getString("bloodPressure"));
                resultArea.append("\n\t Mobile Number :-  " + rs.getString("ContactNumber"));
                resultArea.append("\n\t-------------------------------------------\n");
            }
            if (!found) {
                String message = "No Records Found for " + searchCriteriaName + " : " + searchValue;
                JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.PLAIN_MESSAGE);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while searching.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

