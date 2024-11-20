//Himal Feature

//package com.example.cardealershipservice;
//
//import com.example.cardealershipservice.ThemeSwitchable;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//
//public class StudentTable extends JPanel implements ThemeSwitchable {
//    private JTable table;
//    private DefaultTableModel tableModel;
//
//    public StudentTable() {
//        setLayout(new BorderLayout());
//
//        // Define columns for the table
//        String[] columnNames = {"ID", "Name", "Grade"};
//
//        // Sample data for the table
//        Object[][] data = {
//                {1, "Alice Johnson", "A"},
//                {2, "Bob Smith", "B"},
//                {3, "Cathy Brown", "C"},
//                {4, "David White", "A"},
//                {5, "Ella Black", "B"},
//        };
//
//        // Create the table model and the table
//        tableModel = new DefaultTableModel(data, columnNames);
//        table = new JTable(tableModel);
//
//        // Set table properties for a better appearance
//        table.setFillsViewportHeight(true);
//        table.setRowHeight(25);
//        table.setFont(new Font("Arial", Font.PLAIN, 14));
//        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
//        table.setGridColor(Color.GRAY);
//
//        // Add the table to a scroll pane and then add to the panel
//        JScrollPane scrollPane = new JScrollPane(table);
//        add(scrollPane, BorderLayout.CENTER);
//
//        // Register with ThemeManager to receive theme updates
//        ThemeManager.getInstance().register(this);
//
//        // Apply initial theme
//        applyTheme(ThemeManager.getInstance().isDarkMode() ? ThemeManager.DARK_BACKGROUND : ThemeManager.LIGHT_BACKGROUND);
//    }
//
//    // Apply theme by changing the background color of the table and header
//    @Override
//    public void applyTheme(Color backgroundColor) {
//        setBackground(backgroundColor);
//        table.setBackground(backgroundColor);
//        table.setForeground(backgroundColor.equals(Color.DARK_GRAY) ? Color.WHITE : Color.BLACK);
//        table.getTableHeader().setBackground(backgroundColor);
//        table.getTableHeader().setForeground(backgroundColor.equals(Color.DARK_GRAY) ? Color.WHITE : Color.BLACK);
//    }
//}
