// File: entity/Patient.java
package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import adt.*;

public class Patient {

    private String patientID;
    private String name;
    private String icNumber;
    private LocalDate dateOfBirth;
    private String sex;
    private String contactNumber;
    private String allergyHistory;
    private LocalDate dateOfRegistration;
    private LocalDate lastVisitDate;
    private boolean isActive;
    private String queueNumber;

    public Patient() {
    }

    public Patient(String patientID, String name, String icNumber, LocalDate dateOfBirth, String sex, String contactNumber, String allergyHistory, LocalDate dateOfRegistration, LocalDate lastVisitDate, boolean isActive, String queueNumber) {
        this.name = patientID;
        this.name = name;
        this.icNumber = icNumber;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.contactNumber = contactNumber;
        this.allergyHistory = allergyHistory;
        this.dateOfRegistration = dateOfRegistration;
        this.lastVisitDate = lastVisitDate;
        this.isActive = isActive;
        this.queueNumber = queueNumber;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAllergyHistory() {
        return allergyHistory;
    }

    public void setAllergyHistory(String allergyHistory) {
        this.allergyHistory = allergyHistory;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public LocalDate getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(LocalDate lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getQueueNumber() {
        return queueNumber;
    }

    public void setQueueNumber(String queueNumber) {
        this.queueNumber = queueNumber;
    }

    @Override
    public String toString() {
        return String.format(
                "\n---------------- Patient Information ----------------\n"
                + "| %-15s : %-30s |\n"
                + "| %-15s : %-30s |\n"
                + "| %-15s : %-30s |\n"
                + "| %-15s : %-30s |\n"
                + "| %-15s : %-30s |\n"
                + "| %-15s : %-30s |\n"
                + "| %-15s : %-30s |\n"
                + "| %-15s : %-30s |\n"
                + "| %-15s : %-30s |\n"
                + "| %-15s : %-30s |\n"
                + "| %-15s : %-30s |\n"
                + "-----------------------------------------------------\n",
                "Patient ID", patientID,
                "Name", name,
                "IC Number", icNumber,
                "Date of Birth", (dateOfBirth != null ? dateOfBirth : "-"),
                "Sex", sex,
                "Contact No.", contactNumber,
                "Allergies", allergyHistory,
                "Registered", (dateOfRegistration != null ? dateOfRegistration : "-"),
                "Last Visit", (lastVisitDate != null ? lastVisitDate : "-"),
                "Active", isActive,
                "Queue No.", queueNumber
        );
    }

}
