//Himal Feature
//
// package com.example.cardealershipservice;
///**
// * Himal Shrestha
// * CSC 325 - Softare Engineering
// */
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class ThemeToggleApp extends JFrame implements ThemeSwitchable {
//    private JPanel mainPanel;
//    private ThemeToggleButton toggleButton;
//
//    public ThemeToggleApp() {
//        setTitle("Light/Dark Mode Toggle");
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        mainPanel = new JPanel(new BorderLayout());
//        add(mainPanel);
//
//        // Initialize and add the toggle button to the top-right corner
//        toggleButton = new ThemeToggleButton();
//        JPanel topBarPanel = new JPanel(new BorderLayout());
//        topBarPanel.add(toggleButton, BorderLayout.EAST);
//        mainPanel.add(topBarPanel, BorderLayout.NORTH);
//
//        // Register this frame with ThemeManager to receive theme updates
//        ThemeManager.getInstance().register(this);
//    }
//
//    // Implement applyTheme to change background color based on theme
//    @Override
//    public void applyTheme(Color backgroundColor) {
//        mainPanel.setBackground(backgroundColor);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            ThemeToggleApp app = new ThemeToggleApp();
//            app.setVisible(true);
//        });
//    }
//}
//
