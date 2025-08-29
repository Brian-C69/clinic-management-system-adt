package control;

import adt.LinkedList;
import adt.ListInterface;
import entity.Doctor;

public class MaintainDoctor {
    private ListInterface<Doctor> doctorList;

    public MaintainDoctor() { this.doctorList = new LinkedList<>(); }
    public MaintainDoctor(ListInterface<Doctor> store) { this.doctorList = store; }

    public void addDoctor(Doctor d) {
        if (d == null) return;
        if (d.getDoctorId() == null || findDoctorById(d.getDoctorId()) != null) return;
        doctorList.add(d);
    }

    public boolean updateExistingDoctor(int index, Doctor nd) {
        if (index < 0 || index >= doctorList.size() || nd == null) return false;
        Doctor ex = doctorList.get(index);
        if (nd.getDoctorId() != null) ex.setDoctorId(nd.getDoctorId());
        if (nd.getName() != null) ex.setName(nd.getName());
        if (nd.getMmcNumber() != null) ex.setMmcNumber(nd.getMmcNumber());
        if (nd.getSpecialization() != null) ex.setSpecialization(nd.getSpecialization());
        if (nd.getEmail() != null) ex.setEmail(nd.getEmail());
        if (nd.getGender() != null) ex.setGender(nd.getGender());
        if (nd.getDutySchedule() != null) ex.setDutySchedule(nd.getDutySchedule());
        ex.setIsAvailable(nd.isIsAvailable());
        if (nd.getConsultations() != null) ex.setConsultations(nd.getConsultations());
        if (nd.getStatus() != null) ex.setStatus(nd.getStatus());
        return true;
    }

    public Doctor deleteDoctor(int index) {
        if (index < 0 || index >= doctorList.size()) return null;
        return doctorList.remove(index);
    }

    public Doctor getDoctor(int index) {
        return (index >= 0 && index < doctorList.size()) ? doctorList.get(index) : null;
    }

    public Doctor findDoctorById(String id) {
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor d = doctorList.get(i);
            if (d != null && d.getDoctorId() != null && d.getDoctorId().equalsIgnoreCase(id)) return d;
        }
        return null;
    }

    public ListInterface<Doctor> getAllDoctors() { return doctorList; }
    public int getSize() { return doctorList.size(); }
}
