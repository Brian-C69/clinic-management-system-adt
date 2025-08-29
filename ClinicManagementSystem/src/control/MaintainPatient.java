package control;

import adt.ListInterface;
import adt.LinkedList;
import entity.Patient;
import boundary.PatientUI;

public class MaintainPatient {

    // Permanent patient records
    private ListInterface<Patient> patientList = new LinkedList<>();
    private int queueCounter = 1;
    private int patientIdCounter = 0;

    // ========= CRUD =========
    private String generatePatientId() {
        patientIdCounter++;
        return String.format("P%03d", patientIdCounter);
    }

    public void addPatient(Patient p) {
        // Generate a queue number
        String queueNumber = String.format("PQ%03d", queueCounter++);
        p.setQueueNumber(queueNumber);

        // Auto generate patient id
        p.setPatientID(generatePatientId());
        patientList.add(p);
    }

    public boolean replacePatient(int index, Patient newPatient) {
        return patientList.replace(index, newPatient);
    }

    public boolean updateExistingPatient(int index, Patient patientNewData) {
        if (index >= 0 && index < patientList.size()) {
            Patient existingPatient = patientList.get(index);

            if (patientNewData.getName() != null) existingPatient.setName(patientNewData.getName());
            if (patientNewData.getIcNumber() != null) existingPatient.setIcNumber(patientNewData.getIcNumber());
            if (patientNewData.getDateOfBirth() != null) existingPatient.setDateOfBirth(patientNewData.getDateOfBirth());
            if (patientNewData.getSex() != null) existingPatient.setSex(patientNewData.getSex());
            if (patientNewData.getContactNumber() != null) existingPatient.setContactNumber(patientNewData.getContactNumber());
            if (patientNewData.getAllergyHistory() != null) existingPatient.setAllergyHistory(patientNewData.getAllergyHistory());
            if (patientNewData.getDateOfRegistration() != null) existingPatient.setDateOfRegistration(patientNewData.getDateOfRegistration());
            if (patientNewData.getLastVisitDate() != null) existingPatient.setLastVisitDate(patientNewData.getLastVisitDate());

            // always overwrite isActive
            existingPatient.setIsActive(patientNewData.isActive());
            return true;
        }
        return false;
    }

    public Patient deletePatient(int index) {
        if (index >= 0 && index < patientList.size()) {
            return patientList.remove(index);
        }
        return null;
    }

    public ListInterface<Patient> getAllPatients() {
        return patientList;
    }

    public Patient getPatient(int index) {
        return (index >= 0 && index < patientList.size()) ? patientList.get(index) : null;
    }

    public Patient nextPatient() {
        if (patientList.isEmpty()) {
            return null;
        }
        return patientList.remove(0);
    }

    public Patient peekNextPatient() {
        if (patientList.isEmpty()) {
            return null;
        }
        return patientList.get(0);
    }

    public boolean cancelPatient(String queueNumber) {
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            if (p.getQueueNumber().equals(queueNumber)) {
                patientList.remove(i);
                return true;
            }
        }
        return false;
    }

    public ListInterface<Patient> filterByGender(String gender) {
        ListInterface<Patient> results = new LinkedList<>();
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            if (p.getSex().equalsIgnoreCase(gender)) {
                results.add(p);
            }
        }
        return results;
    }

    public ListInterface<Patient> filterByStatus(boolean active) {
        ListInterface<Patient> results = new LinkedList<>();
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            if (p.isActive() == active) {
                results.add(p);
            }
        }
        return results;
    }

    public ListInterface<Patient> linearSearch(int option, String keyword) {
        ListInterface<Patient> results = new LinkedList<>();
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            switch (option) {
                case 1 -> { // search by ID
                    if (p.getPatientID().equalsIgnoreCase(keyword)) {
                        results.add(p);
                    }
                }
                case 2 -> { // search by name
                    if (p.getName().equalsIgnoreCase(keyword)) {
                        results.add(p);
                    }
                }
            }
        }
        return results;
    }

    public int getSize() {
        return patientList.size();
    }

    // ========= MAIN entry =========
    public static void main(String[] args) {
        PatientUI ui = new PatientUI(new MaintainPatient());

        // Seed data
        Patient p1 = new Patient();
        p1.setName("NTC");
        p1.setIcNumber("123456-01-0001");
        p1.setContactNumber("010-0000001");
        p1.setSex("M");
        p1.setAllergyHistory("None");
        p1.setIsActive(true);

        Patient p2 = new Patient();
        p2.setName("Alice");
        p2.setIcNumber("123456-02-0002");
        p2.setContactNumber("010-0000002");
        p2.setSex("F");
        p2.setAllergyHistory("Peanut");
        p2.setIsActive(false);

        MaintainPatient ctrl = ui.getController();
        ctrl.addPatient(p1);
        ctrl.addPatient(p2);

        // Run menu
        ui.runPatientMaintenance();
    }
}
