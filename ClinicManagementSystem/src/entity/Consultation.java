// File: entity/Consultation.java
/**
 *
 * @author Bryan Kok Fong Wen
 */
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

    private ListInterface<MedicalTreatment> treatments;
    private String notes;
    private LocalDate nextAppointment;
    private boolean isFollowUp;
    private int durationMinutes;
    private double consultationFee;
    private String status;

    public Consultation() {
    }

    public Consultation(String consultationId, LocalDateTime consultationDateTime, Patient patient, Doctor doctor,
            String symptoms, ListInterface<MedicalTreatment> treatments, String notes,
            LocalDate nextAppointment, boolean isFollowUp, int durationMinutes, double consultationFee,
            String status) {
        this.consultationId = consultationId;
        this.consultationDateTime = consultationDateTime;
        this.patient = patient;
        this.doctor = doctor;
        this.symptoms = symptoms;
        this.treatments = treatments;
        this.notes = notes;
        this.nextAppointment = nextAppointment;
        this.isFollowUp = isFollowUp;
        this.durationMinutes = durationMinutes;
        this.consultationFee = consultationFee;
        this.status = status;
    }

    // ===== NEW METHODS =====
    public LocalDateTime getStartTime() {
        return consultationDateTime;
    }

    public LocalDateTime getEndTime() {
        if (consultationDateTime != null && durationMinutes > 0) {
            return consultationDateTime.plusMinutes(durationMinutes);
        }
        return consultationDateTime; // fallback: same as start time
    }

    // ===== Existing Getters/Setters =====
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

    public boolean isFollowUp() {
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
        String patientStr = patient != null ? patient.getPatientID() + " - " + patient.getName() : "-";
        String doctorStr = doctor != null ? doctor.getDoctorId() + " - " + doctor.getName() : "-";
        String consDate = consultationDateTime != null ? consultationDateTime.toString() : "-";
        String nextApp = nextAppointment != null ? nextAppointment.toString() : "-";

        // Shorten long strings to prevent misalignment
        if (patientStr.length() > 25) {
            patientStr = patientStr.substring(0, 22) + "...";
        }
        if (doctorStr.length() > 25) {
            doctorStr = doctorStr.substring(0, 22) + "...";
        }
        String symptomsStr = symptoms != null && symptoms.length() > 15 ? symptoms.substring(0, 12) + "..." : symptoms;
        String notesStr = notes != null && notes.length() > 20 ? notes.substring(0, 17) + "..." : notes;

        return String.format(
                "| %-10s | %-16s | %-25s | %-25s | %-15s | %-20s | %-8s | %-10s | %-16s |",
                consultationId,
                consDate,
                patientStr,
                doctorStr,
                symptomsStr != null ? symptomsStr : "-",
                notesStr != null ? notesStr : "-",
                durationMinutes,
                status != null ? status : "-",
                nextApp
        );
    }
}
