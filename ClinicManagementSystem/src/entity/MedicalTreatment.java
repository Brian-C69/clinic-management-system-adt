// File: entity/MedicalTreatment.java
package entity;

/**
 *
 * @author Choong Yun Xian Brian
 */
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
    private String diagnosis;

    @Override
    public int compareTo(MedicalTreatment other) {
        if (this.startDate == null && other.startDate == null) {
            return 0;
        }
        if (this.startDate == null) {
            return -1;
        }
        if (other.startDate == null) {
            return 1;
        }
        return this.startDate.compareTo(other.startDate);
    }

    public MedicalTreatment() {
    }

    public MedicalTreatment(String medicine, String dosage, String duration, String instructions,
            LocalDate startDate, Patient patient, Doctor doctor, String diagnosis) {
        this.medicine = medicine;
        this.dosage = dosage;
        this.duration = duration;
        this.instructions = instructions;
        this.startDate = startDate;
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        String patientStr = patient != null ? patient.getName() : "-";
        String doctorStr = doctor != null ? doctor.getName() : "-";

        // Truncate long strings to fit table neatly
        String medStr = medicine != null && medicine.length() > 15 ? medicine.substring(0, 12) + "..." : medicine;
        String dosageStr = dosage != null && dosage.length() > 10 ? dosage.substring(0, 7) + "..." : dosage;
        String diagStr = diagnosis != null && diagnosis.length() > 20 ? diagnosis.substring(0, 17) + "..." : diagnosis;
        String instrStr = instructions != null && instructions.length() > 25 ? instructions.substring(0, 22) + "..." : instructions;

        return String.format(
                "| %-15s | %-10s | %-20s | %-20s | %-20s | %-24s | %-17s |",
                medStr,
                dosageStr,
                diagStr != null ? diagStr : "-",
                instrStr != null ? instrStr : "-",
                startDate != null ? startDate.toString() : "-",
                patientStr,
                doctorStr
        );
    }

}
