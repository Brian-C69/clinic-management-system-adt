// File: entity/Doctor.java
package entity;

import adt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Doctor {

    private String doctorId;
    private String name;
    private String mmcNumber;
    private String specialization;
    private String email;
    private String gender;
    private ListInterface<DutySlot> dutySchedule;
    private boolean isAvailable;
    private ListInterface<Consultation> consultations;
    private String status;

    // ----------------- INNER CLASS -----------------
    public static class DutySlot {

        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public DutySlot(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "DutySlot{"
                    + "start=" + startTime
                    + ", end=" + endTime
                    + '}';
        }
    }

    // Inside Doctor.java
    public boolean isAvailableAt(LocalDateTime time) {
        boolean inDuty = false;
        if (dutySchedule != null && !dutySchedule.isEmpty()) {
            for (int i = 0; i < dutySchedule.size(); i++) {
                DutySlot slot = dutySchedule.get(i);
                if (!time.isBefore(slot.getStartTime()) && !time.isAfter(slot.getEndTime())) {
                    inDuty = true;
                    break;
                }
            }
        }
        if (!inDuty) {
            return false;
        }

        // 2. Check consultations overlap
        if (consultations != null && !consultations.isEmpty()) {
            for (int i = 0; i < consultations.size(); i++) {
                Consultation c = consultations.get(i);
                LocalDateTime cStart = c.getStartTime();
                LocalDateTime cEnd = c.getEndTime();
                if (!time.isBefore(cStart) && !time.isAfter(cEnd)) {
                    return false; // Doctor is already busy
                }
            }
        }

        return true;
    }

    public void displayDoctorSchedule(Doctor doctor) {
        System.out.println("\n--- Duty Schedule for " + doctor.getName() + " ---");

        if (doctor.getDutySchedule() == null || doctor.getDutySchedule().isEmpty()) {
            System.out.println("No schedule available.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh:mm a");
        for (Doctor.DutySlot slot : doctor.getDutySchedule()) {
            System.out.println(slot.getStartTime().format(formatter) + " to " + slot.getEndTime().format(formatter));
        }
    }

    public boolean isAvailable(LocalDateTime desiredStart, int durationMinutes) {
        LocalDateTime desiredEnd = desiredStart.plusMinutes(durationMinutes);

        // Check against duty schedule
        if (dutySchedule == null || dutySchedule.isEmpty()) {
            return false; // No duty schedule means not available
        }
        for (DutySlot slot : dutySchedule) {
            if (!desiredStart.isBefore(slot.getStartTime()) && !desiredEnd.isAfter(slot.getEndTime())) {
                // The requested time fits inside a duty slot
                // Next, check if there are overlapping consultations
                if (consultations != null) {
                    for (Consultation c : consultations) {
                        LocalDateTime cStart = c.getConsultationDateTime();
                        LocalDateTime cEnd = cStart.plusMinutes(c.getDurationMinutes());
                        if (desiredStart.isBefore(cEnd) && desiredEnd.isAfter(cStart)) {
                            return false; // Overlaps with existing consultation
                        }
                    }
                }
                return true; // Fits in duty slot and no overlap
            }
        }
        return false; // Did not fit in any duty slot
    }

    public Doctor() {
    }

    public Doctor(String doctorId, String name, String mmcNumber, String specialization,
            String email, String gender, ListInterface<DutySlot> dutySchedule,
            boolean isAvailable, ListInterface<Consultation> consultations, String status) {
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

    // --- Getters and Setters ---
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

    public ListInterface<DutySlot> getDutySchedule() {
        return dutySchedule;
    }

    public void setDutySchedule(ListInterface<DutySlot> dutySchedule) {
        this.dutySchedule = dutySchedule;
    }

    public boolean isAvailable() {
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
        int dutyCount = dutySchedule != null ? dutySchedule.size() : 0;
        int consCount = consultations != null ? consultations.size() : 0;

        return String.format(
                "| %-10s | %-20s | %-20s | %-12s | %-25s | %-6s | %-10s | %-12s | %-13s |",
                doctorId,
                name,
                specialization,
                mmcNumber,
                email,
                gender,
                (isAvailable ? "Yes" : "No"),
                dutyCount,
                consCount,
                status
        );
    }
}
