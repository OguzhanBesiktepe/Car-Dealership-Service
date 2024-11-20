//Himal Feature

//package com.example.cardealershipservice;
////Himal original button
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class ThemeToggleFeature extends JFrame {
//    private JPanel mainPanel;
//    private CustomToggleButton toggleButton;
//
//    // Define light and dark theme background colors
//    private final Color lightBackground = Color.WHITE;
//    private final Color darkBackground = Color.DARK_GRAY;
//
//    public ThemeToggleFeature() {
//        setTitle("Light/Dark Mode Toggle"); // Set the window title
//        setSize(400, 300); // Set the size of the window
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application on window close
//
//        // Initialize the main panel with a BorderLayout to manage layout positions
//        mainPanel = new JPanel(new BorderLayout());
//
//        // Create and configure the custom toggle button
//        toggleButton = new CustomToggleButton();
//        toggleButton.setPreferredSize(new Dimension(70, 35)); // Set button size for better visibility of text
//
//        // Set the initial theme to light mode (toggleButton is initialized at this point)
//        setLightMode();
//
//        // Add a mouse listener to toggle between light and dark themes when clicked
//        toggleButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                // Check the toggle button's state and apply the corresponding theme
//                if (toggleButton.isSelected()) {
//                    setDarkMode(); // Apply dark mode if selected
//                } else {
//                    setLightMode(); // Apply light mode if deselected
//                }
//            }
//        });
//
//        // Create a top bar panel to hold the toggle button aligned to the right
//        JPanel topBarPanel = new JPanel(new BorderLayout());
//        topBarPanel.setOpaque(false); // Make top bar panel transparent
//        topBarPanel.add(toggleButton, BorderLayout.EAST); // Position toggle button on the right
//
//        // Add the top bar panel to the main panel, positioned at the top
//        mainPanel.add(topBarPanel, BorderLayout.NORTH);
//        add(mainPanel); // Add the main panel to the frame
//    }
//
//    // Method to set the light mode theme
//    private void setLightMode() {
//        mainPanel.setBackground(lightBackground); // Set background color for light mode
//        toggleButton.setSelected(false); // Set toggle button to "off" state
//    }
//
//    // Method to set the dark mode theme
//    private void setDarkMode() {
//        mainPanel.setBackground(darkBackground); // Set background color for dark mode
//        toggleButton.setSelected(true); // Set toggle button to "on" state
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            ThemeToggleFeature app = new ThemeToggleFeature(); // Create the application instance
//            app.setVisible(true); // Make the application window visible
//        });
//    }
//
//    // Custom toggle button class to create a rounded switch appearance
//    static class CustomToggleButton extends JToggleButton {
//        private final Color onColor = new Color(76, 175, 80);  // Green color for "On" state
//        private final Color offColor = Color.LIGHT_GRAY;       // Gray color for "Off" state
//
//        @Override
//        protected void paintComponent(Graphics g) {
//            Graphics2D g2d = (Graphics2D) g.create();
//            int width = getWidth();
//            int height = getHeight();
//
//            // Enable anti-aliasing for smooth edges on graphics
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//            // Set the background color based on the toggle's selected state
//            if (isSelected()) {
//                g2d.setColor(onColor); // Green background for "On"
//            } else {
//                g2d.setColor(offColor); // Gray background for "Off"
//            }
//
//            // Draw a rounded rectangle as the toggle button background
//            g2d.fillRoundRect(0, 0, width, height, height, height);
//
//            // Calculate and draw the sliding circle
//            int circleDiameter = height - 6; // Set circle diameter slightly smaller than height for padding
//            int circleX = isSelected() ? width - circleDiameter - 3 : 3; // Circle moves left or right based on state
//            g2d.setColor(Color.WHITE); // Circle color
//            g2d.fillOval(circleX, 3, circleDiameter, circleDiameter); // Draw the circle
//
//            // Draw the "On" or "Off" text centered within the toggle button
//            g2d.setColor(Color.WHITE); // Text color
//            g2d.setFont(new Font("Arial", Font.BOLD, 12)); // Text font and style
//            String text = isSelected() ? "On" : "Off"; // Set text based on the selected state
//            FontMetrics fm = g2d.getFontMetrics(); // Get font metrics for centering the text
//            int textX = (width - fm.stringWidth(text)) / 2; // Center text horizontally
//            int textY = (height + fm.getAscent()) / 2 - 2; // Center text vertically
//            g2d.drawString(text, textX, textY); // Draw the text
//
//            g2d.dispose(); // Dispose of the graphics object to free resources
//        }
//    }
//}
