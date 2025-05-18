package com.example.fraud;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String transactionId;
    private String customerId;
    private Double amount;
    private String country;
    private boolean kycVerified;
    private String transactionDate;

    // Getters and Setters
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public boolean isKycVerified() { return kycVerified; }
    public void setKycVerified(boolean kycVerified) { this.kycVerified = kycVerified; }
    public String getTransactionDate() { return transactionDate; }
    public void setTransactionDate(String transactionDate) { this.transactionDate = transactionDate; }
}