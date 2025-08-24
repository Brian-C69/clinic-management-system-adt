package boundary;

import entity.Consultation;
import adt.LinkedList;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsultationUI {
    private Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n=== Consultation Menu ===");
        System.out.println("1. List All Consultations");
        System.out.println("2. Add Consultation");
        System.out.println("3. Update Consultation");
        System.out.println("4. Delete Consultation");
        System.out.println("0. Back");
        System.out.print("Enter choice: ");
        return scanner.nextInt();
    }

    public int inputConsultationIndex() {
        System.out.print("Enter consultation index: ");
        return scanner.nextInt();
    }

    public Consultation inputConsultationDetails() {
        scanner.nextLine(); // clear buffer
        System.out.print("Enter Consultation ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Symptoms: ");
        String symptoms = scanner.nextLine();

        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();

        System.out.print("Enter Notes: ");
        String notes = scanner.nextLine();

        System.out.print("Enter Duration (minutes): ");
        int duration = scanner.nextInt();

        System.out.print("Enter Consultation Fee: ");
        double fee = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Status (Scheduled/Completed/Cancelled): ");
        String status = scanner.nextLine();

        return new Consultation(
                id,
                LocalDateTime.now(),
                null, // Patient linked in ClinicUI later
                null, // Doctor linked in ClinicUI later
                symptoms,
                diagnosis,
                new LinkedList<>(), // Treatments empty initially
                notes,
                null, // Next appointment optional
                true,
                duration,
                fee,
                status
        );
    }
}
