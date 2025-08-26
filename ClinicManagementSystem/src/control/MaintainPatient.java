package control;

import adt.ListInterface;
import adt.LinkedList;
import entity.Patient;

import java.util.Scanner;

public class MaintainPatient {
    private ListInterface<Patient> patientList = new LinkedList<>();
    private Scanner sc = new Scanner(System.in);

    // ========= MENU =========
    public void runPatientMaintenance() {
        int choice;
        do {
            System.out.println("\n--- Patient Management ---");
            System.out.println("1. Display All Patients");
            System.out.println("2. Add Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Search Patient by Name");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = getValidatedInteger("");

            switch (choice) {
                case 1 -> displayAllPatients();
                case 2 -> addPatient(inputPatientDetails());
                case 3 -> updatePatient();
                case 4 -> deletePatient();
                case 5 -> {
                    String name = getNonEmptyInput("Enter patient name: ");
                    Patient found = searchPatientByName(name);
                    System.out.println(found != null ? found : "Patient not found.");
                }
                case 0 -> System.out.println("Exiting Patient Management...");
                default -> System.out.println("❌ Invalid choice.");
            }
        } while (choice != 0);
    }

    // ========= CRUD =========
    public void addPatient(Patient p) {
        patientList.add(p);
        System.out.println("✅ Patient added successfully!");
    }

    public boolean replacePatient(int index, Patient newPatient) {
        return patientList.replace(index, newPatient);
    }

    public void updatePatient() {
        displayAllPatients();
        int index = getValidatedIndex("Enter patient index to update (starting from 1): ", patientList.size()) - 1;
        Patient existing = patientList.get(index);

        Patient newData = inputPatientDetails();
        updateExistingPatient(index, newData);
        System.out.println("✅ Patient updated: " + existing.getName());
    }

    public boolean updateExistingPatient(int index, Patient patientNewData) {
        if (index >= 0 && index < patientList.size()) {
            Patient exisitingPatient = patientList.get(index);

            if (patientNewData.getName() != null) exisitingPatient.setName(patientNewData.getName());
            if (patientNewData.getIcNumber() != null) exisitingPatient.setIcNumber(patientNewData.getIcNumber());
            if (patientNewData.getDateOfBirth() != null) exisitingPatient.setDateOfBirth(patientNewData.getDateOfBirth());
            if (patientNewData.getSex() != null) exisitingPatient.setSex(patientNewData.getSex());
            if (patientNewData.getContactNumber() != null) exisitingPatient.setContactNumber(patientNewData.getContactNumber());
            if (patientNewData.getAllergyHistory() != null) exisitingPatient.setAllergyHistory(patientNewData.getAllergyHistory());
            if (patientNewData.getDateOfRegistration() != null) exisitingPatient.setDateOfRegistration(patientNewData.getDateOfRegistration());
            if (patientNewData.getLastVisitDate() != null) exisitingPatient.setLastVisitDate(patientNewData.getLastVisitDate());

            exisitingPatient.setIsActive(patientNewData.isIsActive());
            return true;
        }
        return false;
    }

    public void deletePatient() {
        displayAllPatients();
        int index = getValidatedIndex("Enter patient index to delete (starting from 1): ", patientList.size()) - 1;
        Patient removed = patientList.remove(index);
        System.out.println("✅ Patient deleted: " + removed.getName());
    }

    public void displayAllPatients() {
        if (patientList.size() == 0) {
            System.out.println("No patients found.");
        } else {
            System.out.println("\n--- All Patients ---");
            for (int i = 0; i < patientList.size(); i++) {
                System.out.println((i + 1) + ": " + patientList.get(i));
            }
        }
    }

    public Patient getPatient(int index) {
        return (index >= 0 && index < patientList.size()) ? patientList.get(index) : null;
    }

    public Patient searchPatientByName(String name) {
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    // ========= Input Helpers =========
    private Patient inputPatientDetails() {
        String id = getValidatedPatientId();
        String name = getNonEmptyInput("Enter patient name: ");
        String ic = getNonEmptyInput("Enter IC number: ");
        String contact = getNonEmptyInput("Enter contact number: ");

        Patient p = new Patient();
        p.setPatientID(id);
        p.setName(name);
        p.setIcNumber(ic);
        p.setContactNumber(contact);
        p.setIsActive(true);
        return p;
    }

    private String getValidatedPatientId() {
        String input;
        while (true) {
            System.out.print("Enter Patient ID (format: P001): ");
            input = sc.nextLine().trim();
            if (input.matches("^P\\d{3}$")) return input;
            System.out.println("❌ Invalid format. Must be P followed by 3 digits.");
        }
    }

    private String getNonEmptyInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("❌ Input cannot be empty.");
        }
    }

    private int getValidatedIndex(String prompt, int maxSize) {
        int index;
        while (true) {
            try {
                System.out.print(prompt);
                index = Integer.parseInt(sc.nextLine().trim());
                if (index >= 1 && index <= maxSize) return index;
                System.out.println("❌ Enter between 1 and " + maxSize);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number.");
            }
        }
    }

    private int getValidatedInteger(String prompt) {
        while (true) {
            try {
                if (!prompt.isEmpty()) System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }

    // ========= MAIN for testing =========
    public static void main(String[] args) {
        MaintainPatient patientCtrl = new MaintainPatient();
        patientCtrl.runPatientMaintenance();
    }

    // ✅ Added getSize method
    public int getSize() {
        return patientList.size();
    }
}
