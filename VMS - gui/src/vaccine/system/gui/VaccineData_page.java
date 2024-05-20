//package vaccine.system.gui;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//import java.sql.*;
//
//@SuppressWarnings("serial")
//public class VaccineData_page extends JFrame {
//    private JTextArea textArea;
//    private JLabel totalVaccinesLabel;
//
//    public VaccineData_page() {
//        // Set up the frame
//        setTitle("Vaccine Quantity Page");
//        setSize(800, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        // Set up the panel and layout
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout(10, 10));
//        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
//        panel.setBackground(new Color(240, 248, 255));  // Light blue background
//        add(panel);
//
//        // Title label
//        JLabel titleLabel = new JLabel("VACCINE DATA", SwingConstants.CENTER);
//        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
//        titleLabel.setForeground(new Color(0, 128, 128));  // Teal color
//        panel.add(titleLabel, BorderLayout.NORTH);
//
//        // Text area for displaying vaccine data
//        textArea = new JTextArea();
//        textArea.setEditable(false);
//        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
//        textArea.setBorder(BorderFactory.createCompoundBorder(
//                BorderFactory.createLineBorder(new Color(0, 128, 128)),
//                BorderFactory.createEmptyBorder(10, 10, 10, 10)
//        ));
//        textArea.setBackground(new Color(255, 255, 240));  // Light yellow background
//        JScrollPane scrollPane = new JScrollPane(textArea);
//        panel.add(scrollPane, BorderLayout.CENTER);
//
//        // Label for displaying total vaccinations
//        totalVaccinesLabel = new JLabel("Total number of vaccinations administered: 0", SwingConstants.CENTER);
//        totalVaccinesLabel.setFont(new Font("Arial", Font.BOLD, 16));
//        totalVaccinesLabel.setForeground(new Color(0, 128, 128));  // Teal color
//        panel.add(totalVaccinesLabel, BorderLayout.SOUTH);
//
//        // Load vaccine data
//        loadVaccineData();
//    }
//
//    private void loadVaccineData() {
//        int totalVaccines = 0;
//
//        try {
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_database_new", "root", "P@y@l123");
//            PreparedStatement ps = con.prepareStatement("SELECT vaccineName FROM registration_data");
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                String vaccineName = rs.getString("vaccineName");
//                textArea.append("Vaccine Name: " + vaccineName + "\n");
//                totalVaccines++;
//            }
//
//            totalVaccinesLabel.setText("Total number of vaccinations administered: " + totalVaccines);
//
//            // Close resources
//            rs.close();
//            ps.close();
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Error loading vaccine data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}
//

package vaccine.system.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

@SuppressWarnings("serial")
public class VaccineData_page extends JFrame {
    private JTextArea textArea;
    private JLabel totalVaccinesLabel;

    public VaccineData_page() {
        // Set up the frame
        setTitle("Vaccine Quantity Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up the panel and layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(240, 248, 255));  // Light blue background
        add(panel);

        // Title label
        JLabel titleLabel = new JLabel("VACCINE DATA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 128, 128));  // Teal color
        panel.add(titleLabel, BorderLayout.NORTH);

        // Text area for displaying vaccine data
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 128, 128)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        textArea.setBackground(new Color(255, 255, 240));  // Light yellow background
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel for bottom components
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout(10, 10));
        bottomPanel.setBackground(new Color(240, 248, 255));  // Light blue background

        // Label for displaying total vaccinations
        totalVaccinesLabel = new JLabel("Total number of vaccinations administered: 0", SwingConstants.CENTER);
        totalVaccinesLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalVaccinesLabel.setForeground(new Color(0, 128, 128));  // Teal color
        bottomPanel.add(totalVaccinesLabel, BorderLayout.CENTER);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(220, 20, 60));  // Crimson background color
        backButton.setFocusPainted(false);
        backButton.setPreferredSize(new Dimension(100, 40));  // Set preferred size
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle back button action
                dispose();  // Close current window
                Menu_page menuPage = new Menu_page();
                menuPage.setVisible(true);
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 248, 255));  // Match background
        buttonPanel.add(backButton);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Load vaccine data
        loadVaccineData();
    }

    private void loadVaccineData() {
        int totalVaccines = 0;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_database_new", "root", "P@y@l123");
            PreparedStatement ps = con.prepareStatement("SELECT vaccineName FROM registration_data");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String vaccineName = rs.getString("vaccineName");
                textArea.append("Vaccine Name: " + vaccineName + "\n");
                totalVaccines++;
            }

            totalVaccinesLabel.setText("Total number of vaccinations administered: " + totalVaccines);

            // Close resources
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading vaccine data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



