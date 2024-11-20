//Himal Feature

//package com.example.cardealershipservice;
//
//import java.awt.Color;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ThemeManager {
//    private static ThemeManager instance = null;
//    private boolean isDarkMode = false; // Current theme state
//    private final List<ThemeSwitchable> listeners = new ArrayList<>();
//
//    // Theme colors
//    public static final Color LIGHT_BACKGROUND = Color.WHITE;
//    public static final Color DARK_BACKGROUND = Color.DARK_GRAY;
//
//    // Private constructor to enforce singleton pattern
//    private ThemeManager() {}
//
//    // Get the single instance of ThemeManager
//    public static ThemeManager getInstance() {
//        if (instance == null) {
//            instance = new ThemeManager();
//        }
//        return instance;
//    }
//
//    // Toggle the theme and notify all listeners
//    public void toggleTheme() {
//        isDarkMode = !isDarkMode;
//        notifyListeners();
//    }
//
//    // Register a component to receive theme change notifications
//    public void register(ThemeSwitchable component) {
//        listeners.add(component);
//        component.applyTheme(isDarkMode ? DARK_BACKGROUND : LIGHT_BACKGROUND); // Apply initial theme
//    }
//
//    // Notify all registered components of the theme change
//    private void notifyListeners() {
//        Color background = isDarkMode ? DARK_BACKGROUND : LIGHT_BACKGROUND;
//        for (ThemeSwitchable listener : listeners) {
//            listener.applyTheme(background);
//        }
//    }
//
//    // Check if the current theme is dark mode
//    public boolean isDarkMode() {
//        return isDarkMode;
//    }
//}
