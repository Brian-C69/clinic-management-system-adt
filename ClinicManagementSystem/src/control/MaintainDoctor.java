package control;

import adt.ListInterface;
import adt.LinkedList;
import entity.Doctor;

public class MaintainDoctor {
    private ListInterface<Doctor> doctorList = new LinkedList<>();

    public void addDoctor(Doctor d) {
        doctorList.add(d);
    }

    public boolean replaceDoctor(int index, Doctor newDoctor) {
        return doctorList.replace(index, newDoctor);
    }

    public boolean updateExistingDoctor(int index, Doctor doctorNewData) {
        if (index >= 0 && index < doctorList.size()) {
            Doctor existingDoctor = doctorList.get(index);

            if (doctorNewData.getDoctorId() != null) existingDoctor.setDoctorId(doctorNewData.getDoctorId());
            if (doctorNewData.getName() != null) existingDoctor.setName(doctorNewData.getName());
            if (doctorNewData.getMmcNumber() != null) existingDoctor.setMmcNumber(doctorNewData.getMmcNumber());
            if (doctorNewData.getSpecialization() != null) existingDoctor.setSpecialization(doctorNewData.getSpecialization());
            if (doctorNewData.getEmail() != null) existingDoctor.setEmail(doctorNewData.getEmail());
            if (doctorNewData.getGender() != null) existingDoctor.setGender(doctorNewData.getGender());
            if (doctorNewData.getDutySchedule() != null) existingDoctor.setDutySchedule(doctorNewData.getDutySchedule());
            if (doctorNewData.isIsAvailable() != existingDoctor.isIsAvailable()) existingDoctor.setIsAvailable(doctorNewData.isIsAvailable());
            if (doctorNewData.getConsultations() != null) existingDoctor.setConsultations(doctorNewData.getConsultations());
            if (doctorNewData.getStatus() != null) existingDoctor.setStatus(doctorNewData.getStatus());

            return true;
        }
        return false;
    }

    public Doctor deleteDoctor(int index) {
        return doctorList.remove(index);
    }

    public void displayAllDoctors() {
        for (int i = 0; i < doctorList.size(); i++) {
            System.out.println(i + ": " + doctorList.get(i));
        }
    }

    public Doctor getDoctor(int index) {
        return (index >= 0 && index < doctorList.size()) ? doctorList.get(index) : null;
    }

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
