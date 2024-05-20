package vaccine.system.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewAllData_page {
	private Menu_page menuPage;

    public void showData() {
        JFrame frame = new JFrame("View All Data");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 750);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(45, 45, 45));  // Dark gray background

        JLabel titleLabel = new JLabel("VACCINATION RECORD OF PATIENT", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setForeground(Color.WHITE);  // White text color
        titleLabel.setBackground(new Color(60, 179, 113));  // Sea green background color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(45, 45, 45));  // Match frame background
        titlePanel.add(titleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        String[] columnNames = {"Name", "Aadhar Number", "Gender", "Age", "Profession", "Body Temperature", "Address", "Blood Pressure", "Mobile Number", "Vaccine Name"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        // Set table appearance
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setGridColor(new Color(169, 169, 169));  // Dark gray grid color
        table.setBackground(Color.WHITE);
        table.setForeground(new Color(0, 0, 0));  // Black text color

        // Set custom header renderer to make the text bold and set background color
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 16));
        header.setForeground(Color.WHITE);  // White text color
        header.setBackground(new Color(70, 130, 180));  // Steel blue background color
        header.setPreferredSize(new Dimension(header.getWidth(), 30));

        header.setDefaultRenderer(new TableCellRenderer() {
            private final TableCellRenderer delegate = header.getDefaultRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = delegate.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                comp.setFont(new Font("SansSerif", Font.BOLD, 16));
                comp.setForeground(Color.WHITE);
                comp.setBackground(new Color(70, 130, 180));
                return comp;
            }
        });

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccination_database_new", "root", "P@y@l123");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM registration_data");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String aadhar = rs.getString("aadhar");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                String profession = rs.getString("profession");
                float bodyTemp = rs.getFloat("temp");
                String address = rs.getString("address");
                String vaccineName = rs.getString("vaccineName");
                float bloodPressure = rs.getFloat("bloodPressure");
                String mobileNo = rs.getString("ContactNumber");

                Object[] data = {name, aadhar, gender, age, profession, bodyTemp, address, bloodPressure, mobileNo, vaccineName};
                tableModel.addRow(data);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Failed to fetch data from the database.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(220, 20, 60));  // Crimson background color
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        backButton.setPreferredSize(new Dimension(150, 50));  // Set preferred size (width, height)

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(45, 45, 45));  // Match frame background
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Action for back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                menuPage = new Menu_page();
                menuPage.setVisible(true);
            }
        });

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setVisible(boolean b) {
        // This method might not be necessary unless you want to handle visibility in a specific way
    }
}

