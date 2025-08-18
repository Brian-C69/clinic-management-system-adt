package boundary;

import entity.Patient;
import java.util.Scanner;

public class PatientUI {

    private Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n==== Patient Management Menu ====");
        System.out.println("1. List all patients");
        System.out.println("2. Add new patient");
        System.out.println("3. Update patient");
        System.out.println("4. Delete patient");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listAllPatients(String outputStr) {
        System.out.println("\nList of Patients:\n" + outputStr);
    }

    public Patient inputPatientDetails() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter IC Number: ");
        String ic = scanner.nextLine();
        System.out.print("Enter Contact Number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Patient p = new Patient();
        p.setName(name);
        p.setIcNumber(ic);
        p.setContactNumber(phone);
        p.setEmail(email);
        return p;
    }

    public int inputPatientIndex() {
        System.out.print("Enter patient index to update/delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        return index;
    }
}
