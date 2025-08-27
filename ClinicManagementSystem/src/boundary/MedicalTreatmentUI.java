// File: boundary/MedicalTreatmentUI.java
package boundary;

import entity.MedicalTreatment;
import entity.Patient;
import entity.Doctor;

import java.time.LocalDate;
import java.util.Scanner;

public class MedicalTreatmentUI {
    private Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n===== Medical Treatment Menu =====");
        System.out.println("1. List all treatments");
        System.out.println("2. Add new treatment");
        System.out.println("3. Update treatment");
        System.out.println("4. Delete treatment");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
        return scanner.nextInt();
    }

    // ---------------- Input Methods ----------------
    public String inputMedicineName() {
        scanner.nextLine(); // flush
        System.out.print("Enter medicine name: ");
        return scanner.nextLine();
    }

    public String inputDosage() {
        System.out.print("Enter dosage: ");
        return scanner.nextLine();
    }

    public String inputDuration() {
        System.out.print("Enter duration (e.g. 7 days): ");
        return scanner.nextLine();
    }

    public String inputInstructions() {
        System.out.print("Enter instructions: ");
        return scanner.nextLine();
    }

    public LocalDate inputStartDate() {
        System.out.print("Enter start year (YYYY): ");
        int y = scanner.nextInt();
        System.out.print("Enter start month (1-12): ");
        int m = scanner.nextInt();
        System.out.print("Enter start day: ");
        int d = scanner.nextInt();
        scanner.nextLine(); // flush
        return LocalDate.of(y, m, d);
    }

    public int inputTreatmentIndex() {
        System.out.print("Enter treatment index: ");
        return scanner.nextInt();
    }

    // ---------------- Treatment Builders ----------------
    public MedicalTreatment createTreatment(Patient patient, Doctor doctor) {
        String medicine = inputMedicineName();
        String dosage = inputDosage();
        String duration = inputDuration();
        String instructions = inputInstructions();
        LocalDate startDate = inputStartDate();
        return new MedicalTreatment(medicine, dosage, duration, instructions, startDate, patient, doctor);
    }

    public MedicalTreatment updateTreatment(MedicalTreatment old, Patient patient, Doctor doctor) {
        System.out.println("\nUpdating treatment (press enter to keep old value):");

        scanner.nextLine(); // flush
        System.out.print("Medicine [" + old.getMedicine() + "]: ");
        String medicine = scanner.nextLine();
        if (medicine.isEmpty()) medicine = old.getMedicine();

        System.out.print("Dosage [" + old.getDosage() + "]: ");
        String dosage = scanner.nextLine();
        if (dosage.isEmpty()) dosage = old.getDosage();

        System.out.print("Duration [" + old.getDuration() + "]: ");
        String duration = scanner.nextLine();
        if (duration.isEmpty()) duration = old.getDuration();

        System.out.print("Instructions [" + old.getInstructions() + "]: ");
        String instructions = scanner.nextLine();
        if (instructions.isEmpty()) instructions = old.getInstructions();

        LocalDate startDate = old.getStartDate();
        System.out.print("Change start date? (y/n): ");
        String ans = scanner.nextLine();
        if (ans.equalsIgnoreCase("y")) {
            startDate = inputStartDate();
        }

        return new MedicalTreatment(medicine, dosage, duration, instructions, startDate, patient, doctor);
    }
}
