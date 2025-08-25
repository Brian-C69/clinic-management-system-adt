package boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import adt.LinkedList;
import adt.ListInterface;
import entity.*;

public class ConsultationUI {
    private Scanner sc = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\n===== Consultation Management =====");
        System.out.println("1. Display All Consultations");
        System.out.println("2. Add Consultation");
        System.out.println("3. Update Consultation");
        System.out.println("4. Delete Consultation");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
        return sc.nextInt();
    }

    public int inputConsultationIndex() {
        System.out.print("Enter consultation index: ");
        return sc.nextInt();
    }

    public Consultation inputConsultationDetails() {
        sc.nextLine(); // consume newline
        System.out.println("\n--- Enter Consultation Details ---");
        System.out.print("Consultation ID: ");
        String id = sc.nextLine();

        LocalDateTime dateTime = LocalDateTime.now(); // simplified for demo
        System.out.println("DateTime set as current: " + dateTime);

        // For now, create dummy Patient and Doctor (later integrate MaintainPatient / MaintainDoctor)
        Patient patient = new Patient();
        patient.setName("Dummy Patient");

        Doctor doctor = new Doctor();
        doctor.setName("Dummy Doctor");

        System.out.print("Symptoms: ");
        String symptoms = sc.nextLine();

        System.out.print("Diagnosis: ");
        String diagnosis = sc.nextLine();

        ListInterface<MedicalTreatment> treatments = new LinkedList<>(); // empty list for now

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
