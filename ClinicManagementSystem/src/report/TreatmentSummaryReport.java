package report;

import adt.ListInterface;
import adt.LinkedList;
import entity.MedicalTreatment;

import java.time.format.DateTimeFormatter;

public class TreatmentSummaryReport {

    public static void generate(ListInterface<MedicalTreatment> treatments) {
        System.out.println("\n======================== TREATMENT SUMMARY REPORT ========================\n");

        printTreatmentTable(treatments);
        printTreatmentStatistics(treatments);
        printAsciiGraphs(treatments);

        System.out.println("\n==========================================================================\n");
        System.out.println("END OF THE REPORT");
    }

    private static void printTreatmentTable(ListInterface<MedicalTreatment> treatments) {
        System.out.printf("%-4s | %-15s | %-6s | %-8s | %-8s | %-8s | %-12s\n",
                "No.", "Medicine", "Dosage", "Duration", "Doctor", "Patient", "Start Date");
        System.out.println("--------------------------------------------------------------------------------");
        for (int i = 0; i < treatments.size(); i++) {
            MedicalTreatment t = treatments.get(i);
            System.out.printf("%4d | %-15s | %-6s | %-8s | %-8s | %-8s | %-12s\n",
                    i + 1,
                    t.getMedicine(),
                    t.getDosage(),
                    t.getDuration(),
                    t.getDoctor().getDoctorId(),
                    t.getPatient().getPatientID(),
                    t.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );
        }
        System.out.println();
    }

    private static void printTreatmentStatistics(ListInterface<MedicalTreatment> treatments) {
        ListInterface<String> uniqueDoctors = new LinkedList<>();
        ListInterface<String> uniquePatients = new LinkedList<>();

        for (int i = 0; i < treatments.size(); i++) {
            MedicalTreatment t = treatments.get(i);
            String doctorId = t.getDoctor().getDoctorId();
            String patientId = t.getPatient().getPatientID();

            if (!uniqueDoctors.contains(doctorId)) {
                uniqueDoctors.add(doctorId);
            }
            if (!uniquePatients.contains(patientId)) {
                uniquePatients.add(patientId);
            }
        }

        System.out.println("Total Treatments       : " + treatments.size());
        System.out.println("Total Unique Doctors   : " + uniqueDoctors.size());
        System.out.println("Total Unique Patients  : " + uniquePatients.size());
    }

    private static void printAsciiGraphs(ListInterface<MedicalTreatment> treatments) {
        System.out.println("\nTop 5 Most Common Medicines:");

        // Count frequency
        ListInterface<String> medicineNames = new LinkedList<>();
        ListInterface<Integer> medicineCounts = new LinkedList<>();

        for (int i = 0; i < treatments.size(); i++) {
            String name = treatments.get(i).getMedicine();
            boolean found = false;

            for (int j = 0; j < medicineNames.size(); j++) {
                if (medicineNames.get(j).equalsIgnoreCase(name)) {
                    medicineCounts.replace(j, medicineCounts.get(j) + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                medicineNames.add(name);
                medicineCounts.add(1);
            }
        }

        // Manual sort descending by count (simple bubble sort)
        for (int i = 0; i < medicineCounts.size() - 1; i++) {
            for (int j = 0; j < medicineCounts.size() - i - 1; j++) {
                if (medicineCounts.get(j) < medicineCounts.get(j + 1)) {
                    // Swap counts
                    int tempCount = medicineCounts.get(j);
                    medicineCounts.replace(j, medicineCounts.get(j + 1));
                    medicineCounts.replace(j + 1, tempCount);

                    // Swap names
                    String tempName = medicineNames.get(j);
                    medicineNames.replace(j, medicineNames.get(j + 1));
                    medicineNames.replace(j + 1, tempName);
                }
            }
        }

        int limit = Math.min(5, medicineNames.size());
        for (int i = 0; i < limit; i++) {
            String med = medicineNames.get(i);
            int count = medicineCounts.get(i);
            System.out.printf("%-15s | %s (%d)\n", med, "#".repeat(count), count);
        }
    }
}
