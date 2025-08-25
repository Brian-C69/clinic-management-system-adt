package dao;

import adt.*;
import entity.*;

import java.time.LocalDate;

public class ClinicInitializer {

    public ListInterface<Doctor> initializeDoctors() {
        ListInterface<Doctor> dList = new LinkedList<>();
        
        return dList;
    }

    public static void main(String[] args) {
        ClinicInitializer ci = new ClinicInitializer();
        System.out.println("Doctors Initialized: " + ci.initializeDoctors().size());
    }
}
