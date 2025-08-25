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

    public Patient inputPatientDetails() {
        Patient p = new Patient();

        System.out.print("Enter Patient ID: ");
        String patientID = scanner.nextLine();
        if (!patientID.isEmpty()) {
            p.setPatientID(patientID);
        }

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            p.setName(name);
        }

        System.out.print("Enter IC Number: ");
        String ic = scanner.nextLine();
        if (!ic.isEmpty()) {
            p.setIcNumber(ic);
        }

        System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
        String dobStr = scanner.nextLine();
        if (!dobStr.isEmpty()) {
            try {
                LocalDate dob = LocalDate.parse(dobStr);
                p.setDateOfBirth(dob);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Skipping Date of Birth.");
            }
        }

        System.out.print("Enter Sex: ");
        String sex = scanner.nextLine();
        if (!sex.isEmpty()) {
            p.setSex(sex);
        }

        System.out.print("Enter Contact Number: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            p.setContactNumber(phone);
        }

        System.out.print("Enter Allergy History: ");
        String allergy = scanner.nextLine();
        if (!allergy.isEmpty()) {
            p.setAllergyHistory(allergy);
        }

        System.out.print("Enter Date of Registration (yyyy-MM-dd): ");
        String regStr = scanner.nextLine();
        if (!regStr.isEmpty()) {
            try {
                LocalDate regDate = LocalDate.parse(regStr);
                p.setDateOfRegistration(regDate);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Skipping Date of Registration.");
            }
        }

        System.out.print("Enter Last Visit Date (yyyy-MM-dd): ");
        String visitStr = scanner.nextLine();
        if (!visitStr.isEmpty()) {
            try {
                LocalDate visitDate = LocalDate.parse(visitStr);
                p.setLastVisitDate(visitDate);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Skipping Last Visit Date.");
            }
        }

        System.out.print("Is the patient active? (true/false): ");
        String activeInput = scanner.nextLine();
        if (!activeInput.isEmpty()) {
            p.setIsActive(Boolean.parseBoolean(activeInput));
        }

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
}
