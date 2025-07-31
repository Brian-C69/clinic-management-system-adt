package control;
import adt.*;
import entity.*;

public class TreatmentManager {
    private LinkedList<MedicalTreatment> treatmentList = new LinkedList<>();

    public void addTreatment(MedicalTreatment t) {
        treatmentList.add(t);
    }

    public void displayAllTreatments() {
        for (int i = 0; i < treatmentList.size(); i++) {
            System.out.println(i + ": " + treatmentList.get(i));
        }
    }

    public boolean updateTreatment(int index, MedicalTreatment updated) {
        if (index >= 0 && index < treatmentList.size()) {
            treatmentList.remove(index);
            treatmentList.add(updated);
            return true;
        }
        return false;
    }

    public boolean deleteTreatment(int index) {
        return treatmentList.remove(index);
    }
}
