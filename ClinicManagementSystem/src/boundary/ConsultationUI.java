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

    private final Scanner sc = new Scanner(System.in);
    

    // ---------- Menu ----------
    public int getMenuChoice() {
        System.out.println("\n===== Consultation Management =====");
        System.out.println("1. Display All Consultations");
        System.out.println("2. Add Consultation");
        System.out.println("3. Update Consultation");
        System.out.println("4. Delete Consultation");
        System.out.println("5. Add Treatment to Consultation");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
        return readInt();
    }

    public int inputConsultationIndex() {
        System.out.print("Enter consultation index: ");
        return readInt();
    }
    
    

    // ---------- Manual Creation ----------
    public Consultation inputConsultationDetails(Patient patient, Doctor doctor) {
        sc.nextLine(); // flush input
        System.out.println("\n--- Enter Consultation Details ---");

        System.out.print("Consultation ID: ");
        String id = sc.nextLine().trim();

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("DateTime set as current: " + dateTime);

        System.out.print("Symptoms: ");
        String symptoms = sc.nextLine().trim();

        ListInterface<MedicalTreatment> treatments = new LinkedList<>();

        System.out.print("Notes: ");
        String notes = sc.nextLine().trim();

        System.out.print("Next appointment (yyyy-mm-dd): ");
        String nextDateStr = sc.nextLine().trim();
        LocalDate nextAppointment = nextDateStr.isEmpty() ? null : LocalDate.parse(nextDateStr);

        boolean isFollowUp = askBoolean("Is Follow Up? (true/false): ");
        int duration = readInt("Duration (minutes): ");
        double fee = readDouble("Consultation Fee: ");

        System.out.print("Status: ");
        String status = sc.nextLine().trim();

        return new Consultation(id, dateTime, patient, doctor, symptoms, treatments, notes,
                nextAppointment, isFollowUp, duration, fee, status);
    }

    // ---------- Helpers ----------
    private int readInt() {
        return readInt("");
    }

    private int readInt(String prompt) {
        while (true) {
            if (!prompt.isEmpty()) {
                System.out.print(prompt);
            }
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Enter a valid integer.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Enter a valid number.");
            }
        }
    }

    private boolean askBoolean(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim().toLowerCase();
            if (input.matches("true|t|yes|y|1")) {
                return true;
            }
            if (input.matches("false|f|no|n|0")) {
                return false;
            }
            System.out.println("❌ Please enter true/false or yes/no.");
        }
    }
}
