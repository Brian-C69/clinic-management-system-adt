package control;
/**
 *
 * @author Bryan Kok Fong Wen
 */

import adt.ListInterface;
import adt.LinkedList;
import boundary.*;
import entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import report.ConsultationSummaryReport;

public class ConsultationManager {

    private final ListInterface<Consultation> consultationList;
    private final MaintainPatient patientCtrl;
    private final PatientUI patientUI;
    private final MaintainDoctor doctorCtrl;
    private final DoctorUI doctorUI;
    private final ConsultationUI consultUI;
    private final Scanner sc;

    public ConsultationManager(ListInterface<Consultation> consultStore,
                               MaintainPatient patientCtrl,
                               PatientUI patientUI,
                               MaintainDoctor doctorCtrl,
                               DoctorUI doctorUI,
                               Scanner sc) {
        this.consultationList = consultStore != null ? consultStore : new LinkedList<>();
        this.patientCtrl = patientCtrl;
        this.patientUI = patientUI;
        this.doctorCtrl = doctorCtrl;
        this.doctorUI = doctorUI;
        this.consultUI = new ConsultationUI();
        this.sc = sc;
    }

    public ConsultationManager() {
        Scanner scanner = new Scanner(System.in);
        MaintainPatient patientCtrl = new MaintainPatient();
        MaintainDoctor doctorCtrl = new MaintainDoctor();
        this.consultationList = new LinkedList<>();
        this.patientCtrl = patientCtrl;
        this.patientUI = new PatientUI(patientCtrl, scanner);
        this.doctorCtrl = doctorCtrl;
        this.doctorUI = new DoctorUI(doctorCtrl, scanner);
        this.consultUI = new ConsultationUI();
        this.sc = scanner;
    }

    public void runConsultationMaintenance() {
        int choice;
        do {
            choice = consultUI.getMenuChoice();
            switch (choice) {
                case 0 -> System.out.println("Exiting Consultation Management...");
                case 1 -> displayAllConsultations();
                case 2 -> addConsultation();
                case 3 -> updateConsultation();
                case 4 -> deleteConsultation();
                case 5 -> addTreatmentToConsultation();
                case 6 -> searchBySymptoms();
                case 7 -> filterByDoctorId();
                case 8 -> ConsultationSummaryReport.print(consultationList);
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public void addConsultation() {
        Patient patient = choosePatient();
        if (patient == null) return;

        Doctor doctor = chooseDoctor();
        if (doctor == null) return;

        LocalDateTime start;
        int duration;
        while (true) {
            System.out.print("Enter desired consultation start time (yyyy-MM-dd HH:mm): ");
            String input = sc.nextLine().trim();
            try {
                start = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                duration = readInt("Enter consultation duration (minutes): ");
                if (doctor.isAvailable(start, duration)) break;
                System.out.println("❌ Doctor is not available at that time.");
            } catch (Exception e) {
                System.out.println("❌ Invalid date/time format.");
            }
        }

        String consultationId;
        while (true) {
            System.out.print("Enter Consultation ID (format: C001): ");
            consultationId = sc.nextLine().trim();
            if (consultationId.matches("^C\\d{3}$") && findById(consultationId) == null) break;
            System.out.println("❌ Invalid or duplicate ID.");
        }

        String symptoms = getNonEmpty("Enter symptoms: ");
        String notes = getNonEmpty("Enter notes: ");
        LocalDate nextAppointment = readDateOptional("Enter next appointment (yyyy-mm-dd, empty for none): ");
        boolean isFollowUp = askYesNo("Is follow up? (y/n): ");
        double fee = readDouble("Enter consultation fee: ");
        String status = getNonEmpty("Enter status: ");

        Consultation consult = new Consultation(
                consultationId, start, patient, doctor,
                symptoms, new LinkedList<>(), notes,
                nextAppointment, isFollowUp, duration, fee, status
        );

        doctor.getConsultations().add(consult);
        consultationList.add(consult);
        System.out.println("✅ Consultation added.");
    }

    public void displayAllConsultations() {
        if (consultationList.isEmpty()) {
            System.out.println("No consultations found.");
        } else {
            System.out.println("\n--- All Consultations ---");
            for (int i = 0; i < consultationList.size(); i++) {
                System.out.println((i + 1) + " -> " + consultationList.get(i));
            }
        }
    }

    public void updateConsultation() {
        if (consultationList.isEmpty()) {
            System.out.println("No consultations available.");
            return;
        }

        int idx = consultUI.inputConsultationIndex() - 1;
        if (idx < 0 || idx >= consultationList.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Consultation c = consultationList.get(idx);
        c.setSymptoms(getNonEmpty("Enter new symptoms: "));
        c.setNotes(getNonEmpty("Enter new notes: "));
        c.setDurationMinutes(readInt("Enter new duration (minutes): "));
        c.setConsultationFee(readDouble("Enter new consultation fee: "));
        c.setStatus(getNonEmpty("Enter new status: "));
        System.out.println("✅ Consultation updated.");
    }

    public void deleteConsultation() {
        if (consultationList.isEmpty()) {
            System.out.println("No consultations available.");
            return;
        }

        int idx = consultUI.inputConsultationIndex() - 1;
        if (idx < 0 || idx >= consultationList.size()) {
            System.out.println("Invalid index.");
            return;
        }

        Consultation removed = consultationList.remove(idx);
        System.out.println("✅ Deleted consultation: " + removed.getConsultationId());
    }

    public void addTreatmentToConsultation() {
        if (consultationList.isEmpty()) {
            System.out.println("No consultations available.");
            return;
        }

        displayAllConsultations();
        int index = readInt("Enter consultation index to add treatment: ") - 1;

        if (index < 0 || index >= consultationList.size()) {
            System.out.println("❌ Invalid index.");
            return;
        }

        Consultation c = consultationList.get(index);
        MedicalTreatmentUI treatmentUI = new MedicalTreatmentUI(sc);
        MedicalTreatment t = treatmentUI.createTreatment(c.getPatient(), c.getDoctor());
        c.getTreatments().add(t);
        System.out.println("✅ Treatment added to consultation " + c.getConsultationId());
    }

    public void searchBySymptoms() {
        System.out.print("Enter keyword to search in symptoms: ");
        String keyword = sc.nextLine().trim().toLowerCase();
        boolean found = false;
        for (int i = 0; i < consultationList.size(); i++) {
            Consultation c = consultationList.get(i);
            if (c.getSymptoms().toLowerCase().contains(keyword)) {
                System.out.println((i + 1) + " -> " + c);
                found = true;
            }
        }
        if (!found) System.out.println("No consultations matched your search.");
    }

    public void filterByDoctorId() {
        System.out.print("Enter Doctor ID to filter consultations: ");
        String doctorId = sc.nextLine().trim();
        boolean found = false;
        for (int i = 0; i < consultationList.size(); i++) {
            Consultation c = consultationList.get(i);
            if (c.getDoctor() != null && c.getDoctor().getDoctorId().equalsIgnoreCase(doctorId)) {
                System.out.println((i + 1) + " -> " + c);
                found = true;
            }
        }
        if (!found) System.out.println("No consultations found for this doctor.");
    }

    private Consultation findById(String id) {
        for (int i = 0; i < consultationList.size(); i++) {
            if (consultationList.get(i).getConsultationId().equalsIgnoreCase(id)) return consultationList.get(i);
        }
        return null;
    }

    private Patient choosePatient() {
        System.out.println("\n--- Select Patient ---");
        patientUI.displayAllPatients();
        int pIndex = getValidatedIndex("Enter patient index (0 to register new): ", patientCtrl.getSize(), true);
        return (pIndex == 0) ? createAndAddPatient() : patientCtrl.getPatient(pIndex - 1);
    }

    private Doctor chooseDoctor() {
        System.out.println("\n--- Select Doctor ---");
        doctorUI.displayAllDoctors();
        int dIndex = getValidatedIndex("Enter doctor index (0 to register new): ", doctorCtrl.getSize(), true);
        return (dIndex == 0) ? createAndAddDoctor() : doctorCtrl.getDoctor(dIndex - 1);
    }

    private Patient createAndAddPatient() {
        Patient p = patientUI.inputPatientDetails();
        patientCtrl.addPatient(p);
        return p;
    }

    private Doctor createAndAddDoctor() {
        Doctor d = doctorUI.inputDoctorDetails();
        doctorCtrl.addDoctor(d);
        return d;
    }

    private int getValidatedIndex(String prompt, int maxSize, boolean allowZero) {
        int index;
        while (true) {
            try {
                System.out.print(prompt);
                index = Integer.parseInt(sc.nextLine().trim());
                if ((allowZero && index == 0) || (index >= 1 && index <= maxSize)) return index;
                System.out.println("❌ Enter 1.." + maxSize + (allowZero ? " or 0" : ""));
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number.");
            }
        }
    }

    private String getNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("❌ Input cannot be empty!");
        }
    }

    private boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().toLowerCase();
            if (s.matches("y|yes|1|true|t")) return true;
            if (s.matches("n|no|0|false|f")) return false;
            System.out.println("Please answer y/n.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Enter an integer.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Enter a number.");
            }
        }
    }

    private LocalDate readDateOptional(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (s.isEmpty()) return null;
            try {
                return LocalDate.parse(s);
            } catch (Exception e) {
                System.out.println("❌ Invalid date (yyyy-mm-dd).");
            }
        }
    }

    public static void main(String[] args) {
        new ClinicUI().run();
    }
}
