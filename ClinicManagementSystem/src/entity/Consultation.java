// File: entity/Consultation.java
package entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import adt.*; 

public class Consultation {
    private String consultationId;
    private LocalDateTime consultationDateTime;
    private Patient patient;
    private Doctor doctor;
    private String symptoms;
    private String diagnosis;
    private ListInterface<MedicalTreatment> treatments;
    private String notes;
    private LocalDate nextAppointment;
    private boolean isFollowUp;
    private int durationMinutes;
    private double consultationFee;
    private String status;

    public Consultation() {
    }

    public Consultation(String consultationId, LocalDateTime consultationDateTime, Patient patient, Doctor doctor, String symptoms, String diagnosis, ListInterface<MedicalTreatment> treatments, String notes, LocalDate nextAppointment, boolean isFollowUp, int durationMinutes, double consultationFee, String status) {
        this.consultationId = consultationId;
        this.consultationDateTime = consultationDateTime;
        this.patient = patient;
        this.doctor = doctor;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.treatments = treatments;
        this.notes = notes;
        this.nextAppointment = nextAppointment;
        this.isFollowUp = isFollowUp;
        this.durationMinutes = durationMinutes;
        this.consultationFee = consultationFee;
        this.status = status;
    }

    public String getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(String consultationId) {
        this.consultationId = consultationId;
    }

    public LocalDateTime getConsultationDateTime() {
        return consultationDateTime;
    }

    public void setConsultationDateTime(LocalDateTime consultationDateTime) {
        this.consultationDateTime = consultationDateTime;
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

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public ListInterface<MedicalTreatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(ListInterface<MedicalTreatment> treatments) {
        this.treatments = treatments;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getNextAppointment() {
        return nextAppointment;
    }

    public void setNextAppointment(LocalDate nextAppointment) {
        this.nextAppointment = nextAppointment;
    }

    public boolean isIsFollowUp() {
        return isFollowUp;
    }

    public void setIsFollowUp(boolean isFollowUp) {
        this.isFollowUp = isFollowUp;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
    String pid = patient != null ? patient.getPatientID() + " - " + patient.getName() : "-";
    String did = doctor != null ? doctor.getDoctorId() + " - " + doctor.getName() : "-";
    String when = consultationDateTime != null ? consultationDateTime.toString() : "-";
    return String.format("Consultation[%s] @ %s | Patient: %s | Doctor: %s | Status: %s | Fee: %.2f",
            consultationId, when, pid, did, status, consultationFee);
}

    
    
}

