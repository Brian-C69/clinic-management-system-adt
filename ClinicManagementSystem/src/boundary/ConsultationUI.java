package boundary;

import adt.ListInterface;
import control.ConsultationManager;
import control.MaintainDoctor;
import control.MaintainPatient;
import entity.Consultation;
import entity.Doctor;
import entity.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsultationUI {

    private Scanner sc = new Scanner(System.in);
    private ConsultationManager consultMgr;
    private MaintainDoctor doctorCtrl;
    private MaintainPatient patientCtrl;

    public ConsultationUI(ConsultationManager consultMgr, MaintainDoctor doctorCtrl, MaintainPatient patientCtrl) {
        this.consultMgr = consultMgr;
        this.doctorCtrl = doctorCtrl;
        this.patientCtrl = patientCtrl;
    }


    public void runConsultationMaintenance() {
        int choice;
        do {
            System.out.println("\n--- Consultation Management ---");
            System.out.println("1. Display All Consultations");
            System.out.println("2. Add Consultation");
            System.out.println("3. Update Consultation");
            System.out.println("4. Delete Consultation");
            System.out.println("0. Return To Main Menu");
            System.out.print("Enter choice: ");
            choice = getValidatedInteger("");

            switch (choice) {
                case 1 -> displayAllConsultations();
                case 2 -> addConsultation();
                case 3 -> updateConsultation();
                case 4 -> deleteConsultation();
                case 0 -> System.out.println("Exiting Consultation Management...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ========= UI Methods =========

    public void displayAllConsultations() {
        if (consultMgr.getSize() == 0) {
            System.out.println("No consultations available.");
        } else {
            for (int i = 0; i < consultMgr.getSize(); i++) {
                System.out.println("Index: " + (i + 1));
                printConsultation(consultMgr.getConsultation(i));
            }
        }
    }

    public void addConsultation() {
        if (patientCtrl.getSize() == 0 || doctorCtrl.getSize() == 0) {
            System.out.println("⚠ Need at least one patient and one doctor to add consultation.");
            return;
        }

        Consultation c = new Consultation();

        System.out.print("Enter Consultation ID: ");
        c.setConsultationId(sc.nextLine().trim());

        System.out.print("Enter Date & Time (yyyy-MM-dd HH:mm): ");
        String dt = sc.nextLine().trim();
        c.setConsultationDateTime(LocalDateTime.parse(dt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        // Pick Patient
        Patient p = pickPatient();
        if (p == null) return;
        c.setPatient(p);

        // Pick Doctor
        Doctor d = pickDoctor();
        if (d == null) return;
        c.setDoctor(d);

        System.out.print("Enter Symptoms: ");
        c.setSymptoms(sc.nextLine().trim());

        System.out.print("Enter Diagnosis: ");
        c.setDiagnosis(sc.nextLine().trim());

        System.out.print("Enter Notes: ");
        c.setNotes(sc.nextLine().trim());

        System.out.print("Enter Status: ");
        c.setStatus(sc.nextLine().trim());

        System.out.print("Enter Duration (minutes): ");
        c.setDurationMinutes(Integer.parseInt(sc.nextLine().trim()));

        System.out.print("Enter Fee: ");
        c.setConsultationFee(Double.parseDouble(sc.nextLine().trim()));

        System.out.print("Is follow-up? (true/false): ");
        c.setIsFollowUp(Boolean.parseBoolean(sc.nextLine().trim()));

        System.out.print("Next appointment (yyyy-MM-dd, empty for none): ");
        String nextApp = sc.nextLine().trim();
        if (!nextApp.isEmpty()) {
            c.setNextAppointment(LocalDate.parse(nextApp));
        }

        consultMgr.addConsultation(c);
    }

    public void updateConsultation() {
        displayAllConsultations();
        if (consultMgr.getSize() == 0) return;

        System.out.print("Enter consultation index to update: ");
        int index = Integer.parseInt(sc.nextLine().trim()) - 1;

        Consultation c = consultMgr.getConsultation(index);
        if (c == null) {
            System.out.println("Invalid index.");
            return;
        }

        System.out.print("Enter new Symptoms: ");
        c.setSymptoms(sc.nextLine().trim());

        System.out.print("Enter new Diagnosis: ");
        c.setDiagnosis(sc.nextLine().trim());

        System.out.print("Enter new Notes: ");
        c.setNotes(sc.nextLine().trim());

        System.out.print("Enter new Status: ");
        c.setStatus(sc.nextLine().trim());

        System.out.print("Enter new Duration (minutes): ");
        c.setDurationMinutes(Integer.parseInt(sc.nextLine().trim()));

        System.out.print("Enter new Fee: ");
        c.setConsultationFee(Double.parseDouble(sc.nextLine().trim()));

        System.out.print("Is follow-up? (true/false): ");
        c.setIsFollowUp(Boolean.parseBoolean(sc.nextLine().trim()));

        System.out.println("✅ Consultation updated successfully.");
    }

    public void deleteConsultation() {
        displayAllConsultations();
        if (consultMgr.getSize() == 0) return;

        System.out.print("Enter consultation index to delete: ");
        int index = Integer.parseInt(sc.nextLine().trim()) - 1;

        Consultation removed = consultMgr.deleteConsultation(index);
        if (removed != null) {
            System.out.println("✅ Consultation deleted: " + removed.getConsultationId());
        }
    }

    // ========= Selection Helpers =========

    private Patient pickPatient() {
        System.out.println("\n--- Select Patient ---");
        for (int i = 0; i < patientCtrl.getSize(); i++) {
            Patient p = patientCtrl.getPatient(i);
            System.out.printf("%d. %s (%s)%n", i + 1, p.getName(), p.getPatientID());
        }
        System.out.print("Enter patient index (starting from 1, 0 to cancel): ");
        int ix = Integer.parseInt(sc.nextLine());
        if (ix == 0) return null;
        return patientCtrl.getPatient(ix - 1);
    }

    private Doctor pickDoctor() {
        System.out.println("\n--- Select Doctor ---");
        for (int i = 0; i < doctorCtrl.getSize(); i++) {
            Doctor d = doctorCtrl.getDoctor(i);
            System.out.printf("%d. %s (%s)%n", i + 1, d.getName(), d.getDoctorId());
        }
        System.out.print("Enter doctor index (starting from 1, 0 to cancel): ");
        int ix = Integer.parseInt(sc.nextLine());
        if (ix == 0) return null;
        return doctorCtrl.getDoctor(ix - 1);
    }

    // ========= Helpers =========
    private void printConsultation(Consultation c) {
        System.out.println("-----------------------------");
        System.out.println("Consultation ID: " + c.getConsultationId());
        System.out.println("Date & Time: " + c.getConsultationDateTime());
        System.out.println("Patient: " + (c.getPatient() != null ? c.getPatient().getName() : "-"));
        System.out.println("Doctor: " + (c.getDoctor() != null ? c.getDoctor().getName() : "-"));
        System.out.println("Symptoms: " + c.getSymptoms());
        System.out.println("Diagnosis: " + c.getDiagnosis());
        System.out.println("Notes: " + c.getNotes());
        System.out.println("Status: " + c.getStatus());
        System.out.println("Duration: " + c.getDurationMinutes() + " minutes");
        System.out.println("Fee: RM" + c.getConsultationFee());
        System.out.println("Follow-up: " + c.getisIsFollowUp());
    }

    private int getValidatedInteger(String prompt) {
        while (true) {
            try {
                if (!prompt.isEmpty()) System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
