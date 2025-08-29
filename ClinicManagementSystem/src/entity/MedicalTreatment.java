// File: entity/MedicalTreatment.java
package entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import adt.*;

public class MedicalTreatment implements Comparable<MedicalTreatment> {
    private String medicine;
    private String dosage;
    private String duration;
    private String instructions;
    private LocalDate startDate;
    private Patient patient;
    private Doctor doctor;

    @Override
    public int compareTo(MedicalTreatment other) {
        return this.getStartDate().compareTo(other.getStartDate());
    }
    

    
    public MedicalTreatment() {
    }

    public MedicalTreatment(String medicine, String dosage, String duration, String instructions, LocalDate startDate, Patient patient, Doctor doctor) {
        this.medicine = medicine;
        this.dosage = dosage;
        this.duration = duration;
        this.instructions = instructions;
        this.startDate = startDate;
        this.patient = patient;
        this.doctor = doctor;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "MedicalTreatment{" + "medicine=" + medicine + ", dosage=" + dosage + ", duration=" + duration + ", instructions=" + instructions + ", startDate=" + startDate + ", patient=" + patient + ", doctor=" + doctor + '}';
    }
    
    
}


