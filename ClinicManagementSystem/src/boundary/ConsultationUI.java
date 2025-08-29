// File: boundary/ConsultationUI.java
package boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import adt.LinkedList;
import adt.ListInterface;
import entity.Consultation;
import entity.Doctor;
import entity.MedicalTreatment;
import entity.Patient;

public class ConsultationUI {
    private Scanner sc = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n===== Consultation Management =====");
        System.out.println("1. Display All Consultations");
        System.out.println("2. Add Consultation");
        System.out.println("3. Update Consultation");
        System.out.println("4. Delete Consultation");
        System.out.println("5. Add Treatment to Consultation");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
        return sc.nextInt();
    }

    public int inputConsultationIndex() {
        System.out.print("Enter consultation index: ");
        return sc.nextInt();
    }

    /**
     * Used when manually creating a Consultation object
     * (if you ever want to do that without using ConsultationManager)
     */
    public Consultation inputConsultationDetails(Patient patient, Doctor doctor) {
        sc.nextLine(); // consume leftover newline
        System.out.println("\n--- Enter Consultation Details ---");

        System.out.print("Consultation ID: ");
        String id = sc.nextLine();

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("DateTime set as current: " + dateTime);

        System.out.print("Symptoms: ");
        String symptoms = sc.nextLine();

        System.out.print("Diagnosis: ");
        String diagnosis = sc.nextLine();

        ListInterface<MedicalTreatment> treatments = new LinkedList<>(); // empty list initially

        System.out.print("Notes: ");
        String notes = sc.nextLine();

        System.out.print("Next appointment (yyyy-mm-dd): ");
        String nextDateStr = sc.nextLine();
        LocalDate nextAppointment = nextDateStr.isEmpty() ? null : LocalDate.parse(nextDateStr);

        System.out.print("Is Follow Up? (true/false): ");
        boolean isFollowUp = sc.nextBoolean();

        System.out.print("Duration (minutes): ");
        int duration = sc.nextInt();

        System.out.print("Consultation Fee: ");
        double fee = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.print("Status: ");
        String status = sc.nextLine();

        return new Consultation(id, dateTime, patient, doctor, symptoms, diagnosis,
                treatments, notes, nextAppointment, isFollowUp, duration, fee, status);
    }
}
