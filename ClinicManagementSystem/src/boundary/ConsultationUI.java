package boundary;

import entity.Consultation;
import entity.Doctor;
import entity.Patient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsultationUI {

    private Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n==== Consultation Management Menu ====");
        System.out.println("1. List all consultations");
        System.out.println("2. Add new consultation");
        System.out.println("3. Update consultation");
        System.out.println("4. Delete consultation");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listAllConsultations(String outputStr) {
        System.out.println("\nList of Consultations:\n" + outputStr);
    }

    // --- Input helpers ---
    public String inputConsultationId() {
        System.out.print("Enter Consultation ID: ");
        return scanner.nextLine();
    }

    public LocalDateTime inputConsultationDateTime() {
        System.out.print("Enter Consultation DateTime (yyyy-MM-ddTHH:mm): ");
        String input = scanner.nextLine();
        return LocalDateTime.parse(input); // e.g. 2025-08-20T15:00
    }

    public String inputSymptoms() {
        System.out.print("Enter Symptoms: ");
        return scanner.nextLine();
    }

    public String inputDiagnosis() {
        System.out.print("Enter Diagnosis: ");
        return scanner.nextLine();
    }

    public String inputNotes() {
        System.out.print("Enter Notes: ");
        return scanner.nextLine();
    }

    public LocalDate inputNextAppointment() {
        System.out.print("Enter Next Appointment (yyyy-MM-dd): ");
        String input = scanner.nextLine();
        return LocalDate.parse(input);
    }

    public int inputDurationMinutes() {
        System.out.print("Enter Duration (minutes): ");
        int val = scanner.nextInt();
        scanner.nextLine();
        return val;
    }

    public double inputConsultationFee() {
        System.out.print("Enter Consultation Fee: ");
        double val = scanner.nextDouble();
        scanner.nextLine();
        return val;
    }

    public String inputStatus() {
        System.out.print("Enter Status (Scheduled/Completed/Cancelled): ");
        return scanner.nextLine();
    }

    // --- Index for update/delete ---
    public int inputConsultationIndex() {
        System.out.print("Enter consultation index to update/delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();
        return index;
    }
}
