package entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import adt.*; 

public class Patient {
    private String name;
    private String icNumber;
    private LocalDate dateOfBirth;
    private String sex;
    private String contactNumber;
    private String email;
    private String address;
    private String panel;
    private String bloodType;
    private String allergyHistory;
    private String chronicConditions;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private LocalDate dateOfRegistration;
    private LocalDate lastVisitDate;
    private boolean isActive;
    private QueueInterface<Consultation> consultationQueue;

    public Patient() {
    }

    public Patient(String name, String icNumber, LocalDate dateOfBirth, String sex, String contactNumber, String email, String address, String panel, String bloodType, String allergyHistory, String chronicConditions, String emergencyContactName, String emergencyContactNumber, LocalDate dateOfRegistration, LocalDate lastVisitDate, boolean isActive, QueueInterface<Consultation> consultationQueue) {
        this.name = name;
        this.icNumber = icNumber;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.panel = panel;
        this.bloodType = bloodType;
        this.allergyHistory = allergyHistory;
        this.chronicConditions = chronicConditions;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.dateOfRegistration = dateOfRegistration;
        this.lastVisitDate = lastVisitDate;
        this.isActive = isActive;
        this.consultationQueue = consultationQueue;
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

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAllergyHistory() {
        return allergyHistory;
    }

    public void setAllergyHistory(String allergyHistory) {
        this.allergyHistory = allergyHistory;
    }

    public String getChronicConditions() {
        return chronicConditions;
    }

    public void setChronicConditions(String chronicConditions) {
        this.chronicConditions = chronicConditions;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
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

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public QueueInterface<Consultation> getConsultationQueue() {
        return consultationQueue;
    }

    public void setConsultationQueue(QueueInterface<Consultation> consultationQueue) {
        this.consultationQueue = consultationQueue;
    }

    @Override
    public String toString() {
        return "Patient{" + "name=" + name + ", icNumber=" + icNumber + ", dateOfBirth=" + dateOfBirth + ", sex=" + sex + ", contactNumber=" + contactNumber + ", email=" + email + ", address=" + address + ", panel=" + panel + ", bloodType=" + bloodType + ", allergyHistory=" + allergyHistory + ", chronicConditions=" + chronicConditions + ", emergencyContactName=" + emergencyContactName + ", emergencyContactNumber=" + emergencyContactNumber + ", dateOfRegistration=" + dateOfRegistration + ", lastVisitDate=" + lastVisitDate + ", isActive=" + isActive + ", consultationQueue=" + consultationQueue + '}';
    }
    
    
}
