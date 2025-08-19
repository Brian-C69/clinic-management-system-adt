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
        Patient p = new Patient();

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

        System.out.print("Enter Contact Number: ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) {
            p.setContactNumber(phone);
        }

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            p.setEmail(email);
        }

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) {
            p.setAddress(address);
        }

        System.out.print("Enter Panel: ");
        String panel = scanner.nextLine();
        if (!panel.isEmpty()) {
            p.setPanel(panel);
        }

        System.out.print("Enter Blood Type: ");
        String bloodType = scanner.nextLine();
        if (!bloodType.isEmpty()) {
            p.setBloodType(bloodType);
        }

        System.out.print("Enter Allergy History: ");
        String allergy = scanner.nextLine();
        if (!allergy.isEmpty()) {
            p.setAllergyHistory(allergy);
        }

        System.out.print("Enter Chronic Conditions: ");
        String chronic = scanner.nextLine();
        if (!chronic.isEmpty()) {
            p.setChronicConditions(chronic);
        }

        System.out.print("Enter Emergency Contact Name: ");
        String emergencyName = scanner.nextLine();
        if (!emergencyName.isEmpty()) {
            p.setEmergencyContactName(emergencyName);
        }

        System.out.print("Enter Emergency Contact Number: ");
        String emergencyPhone = scanner.nextLine();
        if (!emergencyPhone.isEmpty()) {
            p.setEmergencyContactNumber(emergencyPhone);
        }

        // Optional boolean field
        System.out.print("Is the patient active? (true/false): ");
        String activeInput = scanner.nextLine();
        if (!activeInput.isEmpty()) {
            p.setIsActive(Boolean.parseBoolean(activeInput));
        }

        return p;
    }

    public int inputPatientIndex() {
        System.out.print("Enter patient index to update/delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        return index;
    }
}
