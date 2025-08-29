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

        Doctor d1 = new Doctor();
        d1.setName("Dr. Alice Tan");
        d1.setMmcNumber("MMC1001");
        d1.setSpecialization("Cardiology");
        d1.setEmail("alice.tan@clinic.com");
        d1.setGender("F");
        d1.setDutySchedule(new LinkedList<>());
        d1.setIsAvailable(true);
        d1.setConsultations(new LinkedList<>());
        d1.setStatus("Active");

        d1.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 9, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d1.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 0),
                LocalDateTime.of(2025, 8, 30, 17, 0)));

        Doctor d2 = new Doctor();
        d2.setName("Dr. Mah Ling");
        d2.setMmcNumber("MMC1002");
        d2.setSpecialization("Gastroenterology");
        d2.setEmail("mah.ling@clinic.com");
        d2.setGender("F");
        d2.setDutySchedule(new LinkedList<>());
        d2.setIsAvailable(true);
        d2.setConsultations(new LinkedList<>());
        d2.setStatus("Active");

        d2.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 8, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d2.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 14, 0),
                LocalDateTime.of(2025, 8, 30, 18, 0)));

        Doctor d3 = new Doctor();
        d3.setName("Dr. John Lim");
        d3.setMmcNumber("MMC1003");
        d3.setSpecialization("Dermatology");
        d3.setEmail("john.lim@clinic.com");
        d3.setGender("M");
        d3.setDutySchedule(new LinkedList<>());
        d3.setIsAvailable(true);
        d3.setConsultations(new LinkedList<>());
        d3.setStatus("Active");

        d3.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 10, 0),
                LocalDateTime.of(2025, 8, 30, 13, 0)));
        d3.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 15, 0),
                LocalDateTime.of(2025, 8, 30, 19, 0)));

        Doctor d4 = new Doctor();
        d4.setName("Dr. Sarah Ng");
        d4.setMmcNumber("MMC1004");
        d4.setSpecialization("Pediatrics");
        d4.setEmail("sarah.ng@clinic.com");
        d4.setGender("F");
        d4.setDutySchedule(new LinkedList<>());
        d4.setIsAvailable(true);
        d4.setConsultations(new LinkedList<>());
        d4.setStatus("Active");

        d4.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 9, 30),
                LocalDateTime.of(2025, 8, 30, 12, 30)));
        d4.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 30),
                LocalDateTime.of(2025, 8, 30, 17, 30)));

        Doctor d5 = new Doctor();
        d5.setName("Dr. Michael Chew");
        d5.setMmcNumber("MMC1005");
        d5.setSpecialization("Orthopedics");
        d5.setEmail("michael.chew@clinic.com");
        d5.setGender("M");
        d5.setDutySchedule(new LinkedList<>());
        d5.setIsAvailable(true);
        d5.setConsultations(new LinkedList<>());
        d5.setStatus("Active");

        d5.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 8, 30),
                LocalDateTime.of(2025, 8, 30, 11, 30)));
        d5.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 12, 30),
                LocalDateTime.of(2025, 8, 30, 16, 30)));

        doctors.add(d1);
        doctors.add(d2);
        doctors.add(d3);
        doctors.add(d4);
        doctors.add(d5);

        data.setDoctors(doctors);

        // ----- Patients -----
        ListInterface<Patient> patients = new LinkedList<>();

        Patient p1 = (new Patient("P001", "John Tan", "990101-14-1234",
                LocalDate.of(1999, 1, 1), "M", "0123456789", "None",
                LocalDate.of(2023, 1, 5), LocalDate.of(2023, 6, 10), true, "PQ001"));

        Patient p2 = (new Patient("P002", "Mary Lee", "000202-08-5678",
                LocalDate.of(2000, 2, 2), "F", "0198765432", "Peanut",
                LocalDate.of(2023, 2, 15), null, true, "PQ002"));

        Patient p3 = (new Patient("P003", "David Lim", "880303-10-1122",
                LocalDate.of(1988, 3, 3), "M", "0123987654", "None",
                LocalDate.of(2023, 3, 10), LocalDate.of(2023, 7, 5), true, "PQ003"));

        Patient p4 = (new Patient("P004", "Siti Rahman", "950404-07-3344",
                LocalDate.of(1995, 4, 4), "F", "0132456789", "Dust",
                LocalDate.of(2023, 4, 12), LocalDate.of(2023, 7, 20), true, "PQ004"));

        Patient p5 = (new Patient("P005", "Alex Wong", "970505-08-5566",
                LocalDate.of(1997, 5, 5), "M", "0145678901", "None",
                LocalDate.of(2023, 5, 1), null, true, "PQ005"));

        Patient p6 = (new Patient("P006", "Nurul Huda", "010606-12-7788",
                LocalDate.of(2001, 6, 6), "F", "0162345678", "Gluten",
                LocalDate.of(2023, 6, 18), null, true, "PQ006"));

        Patient p7 = (new Patient("P007", "Michael Tan", "920707-14-9900",
                LocalDate.of(1992, 7, 7), "M", "0173456789", "None",
                LocalDate.of(2023, 7, 5), LocalDate.of(2023, 8, 1), true, "PQ007"));

        Patient p8 = (new Patient("P008", "Aishah Binti Ali", "030808-08-2233",
                LocalDate.of(2003, 8, 8), "F", "0184567890", "Peanut",
                LocalDate.of(2023, 8, 10), null, true, "PQ008"));

        Patient p9 = (new Patient("P009", "Kevin Lee", "990909-10-4455",
                LocalDate.of(1999, 9, 9), "M", "0195678901", "None",
                LocalDate.of(2023, 9, 1), LocalDate.of(2023, 10, 5), true, "PQ009"));

        Patient p10 = (new Patient("P010", "Farah Zainal", "020101-12-6677",
                LocalDate.of(2002, 1, 1), "F", "0126789012", "Seafood",
                LocalDate.of(2023, 1, 20), null, true, "PQ010"));

        Patient p11 = (new Patient("P011", "Daniel Ong", "950202-09-8899",
                LocalDate.of(1995, 2, 2), "M", "0137890123", "None",
                LocalDate.of(2023, 2, 15), LocalDate.of(2023, 6, 25), true, "PQ011"));

        Patient p12 = (new Patient("P012", "Lina Teoh", "000303-11-0011",
                LocalDate.of(2000, 3, 3), "F", "0148901234", "Dust",
                LocalDate.of(2023, 3, 10), null, true, "PQ012"));

        Patient p13 = (new Patient("P013", "Aaron Lim", "980404-07-2233",
                LocalDate.of(1998, 4, 4), "M", "0169012345", "None",
                LocalDate.of(2023, 4, 12), LocalDate.of(2023, 7, 18), true, "PQ013"));

        Patient p14 = (new Patient("P014", "Sabrina Tan", "010505-05-4455",
                LocalDate.of(2001, 5, 5), "F", "0170123456", "Gluten",
                LocalDate.of(2023, 5, 1), null, true, "PQ014"));

        Patient p15 = (new Patient("P015", "Ethan Goh", "920606-06-6677",
                LocalDate.of(1992, 6, 6), "M", "0181234567", "None",
                LocalDate.of(2023, 6, 18), LocalDate.of(2023, 8, 5), true, "PQ015"));

        Patient p16 = (new Patient("P016", "Hafizah Ahmad", "030707-07-8899",
                LocalDate.of(2003, 7, 7), "F", "0192345678", "Peanut",
                LocalDate.of(2023, 7, 5), null, true, "PQ016"));

        Patient p17 = (new Patient("P017", "Joshua Wong", "990808-08-0011",
                LocalDate.of(1999, 8, 8), "M", "0123456789", "None",
                LocalDate.of(2023, 8, 10), LocalDate.of(2023, 9, 12), true, "PQ017"));

        Patient p18 = (new Patient("P018", "Nurul Izzah", "020909-09-2233",
                LocalDate.of(2002, 9, 9), "F", "0134567890", "Seafood",
                LocalDate.of(2023, 9, 1), null, true, "PQ018"));

        Patient p19 = (new Patient("P019", "Calvin Tan", "970101-01-4455",
                LocalDate.of(1997, 1, 1), "M", "0145678901", "None",
                LocalDate.of(2023, 1, 20), LocalDate.of(2023, 4, 10), true, "PQ019"));

        Patient p20 = (new Patient("P020", "Amina Syed", "000202-02-6677",
                LocalDate.of(2000, 2, 2), "F", "0166789012", "Dust",
                LocalDate.of(2023, 2, 15), null, true, "PQ020"));

        patients.add(p1);
        patients.add(p2);
        patients.add(p3);
        patients.add(p4);
        patients.add(p5);
        patients.add(p6);
        patients.add(p7);
        patients.add(p8);
        patients.add(p9);
        patients.add(p10);
        patients.add(p11);
        patients.add(p12);
        patients.add(p13);
        patients.add(p14);
        patients.add(p15);
        patients.add(p16);
        patients.add(p17);
        patients.add(p18);
        patients.add(p19);
        patients.add(p20);

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
