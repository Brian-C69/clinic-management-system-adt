package boundary;

import entity.MedicalTreatment;
import entity.Medicine;
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
        System.out.println("5. Manage Medicines");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
        return scanner.nextInt();
    }

    public int getMedicineMenuChoice() {
        System.out.println("\n===== Medicine Inventory Menu =====");
        System.out.println("1. List all medicines");
        System.out.println("2. Add new medicine");
        System.out.println("3. Update medicine");
        System.out.println("4. Delete medicine");
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

    public Medicine inputMedicineDetails() {
        scanner.nextLine(); // flush
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        System.out.print("Enter quantity available: ");
        int qty = scanner.nextInt();
        System.out.print("Enter unit price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter manufacturer: ");
        String manu = scanner.nextLine();
        System.out.print("Enter batch number: ");
        String batch = scanner.nextLine();
        System.out.print("Enter expiry year (YYYY): ");
        int year = scanner.nextInt();
        System.out.print("Enter expiry month (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter expiry day: ");
        int day = scanner.nextInt();

        return new Medicine(name, desc, type, qty, price,
                LocalDate.of(year, month, day), manu, batch);
    }

    public String inputDosage() {
        scanner.nextLine();
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
        return LocalDate.of(y, m, d);
    }

    public int inputTreatmentIndex() {
        System.out.print("Enter treatment index: ");
        return scanner.nextInt();
    }

    public int inputMedicineIndex() {
        System.out.print("Enter medicine index: ");
        return scanner.nextInt();
    }
}
