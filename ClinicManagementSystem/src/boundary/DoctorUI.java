// File: boundary/DoctorUI.java
package boundary;

import adt.ListInterface;
import control.MaintainDoctor;
import entity.Consultation;
import entity.Doctor;
import entity.Patient;
import java.time.LocalDateTime;

import java.util.Scanner;

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
            System.out.println("No doctor available. Please add doctor !");
        } else {
            for (int i = 0; i < doctorCtrl.getSize(); i++) {
                Doctor displayDuty = doctorCtrl.getDoctor(i);
                displayDuty.displayDoctorSchedule(displayDuty);
                System.out.println("Doctor Index : " + (i + 1) + doctorCtrl.getDoctor(i));
            }
        }
    }

    private void updateDoctor() {
        displayAllDoctors();
        if (doctorCtrl.getSize() == 0) {
            System.out.println("No doctor available. Please add doctor !");
        } else {
            int index = getValidatedIndex("Enter doctor index to update (starting from 1): ", doctorCtrl.getSize()) - 1;
            Doctor newData = inputDoctorDetails();

            boolean updated = doctorCtrl.updateExistingDoctor(index, newData);
            if (updated) {
                System.out.println("Doctor updated successfully.");
            } else {
                System.out.println("Doctor Update failed.");
            }
        }
    }

    private void deleteDoctor() {
        displayAllDoctors();
        if (doctorCtrl.getSize() == 0) {
            System.out.println("No doctor available. Please add doctor !");
        } else {

            int index = getValidatedIndex("Enter doctor index to delete (starting from 1): ", doctorCtrl.getSize()) - 1;
            Doctor beforeDeleteDoctor = doctorCtrl.getDoctor(index);
            Doctor removed = doctorCtrl.deleteDoctor(index);

            System.out.println("You are about to delete doctor " + beforeDeleteDoctor.getName());
            System.out.println("Are you sure you want to delete ? (0 = NO | 1 = YES)");

            int deleteDoctorChoice = Integer.parseInt(sc.nextLine());

            if (deleteDoctorChoice == 1) {
                doctorCtrl.deleteDoctor(index);
                System.out.println("Doctor " + removed.getName() + "Is Deleted !");
            } else {
                System.out.println("Abort Delete Doctor Operation !");
            }
        }
    }

    public Doctor inputDoctorDetails() {
        Doctor d = new Doctor();

        System.out.print("Enter Doctor Name: ");
        d.setName(sc.nextLine().trim());

        System.out.print("Enter MMC Number: ");
        d.setMmcNumber(sc.nextLine().trim());

        System.out.print("Enter Specialization: ");
        d.setSpecialization(sc.nextLine().trim());

        System.out.print("Enter Email: ");
        d.setEmail(sc.nextLine().trim());

        System.out.print("Enter Gender (Male/Female): ");
        d.setGender(sc.nextLine().trim());

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
                    System.out.println("Invalid format. Try again.");
                }
            }
            if (start == null) {
                continue; // skip this slot
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
                    System.out.println("Invalid format. Try again.");
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

        ListInterface<Doctor> foundPatients = doctorCtrl.linearSearch(searchChoice, keyword);
        if (foundPatients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (int i = 0; i < foundPatients.size(); i++) {
                System.out.println(foundPatients.get(i));
            }
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
                for (int i = 0; i < results.size(); i++) {
                    System.out.println(results.get(i));
                }

            }
            case 2 -> {
                boolean yes = getValidatedInteger("Filter active patients? (1 = Yes, 0 = No): ") == 1;
                ListInterface<Doctor> results = doctorCtrl.filterByAvailability(yes);
                for (int i = 0; i < results.size(); i++) {
                    System.out.println(results.get(i));
                }
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
}
