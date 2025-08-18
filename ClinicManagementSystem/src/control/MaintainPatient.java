package control;

import adt.LinkedList;
import entity.Patient;

public class MaintainPatient {
    private LinkedList<Patient> patientList = new LinkedList<>();

    public void addPatient(Patient p) {
        patientList.add(p);
    }

    public boolean updatePatient(int index, Patient updatedPatient) {
        if (index >= 0 && index < patientList.size()) {
            patientList.replace(index, updatedPatient);
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
