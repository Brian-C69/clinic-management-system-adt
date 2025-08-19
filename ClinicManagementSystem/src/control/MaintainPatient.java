package control;

import adt.LinkedList;
import entity.Patient;
import boundary.PatientUI;

public class MaintainPatient {

    public static void main(String[] args) {
        PatientUI ui = new PatientUI();
        MaintainPatient mp = new MaintainPatient();

        boolean running = true;
        while (running) {
            int choice = ui.getMenuChoice();
            switch (choice) {
                case 1:
                    mp.displayAllPatients();
                    break;
                case 2:
                    Patient p = ui.inputPatientDetails();
                    mp.addPatient(p);
                    break;
                case 3:
                    int idx = ui.inputPatientIndex();
                    Patient update = ui.inputPatientDetails();
                    mp.displayAllPatients();
                    mp.updateExistingPatient(idx, update);
                    break;
                case 4:
                    mp.displayAllPatients();
                    int delIdx = ui.inputPatientIndex();
                    mp.deletePatient(delIdx);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

        System.out.println("Exiting Patient Management.");
    }

    private LinkedList<Patient> patientList = new LinkedList<>();

    public void addPatient(Patient p) {
        patientList.add(p);
    }

    public boolean replacePatient(int index, Patient newPatient) {
        if (index >= 0 && index < patientList.size()) {
            patientList.replace(index, newPatient);
            return true;
        }
        return false;
    }

    public boolean updateExistingPatient(int index, Patient patientNewData) {
        if (index >= 0 && index < patientList.size()) {
            Patient exisitingPatient = patientList.get(index); //retrive existing patient info at given index

            if (patientNewData.getName() != null) {
                exisitingPatient.setName(patientNewData.getName()); //Check if any new information for patient, then replace with the new info.
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
            if (patientNewData.getEmail() != null) {
                exisitingPatient.setEmail(patientNewData.getEmail());
            }
            if (patientNewData.getAddress() != null) {
                exisitingPatient.setAddress(patientNewData.getAddress());
            }
            if (patientNewData.getPanel() != null) {
                exisitingPatient.setPanel(patientNewData.getPanel());
            }
            if (patientNewData.getBloodType() != null) {
                exisitingPatient.setBloodType(patientNewData.getBloodType());
            }
            if (patientNewData.getAllergyHistory() != null) {
                exisitingPatient.setAllergyHistory(patientNewData.getAllergyHistory());
            }
            if (patientNewData.getChronicConditions() != null) {
                exisitingPatient.setChronicConditions(patientNewData.getChronicConditions());
            }
            if (patientNewData.getEmergencyContactName() != null) {
                exisitingPatient.setEmergencyContactName(patientNewData.getEmergencyContactName());
            }
            if (patientNewData.getEmergencyContactNumber() != null) {
                exisitingPatient.setEmergencyContactNumber(patientNewData.getEmergencyContactNumber());
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

    public boolean deletePatient(int index) {
        return patientList.remove(index);
    }

    public void displayAllPatients() {
        for (int i = 0; i < patientList.size(); i++) {
            System.out.println(i + ": " + patientList.get(i));
        }
    }

    public Patient getPatient(int index) {
        if (index >= 0 && index < patientList.size()) {
            return patientList.get(index);
        }
        return null;
    }
}
