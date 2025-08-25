package boundary;

import entity.Doctor;
import java.util.Scanner;

public class DoctorUI {
    private Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n==== Doctor Management Menu ====");
        System.out.println("1. List all doctors");
        System.out.println("2. Add new doctor");
        System.out.println("3. Update doctor");
        System.out.println("4. Delete doctor");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listAllDoctors(String outputStr) {
        System.out.println("\nList of Doctors:\n" + outputStr);
    }

    public Doctor inputDoctorDetails() {
        System.out.print("Enter Doctor ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        Doctor d = new Doctor();
        d.setDoctorId(id);
        d.setName(name);
        d.setSpecialization(specialization);
        d.setEmail(email);
        return d;
    }

    public int inputDoctorIndex() {
        System.out.print("Enter doctor index to update/delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        return index;
    }
}
