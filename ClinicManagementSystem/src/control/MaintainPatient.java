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
            System.out.println("6. Peek Next Queue Patient");
            System.out.println("7. Call Next Patient");
            System.out.println("8. Cancel Patient Queue");
            System.out.println("9. Filter Patient");
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
                    System.out.println("2. Name");
                    System.out.println("3. Queue Number");
                    System.out.println("Choose search option: ");

                    int searchChoice = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter search keyword: ");
                    String keyword = sc.nextLine();

                    ListInterface<Patient> foundPatients = linearSearch(searchChoice, keyword);

                    if (foundPatients.isEmpty()) {
                        System.out.println("No patients found.");
                    } else {
                        System.out.println("Search results:");
                        for (int i = 0; i < patientList.size(); i++) {
                            Patient p = foundPatients.get(i);
                            System.out.println("Information found !");
                            System.out.println((i + 1) + ". " + p); // shows numbering + patient info
                        }
                    }
                }
                case 6 -> {
                    peekNextPatient();
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
                    System.out.println("--- Filter Patients ---");
                    System.out.println("1. Filter by Patient ID");
                    System.out.println("2. Filter by Sex");
                    System.out.println("3. Filter by Name");
                    System.out.println("0. Back to Main Menu");
                    System.out.println("Please Choose The Following Filter Option :");
                    int filterChoice = sc.nextInt();
                    sc.nextLine();
                    filterPatient(filterChoice);
                }
                case 0 ->
                    System.out.println("Exiting Patient Management...");
                default ->
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ========= CRUD =========
    public void addPatient(Patient p) {
        //Generate a queue number first
        String queueNumber = String.format("PQ%03d", queueCounter++);
        p.setQueueNumber(queueNumber);
        patientList.add(p);
        System.out.println("Patient added successfully!");
        System.out.println("Patient queue number : " + queueNumber);
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
        System.out.println("Patient updated: " + existing.getName());
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
            existingPatient.setIsActive(patientNewData.isIsActive());

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
            for (int i = 0; i < patientList.size(); i++) {
                Patient p = patientList.get(i);
                System.out.println((i + 1) + ": " + patientList.get(i));
                System.out.println("Queue : " + p.getQueueNumber());
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
                System.out.print("Enter Patient ID: ");
                String id = sc.nextLine();
                for (int i = 0; i < patientList.size(); i++) {
                    Patient p = patientList.get(i);
                    if (p.getPatientID().equalsIgnoreCase(id)) {
                        System.out.println("Displaying Information" + p);
                    }
                }
            }
            case 2 -> {
                System.out.print("Enter Sex (M/F): ");
                String sex = sc.nextLine();
                for (int i = 0; i < patientList.size(); i++) {
                    Patient p = patientList.get(i);
                    if (p.getSex().equalsIgnoreCase(sex)) {
                        System.out.println("Displaying Information" + p);
                    }
                }
            }
            case 3 -> {
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                for (int i = 0; i < patientList.size(); i++) {
                    Patient p = patientList.get(i);
                    if (p.getName().equalsIgnoreCase(name)) {
                        System.out.println("Displaying Information" + p);
                    }
                }
            }
            case 0 -> {
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
                case 1 -> { // search by Patient ID
                    if (p.getPatientID().equalsIgnoreCase(keyword)) {
                        results.add(p);
                    }
                }
                case 2 -> { // search by Name
                    if (p.getName().equalsIgnoreCase(keyword)) {
                        results.add(p);
                    }
                }
                case 3 -> { // search by Queue Number
                    if (p.getQueueNumber().equals(keyword)) {
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
        String id = getValidatedPatientId();
        String name = getNonEmptyInput("Enter patient name: ");
        String ic = getNonEmptyInput("Enter IC number: ");
        String contact = getNonEmptyInput("Enter contact number: ");
        String sex = getNonEmptyInput("Enter sex (M/F): ");
        String allergy = getOptionalInput("Enter allergy history (leave blank if none): ");

        LocalDate dob = getValidatedDate("Enter Date of Birth (yyyy-MM-dd): ");
        LocalDate regDate = LocalDate.now(); // auto set registration date to today
        LocalDate lastVisit = null; // optional, can leave empty or set later

        Patient p = new Patient();
        p.setPatientID(id);
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

    private String getValidatedPatientId() {
        String input;
        while (true) {
            System.out.print("Enter Patient ID (format: P001): ");
            input = sc.nextLine().trim();
            if (input.matches("^P\\d{3}$")) {
                return input;
            }
            System.out.println("Invalid format. Must be P followed by 3 digits.");
        }
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
        patientCtrl.runPatientMaintenance();
    }

    // ✅ Added getSize method
    public int getSize() {
        return patientList.size();
    }
}
