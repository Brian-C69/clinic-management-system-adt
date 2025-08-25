package control;

import adt.ListInterface;
import adt.LinkedList;
import entity.Patient;
import boundary.PatientUI;
import adt.Node;

public class MaintainPatient {

    private ListInterface<Patient> patientList = new LinkedList<>();

    public static void main(String[] args) {
        PatientUI ui = new PatientUI();
        MaintainPatient mp = new MaintainPatient();

        boolean running = true;
        while (running) {
            int choice = ui.getMenuChoice();
            switch (choice) {
                case 1 ->
                    mp.displayAllPatients();
                case 2 -> {
                    Patient p = ui.inputPatientDetails();
                    mp.addPatient(p);
                }
                case 3 -> {
                    mp.displayAllPatients();
                    int idx = ui.inputPatientIndex();
                    Patient update = ui.inputPatientDetails();
                    mp.updateExistingPatient(idx, update);
                }
                case 4 -> {
                    mp.displayAllPatients();
                    int delIdx = ui.inputPatientIndex();
                    mp.deletePatient(delIdx);
                }
                case 5 -> {
                    String name = ui.inputPatientName();
                    Patient found = mp.searchPatientByName(name);
                    ui.displayPatient(found);
                }
                case 0 ->
                    running = false;
                default ->
                    System.out.println("Invalid choice.");
            }
        }
        System.out.println("Exiting Patient Management.");
    }

    public void addPatient(Patient p) {
        patientList.add(p);
    }

    public boolean replacePatient(int index, Patient newPatient) {
        return patientList.replace(index, newPatient);
    }

    public boolean updateExistingPatient(int index, Patient patientNewData) {
        if (index >= 0 && index < patientList.size()) {
            Patient exisitingPatient = patientList.get(index);

            if (patientNewData.getName() != null) {
                exisitingPatient.setName(patientNewData.getName());
            }
            if (patientNewData.getIcNumber() != null) {
                exisitingPatient.setIcNumber(patientNewData.getIcNumber());
            }
            if (patientNewData.getDateOfBirth() != null) {
                exisitingPatient.setDateOfBirth(patientNewData.getDateOfBirth());
            }
            if (patientNewData.getSex() != null) {
                exisitingPatient.setSex(patientNewData.getSex());
            }
            if (patientNewData.getContactNumber() != null) {
                exisitingPatient.setContactNumber(patientNewData.getContactNumber());
            }
            if (patientNewData.getAllergyHistory() != null) {
                exisitingPatient.setAllergyHistory(patientNewData.getAllergyHistory());
            }
            if (patientNewData.getDateOfRegistration() != null) {
                exisitingPatient.setDateOfRegistration(patientNewData.getDateOfRegistration());
            }
            if (patientNewData.getLastVisitDate() != null) {
                exisitingPatient.setLastVisitDate(patientNewData.getLastVisitDate());
            }

            exisitingPatient.setIsActive(patientNewData.isIsActive());
            return true;
        }
        return false;
    }

    public Patient deletePatient(int index) {
        return patientList.remove(index);
    }

    public void displayAllPatients() {
        for (int i = 0; i < patientList.size(); i++) {
            System.out.println(i + ": " + patientList.get(i));
        }
    }

    public Patient getPatient(int index) {
        return (index >= 0 && index < patientList.size()) ? patientList.get(index) : null;
    }

    public Patient searchPatientByName(String name) {
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            if (p.getName().equals(name)) {
                return p;
            }

        }
        return null;
    }
}
