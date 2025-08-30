package control;

/**
 *
 * @author Choong Yun Xian Brian
 */
import adt.LinkedList;
import adt.ListInterface;
import boundary.ClinicUI;
import entity.MedicalTreatment;

public class TreatmentManager {

    private final ListInterface<MedicalTreatment> treatmentList;

    // Constructors
    public TreatmentManager() {
        this(new LinkedList<>());
    }

    public TreatmentManager(ListInterface<MedicalTreatment> store) {
        this.treatmentList = store != null ? store : new LinkedList<>();
    }

    // Add treatment (with optional silent mode)
    public void addTreatment(MedicalTreatment treatment) {
        addTreatment(treatment, false);
    }

    public void addTreatment(MedicalTreatment treatment, boolean silent) {
        if (treatment == null) {
            return;
        }
        treatmentList.add(treatment);
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

        String rowFormat = "| %-15s | %-10s | %-20s | %-25s | %-15s | %-15s | %-20s |";
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format(
                rowFormat,
                "Medicine", "Dosage", "Diagnosis", "Instructions", "Start Date", "Patient", "Doctor"
        ));
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < treatmentList.size(); i++) {
            System.out.println(treatmentList.get(i).toString());
        }

        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    // Get treatment by index
    public MedicalTreatment getTreatment(int index) {
        if (index >= 0 && index < treatmentList.size()) {
            return treatmentList.get(index);
        }
        return null;
    }

    // Update treatment at index
    public boolean updateTreatment(int index, MedicalTreatment updated) {
        if (updated == null || index < 0 || index >= treatmentList.size()) {
            return false;
        }
        return treatmentList.replace(index, updated);
    }

    // Delete treatment at index
    public MedicalTreatment deleteTreatment(int index) {
        if (index < 0 || index >= treatmentList.size()) {
            return null;
        }
        return treatmentList.remove(index);
    }

    // Get total count
    public int getTreatmentCount() {
        return treatmentList.size();
    }

    public ListInterface<MedicalTreatment> searchByMedicine(String keyword) {
        ListInterface<MedicalTreatment> results = new LinkedList<>();
        for (int i = 0; i < treatmentList.size(); i++) {
            MedicalTreatment t = treatmentList.get(i);
            if (t.getMedicine().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(t);
            }
        }
        return results;
    }

    public ListInterface<MedicalTreatment> filterByDoctorId(String doctorId) {
        ListInterface<MedicalTreatment> results = new LinkedList<>();
        for (int i = 0; i < treatmentList.size(); i++) {
            MedicalTreatment t = treatmentList.get(i);
            if (t.getDoctor() != null && t.getDoctor().getDoctorId().equalsIgnoreCase(doctorId)) {
                results.add(t);
            }
        }
        return results;
    }

    public ListInterface<MedicalTreatment> getAllTreatments() {
        return treatmentList;
    }

    // Entry point
    public static void main(String[] args) {
        new ClinicUI().run();
    }
}
