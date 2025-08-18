package boundary;

import entity.MedicalTreatment;
import entity.Medicine;
import entity.Doctor;
import entity.Patient;
import java.time.LocalDate;
import java.util.Scanner;

public class MedicalTreatmentUI {

    private Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n==== Medical Treatment Management Menu ====");
        System.out.println("1. List all treatments");
        System.out.println("2. Add new treatment");
        System.out.println("3. Update treatment");
        System.out.println("4. Delete treatment");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listAllTreatments(String outputStr) {
        System.out.println("\nList of Treatments:\n" + outputStr);
    }

    // --- Input helpers ---
    public String inputMedicineName() {
        System.out.print("Enter Medicine Name: ");
        return scanner.nextLine();
    }

    public String inputDosage() {
        System.out.print("Enter Dosage (e.g., 500mg): ");
        return scanner.nextLine();
    }

    public String inputDuration() {
        System.out.print("Enter Duration (e.g., 5 days): ");
        return scanner.nextLine();
    }

    public String inputInstructions() {
        System.out.print("Enter Instructions: ");
        return scanner.nextLine();
    }

    public LocalDate inputStartDate() {
        System.out.print("Enter Start Date (yyyy-MM-dd): ");
        String input = scanner.nextLine();
        return LocalDate.parse(input);
    }

    public int inputTreatmentIndex() {
        System.out.print("Enter treatment index to update/delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        return index;
    }
}
