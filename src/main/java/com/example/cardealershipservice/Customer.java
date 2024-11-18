package com.example.cardealershipservice;

public class Customer {
    private String firstName;
    private String lastName;
    private String contactInfo;
    private String carModel;
    private String vin;
    private int mileage;
    private String status;

    public Customer(String firstName, String lastName, String contactInfo, String carModel, String vin, int mileage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
        this.carModel = carModel;
        this.vin = vin;
        this.mileage = mileage;
        this.status = "In Queue"; // Default status
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getContactInfo() { return contactInfo; }
    public String getCarModel() { return carModel; }
    public String getVin() { return vin; }
    public int getMileage() { return mileage; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }
}
