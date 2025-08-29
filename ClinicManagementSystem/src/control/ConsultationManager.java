package control;

import adt.ListInterface;
import adt.LinkedList;
import boundary.ClinicUI;
import boundary.ConsultationUI;
import boundary.PatientUI;
import boundary.DoctorUI;
import boundary.MedicalTreatmentUI;
import entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsultationManager {

    private final ListInterface<Consultation> consultationList;
    private final MaintainPatient patientCtrl;
    private final PatientUI patientUI;
    private final MaintainDoctor doctorCtrl;
    private final DoctorUI doctorUI;
    private final ConsultationUI consultUI = new ConsultationUI();
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
        this.sc = sc;
    }

    public ConsultationManager() {
        this(new LinkedList<>(), new MaintainPatient(), new PatientUI(new MaintainPatient()),
                new MaintainDoctor(), new DoctorUI(new MaintainDoctor()), new Scanner(System.in));
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
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public void addConsultation() {
        System.out.println("\n--- Select Patient ---");
        patientUI.displayAllPatients();
        System.out.print("Enter patient index (0 to register new): ");
        int pChoice = getValidatedIndex("", patientCtrl.getSize(), true);

        Patient patient = (pChoice == 0) ? createAndAddPatient() : patientCtrl.getPatient(pChoice - 1);
        if (patient == null) {
            System.out.println("❌ No patient selected.");
            return;
        }

        System.out.println("\n--- Select Doctor ---");
        doctorUI.displayAllDoctors();
        System.out.print("Enter doctor index (0 to register new): ");
        int dChoice = getValidatedIndex("", doctorCtrl.getSize(), true);

        Doctor doctor = (dChoice == 0) ? createAndAddDoctor() : doctorCtrl.getDoctor(dChoice - 1);
        if (doctor == null) {
            System.out.println("❌ No doctor selected.");
            return;
        }

        LocalDateTime desiredStart;
        int durationMinutes;
        while (true) {
            System.out.print("Enter desired consultation start time (yyyy-MM-dd HH:mm): ");
            String input = sc.nextLine().trim();
            try {
                desiredStart = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                durationMinutes = readInt("Enter consultation duration (minutes): ");
                if (doctor.isAvailable(desiredStart, durationMinutes)) {
                    break;
                } else {
                    System.out.println("❌ Doctor is NOT available at this time. Try another time.");
                }
            } catch (Exception e) {
                System.out.println("❌ Invalid date/time format.");
            }
        }

        String consultationId;
        while (true) {
            System.out.print("Enter Consultation ID (format: C001): ");
            consultationId = sc.nextLine().trim();
            if (consultationId.matches("^C\\d{3}$") && findById(consultationId) == null) {
                break;
            }
            System.out.println("❌ Invalid/duplicate ID. Try e.g., C001.");
        }

        String symptoms = getNonEmpty("Enter symptoms: ");
        String diagnosis = getNonEmpty("Enter diagnosis: ");
        String notes = getNonEmpty("Enter notes: ");

        LocalDate nextAppointment = readDateOptional("Enter next appointment (yyyy-mm-dd, empty for none): ");
        boolean isFollowUp = askYesNo("Is follow up? (y/n): ");
        double fee = readDouble("Enter consultation fee: ");
        String status = getNonEmpty("Enter status: ");

        Consultation c = new Consultation(
                consultationId,
                desiredStart,
                patient,
                doctor,
                symptoms,
                diagnosis,
                new LinkedList<>(),
                notes,
                nextAppointment,
                isFollowUp,
                durationMinutes,
                fee,
                status);

        doctor.getConsultations().add(c);
        consultationList.add(c);
        System.out.println("✅ Consultation added.");
    }

    public void displayAllConsultations() {
        if (consultationList.size() == 0) {
            System.out.println("No consultations found.");
        } else {
            System.out.println("\n--- All Consultations ---");
            for (int i = 0; i < consultationList.size(); i++) {
                System.out.println((i + 1) + " -> " + consultationList.get(i));
            }
        }
    }

    public void updateConsultation() {
        if (consultationList.size() == 0) {
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
        c.setDiagnosis(getNonEmpty("Enter new diagnosis: "));
        c.setNotes(getNonEmpty("Enter new notes: "));
        c.setDurationMinutes(readInt("Enter new duration (minutes): "));
        c.setConsultationFee(readDouble("Enter new consultation fee: "));
        c.setStatus(getNonEmpty("Enter new status: "));
        System.out.println("✅ Consultation updated.");
    }

    public void deleteConsultation() {
        if (consultationList.size() == 0) {
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

    private Consultation findById(String id) {
        for (int i = 0; i < consultationList.size(); i++) {
            Consultation c = consultationList.get(i);
            if (c.getConsultationId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
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
                if (!prompt.isEmpty()) {
                    System.out.print(prompt);
                }
                index = Integer.parseInt(sc.nextLine().trim());
                if ((allowZero && index == 0) || (index >= 1 && index <= maxSize)) {
                    return index;
                }
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
            if (!s.isEmpty()) {
                return s;
            }
            System.out.println("❌ Input cannot be empty!");
        }
    }

    private boolean askYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim().toLowerCase();
            if (s.matches("y|yes|1|true|t")) {
                return true;
            }
            if (s.matches("n|no|0|false|f")) {
                return false;
            }
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

    private java.time.LocalDate readDateOptional(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (s.isEmpty()) {
                return null;
            }
            try {
                return java.time.LocalDate.parse(s);
            } catch (Exception e) {
                System.out.println("❌ Invalid date (yyyy-mm-dd).");
            }
        }
    }

    public void addTreatmentToConsultation() {
        if (consultationList.isEmpty()) {
            System.out.println("No consultations available.");
            return;
        }

        displayAllConsultations();
        System.out.print("Enter consultation index to add treatment: ");
        int index = readInt("") - 1;

        if (index < 0 || index >= consultationList.size()) {
            System.out.println("❌ Invalid index.");
            return;
        }

        Consultation selected = consultationList.get(index);
        Patient patient = selected.getPatient();
        Doctor doctor = selected.getDoctor();

        MedicalTreatmentUI treatmentUI = new MedicalTreatmentUI();
        MedicalTreatment treatment = treatmentUI.createTreatment(patient, doctor);

        selected.getTreatments().add(treatment);
        System.out.println("✅ Treatment added to consultation " + selected.getConsultationId());
    }
    
    public static void main(String[] args) {
        new ClinicUI().run();
    }
}
