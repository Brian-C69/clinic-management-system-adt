package control;

import adt.ListInterface;
import adt.LinkedList;
import entity.MedicalTreatment;
import entity.Medicine;

public class TreatmentManager {
    private ListInterface<MedicalTreatment> treatmentList = new LinkedList<>();
    private ListInterface<Medicine> medicineStock = new LinkedList<>();

    // ---------------- Medicine Management ----------------
    public void addMedicine(Medicine m) {
        medicineStock.add(m);
    }

    public void displayAllMedicines() {
        System.out.println("\n--- Medicine Inventory ---");
        for (int i = 0; i < medicineStock.size(); i++) {
            System.out.println(i + " -> " + medicineStock.get(i));
        }
    }

    public Medicine getMedicine(int index) {
        return (index >= 0 && index < medicineStock.size()) ? medicineStock.get(index) : null;
    }

    public boolean updateMedicine(int index, Medicine newMed) {
        return medicineStock.replace(index, newMed);
    }

    public Medicine deleteMedicine(int index) {
        return medicineStock.remove(index);
    }

    // ---------------- Treatment Management ----------------
    public void addTreatment(MedicalTreatment t) {
        Medicine prescribed = t.getMedicine();
        for (int i = 0; i < medicineStock.size(); i++) {
            Medicine m = medicineStock.get(i);
            if (m.getName().equalsIgnoreCase(prescribed.getName())) {
                if (m.getQuantityAvailable() >= 1) {
                    m.setQuantityAvailable(m.getQuantityAvailable() - 1);
                    treatmentList.add(t);
                    System.out.println("✅ Treatment added and stock updated.");
                } else {
                    System.out.println("❌ Not enough stock for medicine: " + m.getName());
                }
                return;
            }
        }
        System.out.println("❌ Medicine not found in stock: " + prescribed.getName());
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
}
