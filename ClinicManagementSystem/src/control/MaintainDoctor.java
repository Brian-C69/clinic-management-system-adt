package control;

import adt.LinkedList;
import entity.Doctor;
import boundary.DoctorUI;

public class MaintainDoctor {
    private LinkedList<Doctor> doctorList = new LinkedList<>();

    public void addDoctor(Doctor d) {
        doctorList.add(d);
    }

    public boolean replaceDoctor(int index, Doctor newDoctor) {
        if (index >= 0 && index < doctorList.size()) {
            doctorList.replace(index, newDoctor);
            return true;
        }
        return false;
    }

    public boolean updateExistingDoctor(int index, Doctor doctorNewData) {
        if (index >= 0 && index < doctorList.size()) {
            Doctor existingDoctor = doctorList.get(index);
            if (doctorNewData.getDoctorId() != null) {
                existingDoctor.setDoctorId(doctorNewData.getDoctorId());
            }
            if (doctorNewData.getName() != null) {
                existingDoctor.setName(doctorNewData.getName());
            }
            if (doctorNewData.getDateOfBirth() != null) {
                existingDoctor.setDateOfBirth(doctorNewData.getDateOfBirth());
            }
            if (doctorNewData.getIcNumber() != null) {
                existingDoctor.setIcNumber(doctorNewData.getIcNumber());
            }
            if (doctorNewData.getMmcNumber() != null) {
                existingDoctor.setMmcNumber(doctorNewData.getMmcNumber());
            }
            if (doctorNewData.getSpecialization() != null) {
                existingDoctor.setSpecialization(doctorNewData.getSpecialization());
            }
            if (doctorNewData.getType() != null) {
                existingDoctor.setType(doctorNewData.getType());
            }
            if (doctorNewData.getPhone() != null) {
                existingDoctor.setPhone(doctorNewData.getPhone());
            }
            if (doctorNewData.getEmail() != null) {
                existingDoctor.setEmail(doctorNewData.getEmail());
            }
            if (doctorNewData.getAddress() != null) {
                existingDoctor.setAddress(doctorNewData.getAddress());
            }
            if (doctorNewData.getGender() != null) {
                existingDoctor.setGender(doctorNewData.getGender());
            }
            if (doctorNewData.getDutySchedule() != null) {
                existingDoctor.setDutySchedule(doctorNewData.getDutySchedule());
            }
            if (doctorNewData.isIsAvailable() != existingDoctor.isIsAvailable()) {
                existingDoctor.setIsAvailable(doctorNewData.isIsAvailable());
            }
            if (doctorNewData.getConsultations() != null) {
                existingDoctor.setConsultations(doctorNewData.getConsultations());
            }
            if (doctorNewData.getConsultationRate() != 0.0) {
                existingDoctor.setConsultationRate(doctorNewData.getConsultationRate());
            }
            if (doctorNewData.getDateJoined() != null) {
                existingDoctor.setDateJoined(doctorNewData.getDateJoined());
            }
            if (doctorNewData.getTotalPatientsSeen() != 0) {
                existingDoctor.setTotalPatientsSeen(doctorNewData.getTotalPatientsSeen());
            }
            if (doctorNewData.getStatus() != null) {
                existingDoctor.setStatus(doctorNewData.getStatus());
            }
            return true;
        }
        return false;
    }

    public boolean deleteDoctor(int index) {
        return doctorList.remove(index);
    }

    public void displayAllDoctors() {
        for (int i = 0; i < doctorList.size(); i++) {
            System.out.println(i + ": " + doctorList.get(i));
        }
    }

    // 🔹 NEW: return doctor by index
    public Doctor getDoctor(int index) {
        if (index >= 0 && index < doctorList.size()) {
            return doctorList.get(index);
        }
        return null;
    }

    // 🔹 NEW: find doctor by ID
    public Doctor findDoctorById(String doctorId) {
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor d = doctorList.get(i);
            if (d.getDoctorId().equals(doctorId)) {
                return d;
            }
        }
        return null;
    }
}
