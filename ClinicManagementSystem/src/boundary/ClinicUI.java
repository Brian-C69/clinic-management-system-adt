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
        // Seed treatments
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

        System.out.println("✔ Sample data loaded: " 
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
                default -> System.out.println("❌ Invalid option.");
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
                default -> System.out.println("❌ Invalid option.");
            }
        } while (choice != 0);
    }

    private void addTreatmentFlow() {
        if (patientCtrl.getSize() == 0 || doctorCtrl.getSize() == 0) {
            System.out.println("⚠ Need at least 1 patient and 1 doctor before adding treatment.");
            return;
        }

        Patient patient = pickPatient();
        if (patient == null) return;

        Doctor doctor = pickDoctor();
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
            System.out.println("❌ Invalid index.");
            return;
        }

        MedicalTreatment updated = treatUI.updateTreatment(old, old.getPatient(), old.getDoctor());
        boolean ok = treatCtrl.updateTreatment(idx, updated);
        System.out.println(ok ? "✅ Treatment updated." : "❌ Update failed.");
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
        if (removed != null) System.out.println("✅ Treatment deleted.");
        else System.out.println("❌ Deletion failed.");
    }

    // ---------- Selection Helpers ----------
    private Patient pickPatient() {
        System.out.println("\n--- Select Patient ---");
        for (int i = 0; i < patientCtrl.getSize(); i++) {
            Patient p = patientCtrl.getPatient(i);
            System.out.printf("%d. %s (%s)%n", i + 1, p.getName(), p.getPatientID());
        }
        System.out.print("Enter patient index (starting from 1, 0 to cancel): ");
        int ix = readIntSafe();
        if (ix == 0) return null;
        Patient p = patientCtrl.getPatient(ix - 1);
        if (p == null) System.out.println("❌ Invalid patient index.");
        return p;
    }

    private Doctor pickDoctor() {
        System.out.println("\n--- Select Doctor ---");
        for (int i = 0; i < doctorCtrl.getSize(); i++) {
            Doctor d = doctorCtrl.getDoctor(i);
            System.out.printf("%d. %s (%s)%n", i + 1, d.getName(), d.getDoctorId());
        }
        System.out.print("Enter doctor index (starting from 1, 0 to cancel): ");
        int ix = readIntSafe();
        if (ix == 0) return null;
        Doctor d = doctorCtrl.getDoctor(ix - 1);
        if (d == null) System.out.println("❌ Invalid doctor index.");
        return d;
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
