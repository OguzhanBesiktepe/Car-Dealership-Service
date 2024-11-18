package com.example.cardealershipservice;

import com.google.gson.JsonObject;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.nio.charset.StandardCharsets;

public class FirestoreService {

    private final String DATABASE_URL = "https://firestore.googleapis.com/v1/projects/cardealershipappw/databases/(default)/documents";
    // Replace with your Firestore Database URL

    // Method to add a new customer
    public void addCustomer(String name, String contactInfo, String carModel, String vin, String mileage) throws Exception {
        String url = DATABASE_URL + "/customers.json";

        JsonObject customer = new JsonObject();
        customer.addProperty("name", name);
        customer.addProperty("contactInfo", contactInfo);
        customer.addProperty("carModel", carModel);
        customer.addProperty("vin", vin);
        customer.addProperty("mileage", mileage);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(customer.toString(), StandardCharsets.UTF_8);
            httpPost.setEntity(entity);

            // Set the Content-Type header on the HttpPost object
            httpPost.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                System.out.println("Customer Added Successfully");
            }
        }
    }

    // Method to add an accessory to a specific customer
    public void addAccessory(String customerId, String accessoryName, String price) throws Exception {
        String url = DATABASE_URL + "/customers/" + customerId + "/accessories.json";

        JsonObject accessory = new JsonObject();
        accessory.addProperty("name", accessoryName);
        accessory.addProperty("price", price);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(accessory.toString(), StandardCharsets.UTF_8);
            httpPost.setEntity(entity);

            // Set the Content-Type header on the HttpPost object
            httpPost.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                System.out.println("Accessory Added for Customer " + customerId);
            }
        }
    }

    // Method to add a service detail to a specific customer
    public void addServiceDetail(String customerId, String serviceDetail, String cost) throws Exception {
        String url = DATABASE_URL + "/customers/" + customerId + "/services.json";

        JsonObject service = new JsonObject();
        service.addProperty("detail", serviceDetail);
        service.addProperty("cost", cost);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(service.toString(), StandardCharsets.UTF_8);
            httpPost.setEntity(entity);

            // Set the Content-Type header on the HttpPost object
            httpPost.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                System.out.println("Service Detail Added for Customer " + customerId);
            }
        }
    }
}