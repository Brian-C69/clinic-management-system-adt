/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;
import adt.*;
import entity.*;
/**
 *
 * @author User
 */
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
            treatmentList.remove(treatmentList.get(index));
            treatmentList.add(updated); // adds to end — could be replaced at same index with a better list
            return true;
        }
        return false;
    }

    public boolean deleteTreatment(int index) {
        if (index >= 0 && index < treatmentList.size()) {
            treatmentList.remove(treatmentList.get(index));
            return true;
        }
        return false;
    }
}

