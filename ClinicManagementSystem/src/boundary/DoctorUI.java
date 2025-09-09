// File: boundary/DoctorUI.java
package boundary;

import adt.ListInterface;
import control.MaintainDoctor;
import entity.Consultation;
import entity.Doctor;
import entity.Patient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import report.DoctorSummaryReport;

public class DoctorUI {

    private Scanner sc = new Scanner(System.in);
    private MaintainDoctor doctorCtrl;

    public DoctorUI(MaintainDoctor doctorCtrl, Scanner sc) {
        this.doctorCtrl = doctorCtrl;
        this.sc = sc;
    }

    public MaintainDoctor getController() {
        return doctorCtrl;
    }

    public void runDoctorMaintenance() {
        int choice;
        do {
            System.out.println("\n--- Doctor Management ---");
            System.out.println("1. Display All Doctors");
            System.out.println("2. Add Doctor");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Search Doctor");
            System.out.println("6. Filter Doctor");
            System.out.println("7. Doctor Summary Report");
            System.out.println("0. Return To Main Menu");
            System.out.print("Enter choice: ");
            choice = getValidatedInteger("");

            switch (choice) {
                case 1 ->
                    displayAllDoctors();
                case 2 ->
                    doctorCtrl.addDoctor(inputDoctorDetails());
                case 3 ->
                    updateDoctor();
                case 4 ->
                    deleteDoctor();
                case 5 ->
                    searchDoctor();
                case 6 ->
                    filterDoctor();
                case 7 ->
                    DoctorSummaryReport.generate(doctorCtrl.getAllDoctors());
                case 0 ->
                    System.out.println("Exiting Doctor Management...");
                default ->
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    // ========= UI Methods =========
    public void displayAllDoctors() {
        if (doctorCtrl.getSize() == 0) {
            System.out.println("No doctor available. Please add doctor!");
        } else {
            // Adjusted column widths (Duty Slots widened to 55 chars)
            String rowFormat = "| %-10s | %-20s | %-20s | %-12s | %-25s | %-6s | %-10s | %-55s | %-13s |";

            // Print header
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format(
                    rowFormat,
                    "Doctor ID", "Name", "Specialization", "MMC Number", "Email", "Gender", "Available", "Duty Slots", "Consultations"
            ));
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            DateTimeFormatter dateTimeFmt = DateTimeFormatter.ofPattern("dd/MM HH:mm");

            // Print each doctor in a neat row
            for (int i = 0; i < doctorCtrl.getSize(); i++) {
                Doctor d = doctorCtrl.getDoctor(i);

                // Build duty slots string
                StringBuilder slotsStr = new StringBuilder();
                if (d.getDutySchedule() != null && !d.getDutySchedule().isEmpty()) {
                    for (int j = 0; j < d.getDutySchedule().size(); j++) {
                        Doctor.DutySlot slot = d.getDutySchedule().get(j);
                        slotsStr.append("[")
                                .append(slot.getStartTime().format(dateTimeFmt))
                                .append(" - ")
                                .append(slot.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                                .append("]");
                        if (j < d.getDutySchedule().size() - 1) {
                            slotsStr.append(", ");
                        }
                    }
                } else {
                    slotsStr.append("None");
                }

                // Print row with formatted duty slots
                System.out.println(String.format(
                        rowFormat,
                        d.getDoctorId(),
                        d.getName(),
                        d.getSpecialization(),
                        d.getMmcNumber(),
                        d.getEmail(),
                        d.getGender(),
                        d.getStatus(),
                        slotsStr.toString(),
                        (d.getConsultations() != null ? d.getConsultations().size() : 0)
                ));
            }

            // Closing line
                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    private void updateDoctor() {
        displayAllDoctors();
        if (doctorCtrl.getSize() == 0) {
            System.out.println("No doctor available. Please add doctor !");
        } else {
            int index = getValidatedIndex("Enter doctor index to update (starting from 1): ", doctorCtrl.getSize()) - 1;

            Doctor foundDoctor = doctorCtrl.getDoctor(index);
            System.out.println("You are about to edit " + foundDoctor.getName() + " information. Proceed ? (0=NO | 1= YES) : ");
            int editDoctorChoice = Integer.parseInt(sc.nextLine());

            if (editDoctorChoice == 1) {
                Doctor doctorNewData = inputDoctorDetails();
                boolean success = doctorCtrl.updateExistingDoctor(index, doctorNewData);

                if (success) {
                    System.out.println("Successfully updated " + doctorNewData.getName() + " information !");
                } else {
                    System.out.println("Error occured !");
                }
            } else {
                System.out.println("Abort Edit Operation !");
            }

        }
    }

    private void deleteDoctor() {
        displayAllDoctors();
        if (doctorCtrl.getSize() == 0) {
            System.out.println("No doctor available. Please add doctor !");
            return;
        }

        int index = getValidatedIndex("Enter doctor index to delete (starting from 1): ", doctorCtrl.getSize()) - 1;
        Doctor doctorToDelete = doctorCtrl.getDoctor(index);

        System.out.println("You are about to delete doctor " + doctorToDelete.getName());
        System.out.println("Are you sure you want to delete ? (0 = NO | 1 = YES)");

        int deleteDoctorChoice = Integer.parseInt(sc.nextLine());

        if (deleteDoctorChoice == 1) {
            Doctor removed = doctorCtrl.deleteDoctor(index);
            System.out.println("Doctor " + removed.getName() + " is deleted!");
        } else {
            System.out.println("Abort Delete Doctor Operation!");
        }
    }

    public Doctor inputDoctorDetails() {
        Doctor d = new Doctor();

        // Doctor name: allow letters, spaces, dots, and other symbols
        d.setName(getValidatedInput(
                "Enter Doctor Name: ",
                "^[A-Za-z .,'-]{2,}$",
                "Name must contain at least 2 characters (letters, spaces, '.', ',', '-', allowed)."
        ));

        // MMC Number: must start with "MMC" followed by exactly 4 digits
        d.setMmcNumber(getValidatedInput(
                "Enter MMC Number (Format: MMC1234): ",
                "^MMC\\d{4}$",
                "MMC Number must start with 'MMC' followed by 4 digits. Example: MMC1234"
        ));

        // Specialization: allow letters, spaces, and hyphens
        d.setSpecialization(getValidatedInput(
                "Enter Specialization: ",
                "^[A-Za-z\\- ]{3,}$",
                "Specialization must contain only letters/spaces (min 3 characters)."
        ));

        // Email validation
        d.setEmail(getValidatedInput(
                "Enter Email: ",
                "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$",
                "Invalid email format. Example: doctor@example.com"
        ));

        // Gender: only "M" or "F"
        d.setGender(getValidatedInput(
                "Enter Gender (M/F): ",
                "^(?i)(M|F)$",
                "Please enter 'M' or 'F' only."
        ));

        // Duty schedule (unchanged logic with validation)
        adt.LinkedList<Doctor.DutySlot> dutySchedule = new adt.LinkedList<>();
        System.out.print("Enter number of duty slots (or press Enter to skip): ");
        String numSlotsStr = sc.nextLine().trim();
        int numSlots = 0;
        if (!numSlotsStr.isEmpty()) {
            try {
                numSlots = Integer.parseInt(numSlotsStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Skipping duty slots.");
            }
        }

        for (int i = 0; i < numSlots; i++) {
            System.out.println("Duty Slot " + (i + 1) + ":");

            LocalDateTime start = null;
            while (start == null) {
                System.out.print("  Enter start date-time (yyyy-MM-ddTHH:mm) or press Enter to skip: ");
                String startStr = sc.nextLine().trim();
                if (startStr.isEmpty()) {
                    System.out.println("Skipping this duty slot.");
                    break;
                }
                try {
                    start = LocalDateTime.parse(startStr);
                } catch (Exception e) {
                    System.out.println("Invalid format. Example: 2025-09-02T14:30");
                }
            }
            if (start == null) {
                continue;
            }

            LocalDateTime end = null;
            while (end == null) {
                System.out.print("  Enter end date-time (yyyy-MM-ddTHH:mm): ");
                String endStr = sc.nextLine().trim();
                try {
                    end = LocalDateTime.parse(endStr);
                    if (end.isBefore(start)) {
                        System.out.println("End time cannot be before start time.");
                        end = null;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid format. Example: 2025-09-02T16:00");
                }
            }

            dutySchedule.add(new Doctor.DutySlot(start, end));
            System.out.println("Duty slot added.");
        }

        d.setDutySchedule(dutySchedule);
        d.setIsAvailable(true);
        d.setConsultations(new adt.LinkedList<Consultation>());
        d.setStatus("Active");

        d.displayDoctorSchedule(d);

        System.out.println("\nDoctor added successfully!");
        return d;
    }

    private void searchDoctor() {
        System.out.println("----------Search Options----------");
        System.out.println("1. Doctor ID");
        System.out.println("2. Doctor Name");
        System.out.println("0. Back to Main Menu");
        System.out.print("Choose search option: ");

        int searchChoice = getValidatedInteger("");
        if (searchChoice == 0) {
            return;
        }

        System.out.print("Enter search keyword: ");
        String keyword = sc.nextLine();

        ListInterface<Doctor> foundDoctors = doctorCtrl.linearSearch(searchChoice, keyword);

        if (foundDoctors.isEmpty()) {
            System.out.println("No doctors found.");
        } else {
            // Define consistent row format
            String rowFormat = "| %-10s | %-20s | %-20s | %-12s | %-25s | %-6s | %-10s | %-12s | %-13s |";

            // Print header once
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(String.format(
                    rowFormat,
                    "Doctor ID", "Name", "Specialization", "MMC Number", "Email", "Gender", "Available", "Duty Slots", "Consultations"
            ));
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

            // Print each found doctor in the table
            for (int i = 0; i < foundDoctors.size(); i++) {
                System.out.println(foundDoctors.get(i));
            }

            // Closing line
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    private void filterDoctor() {
        System.out.println("--- Filter Patients ---");
        System.out.println("1. Filter by Doctor Gender");
        System.out.println("2. Filter by Doctor Availablilty");
        System.out.println("0. Back to Main Menu");
        System.out.println("Please Choose The Following Filter Option :");

        int filterChoice = getValidatedInteger("");
        switch (filterChoice) {
            case 1 -> {
                System.out.print("Please enter gender (M | F): ");
                String gender = sc.nextLine();
                ListInterface<Doctor> results = doctorCtrl.filterByGender(gender);
                // Define consistent row format
                String rowFormat = "| %-10s | %-20s | %-20s | %-12s | %-25s | %-6s | %-10s | %-12s | %-13s |";

                // Print header
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(String.format(
                        rowFormat,
                        "Doctor ID", "Name", "Specialization", "MMC Number", "Email", "Gender", "Available", "Duty Slots", "Consultations"
                ));
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < results.size(); i++) {

                    System.out.println(results.get(i));

                }

                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

            }
            case 2 -> {
                boolean yes = getValidatedInteger("Filter active patients? (1 = Yes, 0 = No): ") == 1;
                ListInterface<Doctor> results = doctorCtrl.filterByAvailability(yes);
                // Define consistent row format
                String rowFormat = "| %-10s | %-20s | %-20s | %-12s | %-25s | %-6s | %-10s | %-12s | %-13s |";
                // Print header
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(String.format(
                        rowFormat,
                        "Doctor ID", "Name", "Specialization", "MMC Number", "Email", "Gender", "Available", "Duty Slots", "Consultations"
                ));

                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (int i = 0; i < results.size(); i++) {

                    System.out.println(results.get(i));

                }

                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            case 0 ->
                System.out.println("Returning back to menu...");
            default ->
                System.out.println("Invalid choice.");
        }
    }

    // ========= Input Helpers =========
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

    private String getValidatedInput(String prompt, String regex, String errorMsg) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (regex == null || input.matches(regex)) {
                return input;
            }
            System.out.println(errorMsg);
        }
    }
}
