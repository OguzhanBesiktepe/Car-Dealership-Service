package com.example.cardealershipservice;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FirestoreService {

    private final String DATABASE_URL = "https://firestore.googleapis.com/v1/projects/cardealershipappw/databases/(default)/documents";
    // Replace with your Firestore Database URL

    // Method to add a new customer
    public void addCustomer(String name, String contactInfo, String carModel, String vin, String mileage) throws Exception {
        String url = DATABASE_URL + "/customers";

        JsonObject fields = new JsonObject();
        fields.add("name", createStringValue(name));
        fields.add("contactInfo", createStringValue(contactInfo));
        fields.add("carModel", createStringValue(carModel));
        fields.add("vin", createStringValue(vin));
        fields.add("mileage", createStringValue(mileage));

        JsonObject customer = new JsonObject();
        customer.add("fields", fields);

        sendPostRequest(url, customer);
        System.out.println("Customer added successfully: " + name);
    }

    // Method to search for customers by name

    public List<JsonObject> searchCustomerByName(String name) throws Exception {
        String url = DATABASE_URL + ":runQuery"; // Firestore query endpoint

        // Create the query JSON payload
        JsonObject queryPayload = new JsonObject();
        JsonObject structuredQuery = new JsonObject();
        JsonObject from = new JsonObject();
        JsonObject where = new JsonObject();
        JsonObject fieldFilter = new JsonObject();
        JsonObject field = new JsonObject();
        JsonObject stringValue = new JsonObject();

        // Set up the "from" collection
        from.addProperty("collectionId", "customers");
        JsonArray fromArray = new JsonArray();  // Create the array first
        fromArray.add(from);
        structuredQuery.add("from", fromArray); // Add the array to the query

        // Set up the "where" condition
        field.addProperty("fieldPath", "name");
        stringValue.addProperty("stringValue", name);
        fieldFilter.add("field", field);
        fieldFilter.addProperty("op", "EQUAL");
        fieldFilter.add("value", stringValue);
        where.add("fieldFilter", fieldFilter);

        structuredQuery.add("where", where);
        queryPayload.add("structuredQuery", structuredQuery);

        // Send the POST request
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(queryPayload.toString(), StandardCharsets.UTF_8);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getCode() != 200) {
                    throw new RuntimeException("Failed with HTTP error code: " + response.getCode());
                }

                // Read and parse the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                JsonArray documents = JsonParser.parseString(result.toString()).getAsJsonArray();
                List<JsonObject> customers = new ArrayList<>();
                for (JsonElement element : documents) {
                    if (element.getAsJsonObject().has("document")) {
                        JsonObject documentFields = element.getAsJsonObject()
                                .getAsJsonObject("document")
                                .getAsJsonObject("fields");
                        customers.add(documentFields);
                    }
                }
                return customers;
            }
        }
    }


    // Method to add an accessory for a specific customer
    public void addAccessory(String customerId, String accessoryName, String price) throws Exception {
        String url = DATABASE_URL + "/customers/" + customerId + "/accessories";

        JsonObject fields = new JsonObject();
        fields.add("name", createStringValue(accessoryName));
        fields.add("price", createStringValue(price));

        JsonObject accessory = new JsonObject();
        accessory.add("fields", fields);

        sendPostRequest(url, accessory);
        System.out.println("Accessory added for customer ID: " + customerId);
    }

    // Method to add a service detail for a specific customer
    public void addServiceDetail(String customerId, String serviceDetail, String cost) throws Exception {
        String url = DATABASE_URL + "/customers/" + customerId + "/services";

        JsonObject fields = new JsonObject();
        fields.add("detail", createStringValue(serviceDetail));
        fields.add("cost", createStringValue(cost));

        JsonObject service = new JsonObject();
        service.add("fields", fields);

        sendPostRequest(url, service);
        System.out.println("Service detail added for customer ID: " + customerId);
    }

    // Helper method to send POST requests
    private void sendPostRequest(String url, JsonObject jsonObject) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8);
            httpPost.setEntity(entity);

            httpPost.setHeader("Content-Type", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                if (response.getCode() != 200) {
                    throw new RuntimeException("Failed with HTTP error code: " + response.getCode());
                }
            }
        }
    }

    // Helper method to create Firestore field JSON
    private JsonObject createStringValue(String value) {
        JsonObject stringValue = new JsonObject();
        stringValue.addProperty("stringValue", value);
        return stringValue;
    }
}
