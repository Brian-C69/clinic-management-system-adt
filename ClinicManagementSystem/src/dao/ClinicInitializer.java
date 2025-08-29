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
        doctors.add(d1);

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
        doctors.add(d2);

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
        doctors.add(d3);

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
        doctors.add(d4);

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
        doctors.add(d5);

        Doctor d6 = new Doctor();
        d6.setName("Dr. Sarah Lim");
        d6.setMmcNumber("MMC1006");
        d6.setSpecialization("Neurology");
        d6.setEmail("sarah.lim@clinic.com");
        d6.setGender("F");
        d6.setDutySchedule(new LinkedList<>());
        d6.setIsAvailable(true);
        d6.setConsultations(new LinkedList<>());
        d6.setStatus("Active");
        d6.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 9, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d6.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 0),
                LocalDateTime.of(2025, 8, 30, 17, 0)));
        doctors.add(d6);

        Doctor d7 = new Doctor();
        d7.setName("Dr. Jason Tan");
        d7.setMmcNumber("MMC1007");
        d7.setSpecialization("Pediatrics");
        d7.setEmail("jason.tan@clinic.com");
        d7.setGender("M");
        d7.setDutySchedule(new LinkedList<>());
        d7.setIsAvailable(true);
        d7.setConsultations(new LinkedList<>());
        d7.setStatus("Active");
        d7.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 8, 30),
                LocalDateTime.of(2025, 8, 30, 12, 30)));
        d7.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 30),
                LocalDateTime.of(2025, 8, 30, 16, 30)));
        doctors.add(d7);

        Doctor d8 = new Doctor();
        d8.setName("Dr. Emily Wong");
        d8.setMmcNumber("MMC1008");
        d8.setSpecialization("Dermatology");
        d8.setEmail("emily.wong@clinic.com");
        d8.setGender("F");
        d8.setDutySchedule(new LinkedList<>());
        d8.setIsAvailable(true);
        d8.setConsultations(new LinkedList<>());
        d8.setStatus("Active");
        d8.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 9, 30),
                LocalDateTime.of(2025, 8, 30, 12, 30)));
        d8.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 14, 0),
                LocalDateTime.of(2025, 8, 30, 17, 0)));
        doctors.add(d8);

        Doctor d9 = new Doctor();
        d9.setName("Dr. Kevin Lee");
        d9.setMmcNumber("MMC1009");
        d9.setSpecialization("Gastroenterology");
        d9.setEmail("kevin.lee@clinic.com");
        d9.setGender("M");
        d9.setDutySchedule(new LinkedList<>());
        d9.setIsAvailable(true);
        d9.setConsultations(new LinkedList<>());
        d9.setStatus("Active");
        d9.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 8, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d9.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 0),
                LocalDateTime.of(2025, 8, 30, 17, 0)));
        doctors.add(d9);

        Doctor d10 = new Doctor();
        d10.setName("Dr. Rachel Ong");
        d10.setMmcNumber("MMC1010");
        d10.setSpecialization("Ophthalmology");
        d10.setEmail("rachel.ong@clinic.com");
        d10.setGender("F");
        d10.setDutySchedule(new LinkedList<>());
        d10.setIsAvailable(true);
        d10.setConsultations(new LinkedList<>());
        d10.setStatus("Active");
        d10.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 9, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d10.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 0),
                LocalDateTime.of(2025, 8, 30, 17, 0)));
        doctors.add(d10);

        Doctor d11 = new Doctor();
        d11.setName("Dr. Daniel Chua");
        d11.setMmcNumber("MMC1011");
        d11.setSpecialization("Orthopedics");
        d11.setEmail("daniel.chua@clinic.com");
        d11.setGender("M");
        d11.setDutySchedule(new LinkedList<>());
        d11.setIsAvailable(true);
        d11.setConsultations(new LinkedList<>());
        d11.setStatus("Active");
        d11.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 8, 30),
                LocalDateTime.of(2025, 8, 30, 12, 30)));
        d11.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 30),
                LocalDateTime.of(2025, 8, 30, 16, 30)));
        doctors.add(d11);

        Doctor d12 = new Doctor();
        d12.setName("Dr. Fiona Tan");
        d12.setMmcNumber("MMC1012");
        d12.setSpecialization("Cardiology");
        d12.setEmail("fiona.tan@clinic.com");
        d12.setGender("F");
        d12.setDutySchedule(new LinkedList<>());
        d12.setIsAvailable(true);
        d12.setConsultations(new LinkedList<>());
        d12.setStatus("Active");
        d12.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 9, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d12.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 14, 0),
                LocalDateTime.of(2025, 8, 30, 18, 0)));
        doctors.add(d12);

        Doctor d13 = new Doctor();
        d13.setName("Dr. Benjamin Ng");
        d13.setMmcNumber("MMC1013");
        d13.setSpecialization("Neurology");
        d13.setEmail("benjamin.ng@clinic.com");
        d13.setGender("M");
        d13.setDutySchedule(new LinkedList<>());
        d13.setIsAvailable(true);
        d13.setConsultations(new LinkedList<>());
        d13.setStatus("Active");
        d13.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 8, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d13.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 0),
                LocalDateTime.of(2025, 8, 30, 17, 0)));
        doctors.add(d13);

        Doctor d14 = new Doctor();
        d14.setName("Dr. Stephanie Lim");
        d14.setMmcNumber("MMC1014");
        d14.setSpecialization("Pediatrics");
        d14.setEmail("stephanie.lim@clinic.com");
        d14.setGender("F");
        d14.setDutySchedule(new LinkedList<>());
        d14.setIsAvailable(true);
        d14.setConsultations(new LinkedList<>());
        d14.setStatus("Active");
        d14.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 9, 30),
                LocalDateTime.of(2025, 8, 30, 12, 30)));
        d14.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 30),
                LocalDateTime.of(2025, 8, 30, 17, 0)));
        doctors.add(d14);

        Doctor d15 = new Doctor();
        d15.setName("Dr. Samuel Goh");
        d15.setMmcNumber("MMC1015");
        d15.setSpecialization("Dermatology");
        d15.setEmail("samuel.goh@clinic.com");
        d15.setGender("M");
        d15.setDutySchedule(new LinkedList<>());
        d15.setIsAvailable(true);
        d15.setConsultations(new LinkedList<>());
        d15.setStatus("Active");
        d15.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 8, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d15.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 0),
                LocalDateTime.of(2025, 8, 30, 17, 0)));
        doctors.add(d15);

        Doctor d16 = new Doctor();
        d16.setName("Dr. Olivia Tan");
        d16.setMmcNumber("MMC1016");
        d16.setSpecialization("Ophthalmology");
        d16.setEmail("olivia.tan@clinic.com");
        d16.setGender("F");
        d16.setDutySchedule(new LinkedList<>());
        d16.setIsAvailable(true);
        d16.setConsultations(new LinkedList<>());
        d16.setStatus("Active");
        d16.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 9, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d16.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 14, 0),
                LocalDateTime.of(2025, 8, 30, 18, 0)));
        doctors.add(d16);

        Doctor d17 = new Doctor();
        d17.setName("Dr. Ryan Chua");
        d17.setMmcNumber("MMC1017");
        d17.setSpecialization("Orthopedics");
        d17.setEmail("ryan.chua@clinic.com");
        d17.setGender("M");
        d17.setDutySchedule(new LinkedList<>());
        d17.setIsAvailable(true);
        d17.setConsultations(new LinkedList<>());
        d17.setStatus("Active");
        d17.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 8, 30),
                LocalDateTime.of(2025, 8, 30, 12, 30)));
        d17.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 30),
                LocalDateTime.of(2025, 8, 30, 16, 30)));
        doctors.add(d17);

        Doctor d18 = new Doctor();
        d18.setName("Dr. Grace Ng");
        d18.setMmcNumber("MMC1018");
        d18.setSpecialization("Cardiology");
        d18.setEmail("grace.ng@clinic.com");
        d18.setGender("F");
        d18.setDutySchedule(new LinkedList<>());
        d18.setIsAvailable(true);
        d18.setConsultations(new LinkedList<>());
        d18.setStatus("Active");
        d18.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 9, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d18.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 0),
                LocalDateTime.of(2025, 8, 30, 17, 0)));
        doctors.add(d18);

        Doctor d19 = new Doctor();
        d19.setName("Dr. Adrian Lee");
        d19.setMmcNumber("MMC1019");
        d19.setSpecialization("Neurology");
        d19.setEmail("adrian.lee@clinic.com");
        d19.setGender("M");
        d19.setDutySchedule(new LinkedList<>());
        d19.setIsAvailable(true);
        d19.setConsultations(new LinkedList<>());
        d19.setStatus("Active");
        d19.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 8, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d19.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 13, 0),
                LocalDateTime.of(2025, 8, 30, 17, 0)));
        doctors.add(d19);

        Doctor d20 = new Doctor();
        d20.setName("Dr. Vanessa Koh");
        d20.setMmcNumber("MMC1020");
        d20.setSpecialization("Pediatrics");
        d20.setEmail("vanessa.koh@clinic.com");
        d20.setGender("F");
        d20.setDutySchedule(new LinkedList<>());
        d20.setIsAvailable(true);
        d20.setConsultations(new LinkedList<>());
        d20.setStatus("Active");
        d20.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 9, 0),
                LocalDateTime.of(2025, 8, 30, 12, 0)));
        d20.getDutySchedule().add(new Doctor.DutySlot(LocalDateTime.of(2025, 8, 30, 14, 0),
                LocalDateTime.of(2025, 8, 30, 17, 0)));
        doctors.add(d20);

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

        Consultation c1 = new Consultation("C001", LocalDateTime.of(2023, 8, 1, 10, 0), p1, d1, "Chest pain", "Mild angina", new LinkedList<>(), "Needs further tests", LocalDate.of(2023, 8, 15), true, 30, 100.0, "Completed");
        Consultation c2 = new Consultation("C002", LocalDateTime.of(2023, 8, 2, 14, 0), p2, d2, "Skin rash", "Eczema", new LinkedList<>(), "Prescribed cream", null, false, 20, 60.0, "Completed");
        Consultation c3 = new Consultation("C003", LocalDateTime.of(2023, 8, 3, 9, 30), p3, d3, "Back pain", "Muscle strain", new LinkedList<>(), "Physiotherapy recommended", null, false, 25, 80.0, "Completed");
        Consultation c4 = new Consultation("C004", LocalDateTime.of(2023, 8, 4, 11, 0), p4, d4, "Fever", "Viral infection", new LinkedList<>(), "Rest and hydration", null, false, 15, 50.0, "Completed");
        Consultation c5 = new Consultation("C005", LocalDateTime.of(2023, 8, 5, 13, 0), p5, d5, "Knee pain", "Arthritis", new LinkedList<>(), "Prescribed anti-inflammatory", null, false, 30, 120.0, "Completed");
        Consultation c6 = new Consultation("C006", LocalDateTime.of(2023, 8, 6, 14, 30), p6, d6, "Headache", "Migraine", new LinkedList<>(), "Prescribed painkillers", null, false, 20, 70.0, "Completed");
        Consultation c7 = new Consultation("C007", LocalDateTime.of(2023, 8, 7, 10, 0), p7, d7, "Cough", "Bronchitis", new LinkedList<>(), "Antibiotics prescribed", null, false, 25, 90.0, "Completed");
        Consultation c8 = new Consultation("C008", LocalDateTime.of(2023, 8, 8, 15, 0), p8, d8, "Rash", "Allergy", new LinkedList<>(), "Antihistamines prescribed", null, false, 20, 60.0, "Completed");
        Consultation c9 = new Consultation("C009", LocalDateTime.of(2023, 8, 9, 9, 0), p9, d9, "Eye redness", "Conjunctivitis", new LinkedList<>(), "Eye drops prescribed", null, false, 15, 50.0, "Completed");
        Consultation c10 = new Consultation("C010", LocalDateTime.of(2023, 8, 10, 11, 30), p10, d10, "Ear pain", "Otitis media", new LinkedList<>(), "Antibiotic drops prescribed", null, false, 20, 75.0, "Completed");
        Consultation c11 = new Consultation("C011", LocalDateTime.of(2023, 8, 11, 10, 15), p11, d11, "Ankle sprain", "Ligament injury", new LinkedList<>(), "Rest and bandage", null, false, 30, 100.0, "Completed");
        Consultation c12 = new Consultation("C012", LocalDateTime.of(2023, 8, 12, 14, 45), p12, d12, "Palpitations", "Arrhythmia", new LinkedList<>(), "EKG and follow-up", null, false, 25, 110.0, "Completed");
        Consultation c13 = new Consultation("C013", LocalDateTime.of(2023, 8, 13, 9, 30), p13, d13, "Dizziness", "Vertigo", new LinkedList<>(), "Balance exercises", null, false, 20, 80.0, "Completed");
        Consultation c14 = new Consultation("C014", LocalDateTime.of(2023, 8, 14, 13, 0), p14, d14, "Fever", "Flu", new LinkedList<>(), "Rest and hydration", null, false, 15, 50.0, "Completed");
        Consultation c15 = new Consultation("C015", LocalDateTime.of(2023, 8, 15, 10, 0), p15, d15, "Acne", "Mild acne", new LinkedList<>(), "Topical cream prescribed", null, false, 20, 60.0, "Completed");
        Consultation c16 = new Consultation("C016", LocalDateTime.of(2023, 8, 16, 11, 30), p16, d16, "Blurred vision", "Myopia", new LinkedList<>(), "Prescription updated", null, false, 15, 50.0, "Completed");
        Consultation c17 = new Consultation("C017", LocalDateTime.of(2023, 8, 17, 14, 0), p17, d17, "Shoulder pain", "Tendonitis", new LinkedList<>(), "Physiotherapy recommended", null, false, 25, 90.0, "Completed");
        Consultation c18 = new Consultation("C018", LocalDateTime.of(2023, 8, 18, 9, 0), p18, d18, "Chest discomfort", "Mild angina", new LinkedList<>(), "Further tests needed", null, false, 30, 100.0, "Completed");
        Consultation c19 = new Consultation("C019", LocalDateTime.of(2023, 8, 19, 13, 15), p19, d19, "Back stiffness", "Muscle strain", new LinkedList<>(), "Exercise recommended", null, false, 20, 70.0, "Completed");
        Consultation c20 = new Consultation("C020", LocalDateTime.of(2023, 8, 20, 10, 0), p20, d20, "Allergy", "Dust allergy", new LinkedList<>(), "Antihistamines prescribed", null, false, 20, 60.0, "Completed");

        consultations.add(c1);
        consultations.add(c2);
        consultations.add(c3);
        consultations.add(c4);
        consultations.add(c5);
        consultations.add(c6);
        consultations.add(c7);
        consultations.add(c8);
        consultations.add(c9);
        consultations.add(c10);
        consultations.add(c11);
        consultations.add(c12);
        consultations.add(c13);
        consultations.add(c14);
        consultations.add(c15);
        consultations.add(c16);
        consultations.add(c17);
        consultations.add(c18);
        consultations.add(c19);
        consultations.add(c20);

        data.setConsultations(consultations);

// Link consultations to doctors
        d1.setConsultations(new LinkedList<>());
        d1.getConsultations().add(c1);
        d2.setConsultations(new LinkedList<>());
        d2.getConsultations().add(c2);
        d3.setConsultations(new LinkedList<>());
        d3.getConsultations().add(c3);
        d4.setConsultations(new LinkedList<>());
        d4.getConsultations().add(c4);
        d5.setConsultations(new LinkedList<>());
        d5.getConsultations().add(c5);
        d6.setConsultations(new LinkedList<>());
        d6.getConsultations().add(c6);
        d7.setConsultations(new LinkedList<>());
        d7.getConsultations().add(c7);
        d8.setConsultations(new LinkedList<>());
        d8.getConsultations().add(c8);
        d9.setConsultations(new LinkedList<>());
        d9.getConsultations().add(c9);
        d10.setConsultations(new LinkedList<>());
        d10.getConsultations().add(c10);
        d11.setConsultations(new LinkedList<>());
        d11.getConsultations().add(c11);
        d12.setConsultations(new LinkedList<>());
        d12.getConsultations().add(c12);
        d13.setConsultations(new LinkedList<>());
        d13.getConsultations().add(c13);
        d14.setConsultations(new LinkedList<>());
        d14.getConsultations().add(c14);
        d15.setConsultations(new LinkedList<>());
        d15.getConsultations().add(c15);
        d16.setConsultations(new LinkedList<>());
        d16.getConsultations().add(c16);
        d17.setConsultations(new LinkedList<>());
        d17.getConsultations().add(c17);
        d18.setConsultations(new LinkedList<>());
        d18.getConsultations().add(c18);
        d19.setConsultations(new LinkedList<>());
        d19.getConsultations().add(c19);
        d20.setConsultations(new LinkedList<>());
        d20.getConsultations().add(c20);

// ----- Treatments -----
        ListInterface<MedicalTreatment> treatments = new LinkedList<>();

        MedicalTreatment t1 = new MedicalTreatment("Paracetamol", "500mg", "5 days", "Take after meals", LocalDate.of(2023, 8, 1), p1, d1);
        MedicalTreatment t2 = new MedicalTreatment("Antihistamine", "10mg", "7 days", "Before sleep", LocalDate.of(2023, 8, 2), p2, d2);
        MedicalTreatment t3 = new MedicalTreatment("Ibuprofen", "400mg", "3 days", "After meals", LocalDate.of(2023, 8, 3), p3, d3);
        MedicalTreatment t4 = new MedicalTreatment("Paracetamol", "500mg", "3 days", "Take when needed", LocalDate.of(2023, 8, 4), p4, d4);
        MedicalTreatment t5 = new MedicalTreatment("Diclofenac", "50mg", "7 days", "After meals", LocalDate.of(2023, 8, 5), p5, d5);
        MedicalTreatment t6 = new MedicalTreatment("Sumatriptan", "25mg", "As needed", "During headache", LocalDate.of(2023, 8, 6), p6, d6);
        MedicalTreatment t7 = new MedicalTreatment("Amoxicillin", "500mg", "7 days", "Before meals", LocalDate.of(2023, 8, 7), p7, d7);
        MedicalTreatment t8 = new MedicalTreatment("Cetirizine", "10mg", "5 days", "Before sleep", LocalDate.of(2023, 8, 8), p8, d8);
        MedicalTreatment t9 = new MedicalTreatment("Eye drops", "5ml", "7 days", "Twice daily", LocalDate.of(2023, 8, 9), p9, d9);
        MedicalTreatment t10 = new MedicalTreatment("Antibiotic drops", "5ml", "5 days", "Twice daily", LocalDate.of(2023, 8, 10), p10, d10);
        MedicalTreatment t11 = new MedicalTreatment("Bandage", "1 unit", "3 days", "Rest", LocalDate.of(2023, 8, 11), p11, d11);
        MedicalTreatment t12 = new MedicalTreatment("Beta blockers", "50mg", "10 days", "Morning", LocalDate.of(2023, 8, 12), p12, d12);
        MedicalTreatment t13 = new MedicalTreatment("Meclizine", "25mg", "5 days", "After meals", LocalDate.of(2023, 8, 13), p13, d13);
        MedicalTreatment t14 = new MedicalTreatment("Paracetamol", "500mg", "3 days", "Take when needed", LocalDate.of(2023, 8, 14), p14, d14);
        MedicalTreatment t15 = new MedicalTreatment("Benzoyl peroxide cream", "5%", "7 days", "Apply twice daily", LocalDate.of(2023, 8, 15), p15, d15);
        MedicalTreatment t16 = new MedicalTreatment("Glasses", "Prescription", "N/A", "Wear daily", LocalDate.of(2023, 8, 16), p16, d16);
        MedicalTreatment t17 = new MedicalTreatment("Physiotherapy", "1 session", "3 days", "As instructed", LocalDate.of(2023, 8, 17), p17, d17);
        MedicalTreatment t18 = new MedicalTreatment("Nitroglycerin", "0.3mg", "As needed", "Sublingual", LocalDate.of(2023, 8, 18), p18, d18);
        MedicalTreatment t19 = new MedicalTreatment("Exercise", "N/A", "14 days", "Daily", LocalDate.of(2023, 8, 19), p19, d19);
        MedicalTreatment t20 = new MedicalTreatment("Antihistamine", "10mg", "7 days", "Before sleep", LocalDate.of(2023, 8, 20), p20, d20);

        treatments.add(t1);
        treatments.add(t2);
        treatments.add(t3);
        treatments.add(t4);
        treatments.add(t5);
        treatments.add(t6);
        treatments.add(t7);
        treatments.add(t8);
        treatments.add(t9);
        treatments.add(t10);
        treatments.add(t11);
        treatments.add(t12);
        treatments.add(t13);
        treatments.add(t14);
        treatments.add(t15);
        treatments.add(t16);
        treatments.add(t17);
        treatments.add(t18);
        treatments.add(t19);
        treatments.add(t20);

        data.setTreatments(treatments);

// Attach treatments to consultations
        c1.getTreatments().add(t1);
        c2.getTreatments().add(t2);
        c3.getTreatments().add(t3);
        c4.getTreatments().add(t4);
        c5.getTreatments().add(t5);
        c6.getTreatments().add(t6);
        c7.getTreatments().add(t7);
        c8.getTreatments().add(t8);
        c9.getTreatments().add(t9);
        c10.getTreatments().add(t10);
        c11.getTreatments().add(t11);
        c12.getTreatments().add(t12);
        c13.getTreatments().add(t13);
        c14.getTreatments().add(t14);
        c15.getTreatments().add(t15);
        c16.getTreatments().add(t16);
        c17.getTreatments().add(t17);
        c18.getTreatments().add(t18);
        c19.getTreatments().add(t19);
        c20.getTreatments().add(t20);

        return data;
    }
}
