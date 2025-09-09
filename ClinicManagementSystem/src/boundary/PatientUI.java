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

    public PatientUI(MaintainPatient patientCtrl, Scanner sc) {
        this.patientCtrl = patientCtrl;
        this.sc = sc;
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
            System.out.println("10. Generate Patient Summary Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = getValidatedInteger("");

            switch (choice) {
                case 1 ->
                    displayAllPatients();
                case 2 -> {
                    Patient p = inputPatientDetails();
                    patientCtrl.addPatient(p);
                }
                case 3 ->
                    updatePatient();
                case 4 ->
                    deletePatient();
                case 5 ->
                    searchPatient();
                case 6 ->
                    filterPatient();
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
                case 10 -> {
                    if (patientCtrl.getSize() == 0) {
                        System.out.println("No patients to report.");
                    } else {
                        report.PatientSummaryReport.generate(patientCtrl.getAllPatients());
                    }
                }
                case 0 ->
                    System.out.println("Exiting Patient Management...");
                default ->
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public void displayAllPatients() {
        ListInterface<Patient> patients = patientCtrl.getAllPatients();
        if (patients.isEmpty()) {
            System.out.println("No patient currently in the list. Please add patient.");
        } else {
            // Define one consistent format string
            String rowFormat = "| %-10s | %-20s | %-15s | %-12s | %-6s | %-15s | %-15s | %-12s | %-12s | %-6s | %-8s |";

            // Print header
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format(
                    rowFormat,
                    "Patient ID", "Name", "IC Number", "DOB", "Gender", "Contact No",
                    "Allergy", "Reg Date", "Last Visit", "Active", "Queue No"
            ));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            // Print patient rows
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(patients.get(i).toString());
            }

            // Closing line
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    private void updatePatient() {
        if (patientCtrl.getSize() == 0) {
            System.out.println("No patient currently in the list. Please add patient");
            return;
        }

        displayAllPatients();
        int index = getValidatedIndex("Enter patient index to update (starting from 1): ", patientCtrl.getSize()) - 1;
        Patient foundPatient = patientCtrl.getPatient(index);

        System.out.println("You are about to edit " + foundPatient.getName() + " information.");

        int editPatientChoice = -1;
        while (true) {
            System.out.print("Proceed ? (0 = NO | 1 = YES): ");
            if (sc.hasNextInt()) {
                editPatientChoice = sc.nextInt();
                sc.nextLine();
                if (editPatientChoice == 0 || editPatientChoice == 1) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter 0 for NO or 1 for YES.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number (0 or 1).");
                sc.next();
            }
        }

        if (editPatientChoice == 1) {
            Patient newData = inputPatientDetails();
            boolean success = patientCtrl.updateExistingPatient(index, newData);

            if (success) {
                System.out.println("Patient updated successfully.");
            } else {
                System.out.println("Update failed.");
            }
        } else {
            System.out.println("Abort Edit Operation!");
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

        System.out.println("Are you sure you want to delete " + beforeDeletePatient.getName() + "?");

        int confirmDeletePatient = -1;
        while (true) {
            System.out.print("(0 = NO | 1 = YES): ");
            if (sc.hasNextInt()) {
                confirmDeletePatient = sc.nextInt();
                sc.nextLine();
                if (confirmDeletePatient == 0 || confirmDeletePatient == 1) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter 0 for NO or 1 for YES.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number (0 or 1).");
                sc.next();
            }
        }

        if (confirmDeletePatient == 1) {
            Patient removed = patientCtrl.deletePatient(index);
            System.out.println("Patient deleted: " + removed.getName());
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
        if (searchChoice == 0) {
            return;
        }

        System.out.print("Enter search keyword: ");
        String keyword = sc.nextLine();

        ListInterface<Patient> foundPatients = patientCtrl.linearSearch(searchChoice, keyword);
        if (foundPatients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            String rowFormat = "| %-10s | %-20s | %-15s | %-12s | %-6s | %-15s | %-15s | %-12s | %-12s | %-6s | %-8s |";

            // Print header
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format(
                    rowFormat,
                    "Patient ID", "Name", "IC Number", "DOB", "Gender", "Contact No",
                    "Allergy", "Reg Date", "Last Visit", "Active", "Queue No"
            ));
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < foundPatients.size(); i++) {
                System.out.println(foundPatients.get(i));
            }

            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
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
                String rowFormat = "| %-10s | %-20s | %-15s | %-12s | %-6s | %-15s | %-15s | %-12s | %-12s | %-6s | %-8s |";

                // Print header
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(String.format(
                        rowFormat,
                        "Patient ID", "Name", "IC Number", "DOB", "Gender", "Contact No",
                        "Allergy", "Reg Date", "Last Visit", "Active", "Queue No"
                ));
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < results.size(); i++) {
                    System.out.println(results.get(i));
                }

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            case 2 -> {
                boolean active = getValidatedInteger("Filter active patients? (1 = Yes, 0 = No): ") == 1;
                ListInterface<Patient> results = patientCtrl.filterByStatus(active);
                String rowFormat = "| %-10s | %-20s | %-15s | %-12s | %-6s | %-15s | %-15s | %-12s | %-12s | %-6s | %-8s |";

                // Print header
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(String.format(
                        rowFormat,
                        "Patient ID", "Name", "IC Number", "DOB", "Gender", "Contact No",
                        "Allergy", "Reg Date", "Last Visit", "Active", "Queue No"
                ));
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < results.size(); i++) {
                    System.out.println(results.get(i));
                }

                System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            case 0 ->
                System.out.println("Returning back to menu...");
            default ->
                System.out.println("Invalid choice.");
        }
    }

    // ========= Input Helpers =========
    public Patient inputPatientDetails() {
        Patient p = new Patient();

        // --- Name Validation ---
        String setPatientName;
        while (true) {
            System.out.print("Enter patient name: ");
            setPatientName = sc.nextLine().trim();
            if (setPatientName.isEmpty()) {
                System.out.println("❌ Name cannot be empty!");
            } else if (!setPatientName.matches("^[a-zA-Z ]+$")) {
                System.out.println("❌ Name should only contain letters and spaces!");
            } else {
                break;
            }
        }
        p.setName(setPatientName);

        // --- IC Number Validation ---
        String ic;
        while (true) {
            System.out.print("Enter IC number: ");
            ic = sc.nextLine().trim();
            if (ic.isEmpty()) {
                System.out.println("IC cannot be empty!");
            } else if (!ic.matches("\\d{6,12}")) {
                System.out.println("IC must be digits only (6–12 digits).");
            } else {
                break;
            }
        }
        p.setIcNumber(ic);

        // --- Contact Number Validation ---
        String contact;
        while (true) {
            System.out.print("Enter contact number: ");
            contact = sc.nextLine().trim();
            if (!contact.matches("\\d{10,12}")) {
                System.out.println("Contact number must be 10–12 digits.");
            } else {
                break;
            }
        }
        p.setContactNumber(contact);

        // --- Sex Validation ---
        String sex;
        while (true) {
            System.out.print("Enter sex (M/F): ");
            sex = sc.nextLine().trim().toUpperCase();
            if (!sex.equals("M") && !sex.equals("F")) {
                System.out.println("Invalid input! Please enter M or F.");
            } else {
                break;
            }
        }
        p.setSex(sex);

        // --- Allergy History (optional) ---
        System.out.print("Enter allergy history (leave blank if none): ");
        p.setAllergyHistory(sc.nextLine().trim());

        // --- Patient Status Validation ---
        int statusChoice;
        while (true) {
            System.out.print("Enter Patient Status (0 = NOT ACTIVE | 1 = ACTIVE): ");
            try {
                statusChoice = Integer.parseInt(sc.nextLine());
                if (statusChoice == 0) {
                    p.setIsActive(false);
                    break;
                } else if (statusChoice == 1) {
                    p.setIsActive(true);
                    break;
                } else {
                    System.out.println("Invalid choice! Only 0 or 1 allowed.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (0 or 1).");
            }
        }

        // --- Date of Birth (your existing validation method) ---
        p.setDateOfBirth(getValidatedDate("Enter Date of Birth (yyyy-MM-dd): "));

        // --- Auto-generated fields ---
        p.setDateOfRegistration(LocalDate.now());
        p.setLastVisitDate(null);

        System.out.println("Patient " + p.getName() + " added successfully!");
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
