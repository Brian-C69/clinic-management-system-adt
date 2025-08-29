package control;

import adt.LinkedList;
import adt.ListInterface;
import boundary.ClinicUI;
import entity.MedicalTreatment;

public class TreatmentManager {
    private ListInterface<MedicalTreatment> treatmentList;

    public TreatmentManager() {
        this.treatmentList = new LinkedList<>();
    }

    public TreatmentManager(ListInterface<MedicalTreatment> store) {
        this.treatmentList = store != null ? store : new LinkedList<>();
    }

    // Add treatment with optional silent mode
    public void addTreatment(MedicalTreatment t) {
        addTreatment(t, false);
    }

    public void addTreatment(MedicalTreatment t, boolean silent) {
        if (t == null) return;
        treatmentList.add(t);
        if (!silent) {
            System.out.println("✅ Treatment added.");
        }
    }

    // Display all treatments
    public void displayAllTreatments() {
        System.out.println("\n--- Treatment Records ---");
        if (treatmentList.isEmpty()) {
            System.out.println("No treatments found.");
            return;
        }
        for (int i = 0; i < treatmentList.size(); i++) {
            System.out.println((i + 1) + " -> " + treatmentList.get(i));
        }
    }

    // Retrieve a treatment by index
    public MedicalTreatment getTreatment(int index) {
        if (index >= 0 && index < treatmentList.size()) {
            return treatmentList.get(index);
        }
        return null;
    }

    // Update an existing treatment
    public boolean updateTreatment(int index, MedicalTreatment updated) {
        if (updated == null || index < 0 || index >= treatmentList.size()) {
            return false;
        }
        return treatmentList.replace(index, updated);
    }

    // Delete a treatment by index
    public MedicalTreatment deleteTreatment(int index) {
        if (index < 0 || index >= treatmentList.size()) {
            return null;
        }
        return treatmentList.remove(index);
    }

    // Return number of treatments
    public int getTreatmentCount() {
        return treatmentList.size();
    }

    // Entry point
    public static void main(String[] args) {
        new ClinicUI().run();
    }
}
