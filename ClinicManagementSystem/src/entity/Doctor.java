// File: entity/Doctor.java
package entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import adt.*; 

public class Doctor {
    private String doctorId;
    private String name;
    private String mmcNumber;
    private String specialization;
    private String email;
    private String gender;
    private ListInterface<String> dutySchedule;
    private boolean isAvailable;
    private ListInterface<Consultation> consultations;
    private String status;

    public Doctor() {
    }

    public Doctor(String doctorId, String name, LocalDate dateOfBirth, String icNumber, String mmcNumber, String specialization, String email, String gender, ListInterface<String> dutySchedule, boolean isAvailable, ListInterface<Consultation> consultations, String status) {
        this.doctorId = doctorId;
        this.name = name;
        this.mmcNumber = mmcNumber;
        this.specialization = specialization;
        this.email = email;
        this.gender = gender;
        this.dutySchedule = dutySchedule;
        this.isAvailable = isAvailable;
        this.consultations = consultations;
        this.status = status;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMmcNumber() {
        return mmcNumber;
    }

    public void setMmcNumber(String mmcNumber) {
        this.mmcNumber = mmcNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ListInterface<String> getDutySchedule() {
        return dutySchedule;
    }

    public void setDutySchedule(ListInterface<String> dutySchedule) {
        this.dutySchedule = dutySchedule;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public ListInterface<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(ListInterface<Consultation> consultations) {
        this.consultations = consultations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Doctor{" + "doctorId=" + doctorId + ", name=" + name + ", mmcNumber=" + mmcNumber + ", specialization=" + specialization + ", email=" + email + ", gender=" + gender + ", dutySchedule=" + dutySchedule + ", isAvailable=" + isAvailable + ", consultations=" + consultations + ", status=" + status + '}';
    }
    
    
}