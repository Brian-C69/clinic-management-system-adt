// File: boundary/ClinicUI.java
package boundary;

import control.*;
import entity.*;
import dao.ClinicInitializer;

import java.util.Scanner;

public class ClinicUI {

    // One Scanner for the whole app
    private final Scanner sc = new Scanner(System.in);

    // Shared controllers (single source of truth)
    private final MaintainDoctor doctorCtrl   = new MaintainDoctor();
    private final MaintainPatient patientCtrl = new MaintainPatient();
    private final TreatmentManager treatCtrl  = new TreatmentManager();

    // UIs (inject the same scanner everywhere)
    private final DoctorUI doctorUI               = new DoctorUI(doctorCtrl, sc);
    private final PatientUI patientUI             = new PatientUI(patientCtrl, sc);
    private final ConsultationUI consultationUI   = new ConsultationUI();
    private final MedicalTreatmentUI treatUI      = new MedicalTreatmentUI(sc);

    // Consultation module (uses shared controllers, shared list, and shared scanner)
    private ConsultationManager consultMgr;

    // ---------------- boot with seed data & wire modules ----------------
    public ClinicUI() {
        seedData();
    }

    private void seedData() {
        ClinicInitializer init = new ClinicInitializer();
        ClinicData data = init.initializeClinic();

        // Seed doctors & patients into shared controllers
        for (Doctor d : data.getDoctors()) {
            doctorCtrl.addDoctor(d);
        }
        for (Patient p : data.getPatients()) {
            patientCtrl.addPatient(p);
        }

        // Seed treatments into shared manager
        for (MedicalTreatment t : data.getTreatments()) {
            treatCtrl.addTreatment(t, true);
        }

        // Build ConsultationManager with shared controllers, shared consultation list, and same Scanner
        consultMgr = new ConsultationManager(
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
                case 4 -> treatUI.runTreatmentMaintenance(
                            treatCtrl, patientCtrl, doctorCtrl, sc);
                case 0 -> {
                    System.out.println("Exiting Clinic System...");
                    return;
                }
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }

    // ---------- Input helper ----------
    private int readIntSafe() {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Enter a number: ");
            }
        }
    }

    // Optional entry point
    public static void main(String[] args) {
        new ClinicUI().run();
    }
}
