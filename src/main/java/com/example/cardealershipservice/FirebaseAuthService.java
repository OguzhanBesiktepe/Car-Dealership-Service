package com.example.cardealershipservice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FirebaseAuthService {

    private final String API_KEY = "AIzaSyDv5zctxdxFGnXtTC0g_oQ68qhqRUWjjgU";  // Replace with your Firebase API key

    /**
     * Sign in a user with email and password.
     *
     * @param email    User's email.
     * @param password User's password.
     * @return ID token if successful.
     * @throws Exception If login fails.
     */
    public String signInWithEmailPassword(String email, String password) throws Exception {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);

            // Create JSON request body
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("email", email);
            requestBody.addProperty("password", password);
            requestBody.addProperty("returnSecureToken", true);

            // Set JSON payload as entity
            StringEntity entity = new StringEntity(requestBody.toString(), StandardCharsets.UTF_8);
            httpPost.setEntity(entity);

            // Set the Content-Type header
            httpPost.setHeader("Content-Type", "application/json");

            // Execute the HTTP request and read the response
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder responseContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }

                // Parse the response
                JsonObject jsonResponse = JsonParser.parseString(responseContent.toString()).getAsJsonObject();
                if (jsonResponse.has("idToken")) {
                    return jsonResponse.get("idToken").getAsString();  // Return ID token
                } else {
                    throw new Exception("Login failed: " + jsonResponse.toString());
                }
            }
        }
    }

    /**
     * Register a new user with email and password.
     *
     * @param email    User's email.
     * @param password User's password.
     * @throws Exception If registration fails.
     */
    public void createUserWithEmailPassword(String email, String password) throws Exception {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);

            // Create JSON request body
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("email", email);
            requestBody.addProperty("password", password);
            requestBody.addProperty("returnSecureToken", true);

            // Set JSON payload as entity
            StringEntity entity = new StringEntity(requestBody.toString(), StandardCharsets.UTF_8);
            httpPost.setEntity(entity);

            // Set the Content-Type header
            httpPost.setHeader("Content-Type", "application/json");

            // Execute the HTTP request and read the response
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder responseContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }

                // Parse the response
                JsonObject jsonResponse = JsonParser.parseString(responseContent.toString()).getAsJsonObject();
                if (!jsonResponse.has("idToken")) {
                    throw new Exception("Registration failed: " + jsonResponse.toString());
                }
            }
        }
    }

    /**
     * Send a password reset email.
     *
     * @param email User's email.
     * @throws Exception If sending fails.
     */
    public void sendPasswordResetEmail(String email) throws Exception {
        String url = "https://identitytoolkit.googleapis.com/v1/accounts:sendOobCode?key=" + API_KEY;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);

            // Create JSON request body
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("requestType", "PASSWORD_RESET");
            requestBody.addProperty("email", email);

            // Set JSON payload as entity
            StringEntity entity = new StringEntity(requestBody.toString(), StandardCharsets.UTF_8);
            httpPost.setEntity(entity);

            // Set the Content-Type header
            httpPost.setHeader("Content-Type", "application/json");

            // Execute the HTTP request and read the response
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder responseContent = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }

                // Parse the response
                JsonObject jsonResponse = JsonParser.parseString(responseContent.toString()).getAsJsonObject();
                if (jsonResponse.has("error")) {
                    throw new Exception("Error sending password reset email: " + jsonResponse.toString());
                }
            }
        }
    }
}