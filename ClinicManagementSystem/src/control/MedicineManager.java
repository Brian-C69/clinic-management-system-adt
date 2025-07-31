package control;

import adt.*;
import entity.*;

public class MedicineManager {
    private ListInterface<Medicine> medicineList;

    public MedicineManager() {
        // Assume a custom linked list implementation
        medicineList = new LinkedList<>();
    }

    // Create
    public void addMedicine(Medicine medicine) {
        medicineList.add(medicine);
    }

    // Read
    public Medicine getMedicineByName(String name) {
        for (int i = 0; i < medicineList.size(); i++) {
            Medicine m = medicineList.get(i);
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public void listAllMedicine() {
        for (int i = 0; i < medicineList.size(); i++) {
            System.out.println(medicineList.get(i));
        }
    }

    // Update
    public boolean updateQuantity(String name, int newQty) {
        Medicine m = getMedicineByName(name);
        if (m != null) {
            m.setQuantityAvailable(newQty);
            return true;
        }
        return false;
    }

    // Delete
    public boolean removeMedicine(String name) {
        for (int i = 0; i < medicineList.size(); i++) {
            if (medicineList.get(i).getName().equalsIgnoreCase(name)) {
                medicineList.remove(i);
                return true;
            }
        }
        return false;
    }
}
