package control;

import adt.LinkedList;
import adt.ListInterface;
import entity.Consultation;

public class ConsultationManager {

    private ListInterface<Consultation> consultationList;

    public ConsultationManager() {
        this.consultationList = new LinkedList<>();
    }

    public ConsultationManager(ListInterface<Consultation> store) {
        this.consultationList = store;
    }

    public void addConsultation(Consultation c) {
        if (c != null) {
            consultationList.add(c);
            System.out.println("✅ Consultation added.");
        } else {
            System.out.println("❌ Cannot add null consultation.");
        }
    }

    public boolean updateConsultation(int index, Consultation newData) {
        if (index < 0 || index >= consultationList.size() || newData == null) {
            return false;
        }
        Consultation old = consultationList.get(index);

        if (newData.getConsultationDateTime() != null)
            old.setConsultationDateTime(newData.getConsultationDateTime());
        if (newData.getPatient() != null)
            old.setPatient(newData.getPatient());
        if (newData.getDoctor() != null)
            old.setDoctor(newData.getDoctor());
        if (newData.getSymptoms() != null)
            old.setSymptoms(newData.getSymptoms());
        if (newData.getDiagnosis() != null)
            old.setDiagnosis(newData.getDiagnosis());
        if (newData.getNotes() != null)
            old.setNotes(newData.getNotes());
        if (newData.getNextAppointment() != null)
            old.setNextAppointment(newData.getNextAppointment());

        old.setIsFollowUp(newData.getisIsFollowUp());
        old.setDurationMinutes(newData.getDurationMinutes());
        old.setConsultationFee(newData.getConsultationFee());
        if (newData.getStatus() != null)
            old.setStatus(newData.getStatus());

        if (newData.getTreatments() != null && newData.getTreatments().size() > 0) {
            old.setTreatments(newData.getTreatments());
        }

        return true;
    }

    public Consultation deleteConsultation(int index) {
        if (consultationList.isEmpty() || index < 0 || index >= consultationList.size()) {
            System.out.println("❌ Invalid index or no consultations available.");
            return null;
        }
        return consultationList.remove(index);
    }

    public Consultation getConsultation(int index) {
        return (index >= 0 && index < consultationList.size()) ? consultationList.get(index) : null;
    }

    public ListInterface<Consultation> getAllConsultations() {
        return consultationList;
    }

    public int getSize() {
        return consultationList.size();
    }
}
