package com.example.cardealershipservice;

import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterControllerTest {

    private RegisterController registerController;

    @BeforeEach
    void setUp() {
        registerController = new RegisterController();


        registerController.emailField = new TextField();
        registerController.passwordField = new PasswordField();
    }

    @Test
    void testHandleRegisterAction_ValidInput() {

        registerController.emailField.setText("test@example.com");
        registerController.passwordField.setText("password123");


        try {
            registerController.handleRegisterAction();


            assertTrue(true, "Method executed successfully with valid input.");
        } catch (Exception e) {
            fail("Method threw an unexpected exception: " + e.getMessage());
        }
    }

    @Test
    void testHandleRegisterAction_InvalidInput() {

        registerController.emailField.setText("");
        registerController.passwordField.setText("");


        try {
            registerController.handleRegisterAction();
            fail("Expected exception or error handling for invalid input.");
        } catch (Exception e) {

            assertNotNull(e, "Exception thrown as expected for invalid input.");
        }
    }
}
