// File: boundary/PatientUI.java
package boundary;

import control.MaintainPatient;
import entity.Patient;
import adt.ListInterface;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PatientUI {

    private Scanner sc = new Scanner(System.in);
    private MaintainPatient patientCtrl;

    public PatientUI(MaintainPatient patientCtrl) {
        this.patientCtrl = patientCtrl;
    }

    public MaintainPatient getController() {
        return patientCtrl;
    }

    public void runPatientMaintenance() {
        int choice;
        do {
            System.out.println("\n--- Patient Management ---");
            System.out.println("1. Display All Patients");
            System.out.println("2. Add Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Search Patient");
            System.out.println("6. Filter Patient");
            System.out.println("7. Call Next Patient");
            System.out.println("8. Cancel Patient Queue");
            System.out.println("9. Peek Next Queue Patient");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = getValidatedInteger("");

            switch (choice) {
                case 1 -> displayAllPatients();
                case 2 -> {
                    Patient p = inputPatientDetails();
                    patientCtrl.addPatient(p);
                }
                case 3 -> updatePatient();
                case 4 -> deletePatient();
                case 5 -> searchPatient();
                case 6 -> filterPatient();
                case 7 -> {
                    Patient next = patientCtrl.nextPatient();
                    if (next != null) {
                        System.out.println("Now serving: " + next.getQueueNumber() + " | " + next.getName());
                    } else {
                        System.out.println("No patients in queue.");
                    }
                }
                case 8 -> {
                    System.out.print("Enter queue number to cancel: ");
                    String queueNumber = sc.nextLine();
                    boolean cancelled = patientCtrl.cancelPatient(queueNumber);
                    System.out.println(cancelled
                            ? "Patient with queue " + queueNumber + " has been cancelled."
                            : "No patient found with queue " + queueNumber);
                }
                case 9 -> {
                    Patient next = patientCtrl.peekNextPatient();
                    if (next != null) {
                        System.out.println("Next patient: " + next.getQueueNumber() + " | " + next.getName());
                    } else {
                        System.out.println("No patients in queue.");
                    }
                }
                case 0 -> System.out.println("Exiting Patient Management...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public void displayAllPatients() {
        ListInterface<Patient> patients = patientCtrl.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patient currently in the list. Please add patient.");
        } else {
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(patients.get(i));
            }
        }
    }

    private void updatePatient() {
        if (patientCtrl.getSize() == 0) {
            System.out.println("No patient currently in the list. Please add patient");
            return;
        }

        displayAllPatients();
        int index = getValidatedIndex("Enter patient index to update (starting from 1): ", patientCtrl.getSize()) - 1;
        Patient newData = inputPatientDetails();
        boolean success = patientCtrl.updateExistingPatient(index, newData);

        if (success) {
            System.out.println("Patient updated successfully.");
        } else {
            System.out.println("Update failed.");
        }
    }

    private void deletePatient() {
        if (patientCtrl.getSize() == 0) {
            System.out.println("No patient currently in the list.");
            return;
        }

        displayAllPatients();
        int index = getValidatedIndex("Enter patient index to delete (starting from 1): ", patientCtrl.getSize()) - 1;
        Patient beforeDeletePatient = patientCtrl.getPatient(index);

        System.out.println("Are you sure you want to delete " + beforeDeletePatient.getName() + " ? (0 = NO | 1 = YES)");
        int confirmDeletePatient = getValidatedInteger("");

        if (confirmDeletePatient == 1) {
            Patient removed = patientCtrl.deletePatient(index);
            System.out.println("Patient deleted : " + removed.getName());
        } else {
            System.out.println("Aborted Delete Operation");
        }
    }

    private void searchPatient() {
        System.out.println("----------Search Options----------");
        System.out.println("1. Patient ID");
        System.out.println("2. Patient Name");
        System.out.println("0. Back to Main Menu");
        System.out.print("Choose search option: ");

        int searchChoice = getValidatedInteger("");
        if (searchChoice == 0) return;

        System.out.print("Enter search keyword: ");
        String keyword = sc.nextLine();

        ListInterface<Patient> foundPatients = patientCtrl.linearSearch(searchChoice, keyword);
        if (foundPatients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (int i = 0; i < foundPatients.size(); i++) {
                System.out.println(foundPatients.get(i));
            }
        }
    }

    private void filterPatient() {
        System.out.println("--- Filter Patients ---");
        System.out.println("1. Filter by Patient Gender");
        System.out.println("2. Filter by Patient Status");
        System.out.println("0. Back to Main Menu");
        System.out.println("Please Choose The Following Filter Option :");

        int filterChoice = getValidatedInteger("");
        switch (filterChoice) {
            case 1 -> {
                System.out.print("Please enter gender (M | F): ");
                String gender = sc.nextLine();
                ListInterface<Patient> results = patientCtrl.filterByGender(gender);
                for (int i = 0; i < results.size(); i++) {
                    System.out.println(results.get(i));
                }
            }
            case 2 -> {
                boolean active = getValidatedInteger("Filter active patients? (1 = Yes, 0 = No): ") == 1;
                ListInterface<Patient> results = patientCtrl.filterByStatus(active);
                for (int i = 0; i < results.size(); i++) {
                    System.out.println(results.get(i));
                }
            }
            case 0 -> System.out.println("Returning back to menu...");
            default -> System.out.println("Invalid choice.");
        }
    }

    // ========= Input Helpers =========
    public Patient inputPatientDetails() {
        Patient p = new Patient();

        System.out.print("Enter patient name: ");
        p.setName(sc.nextLine());

        System.out.print("Enter IC number: ");
        p.setIcNumber(sc.nextLine());

        System.out.print("Enter contact number: ");
        p.setContactNumber(sc.nextLine());

        System.out.print("Enter sex (M/F): ");
        p.setSex(sc.nextLine());

        System.out.print("Enter allergy history: ");
        p.setAllergyHistory(sc.nextLine());

        p.setDateOfBirth(getValidatedDate("Enter Date of Birth (yyyy-MM-dd): "));
        p.setDateOfRegistration(LocalDate.now());
        p.setLastVisitDate(null);
        p.setIsActive(true);

        return p;
    }

    private int getValidatedIndex(String prompt, int maxSize) {
        int index;
        while (true) {
            try {
                System.out.print(prompt);
                index = Integer.parseInt(sc.nextLine().trim());
                if (index >= 1 && index <= maxSize) {
                    return index;
                }
                System.out.println("Enter between 1 and " + maxSize);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }

    private int getValidatedInteger(String prompt) {
        while (true) {
            try {
                if (!prompt.isEmpty()) {
                    System.out.print(prompt);
                }
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private LocalDate getValidatedDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }
}
