// File: boundary/ClinicUI.java
package boundary;

import control.*;
import entity.*;
import adt.*;
import dao.ClinicInitializer;

import java.util.Scanner;

public class ClinicUI {

    // Shared controllers (single source of truth for doctor/patient/treatment)
    private final MaintainDoctor doctorCtrl = new MaintainDoctor();
    private final MaintainPatient patientCtrl = new MaintainPatient();
    private final TreatmentManager treatCtrl = new TreatmentManager();

    // UIs
    private final DoctorUI doctorUI = new DoctorUI(doctorCtrl);
    private final PatientUI patientUI = new PatientUI(patientCtrl);
    private final ConsultationUI consultUI = new ConsultationUI();
    private final MedicalTreatmentUI treatUI = new MedicalTreatmentUI();
    private final Scanner sc = new Scanner(System.in);

    // ✅ ConsultationManager (now seeded with data)
    private final ConsultationManager consultMgr;

    public ClinicUI() {
        ClinicInitializer init = new ClinicInitializer();
        ClinicData data = init.initializeClinic();

        // Seed doctors
        for (Doctor d : data.getDoctors()) doctorCtrl.addDoctor(d);
        // Seed patients
        for (Patient p : data.getPatients()) patientCtrl.addPatient(p);
        // Seed treatments (silent mode)
        for (MedicalTreatment t : data.getTreatments()) treatCtrl.addTreatment(t, true);

        // ✅ Seed consultations with shared doctor/patient controllers
        this.consultMgr = new ConsultationManager(
            data.getConsultations(),
            patientCtrl,
            patientUI,
            doctorCtrl,
            doctorUI,
            sc
        );

        System.out.println("\u2714 Sample data loaded: " 
            + data.getDoctors().size() + " doctors, "
            + data.getPatients().size() + " patients, "
            + data.getConsultations().size() + " consultations, "
            + data.getTreatments().size() + " treatments.");
    }

    public void run() {
        while (true) {
            System.out.println("\n===== Clinic Management System =====");
            System.out.println("1. Doctor Management");
            System.out.println("2. Patient Management");
            System.out.println("3. Consultation Management");
            System.out.println("4. Medical Treatment Management");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = readIntSafe();
            switch (choice) {
                case 1 -> doctorUI.runDoctorMaintenance();
                case 2 -> patientUI.runPatientMaintenance();
                case 3 -> consultMgr.runConsultationMaintenance();
                case 4 -> treatmentMenu();
                case 0 -> {
                    System.out.println("Exiting Clinic System...");
                    return;
                }
                default -> System.out.println("\u274C Invalid option.");
            }
        }
    }

    // ---------- Treatment Menu ----------
    private void treatmentMenu() {
        int choice;
        do {
            System.out.println("\n===== Medical Treatment Management =====");
            System.out.println("1. List all treatments");
            System.out.println("2. Add treatment");
            System.out.println("3. Update treatment");
            System.out.println("4. Delete treatment");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = readIntSafe();

            switch (choice) {
                case 1 -> treatCtrl.displayAllTreatments();
                case 2 -> addTreatmentFlow();
                case 3 -> updateTreatmentFlow();
                case 4 -> deleteTreatmentFlow();
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("\u274C Invalid option.");
            }
        } while (choice != 0);
    }

    private void addTreatmentFlow() {
        if (patientCtrl.getSize() == 0 || doctorCtrl.getSize() == 0) {
            System.out.println("\u26A0 Need at least 1 patient and 1 doctor before adding treatment.");
            return;
        }

        Patient patient = treatUI.selectPatient(patientCtrl);
        if (patient == null) return;

        Doctor doctor = treatUI.selectDoctor(doctorCtrl);
        if (doctor == null) return;

        MedicalTreatment t = treatUI.createTreatment(patient, doctor);
        treatCtrl.addTreatment(t);
    }

    private void updateTreatmentFlow() {
        if (treatCtrl.getTreatmentCount() == 0) {
            System.out.println("No treatments to update.");
            return;
        }
        treatCtrl.displayAllTreatments();
        System.out.print("Enter treatment index to update (starting from 1): ");
        int idx = readIntSafe() - 1;

        MedicalTreatment old = treatCtrl.getTreatment(idx);
        if (old == null) {
            System.out.println("\u274C Invalid index.");
            return;
        }

        MedicalTreatment updated = treatUI.updateTreatment(old, old.getPatient(), old.getDoctor());
        boolean ok = treatCtrl.updateTreatment(idx, updated);
        System.out.println(ok ? "\u2705 Treatment updated." : "\u274C Update failed.");
    }

    private void deleteTreatmentFlow() {
        if (treatCtrl.getTreatmentCount() == 0) {
            System.out.println("No treatments to delete.");
            return;
        }
        treatCtrl.displayAllTreatments();
        System.out.print("Enter treatment index to delete (starting from 1): ");
        int idx = readIntSafe() - 1;

        MedicalTreatment removed = treatCtrl.deleteTreatment(idx);
        if (removed != null) System.out.println("\u2705 Treatment deleted.");
        else System.out.println("\u274C Deletion failed.");
    }

    private int readIntSafe() {
        while (true) {
            String s = sc.nextLine().trim();
            try { return Integer.parseInt(s); }
            catch (NumberFormatException e) {
                System.out.print("Enter a number: ");
            }
        }
    }
}
