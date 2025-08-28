package control;

import adt.ListInterface;
import adt.LinkedList;
import entity.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class MaintainPatient {

    //Patient permenant information storage
    private ListInterface<Patient> patientList = new LinkedList<>();
    //Queue storage
    private ListInterface<Patient> patientQueue = new LinkedList<>();
    private Scanner sc = new Scanner(System.in);
    private int queueCounter = 1;
    private int patientIdCounter = 0;

    // ========= MENU =========
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
                case 1 ->
                    displayAllPatients();
                case 2 ->
                    addPatient(inputPatientDetails());
                case 3 ->
                    updatePatient();
                case 4 ->
                    deletePatient();
                case 5 -> {
                    System.out.println("----------Search Options----------");
                    System.out.println("1. Patient ID");
                    System.out.println("2. Patient Name");
                    System.out.println("0. Back to Main Menu");
                    System.out.println("Choose search option: ");

                    int searchChoice = Integer.parseInt(sc.nextLine());
                    if (searchChoice == 0) {
                        System.out.println("Returning back to menu...");
                        // Return empty list
                    } else {

                        System.out.print("Enter search keyword: ");
                        String keyword = sc.nextLine();

                        ListInterface<Patient> foundPatients = linearSearch(searchChoice, keyword);

                        if (foundPatients.isEmpty()) {
                            System.out.println("No patients found.");
                        } else {
                            for (int i = 0; i < foundPatients.size(); i++) {
                                Patient p = foundPatients.get(i);
                                System.out.println(p);
                            }
                        }
                    }

                }
                case 6 -> {
                    System.out.println("--- Filter Patients ---");
                    System.out.println("1. Filter by Patient Gender");
                    System.out.println("2. Filter by Patient Status");
                    System.out.println("0. Back to Main Menu");
                    System.out.println("Please Choose The Following Filter Option :");
                    int filterChoice = sc.nextInt();
                    sc.nextLine();
                    filterPatient(filterChoice);

                }
                case 7 -> {
                    nextPatient();
                }
                case 8 -> {
                    System.out.print("Enter queue number to cancel: ");
                    String queueNumber = sc.nextLine();
                    cancelPatient(queueNumber);

                }
                case 9 -> {
                    peekNextPatient();
                }
                case 0 ->
                    System.out.println("Exiting Patient Management...");
                default ->
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ========= CRUD =========
    private String generatePatientId() {
        patientIdCounter++;
        return String.format("P%03d", patientIdCounter);
    }

    public void addPatient(Patient p) {
        //Generate a queue number first
        String queueNumber = String.format("PQ%03d", queueCounter++);
        p.setQueueNumber(queueNumber);

        //Auto generate patient id
        p.setPatientID(generatePatientId());
        patientList.add(p);
    }

    public boolean replacePatient(int index, Patient newPatient) {
        return patientList.replace(index, newPatient);
    }

    public void updatePatient() {
        if (patientList.size() == 0) {
            System.out.println("No patient currently in the list. Please add patient");
        } else {
            displayAllPatients();
            int index = getValidatedIndex("Enter patient index to update (starting from 1): ", patientList.size()) - 1;
            Patient existing = patientList.get(index);

            Patient newData = inputPatientDetails();
            updateExistingPatient(index, newData);
            System.out.println("Patient updated: " + existing.getName());
        }
    }

    public boolean updateExistingPatient(int index, Patient patientNewData) {
        if (index >= 0 && index < patientList.size()) {
            Patient existingPatient = patientList.get(index);

            if (patientNewData.getName() != null) {
                existingPatient.setName(patientNewData.getName());
            }
            if (patientNewData.getIcNumber() != null) {
                existingPatient.setIcNumber(patientNewData.getIcNumber());
            }
            if (patientNewData.getDateOfBirth() != null) {
                existingPatient.setDateOfBirth(patientNewData.getDateOfBirth());
            }
            if (patientNewData.getSex() != null) {
                existingPatient.setSex(patientNewData.getSex());
            }
            if (patientNewData.getContactNumber() != null) {
                existingPatient.setContactNumber(patientNewData.getContactNumber());
            }
            if (patientNewData.getAllergyHistory() != null) {
                existingPatient.setAllergyHistory(patientNewData.getAllergyHistory());
            }
            if (patientNewData.getDateOfRegistration() != null) {
                existingPatient.setDateOfRegistration(patientNewData.getDateOfRegistration());
            }
            if (patientNewData.getLastVisitDate() != null) {
                existingPatient.setLastVisitDate(patientNewData.getLastVisitDate());
            }

            // follow your old style: always overwrite isActive
            existingPatient.setIsActive(patientNewData.isActive());

            return true;
        }
        return false;
    }

    public void deletePatient() {
        if (patientList.isEmpty()) {
            System.out.println("No patient currently in the list. Please add patient");
        } else {
            displayAllPatients();
            int index = getValidatedIndex("Enter patient index to delete (starting from 1): ", patientList.size()) - 1;

            Patient beforeDeletePatient = patientList.get(index);
            int confirmDeletePatient;
            System.out.println("Are you sure you want to delete " + beforeDeletePatient.getName() + " ? (0 = NO | 1 = YES)");
            confirmDeletePatient = Integer.parseInt(sc.nextLine());

            if (confirmDeletePatient == 1) {
                Patient removed = patientList.remove(index);
                System.out.println("Patient deleted : " + removed.getName());
            } else {
                System.out.println("Aborted Delete Operation");
            }
        }
    }

    public void displayAllPatients() {
        if (patientList.isEmpty()) {
            System.out.println("No patient currently in the list. Please add patient");
        } else {
            for (int i = 0; i < patientList.size(); i++) {
                Patient p = patientList.get(i);
                System.out.println(p);
            }
        }
    }

    public Patient getPatient(int index) {
        return (index >= 0 && index < patientList.size()) ? patientList.get(index) : null;
    }

    public Patient nextPatient() {
        if (patientList.isEmpty()) {
            System.out.println("No patients in queue.");
            return null;
        }

        Patient next = patientList.remove(0);
        System.out.println("Now serving: " + next.getQueueNumber() + " | " + next.getName());
        return next;
    }

    public Patient peekNextPatient() {
        if (patientList.isEmpty()) {
            System.out.println("No patients in queue.");
            return null;
        }
        Patient next = patientList.get(0);
        System.out.println("Next patient: " + next.getQueueNumber() + " | " + next.getName());
        return next;
    }

    public boolean cancelPatient(String queueNumber) {
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            if (p.getQueueNumber().equals(queueNumber)) {
                patientList.remove(i);
                System.out.println("Patient with queue " + queueNumber + " has been cancelled.");
                return true;
            }
        }
        System.out.println("No patient found with queue " + queueNumber);
        return false;
    }

    void filterPatient(int filterChoice) {
        Scanner sc = new Scanner(System.in);
        switch (filterChoice) {
            case 1 -> {
                System.out.println("Please enter gender (M | F): ");
                String filterPatientGender = sc.nextLine();

                boolean found = false;

                for (int i = 0; i < patientList.size(); i++) {
                    Patient p = patientList.get(i);

                    if (p.getSex().equalsIgnoreCase(filterPatientGender)) {
                        System.out.println(p);  // Assuming Patient.toString() prints details
                        found = true;
                    }
                }

                if (!found) {
                    System.out.println("No patients found with gender: " + filterPatientGender);
                }
            }
            case 2 -> {
                boolean active = getValidatedInteger("Filter active patients? (1 = Yes, 0 = No): ") == 1;
                for (int i = 0; i < patientList.size(); i++) {
                    Patient p = patientList.get(i);
                    if (p.isActive() == active) { // assuming you track active status
                        System.out.println(p);
                    }
                }
            }
            case 0 -> {
                System.out.println("Returning back to menu...");
                return;
            }
            default ->
                System.out.println("Invalid choice.");
        }
    }

    public ListInterface<Patient> linearSearch(int option, String keyword) {
        ListInterface<Patient> results = new LinkedList<>();

        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            switch (option) {
                case 1 -> {
                    if (p.getPatientID().equalsIgnoreCase(keyword)) {
                        results.add(p);
                    }
                }
                case 2 -> {
                    if (p.getName().equalsIgnoreCase(keyword)) {
                        results.add(p);
                    }
                }
                default ->
                    System.out.println("Invalid search option.");
            }
        }

        return results;
    }

    // ========= Input Helpers =========
    Patient inputPatientDetails() {
        String name = getNonEmptyInput("Enter patient name: ");
        String ic = getNonEmptyInput("Enter IC number: ");
        String contact = getNonEmptyInput("Enter contact number: ");
        String sex = getNonEmptyInput("Enter sex (M/F): ");
        String allergy = getOptionalInput("Enter allergy history (leave blank if none): ");

        LocalDate dob = getValidatedDate("Enter Date of Birth (yyyy-MM-dd): ");
        LocalDate regDate = LocalDate.now(); // auto set registration date to today
        LocalDate lastVisit = null; // optional, can leave empty or set later

        Patient p = new Patient();
        p.setName(name);
        p.setIcNumber(ic);
        p.setContactNumber(contact);
        p.setSex(sex);
        p.setAllergyHistory(allergy);
        p.setDateOfBirth(dob);
        p.setDateOfRegistration(regDate);
        p.setLastVisitDate(lastVisit);
        p.setIsActive(true);

        return p;
    }

    private String getNonEmptyInput(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
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

    private String getOptionalInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim(); // can be empty
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

    // ========= MAIN for testing =========
    public static void main(String[] args) {
        MaintainPatient patientCtrl = new MaintainPatient();

        Patient p1 = new Patient();
        p1.setName("NTC");
        p1.setIcNumber("123456-01-0001");
        p1.setContactNumber("010-0000001");
        p1.setSex("M");
        p1.setAllergyHistory("None");
        p1.setIsActive(true);

        Patient p2 = new Patient();
        p2.setName("Bob");
        p2.setIcNumber("123456-02-0002");
        p2.setContactNumber("011-0000002");
        p2.setSex("M");
        p2.setAllergyHistory("Peanut");
        p2.setIsActive(false);

        Patient p3 = new Patient();
        p3.setName("Alice");
        p3.setIcNumber("123456-03-0003");
        p3.setContactNumber("013-0000003");
        p3.setSex("F");
        p3.setAllergyHistory("Seafood");
        p3.setIsActive(true);

        Patient p4 = new Patient();
        p4.setName("Pei Ling");
        p4.setIcNumber("123456-03-0003");
        p4.setContactNumber("014-0000004");
        p4.setSex("F");
        p4.setAllergyHistory("Badminton");
        p4.setIsActive(false);

        patientCtrl.addPatient(p1);
        patientCtrl.addPatient(p2);
        patientCtrl.addPatient(p3);
        patientCtrl.addPatient(p4);

        patientCtrl.runPatientMaintenance();
    }

    // ✅ Added getSize method
    public int getSize() {
        return patientList.size();
    }
}
