package dao;

import control.*;
import entity.*;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        // Create MaintainPatient object
        MaintainPatient mp = new MaintainPatient();
        MaintainDoctor md = new MaintainDoctor();

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

        Patient p2NewInfo = new Patient();
        p2.setName("Bernard Choong");

        //Doctor Sample
        Doctor d1 = new Doctor();
        d1.setDoctorId("1");
        d1.setName("Doctor Joe Mama");
        d1.setGender("Male");
        d1.setAddress("ABC street 123");

        Doctor d2 = new Doctor();
        d2.setDoctorId("2");
        d2.setName("Doctor Very Geng");
        d2.setGender("Female");
        d2.setAddress("DFG 213 Street");
        
        Doctor d3 = new Doctor();
        d3.setDoctorId("3");
        
        Doctor d3NewData = new Doctor();
        d3.setDoctorId("4");
        d3.setName("Doctor Professional");

        // Add to MaintainPatient
        mp.addPatient(p1);
        mp.addPatient(p2);

        mp.updateExistingPatient(0, p2NewInfo);

        // Add to MaintainDoctor
        md.addDoctor(d1);
        md.addDoctor(d2);
        md.addDoctor(d3);
        
        md.updateExistingDoctor(2, d3NewData);

        // Display all
        System.out.println("---- Patient List ----");
        mp.displayAllPatients();
        System.out.println("---- Doctor List ----");
        md.displayAllDoctors();
    }
}
