package boundary;

import adt.LinkedList;
import entity.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PatientUI {

    private LinkedList<Patient> patientList = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n==== Patient Management Menu ====");
        System.out.println("1. List all patients");
        System.out.println("2. Add new patient");
        System.out.println("3. Update patient");
        System.out.println("4. Delete patient");
        System.out.println("5. Search patient by name");
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

    public Patient inputPatientDetails(int queueCounter) {
        Patient p = new Patient();

        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine().trim();
        while (patientID.isEmpty()) {
            System.out.print("Patient ID cannot be empty. Please enter again: ");
            patientID = scanner.nextLine().trim();
        }
        p.setPatientID(patientID);

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Please enter again: ");
            name = scanner.nextLine().trim();
        }
        p.setName(name);

        System.out.print("Enter IC Number: ");
        String ic = scanner.nextLine().trim();
        if (!ic.isEmpty()) {
            p.setIcNumber(ic);
        }

        p.setDateOfBirth(readDate("Enter Date of Birth (yyyy-MM-dd): "));

        System.out.print("Enter Sex (M/F): ");
        String sex = scanner.nextLine().trim();
        if (!sex.isEmpty()) {
            p.setSex(sex);
        }

        System.out.print("Enter Contact Number: ");
        String phone = scanner.nextLine().trim();
        if (!phone.isEmpty()) {
            p.setContactNumber(phone);
        }

        System.out.print("Enter Allergy History: ");
        String allergy = scanner.nextLine().trim();
        if (!allergy.isEmpty()) {
            p.setAllergyHistory(allergy);
        }

        p.setDateOfRegistration(readDate("Enter Date of Registration (yyyy-MM-dd): "));
        p.setLastVisitDate(readDate("Enter Last Visit Date (yyyy-MM-dd): "));

        System.out.print("Is the patient active? (true/false): ");
        String activeInput = scanner.nextLine().trim();
        if (!activeInput.isEmpty()) {
            p.setIsActive(Boolean.parseBoolean(activeInput));
        } else {
            p.setIsActive(true); // default active
        }

        // Automatically assign queue number (example format Q001, Q002...)
        String queueNo = String.format("Q%03d", queueCounter);
        p.setQueueNumber(queueNo);
        System.out.println("Assigned Queue Number: " + queueNo);

        return p;
    }

    public int inputPatientIndex() {

        System.out.print("---------- List of patients ----------\n");
        System.out.print("Enter patient index to update/delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 0 || index >= patientList.size()) {

            return index;
        }
        return 123;
    }

    public String inputPatientName() {
        System.out.print("Please enter patient name: ");
        return scanner.nextLine();
    }

    public void displayPatient(Patient p) {
        if (p != null) {
            System.out.println("Patient found!");
            System.out.println("Name: " + p.getName());
        } else {
            System.out.println("Patient not found.");
        }
    }

    private LocalDate readDate(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            return null;
        }

        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Expected yyyy-MM-dd. Skipping...");
            return null;
        }
    }
}
