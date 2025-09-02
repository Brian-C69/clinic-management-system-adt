package control;

/**
 *
 * @author Na Ting Choon
 */

import adt.LinkedList;
import adt.ListInterface;
import boundary.ClinicUI;
import entity.Patient;

public class MaintainPatient {

    private final ListInterface<Patient> patientList;
    private final ListInterface<Patient> patientQueueList;
    private int queueCounter = 1;
    private int patientIdCounter = 0;

    public MaintainPatient() {
        this.patientList = new LinkedList<>();
        this.patientQueueList = new LinkedList<>();
    }

    public MaintainPatient(ListInterface<Patient> store) {
        this.patientList = store;
        this.patientQueueList = new LinkedList<>();
    }

    public MaintainPatient(ListInterface<Patient> store, ListInterface<Patient> queueStore) {
        this.patientList = store;
        this.patientQueueList = queueStore;
    }

    private String nextPatientId() {
        patientIdCounter++;
        return String.format("P%03d", patientIdCounter);
    }

    private String nextQueueNo() {
        return String.format("PQ%03d", queueCounter++);
    }

    public void addPatient(Patient p) {
        if (p == null) {
            return;
        }
        if (p.getPatientID() == null || p.getPatientID().isEmpty()) {
            p.setPatientID(nextPatientId());
        }
        if (p.getQueueNumber() == null || p.getQueueNumber().isEmpty()) {
            p.setQueueNumber(nextQueueNo());
        }
        patientList.add(p);
        patientQueueList.add(p);
    }

    public boolean updateExistingPatient(int index, Patient nd) {
        if (index < 0 || index >= patientList.size() || nd == null) {
            return false;
        }
        Patient ex = patientList.get(index);
        if (nd.getName() != null) {
            ex.setName(nd.getName());
        }
        if (nd.getIcNumber() != null) {
            ex.setIcNumber(nd.getIcNumber());
        }
        if (nd.getDateOfBirth() != null) {
            ex.setDateOfBirth(nd.getDateOfBirth());
        }
        if (nd.getSex() != null) {
            ex.setSex(nd.getSex());
        }
        if (nd.getContactNumber() != null) {
            ex.setContactNumber(nd.getContactNumber());
        }
        if (nd.getAllergyHistory() != null) {
            ex.setAllergyHistory(nd.getAllergyHistory());
        }
        if (nd.getDateOfRegistration() != null) {
            ex.setDateOfRegistration(nd.getDateOfRegistration());
        }
        if (nd.getLastVisitDate() != null) {
            ex.setLastVisitDate(nd.getLastVisitDate());
        }
        ex.setIsActive(nd.isActive());
        return true;
    }

    public Patient deletePatient(int index) {
        if (index < 0 || index >= patientList.size()) {
            return null;
        }
        Patient removed = patientList.remove(index);
        // also remove from queue if present
        for (int i = 0; i < patientQueueList.size(); i++) {
            if (patientQueueList.get(i) == removed) {
                patientQueueList.remove(i);
                break;
            }
        }
        return removed;
    }

    public ListInterface<Patient> getAllPatients() {
        return patientList;
    }

    public Patient getPatient(int index) {
        return (index >= 0 && index < patientList.size()) ? patientList.get(index) : null;
    }

    public int getSize() {
        return patientList.size();
    }

    // queue ops
    public Patient nextPatient() {
        return patientQueueList.isEmpty() ? null : patientQueueList.remove(0);
    }

    public Patient peekNextPatient() {
        return patientQueueList.isEmpty() ? null : patientQueueList.get(0);
    }

    public boolean cancelPatient(String queueNumber) {
        for (int i = 0; i < patientQueueList.size(); i++) {
            Patient p = patientQueueList.get(i);
            if (p.getQueueNumber() != null && p.getQueueNumber().equals(queueNumber)) {
                patientQueueList.remove(i);
                return true;
            }
        }
        return false;
    }

    // filters/search
    public ListInterface<Patient> filterByGender(String gender) {
        ListInterface<Patient> r = new LinkedList<>();
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            if (p.getSex() != null && p.getSex().equalsIgnoreCase(gender)) {
                r.add(p);
            }
        }
        return r;
    }

    public ListInterface<Patient> filterByStatus(boolean active) {
        ListInterface<Patient> r = new LinkedList<>();
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            if (p.isActive() == active) {
                r.add(p);
            }
        }
        return r;
    }

    public ListInterface<Patient> linearSearch(int option, String keyword) {
        ListInterface<Patient> r = new LinkedList<>();
        String key = keyword == null ? "" : keyword.toLowerCase();
        for (int i = 0; i < patientList.size(); i++) {
            Patient p = patientList.get(i);
            switch (option) {
                case 1 -> {
                    if (p.getPatientID() != null && p.getPatientID().equalsIgnoreCase(keyword)) {
                        r.add(p);
                    }
                }
                case 2 -> {
                    if (p.getName() != null && p.getName().toLowerCase().contains(key)) {
                        r.add(p);
                    }
                }
            }
        }
        return r;
    }

    public static void main(String[] args) {
        new ClinicUI().run();
    }
}
