package com.example.cardealershipservice;

import javax.swing.*;
import java.awt.*;

public class ThemeToggleAppWithStudentTable extends JFrame {
    private JPanel mainPanel;
    private ThemeToggleButton toggleButton;
    private StudentTable studentTable;

    public ThemeToggleAppWithStudentTable() {
        setTitle("Light/Dark Mode Toggle with Student Table");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with BorderLayout
        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);

        // Initialize and add the toggle button to the top-right corner
        toggleButton = new ThemeToggleButton();
        JPanel topBarPanel = new JPanel(new BorderLayout());
        topBarPanel.add(toggleButton, BorderLayout.EAST);
        mainPanel.add(topBarPanel, BorderLayout.NORTH);

        // Create and add the student table to the main panel
        studentTable = new StudentTable();
        mainPanel.add(studentTable, BorderLayout.CENTER);

        // Register the main panel with ThemeManager to receive theme updates
        ThemeManager.getInstance().register(new ThemeSwitchable() {
            @Override
            public void applyTheme(Color backgroundColor) {
                mainPanel.setBackground(backgroundColor);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ThemeToggleAppWithStudentTable app = new ThemeToggleAppWithStudentTable();
            app.setVisible(true);
        });
    }
}
