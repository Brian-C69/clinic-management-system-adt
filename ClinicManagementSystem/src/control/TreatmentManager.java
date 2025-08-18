package control;

import adt.LinkedList;
import adt.ListInterface;
import entity.MedicalTreatment;

public class TreatmentManager {
    private ListInterface<MedicalTreatment> treatmentList = new LinkedList<>();

    // CREATE
    public void addTreatment(MedicalTreatment treatment) {
        treatmentList.add(treatment);
    }

    // READ
    public void displayAllTreatments() {
        for (int i = 0; i < treatmentList.size(); i++) {
            System.out.println(i + " -> " + treatmentList.get(i));
        }
    }

    public MedicalTreatment getTreatment(int index) {
        return treatmentList.get(index);
    }

    // UPDATE
    public boolean updateTreatment(int index, MedicalTreatment updated) {
        return treatmentList.replace(index, updated);
    }

    // DELETE
    public boolean deleteTreatment(int index) {
        return treatmentList.remove(index);
    }

    // SORT treatments by start date (ascending)
    public void sortTreatmentsByDate() {
        for (int i = 0; i < treatmentList.size() - 1; i++) {
            for (int j = i + 1; j < treatmentList.size(); j++) {
                MedicalTreatment t1 = treatmentList.get(i);
                MedicalTreatment t2 = treatmentList.get(j);

                if (t1.compareTo(t2) > 0) {
                    // swap
                    treatmentList.replace(i, t2);
                    treatmentList.replace(j, t1);
                }
            }
        }
    }

    public void displaySortedTreatments() {
        sortTreatmentsByDate();
        System.out.println("Treatments sorted by start date:");
        displayAllTreatments();
    }
}
