package control;

import adt.LinkedList;
import adt.ListInterface;
import entity.Consultation;
import entity.Doctor;
import entity.Patient;

public class ConsultationManager {
    private ListInterface<Consultation> consultationList = new LinkedList<>();

    // CREATE
    public void addConsultation(Consultation consultation) {
        consultationList.add(consultation);
    }

    // READ
    public void displayAllConsultations() {
        for (int i = 0; i < consultationList.size(); i++) {
            System.out.println(i + " -> " + consultationList.get(i));
        }
    }

    public Consultation getConsultation(int index) {
        return consultationList.get(index);
    }

    // UPDATE
    public boolean updateConsultation(int index, Consultation updated) {
        return consultationList.replace(index, updated);
    }

    // DELETE
    public boolean deleteConsultation(int index) {
        return consultationList.remove(index);
    }

    // 🔹 NEW: delete consultations by doctor
    public void deleteConsultationsByDoctor(Doctor doctor) {
        for (int i = 0; i < consultationList.size(); i++) {
            Consultation c = consultationList.get(i);
            if (c.getDoctor().equals(doctor)) {
                consultationList.remove(i);
                i--; // adjust after removal
            }
        }
    }

    // 🔹 NEW: delete consultations by patient
    public void deleteConsultationsByPatient(Patient patient) {
        for (int i = 0; i < consultationList.size(); i++) {
            Consultation c = consultationList.get(i);
            if (c.getPatient().equals(patient)) {
                consultationList.remove(i);
                i--; // adjust after removal
            }
        }
    }

    // 🔹 NEW: size
    public int getSize() {
        return consultationList.size();
    }
}
