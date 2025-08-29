package control;

import adt.LinkedList;
import adt.ListInterface;
import entity.MedicalTreatment;

public class TreatmentManager {
    private ListInterface<MedicalTreatment> treatmentList;

    public TreatmentManager() { this.treatmentList = new LinkedList<>(); }
    public TreatmentManager(ListInterface<MedicalTreatment> store) { this.treatmentList = store; }

    public void addTreatment(MedicalTreatment t) {
        addTreatment(t, false); // default is not silent
    }

    public void addTreatment(MedicalTreatment t, boolean silent) {
        if (t == null) return;
        treatmentList.add(t);
        if (!silent) {
            System.out.println("✅ Treatment added.");
        }
    }

    public void displayAllTreatments() {
        System.out.println("\n--- Treatment Records ---");
        for (int i = 0; i < treatmentList.size(); i++) {
            System.out.println((i + 1) + " -> " + treatmentList.get(i));
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