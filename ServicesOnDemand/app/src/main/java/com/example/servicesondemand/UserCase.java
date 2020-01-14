package com.example.servicesondemand;

public class UserCase {
    public String category;
    public String details;
    public String phoneNumber;

    public UserCase(){

    }

    public UserCase(String category, String details, String phoneNumber) {
        this.category = category;
        this.details = details;
        this.phoneNumber = phoneNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
