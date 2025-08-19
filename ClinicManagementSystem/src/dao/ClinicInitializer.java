package dao;

import adt.*;
import entity.*;

import java.time.LocalDate;

public class ClinicInitializer {

    public ListInterface<Doctor> initializeDoctors() {
        ListInterface<Doctor> dList = new LinkedList<>();
        dList.add(new Doctor("D001", "Dr. John", LocalDate.of(1980, 5, 15),
                "800515-10-5678", "MMC12345", "General", "Full-time",
                "0129998888", "drjohn@mail.com", "PJ", "M",
                new LinkedList<>(), true, new LinkedList<>(), 80.0,
                LocalDate.of(2015, 3, 1), 500, "Active"));
        dList.add(new Doctor("D002", "Dr. Sarah", LocalDate.of(1985, 8, 20),
                "850820-05-8888", "MMC54321", "Pediatrics", "Part-time",
                "0178889999", "drsarah@mail.com", "KL", "F",
                new LinkedList<>(), true, new LinkedList<>(), 100.0,
                LocalDate.of(2018, 6, 1), 300, "Active"));
        return dList;
    }

    public static void main(String[] args) {
        ClinicInitializer ci = new ClinicInitializer();
        System.out.println("Doctors Initialized: " + ci.initializeDoctors().size());
        System.out.println("Patients Initialized: " + ci.initializePatients().size());
    }
}
