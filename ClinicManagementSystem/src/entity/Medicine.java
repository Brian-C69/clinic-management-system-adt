package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import adt.*; 

public class Medicine {
    private String name;
    private String description;
    private String type;
    private int quantityAvailable;
    private double unitPrice;
    private LocalDate expiryDate;
    private String manufacturer;
    private String batchNumber;

    public Medicine() {
    }
    
    public Medicine(String name, String description, String type, int quantityAvailable, double unitPrice, LocalDate expiryDate, String manufacturer, String batchNumber) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.quantityAvailable = quantityAvailable;
        this.unitPrice = unitPrice;
        this.expiryDate = expiryDate;
        this.manufacturer = manufacturer;
        this.batchNumber = batchNumber;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }

    @Override
    public String toString() {
        return "Medicine{" + "name=" + name + ", description=" + description + ", type=" + type + ", quantityAvailable=" + quantityAvailable + ", unitPrice=" + unitPrice + ", expiryDate=" + expiryDate + ", manufacturer=" + manufacturer + ", batchNumber=" + batchNumber + '}';
    }
    
    
}

