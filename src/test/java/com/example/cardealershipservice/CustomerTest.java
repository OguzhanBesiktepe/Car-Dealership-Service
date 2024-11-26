package com.example.cardealershipservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testCustomerConstructorAndGetters() {

        String firstName = "John";
        String lastName = "Doe";
        String contactInfo = "john.doe@example.com";
        String carModel = "Toyota Camry";
        String vin = "1HGCM82633A123456";
        int mileage = 120000;


        Customer customer = new Customer(firstName, lastName, contactInfo, carModel, vin, mileage);


        assertEquals(firstName, customer.getFirstName());
        assertEquals(lastName, customer.getLastName());
        assertEquals(contactInfo, customer.getContactInfo());
        assertEquals(carModel, customer.getCarModel());
        assertEquals(vin, customer.getVin());
        assertEquals(mileage, customer.getMileage());
        assertEquals("In Queue", customer.getStatus()); // Default status
    }

    @Test
    void testSetStatus() {

        Customer customer = new Customer("Jane", "Smith", "jane.smith@example.com", "Honda Civic", "2HGFG1B82DH123456", 90000);


        customer.setStatus("In Progress");


        assertEquals("In Progress", customer.getStatus());


        customer.setStatus("Completed");


        assertEquals("Completed", customer.getStatus());
    }
}