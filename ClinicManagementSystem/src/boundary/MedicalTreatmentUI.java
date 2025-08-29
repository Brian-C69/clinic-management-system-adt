package boundary;

import entity.MedicalTreatment;
import entity.Patient;
import entity.Doctor;
import control.MaintainDoctor;
import control.MaintainPatient;

import java.time.LocalDate;
import java.util.Scanner;

public class MedicalTreatmentUI {
    private final Scanner scanner = new Scanner(System.in);

    // ---------- Menu ----------
    public int getMenuChoice() {
        System.out.println("\n===== Medical Treatment Menu =====");
        System.out.println("1. List all treatments");
        System.out.println("2. Add new treatment");
        System.out.println("3. Update treatment");
        System.out.println("4. Delete treatment");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
        return readInt();
    }

    // ---------- Treatment Creation ----------
    public MedicalTreatment createTreatment(Patient patient, Doctor doctor) {
        scanner.nextLine(); // flush before text input
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

    // ---------- Index Prompt ----------
    public int inputTreatmentIndex() {
        System.out.print("Enter treatment index: ");
        return readInt();
    }

    // ---------- Select Patient/Doctor ----------
    public Patient selectPatient(MaintainPatient patientCtrl) {
        System.out.println("\n--- Select Patient ---");
        for (int i = 0; i < patientCtrl.getSize(); i++) {
            Patient p = patientCtrl.getPatient(i);
            System.out.printf("%d. %s (%s)%n", i + 1, p.getName(), p.getPatientID());
        }
        System.out.print("Enter patient index (1-n, 0 to cancel): ");
        int ix = readInt();
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
        System.out.print("Enter doctor index (1-n, 0 to cancel): ");
        int ix = readInt();
        if (ix == 0) return null;
        Doctor d = doctorCtrl.getDoctor(ix - 1);
        if (d == null) System.out.println("❌ Invalid doctor index.");
        return d;
    }

    // ---------- Date Input ----------
    public LocalDate inputStartDate() {
        int y = readInt("Enter start year (YYYY): ");
        int m = readInt("Enter start month (1-12): ");
        int d = readInt("Enter start day: ");
        return LocalDate.of(y, m, d);
    }

    // ---------- Input Helpers ----------
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
