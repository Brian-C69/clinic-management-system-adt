// File: control/TreatmentManager.java
package control;

import adt.ListInterface;
import adt.LinkedList;
import entity.MedicalTreatment;

public class TreatmentManager {
    private ListInterface<MedicalTreatment> treatmentList = new LinkedList<>();

    // ---------------- Treatment Management ----------------
    public void addTreatment(MedicalTreatment t) {
        treatmentList.add(t);
        System.out.println("✅ Treatment added.");
    }

    public void displayAllTreatments() {
        System.out.println("\n--- Treatment Records ---");
        for (int i = 0; i < treatmentList.size(); i++) {
            System.out.println(i + " -> " + treatmentList.get(i));
        }
    }

    public MedicalTreatment getTreatment(int index) {
        return (index >= 0 && index < treatmentList.size()) ? treatmentList.get(index) : null;
    }

    public boolean updateTreatment(int index, MedicalTreatment updated) {
        return treatmentList.replace(index, updated);
    }

    public MedicalTreatment deleteTreatment(int index) {
        return treatmentList.remove(index);
    }

    public int getTreatmentCount() {
        return treatmentList.size();
    }
}
