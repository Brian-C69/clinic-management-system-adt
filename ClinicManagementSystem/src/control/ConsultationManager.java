package control;
import adt.*;
import entity.*;

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
            consultationList.remove(index);
            consultationList.add(updated);
            return true;
        }
        return false;
    }

    public boolean deleteConsultation(int index) {
        return consultationList.remove(index);
    }
}


