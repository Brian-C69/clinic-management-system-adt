package dao;

import control.*;
import entity.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Create MaintainPatient object
        MaintainPatient mp = new MaintainPatient();

        // Sample Patients
        Patient p1 = new Patient();
        p1.setName("Alice Tan");
        p1.setIcNumber("990101-14-5678");
        p1.setDateOfBirth(LocalDate.of(1999, 1, 1));
        p1.setSex("Female");
        p1.setIsActive(true);

        Patient p2 = new Patient();
        p2.setName("Bob Lim");
        p2.setIcNumber("880202-07-1234");
        p2.setDateOfBirth(LocalDate.of(1988, 2, 2));
        p2.setSex("Male");
        p2.setIsActive(true);

        // Add to MaintainPatient
        mp.addPatient(p1);
        mp.addPatient(p2);

        // Display all
        System.out.println("---- Patient List ----");
        mp.displayAllPatients();
    }
}
