package control;

import adt.LinkedList;
import adt.ListInterface;
import boundary.ConsultationUI;
import entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsultationManager {
    private ListInterface<Consultation> consultationList = new LinkedList<>();
    private ConsultationUI consultUI = new ConsultationUI();
    private MaintainPatient patientCtrl = new MaintainPatient();
    private MaintainDoctor doctorCtrl = new MaintainDoctor();
    private Scanner sc = new Scanner(System.in);

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
        // ========== Select or Register Patient ==========
        System.out.println("\n--- Select Patient ---");
        if (patientCtrl.getSize() > 0) {
            patientCtrl.displayAllPatients();
        } else {
            System.out.println("⚠ No patients registered yet.");
        }

        System.out.print("Enter patient index (0 to register new): ");
        int pChoice = getValidatedIndex("", patientCtrl.getSize(), true);

        Patient selectedPatient;
        if (pChoice == 0) {
            System.out.println("\n--- Register New Patient ---");
            selectedPatient = patientCtrl.inputPatientDetails();
            patientCtrl.addPatient(selectedPatient);
        } else {
            selectedPatient = patientCtrl.getPatient(pChoice - 1);
        }

        // ========== Select or Register Doctor ==========
        System.out.println("\n--- Select Doctor ---");
        if (doctorCtrl.getSize() > 0) {
            doctorCtrl.displayAllDoctors();
        } else {
            System.out.println("⚠ No doctors registered yet.");
        }

        System.out.print("Enter doctor index (0 to register new): ");
        int dChoice = getValidatedIndex("", doctorCtrl.getSize(), true);

        Doctor selectedDoctor;
        if (dChoice == 0) {
            System.out.println("\n--- Register New Doctor ---");
            selectedDoctor = doctorCtrl.inputDoctorDetails();
            doctorCtrl.addDoctor(selectedDoctor);
        } else {
            selectedDoctor = doctorCtrl.getDoctor(dChoice - 1);
        }

        // Consultation ID validation
        String consultationId;
        while (true) {
            System.out.print("Enter Consultation ID (format: C001): ");
            consultationId = sc.nextLine().trim();
            if (consultationId.matches("^C\\d{3}$")) break;
            System.out.println("❌ Invalid format! ID must start with 'C' followed by 3 digits (e.g., C001).");
        }

        // DateTime
        LocalDateTime dateTime = LocalDateTime.now();

        // Symptoms (cannot be empty)
        String symptoms = getNonEmptyInput("Enter symptoms: ");

        // Diagnosis (cannot be empty)
        String diagnosis = getNonEmptyInput("Enter diagnosis: ");

        // Notes
        String notes = getNonEmptyInput("Enter notes: ");

        // Next appointment
        System.out.print("Enter next appointment date (yyyy-mm-dd, leave empty if none): ");
        String nextApptStr = sc.nextLine().trim();
        LocalDate nextAppointment = nextApptStr.isEmpty() ? null : LocalDate.parse(nextApptStr);

        // Duration
        int durationMinutes = getValidatedInteger("Enter duration in minutes: ");

        // Fee
        double consultationFee = getValidatedDouble("Enter consultation fee: ");

        // Status
        String status = getNonEmptyInput("Enter status: ");

        Consultation c = new Consultation(
                consultationId, dateTime, selectedPatient, selectedDoctor,
                symptoms, diagnosis, new LinkedList<>(), notes,
                nextAppointment, false, durationMinutes, consultationFee, status
        );

        consultationList.add(c);
        System.out.println("✅ Consultation added successfully!");
    }

    // READ
    public void displayAllConsultations() {
        if (consultationList.size() == 0) {
            System.out.println("No consultations found.");
        } else {
            System.out.println("\n--- All Consultations ---");
            for (int i = 0; i < consultationList.size(); i++) {
                System.out.println((i + 1) + " -> " + consultationList.get(i));
            }
        }
    }

    public Consultation getConsultation(int userIndex) {
        int index = userIndex - 1; 
        return (index >= 0 && index < consultationList.size()) ? consultationList.get(index) : null;
    }

    // UPDATE
    public void updateConsultation() {
        if (consultationList.size() == 0) {
            System.out.println("No consultations available to update.");
            return;
        }

        int userIndex = consultUI.inputConsultationIndex();
        int index = userIndex - 1; 
        if (index >= 0 && index < consultationList.size()) {
            Consultation existing = consultationList.get(index);

            // Only editable fields
            existing.setSymptoms(getNonEmptyInput("Enter new symptoms: "));
            existing.setDiagnosis(getNonEmptyInput("Enter new diagnosis: "));
            existing.setNotes(getNonEmptyInput("Enter new notes: "));
            existing.setDurationMinutes(getValidatedInteger("Enter new duration (minutes): "));
            existing.setConsultationFee(getValidatedDouble("Enter new consultation fee: "));
            existing.setStatus(getNonEmptyInput("Enter new status: "));

            System.out.println("✅ Consultation updated.");
        } else {
            System.out.println("Invalid consultation index.");
        }
    }

    // DELETE
    public void deleteConsultation() {
        if (consultationList.size() == 0) {
            System.out.println("No consultations available to delete.");
            return;
        }

        int userIndex = consultUI.inputConsultationIndex();
        int index = userIndex - 1; 
        if (index >= 0 && index < consultationList.size()) {
            Consultation removed = consultationList.remove(index);
            System.out.println("✅ Deleted consultation: " + removed.getConsultationId());
        } else {
            System.out.println("Invalid consultation index.");
        }
    }

    // ================= Validation Helpers =================
    private String getNonEmptyInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("❌ Input cannot be empty!");
        }
    }

    // overloaded version (allow 0 if creating new entry)
    private int getValidatedIndex(String prompt, int maxSize, boolean allowZero) {
        int index;
        while (true) {
            try {
                if (!prompt.isEmpty()) System.out.print(prompt);
                index = Integer.parseInt(sc.nextLine().trim());
                if ((allowZero && index == 0) || (index >= 1 && index <= maxSize)) {
                    return index;
                }
                System.out.println("❌ Invalid choice! Please enter between 1 and " + maxSize + (allowZero ? " or 0 for new" : ""));
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
            }
        }
    }

    private int getValidatedInteger(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid integer.");
            }
        }
    }

    private double getValidatedDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }

    // ================= Testing =================
    public static void main(String[] args) {
        ConsultationManager consultCtrl = new ConsultationManager();
        consultCtrl.runConsultationMaintenance();
    }
}
