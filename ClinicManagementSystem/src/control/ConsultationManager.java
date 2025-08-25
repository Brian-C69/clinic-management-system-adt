package control;

import adt.LinkedList;
import adt.ListInterface;
import boundary.ConsultationUI;
import entity.Consultation;
import entity.Doctor;
import entity.Patient;

public class ConsultationManager {
    private ListInterface<Consultation> consultationList = new LinkedList<>();
    private ConsultationUI consultUI = new ConsultationUI();

    // ================= Main Menu Loop =================
    public void runConsultationMaintenance() {
        int choice;
        do {
            choice = consultUI.getMenuChoice();
            switch (choice) {
                case 0 -> System.out.println("Exiting Consultation Management...");
                case 1 -> displayAllConsultations();
                case 2 -> addConsultation();
                case 3 -> updateConsultation();
                case 4 -> deleteConsultation();
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // CREATE
    public void addConsultation() {
        Consultation c = consultUI.inputConsultationDetails();
        consultationList.add(c);
        System.out.println("✅ Consultation added.");
    }

    // READ
    public void displayAllConsultations() {
        if (consultationList.size() == 0) {
            System.out.println("No consultations found.");
        } else {
            System.out.println("\n--- All Consultations ---");
            for (int i = 0; i < consultationList.size(); i++) {
                System.out.println(i + " -> " + consultationList.get(i));
            }
        }
    }

    public Consultation getConsultation(int index) {
        return (index >= 0 && index < consultationList.size()) ? consultationList.get(index) : null;
    }

    // UPDATE
    public void updateConsultation() {
        int index = consultUI.inputConsultationIndex();
        if (index >= 0 && index < consultationList.size()) {
            Consultation updated = consultUI.inputConsultationDetails();
            consultationList.replace(index, updated);
            System.out.println("✅ Consultation updated.");
        } else {
            System.out.println("Invalid consultation index.");
        }
    }

    // DELETE
    public void deleteConsultation() {
        int index = consultUI.inputConsultationIndex();
        if (index >= 0 && index < consultationList.size()) {
            Consultation removed = consultationList.remove(index);
            System.out.println("✅ Deleted consultation: " + removed.getConsultationId());
        } else {
            System.out.println("Invalid consultation index.");
        }
    }

    // Helper methods for Doctor/Patient deletion (optional integration)
    public void deleteConsultationsByDoctor(Doctor doctor) {
        for (int i = 0; i < consultationList.size(); i++) {
            Consultation c = consultationList.get(i);
            if (c.getDoctor() != null && c.getDoctor().equals(doctor)) {
                consultationList.remove(i);
                i--;
            }
        }
    }

    public void deleteConsultationsByPatient(Patient patient) {
        for (int i = 0; i < consultationList.size(); i++) {
            Consultation c = consultationList.get(i);
            if (c.getPatient() != null && c.getPatient().equals(patient)) {
                consultationList.remove(i);
                i--;
            }
        }
    }

    public int getSize() {
        return consultationList.size();
    }

    // ================= Testing =================
    public static void main(String[] args) {
        ConsultationManager consultCtrl = new ConsultationManager();
        consultCtrl.runConsultationMaintenance();
    }
}
