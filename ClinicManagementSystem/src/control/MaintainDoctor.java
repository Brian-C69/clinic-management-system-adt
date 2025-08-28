package control;

import adt.ListInterface;
import adt.LinkedList;
import entity.Doctor;

import java.util.Scanner;

public class MaintainDoctor {
    private ListInterface<Doctor> doctorList = new LinkedList<>();
    private Scanner sc = new Scanner(System.in);

    // ========= MENU =========
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
                case 2 -> addDoctor(inputDoctorDetails());
                case 3 -> updateDoctor();
                case 4 -> deleteDoctor();
                case 5 -> {
                    String id = getValidatedDoctorId();
                    Doctor d = findDoctorById(id);
                    System.out.println(d != null ? d : "Doctor not found.");
                }
                case 0 -> System.out.println("Exiting Doctor Management...");
                default -> System.out.println("❌ Invalid choice.");
            }
        } while (choice != 0);
    }

    // ========= CRUD =========
    public void addDoctor(Doctor d) {
        doctorList.add(d);
        System.out.println("✅ Doctor added successfully!");
    }

    public boolean replaceDoctor(int index, Doctor newDoctor) {
        return doctorList.replace(index, newDoctor);
    }

    public void updateDoctor() {
        displayAllDoctors();
        int index = getValidatedIndex("Enter doctor index to update (starting from 1): ", doctorList.size()) - 1;
        Doctor existing = doctorList.get(index);

        Doctor newData = inputDoctorDetails();
        updateExistingDoctor(index, newData);
        System.out.println("✅ Doctor updated: " + existing.getName());
    }

    public boolean updateExistingDoctor(int index, Doctor doctorNewData) {
        if (index >= 0 && index < doctorList.size()) {
            Doctor existingDoctor = doctorList.get(index);

            if (doctorNewData.getDoctorId() != null) existingDoctor.setDoctorId(doctorNewData.getDoctorId());
            if (doctorNewData.getName() != null) existingDoctor.setName(doctorNewData.getName());
            if (doctorNewData.getMmcNumber() != null) existingDoctor.setMmcNumber(doctorNewData.getMmcNumber());
            if (doctorNewData.getSpecialization() != null) existingDoctor.setSpecialization(doctorNewData.getSpecialization());
            if (doctorNewData.getEmail() != null) existingDoctor.setEmail(doctorNewData.getEmail());
            if (doctorNewData.getGender() != null) existingDoctor.setGender(doctorNewData.getGender());
            if (doctorNewData.getDutySchedule() != null) existingDoctor.setDutySchedule(doctorNewData.getDutySchedule());
            if (doctorNewData.isIsAvailable() != existingDoctor.isIsAvailable()) existingDoctor.setIsAvailable(doctorNewData.isIsAvailable());
            if (doctorNewData.getConsultations() != null) existingDoctor.setConsultations(doctorNewData.getConsultations());
            if (doctorNewData.getStatus() != null) existingDoctor.setStatus(doctorNewData.getStatus());

            return true;
        }
        return false;
    }

    public void deleteDoctor() {
        displayAllDoctors();
        int index = getValidatedIndex("Enter doctor index to delete (starting from 1): ", doctorList.size()) - 1;
        Doctor removed = doctorList.remove(index);
        System.out.println("✅ Doctor deleted: " + removed.getName());
    }

    public void displayAllDoctors() {
        if (doctorList.size() == 0) {
            System.out.println("No doctors found.");
        } else {
            System.out.println("\n--- All Doctors ---");
            for (int i = 0; i < doctorList.size(); i++) {
                System.out.println((i + 1) + ": " + doctorList.get(i));
            }
        }
    }

    public Doctor getDoctor(int index) {
        return (index >= 0 && index < doctorList.size()) ? doctorList.get(index) : null;
    }

    public Doctor findDoctorById(String doctorId) {
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor d = doctorList.get(i);
            if (d.getDoctorId().equals(doctorId)) {
                return d;
            }
        }
        return null;
    }

    // ========= Input Helpers =========
    Doctor inputDoctorDetails() {
        String id = getValidatedDoctorId();
        String name = getNonEmptyInput("Enter doctor name: ");
        String specialization = getNonEmptyInput("Enter specialization: ");
        String email = getNonEmptyInput("Enter email: ");

        Doctor d = new Doctor();
        d.setDoctorId(id);
        d.setName(name);
        d.setSpecialization(specialization);
        d.setEmail(email);
        return d;
    }

    private String getValidatedDoctorId() {
        String input;
        while (true) {
            System.out.print("Enter Doctor ID (format: D001): ");
            input = sc.nextLine().trim();
            if (input.matches("^D\\d{3}$")) return input;
            System.out.println("❌ Invalid format. Must be D followed by 3 digits.");
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
        MaintainDoctor doctorCtrl = new MaintainDoctor();
        doctorCtrl.runDoctorMaintenance();
    }
    

    // ✅ Added getSize method
    public int getSize() {
        return doctorList.size();
    }
}
