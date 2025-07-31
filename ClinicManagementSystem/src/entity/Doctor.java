package entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import adt.*; 

public class Doctor {
    private String doctorId;
    private String name;
    private LocalDate dateOfBirth;
    private String icNumber;
    private String mmcNumber;
    private String specialization;
    private String type;
    private String phone;
    private String email;
    private String address;
    private String gender;
    private ListInterface<String> dutySchedule;
    private boolean isAvailable;
    private ListInterface<Consultation> consultations;
    private double consultationRate;
    private LocalDate dateJoined;
    private int totalPatientsSeen;
    private String status;

    public Doctor() {
    }

    public Doctor(String doctorId, String name, LocalDate dateOfBirth, String icNumber, String mmcNumber, String specialization, String type, String phone, String email, String address, String gender, ListInterface<String> dutySchedule, boolean isAvailable, ListInterface<Consultation> consultations, double consultationRate, LocalDate dateJoined, int totalPatientsSeen, String status) {
        this.doctorId = doctorId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.icNumber = icNumber;
        this.mmcNumber = mmcNumber;
        this.specialization = specialization;
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.dutySchedule = dutySchedule;
        this.isAvailable = isAvailable;
        this.consultations = consultations;
        this.consultationRate = consultationRate;
        this.dateJoined = dateJoined;
        this.totalPatientsSeen = totalPatientsSeen;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getConsultationRate() {
        return consultationRate;
    }

    public void setConsultationRate(double consultationRate) {
        this.consultationRate = consultationRate;
    }

    public LocalDate getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(LocalDate dateJoined) {
        this.dateJoined = dateJoined;
    }

    public int getTotalPatientsSeen() {
        return totalPatientsSeen;
    }

    public void setTotalPatientsSeen(int totalPatientsSeen) {
        this.totalPatientsSeen = totalPatientsSeen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Doctor{" + "doctorId=" + doctorId + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", icNumber=" + icNumber + ", mmcNumber=" + mmcNumber + ", specialization=" + specialization + ", type=" + type + ", phone=" + phone + ", email=" + email + ", address=" + address + ", gender=" + gender + ", dutySchedule=" + dutySchedule + ", isAvailable=" + isAvailable + ", consultations=" + consultations + ", consultationRate=" + consultationRate + ", dateJoined=" + dateJoined + ", totalPatientsSeen=" + totalPatientsSeen + ", status=" + status + '}';
    }
    
    
}