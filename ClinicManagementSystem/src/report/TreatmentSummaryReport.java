// File: report/TreatmentSummaryReport.java
package report;

import adt.ListInterface;
import entity.MedicalTreatment;

import java.time.format.DateTimeFormatter;
import java.util.*;

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
        Set<String> uniqueDoctors = new HashSet<>();
        Set<String> uniquePatients = new HashSet<>();

        for (int i = 0; i < treatments.size(); i++) {
            MedicalTreatment t = treatments.get(i);
            uniqueDoctors.add(t.getDoctor().getDoctorId());
            uniquePatients.add(t.getPatient().getPatientID());
        }

        System.out.println("Total Treatments       : " + treatments.size());
        System.out.println("Total Unique Doctors   : " + uniqueDoctors.size());
        System.out.println("Total Unique Patients  : " + uniquePatients.size());
    }

    private static void printAsciiGraphs(ListInterface<MedicalTreatment> treatments) {
        System.out.println("\nTop 5 Most Common Medicines:");
        Map<String, Integer> freq = new HashMap<>();
        for (int i = 0; i < treatments.size(); i++) {
            String name = treatments.get(i).getMedicine();
            freq.put(name, freq.getOrDefault(name, 0) + 1);
        }

        freq.entrySet().stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(5)
            .forEach(entry -> {
                System.out.printf("%-15s | %s (%d)\n",
                        entry.getKey(),
                        "#".repeat(entry.getValue()),
                        entry.getValue());
            });
    }
}
