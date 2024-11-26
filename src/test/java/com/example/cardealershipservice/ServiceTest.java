package com.example.cardealershipservice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    void testServiceConstructorAndGetters() {

        String serviceName = "Oil Change";
        double serviceCost = 29.99;


        Service service = new Service(serviceName, serviceCost);

        assertEquals(serviceName, service.getName(), "The service name should match the input.");
        assertEquals(serviceCost, service.getCost(), "The service cost should match the input.");
    }

    @Test
    void testServiceWithZeroCost() {

        String serviceName = "Free Consultation";
        double serviceCost = 0.0;


        Service service = new Service(serviceName, serviceCost);


        assertEquals(serviceName, service.getName(), "The service name should be 'Free Consultation'.");
        assertEquals(serviceCost, service.getCost(), "The service cost should be 0.0.");
    }

    @Test
    void testServiceWithNegativeCost() {

        String serviceName = "Invalid Service";
        double serviceCost = -10.0;


        Service service = new Service(serviceName, serviceCost);


        assertEquals(serviceName, service.getName(), "The service name should match the input.");
        assertEquals(serviceCost, service.getCost(), "The service cost should allow negative values.");
    }
}
