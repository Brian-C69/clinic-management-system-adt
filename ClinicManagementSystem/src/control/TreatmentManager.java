package control;

import adt.LinkedList;
import adt.ListInterface;
import entity.MedicalTreatment;
import entity.Medicine;
import entity.Patient;
import entity.Doctor;

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
        if (index >= 0 && index < medicineStock.size()) {
            return medicineStock.get(index);
        }
        return null;
    }

    public boolean updateMedicine(int index, Medicine newMed) {
        return medicineStock.replace(index, newMed);
    }

    public boolean deleteMedicine(int index) {
        return medicineStock.remove(index);
    }

    // ---------------- Treatment Management ----------------
    public void addTreatment(MedicalTreatment t) {
        // Deduct stock from medicine when treatment is prescribed
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
        if (index >= 0 && index < treatmentList.size()) {
            return treatmentList.get(index);
        }
        return null;
    }

    public boolean updateTreatment(int index, MedicalTreatment updated) {
        return treatmentList.replace(index, updated);
    }

    public boolean deleteTreatment(int index) {
        return treatmentList.remove(index);
    }
}
