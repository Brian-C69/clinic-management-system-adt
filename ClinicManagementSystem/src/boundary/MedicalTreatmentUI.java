// File: boundary/MedicalTreatmentUI.java
package boundary;

import entity.MedicalTreatment;
import entity.Patient;
import entity.Doctor;
import control.MaintainDoctor;
import control.MaintainPatient;
import control.TreatmentManager;

import java.time.LocalDate;
import java.util.Scanner;

public class MedicalTreatmentUI {

    private final Scanner scanner;

    public MedicalTreatmentUI(Scanner scanner) {
        this.scanner = scanner;
    }

    // ---------- Main loop ----------
    public void runTreatmentMaintenance(TreatmentManager treatCtrl,
            MaintainPatient patientCtrl,
            MaintainDoctor doctorCtrl,
            Scanner sc) {
        int choice;
        do {
            System.out.println("\n===== Medical Treatment Menu =====");
            System.out.println("1. List all treatments");
            System.out.println("2. Add new treatment");
            System.out.println("3. Update treatment");
            System.out.println("4. Delete treatment");
            System.out.println("5. Search treatment by medicine");
            System.out.println("6. Filter treatment by doctor ID");
            System.out.println("0. Back");
            System.out.print("Enter choice: ");
            choice = readInt();

            switch (choice) {
                case 1 -> treatCtrl.displayAllTreatments();
                case 2 -> {
                    if (patientCtrl.getSize() == 0 || doctorCtrl.getSize() == 0) {
                        System.out.println("⚠ Need at least 1 patient and 1 doctor before adding treatment.");
                        break;
                    }
                    Patient patient = selectPatient(patientCtrl);
                    if (patient == null) break;
                    Doctor doctor = selectDoctor(doctorCtrl);
                    if (doctor == null) break;
                    MedicalTreatment t = createTreatment(patient, doctor);
                    treatCtrl.addTreatment(t);
                }
                case 3 -> {
                    if (treatCtrl.getTreatmentCount() == 0) {
                        System.out.println("No treatments to update.");
                        break;
                    }
                    treatCtrl.displayAllTreatments();
                    int idx = readInt("Enter treatment index to update (starting from 1): ") - 1;
                    MedicalTreatment old = treatCtrl.getTreatment(idx);
                    if (old == null) {
                        System.out.println("❌ Invalid index.");
                        break;
                    }
                    MedicalTreatment updated = updateTreatment(old, old.getPatient(), old.getDoctor());
                    boolean ok = treatCtrl.updateTreatment(idx, updated);
                    System.out.println(ok ? "✅ Treatment updated." : "❌ Update failed.");
                }
                case 4 -> {
                    if (treatCtrl.getTreatmentCount() == 0) {
                        System.out.println("No treatments to delete.");
                        break;
                    }
                    treatCtrl.displayAllTreatments();
                    int idx = readInt("Enter treatment index to delete (starting from 1): ") - 1;
                    MedicalTreatment removed = treatCtrl.deleteTreatment(idx);
                    System.out.println(removed != null ? "✅ Treatment deleted." : "❌ Deletion failed.");
                }
                case 5 -> {
                    String keyword = readLine("Enter medicine keyword to search: ");
                    var results = treatCtrl.searchByMedicine(keyword);
                    if (results.isEmpty()) System.out.println("No matching treatments found.");
                    else {
                        System.out.println("\n--- Search Results ---");
                        for (int i = 0; i < results.size(); i++) {
                            System.out.println((i + 1) + ". " + results.get(i));
                        }
                    }
                }
                case 6 -> {
                    String docId = readLine("Enter doctor ID to filter: ");
                    var results = treatCtrl.filterByDoctorId(docId);
                    if (results.isEmpty()) System.out.println("No treatments found for Doctor ID: " + docId);
                    else {
                        System.out.println("\n--- Filtered Treatments ---");
                        for (int i = 0; i < results.size(); i++) {
                            System.out.println((i + 1) + ". " + results.get(i));
                        }
                    }
                }
                case 0 -> System.out.println("Returning to main menu...");
                default -> System.out.println("❌ Invalid option.");
            }
        } while (choice != 0);
    }

    // ---------- Builders ----------
    public MedicalTreatment createTreatment(Patient patient, Doctor doctor) {
        System.out.println("\n--- Creating Medical Treatment ---");
        String medicine = readLine("Enter medicine name: ");
        String dosage = readLine("Enter dosage: ");
        String duration = readLine("Enter duration (e.g. 7 days): ");
        String instructions = readLine("Enter instructions: ");
        String diagnosis = readLine("Enter diagnosis: ");
        LocalDate startDate = inputStartDate();

        return new MedicalTreatment(medicine, dosage, duration, instructions, startDate, patient, doctor, diagnosis);
    }

    public MedicalTreatment updateTreatment(MedicalTreatment old, Patient patient, Doctor doctor) {
        System.out.println("\n--- Updating Treatment (press Enter to keep current) ---");

        String medicine = readOptional("Medicine", old.getMedicine());
        String dosage = readOptional("Dosage", old.getDosage());
        String duration = readOptional("Duration", old.getDuration());
        String instructions = readOptional("Instructions", old.getInstructions());
        String diagnosis = readOptional("Diagnosis", old.getDiagnosis());

        LocalDate startDate = old.getStartDate();
        if (askYesNo("Change start date? (y/n): ")) {
            startDate = inputStartDate();
        }

        return new MedicalTreatment(medicine, dosage, duration, instructions, startDate, patient, doctor, diagnosis);
    }

    // ---------- Selection ----------
    public Patient selectPatient(MaintainPatient patientCtrl) {
        System.out.println("\n--- Select Patient ---");
        for (int i = 0; i < patientCtrl.getSize(); i++) {
            Patient p = patientCtrl.getPatient(i);
            System.out.printf("%d. %s (%s)%n", i + 1, p.getName(), p.getPatientID());
        }
        int ix = readInt("Enter patient index (1-n, 0 to cancel): ");
        if (ix == 0) return null;
        Patient p = patientCtrl.getPatient(ix - 1);
        if (p == null) System.out.println("❌ Invalid patient index.");
        return p;
    }

    public Doctor selectDoctor(MaintainDoctor doctorCtrl) {
        System.out.println("\n--- Select Doctor ---");
        for (int i = 0; i < doctorCtrl.getSize(); i++) {
            Doctor d = doctorCtrl.getDoctor(i);
            System.out.printf("%d. %s (%s)%n", i + 1, d.getName(), d.getDoctorId());
        }
        int ix = readInt("Enter doctor index (1-n, 0 to cancel): ");
        if (ix == 0) return null;
        Doctor d = doctorCtrl.getDoctor(ix - 1);
        if (d == null) System.out.println("❌ Invalid doctor index.");
        return d;
    }

    // ---------- Date ----------
    public LocalDate inputStartDate() {
        int y = readInt("Enter start year (YYYY): ");
        int m = readInt("Enter start month (1-12): ");
        int d = readInt("Enter start day: ");
        return LocalDate.of(y, m, d);
    }

    // ---------- Input helpers ----------
    private int readInt() {
        return readInt(null);
    }

    private int readInt(String prompt) {
        while (true) {
            if (prompt != null) System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Enter a valid number.");
            }
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private String readOptional(String label, String oldValue) {
        System.out.printf("%s [%s]: ", label, oldValue);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? oldValue : input;
    }

    private boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.matches("y|yes|true|1")) return true;
            if (input.matches("n|no|false|0")) return false;
            System.out.println("❌ Please enter y/n.");
        }
    }
}
