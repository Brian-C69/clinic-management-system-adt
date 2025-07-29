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
public class ConsultationManager {
    private LinkedList<Consultation> consultationList = new LinkedList<>();

    public void addConsultation(Consultation c) {
        consultationList.add(c);
    }

    public void displayAllConsultations() {
        for (int i = 0; i < consultationList.size(); i++) {
            System.out.println(i + ": " + consultationList.get(i));
        }
    }

    public boolean updateConsultation(int index, Consultation updated) {
        if (index >= 0 && index < consultationList.size()) {
            consultationList.remove(consultationList.get(index));
            consultationList.add(updated); // adds to end — not ideal
            return true;
        }
        return false;
    }

    public boolean deleteConsultation(int index) {
        if (index >= 0 && index < consultationList.size()) {
            consultationList.remove(consultationList.get(index));
            return true;
        }
        return false;
    }
}

