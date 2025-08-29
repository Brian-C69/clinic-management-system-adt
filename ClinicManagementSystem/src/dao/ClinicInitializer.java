package dao;

import adt.*;
import entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ClinicInitializer {

    public ListInterface<Doctor> initializeDoctors() {
        ListInterface<Doctor> dList = new LinkedList<>();
        for (int i = 1; i <= 50; i++) {
            ListInterface<String> schedule = new LinkedList<>();
            schedule.add("Mon-Fri 9-5");
            ListInterface<Consultation> consultations = new LinkedList<>();
            Doctor doc = new Doctor(
                    String.format("D%03d", i),
                    "Doctor " + i,
                    LocalDate.of(1980, 1, 1),
                    String.format("IC%06d", i),
                    String.format("MMC%03d", i),
                    "General",
                    "doctor" + i + "@example.com",
                    (i % 2 == 0) ? "Female" : "Male",
                    schedule,
                    true,
                    consultations,
                    "Active"
            );
            dList.add(doc);
        }
        return dList;
    }

    public ListInterface<Patient> initializePatients() {
        ListInterface<Patient> pList = new LinkedList<>();
        for (int i = 1; i <= 50; i++) {
            Patient p = new Patient(
                    String.format("P%03d", i),
                    "Patient " + i,
                    String.format("IC%06d", i),
                    LocalDate.of(1990, 1, 1),
                    (i % 2 == 0) ? "Female" : "Male",
                    "012345678" + i,
                    "None",
                    LocalDate.now(),
                    null,
                    true,
                    String.format("Q%03d", i)
            );
            pList.add(p);
        }
        return pList;
    }

    public ListInterface<MedicalTreatment> initializeTreatments(ListInterface<Patient> patients, ListInterface<Doctor> doctors) {
        ListInterface<MedicalTreatment> tList = new LinkedList<>();
        for (int i = 1; i <= 50; i++) {
            MedicalTreatment mt = new MedicalTreatment(
                    "Medicine " + i,
                    "1 pill",
                    "7 days",
                    "After meal",
                    LocalDate.now(),
                    patients.get(i - 1),
                    doctors.get(i - 1)
            );
            tList.add(mt);
        }
        return tList;
    }

    public ListInterface<Consultation> initializeConsultations(ListInterface<Patient> patients, ListInterface<Doctor> doctors, ListInterface<MedicalTreatment> treatments) {
        ListInterface<Consultation> cList = new LinkedList<>();
        for (int i = 1; i <= 50; i++) {
            ListInterface<MedicalTreatment> consTreatments = new LinkedList<>();
            consTreatments.add(treatments.get(i - 1));
            Consultation cons = new Consultation(
                    String.format("C%03d", i),
                    LocalDateTime.now(),
                    patients.get(i - 1),
                    doctors.get(i - 1),
                    "Symptoms " + i,
                    "Diagnosis " + i,
                    consTreatments,
                    "Notes " + i,
                    LocalDate.now().plusDays(30),
                    false,
                    30,
                    50.0,
                    "Scheduled"
            );
            cList.add(cons);
            doctors.get(i - 1).getConsultations().add(cons);
        }
        return cList;
    }

    public static void main(String[] args) {
        ClinicInitializer ci = new ClinicInitializer();
        ListInterface<Doctor> doctors = ci.initializeDoctors();
        ListInterface<Patient> patients = ci.initializePatients();
        ListInterface<MedicalTreatment> treatments = ci.initializeTreatments(patients, doctors);
        ListInterface<Consultation> consultations = ci.initializeConsultations(patients, doctors, treatments);

        System.out.println("Doctors Initialized: " + doctors.size());
        System.out.println("Patients Initialized: " + patients.size());
        System.out.println("Treatments Initialized: " + treatments.size());
        System.out.println("Consultations Initialized: " + consultations.size());
    }
}
