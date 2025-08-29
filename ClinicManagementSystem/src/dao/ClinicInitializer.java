// File: dao/ClinicInitializer.java
package dao;

import adt.*;
import entity.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClinicInitializer {

    public ClinicData initializeClinic() {
        ClinicData data = new ClinicData();

        // ----- Doctors -----
        ListInterface<Doctor> doctors = new LinkedList<>();
        Doctor d1 = new Doctor("D001", "Dr. Alice", "MMC1001", "Cardiology",
                "alice@clinic.com", "F", new LinkedList<>(), true, new LinkedList<>(), "Active");
        Doctor d2 = new Doctor("D002", "Dr. Bob", "MMC1002", "Dermatology",
                "bob@clinic.com", "M", new LinkedList<>(), true, new LinkedList<>(), "Active");
        doctors.add(d1);
        doctors.add(d2);
        data.setDoctors(doctors);

        // ----- Patients -----
        ListInterface<Patient> patients = new LinkedList<>();
        Patient p1 = new Patient("P001", "John Tan", "990101-14-1234",
                LocalDate.of(1999, 1, 1), "M", "0123456789", "None",
                LocalDate.of(2023, 1, 5), LocalDate.of(2023, 6, 10), true, "PQ001");
        Patient p2 = new Patient("P002", "Mary Lee", "000202-08-5678",
                LocalDate.of(2000, 2, 2), "F", "0198765432", "Peanut",
                LocalDate.of(2023, 2, 15), null, true, "PQ002");
        patients.add(p1);
        patients.add(p2);
        data.setPatients(patients);

        // ----- Consultations -----
        ListInterface<Consultation> consultations = new LinkedList<>();
        Consultation c1 = new Consultation("C001", LocalDateTime.of(2023, 8, 1, 10, 0),
                p1, d1, "Chest pain", "Mild angina",
                new LinkedList<>(), "Needs further tests",
                LocalDate.of(2023, 8, 15), true, 30, 100.0, "Completed");
        Consultation c2 = new Consultation("C002", LocalDateTime.of(2023, 8, 2, 14, 0),
                p2, d2, "Skin rash", "Eczema",
                new LinkedList<>(), "Prescribed cream",
                null, false, 20, 60.0, "Completed");
        consultations.add(c1);
        consultations.add(c2);
        data.setConsultations(consultations);

        // Link consultations to doctors
        d1.setConsultations(new LinkedList<>());
        d1.getConsultations().add(c1);
        d2.setConsultations(new LinkedList<>());
        d2.getConsultations().add(c2);

        // ----- Treatments -----
        ListInterface<MedicalTreatment> treatments = new LinkedList<>();
        MedicalTreatment t1 = new MedicalTreatment("Paracetamol", "500mg", "5 days",
                "Take after meals", LocalDate.of(2023, 8, 1), p1, d1);
        MedicalTreatment t2 = new MedicalTreatment("Antihistamine", "10mg", "7 days",
                "Before sleep", LocalDate.of(2023, 8, 2), p2, d2);
        treatments.add(t1);
        treatments.add(t2);
        data.setTreatments(treatments);

        // Attach treatments to consultations
        c1.getTreatments().add(t1);
        c2.getTreatments().add(t2);

        return data;
    }

    // Simple test harness (can remove later lol)
    public static void main(String[] args) {
        ClinicInitializer init = new ClinicInitializer();
        ClinicData data = init.initializeClinic();

        System.out.println("=== Doctors ===");
        for (Doctor d : data.getDoctors()) {
            System.out.println(d);
        }

        System.out.println("\n=== Patients ===");
        for (Patient p : data.getPatients()) {
            System.out.println(p);
        }

        System.out.println("\n=== Consultations ===");
        for (Consultation c : data.getConsultations()) {
            System.out.println(c);
        }

        System.out.println("\n=== Treatments ===");
        for (MedicalTreatment t : data.getTreatments()) {
            System.out.println(t);
        }
    }
}
