// File: boundary/DoctorUI.java
package boundary;

import control.MaintainDoctor;
import entity.Doctor;

import java.util.Scanner;

public class DoctorUI {
    private Scanner sc = new Scanner(System.in);
    private MaintainDoctor doctorCtrl;

    public DoctorUI(MaintainDoctor doctorCtrl) {
        this.doctorCtrl = doctorCtrl;
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
            System.out.println("5. Find Doctor by ID");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = getValidatedInteger("");

            switch (choice) {
                case 1 -> displayAllDoctors();
                case 2 -> doctorCtrl.addDoctor(inputDoctorDetails());
                case 3 -> updateDoctor();
                case 4 -> deleteDoctor();
                case 5 -> {
                    String id = getValidatedDoctorId();
                    Doctor d = doctorCtrl.findDoctorById(id);
                    System.out.println(d != null ? d : "Doctor not found.");
                }
                case 0 -> System.out.println("Exiting Doctor Management...");
                default -> System.out.println("❌ Invalid choice.");
            }
        } while (choice != 0);
    }

    // ========= UI Methods =========
    public void displayAllDoctors() {
        if (doctorCtrl.getSize() == 0) {
            System.out.println("No doctors found.");
        } else {
            System.out.println("\n--- All Doctors ---");
            for (int i = 0; i < doctorCtrl.getSize(); i++) {
                System.out.println((i + 1) + ": " + doctorCtrl.getDoctor(i));
            }
        }
    }

    private void updateDoctor() {
        displayAllDoctors();
        if (doctorCtrl.getSize() == 0) return;

        int index = getValidatedIndex("Enter doctor index to update (starting from 1): ", doctorCtrl.getSize()) - 1;
        Doctor newData = inputDoctorDetails();

        boolean updated = doctorCtrl.updateExistingDoctor(index, newData);
        if (updated) {
            System.out.println("✅ Doctor updated successfully.");
        } else {
            System.out.println("❌ Update failed.");
        }
    }

    private void deleteDoctor() {
        displayAllDoctors();
        if (doctorCtrl.getSize() == 0) return;

        int index = getValidatedIndex("Enter doctor index to delete (starting from 1): ", doctorCtrl.getSize()) - 1;
        Doctor removed = doctorCtrl.deleteDoctor(index);

        if (removed != null) {
            System.out.println("✅ Doctor deleted: " + removed.getName());
        } else {
            System.out.println("❌ Deletion failed.");
        }
    }

    public Doctor inputDoctorDetails() {
        Doctor d = new Doctor();

        d.setDoctorId(getValidatedDoctorId());
        System.out.print("Enter Doctor Name: ");
        d.setName(sc.nextLine().trim());
        System.out.print("Enter Specialization: ");
        d.setSpecialization(sc.nextLine().trim());
        System.out.print("Enter Email: ");
        d.setEmail(sc.nextLine().trim());

        return d;
    }

    // ========= Input Helpers =========
    private String getValidatedDoctorId() {
        String input;
        while (true) {
            System.out.print("Enter Doctor ID (format: D001): ");
            input = sc.nextLine().trim();
            if (input.matches("^D\\d{3}$")) return input;
            System.out.println("❌ Invalid format. Must be D followed by 3 digits.");
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
}
