package report;

import adt.ListInterface;
import adt.LinkedList;
import entity.Consultation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsultationSummaryReport {

    private final ListInterface<Consultation> consultationList;

    public ConsultationSummaryReport(ListInterface<Consultation> consultationList) {
        this.consultationList = consultationList;
    }

    public void generateReport() {
        if (consultationList.isEmpty()) {
            System.out.println("No consultations available for report.");
            return;
        }

        ListInterface<String> doctorNames = new LinkedList<>();
        ListInterface<Integer> doctorCounts = new LinkedList<>();

        ListInterface<String> symptomNames = new LinkedList<>();
        ListInterface<Integer> symptomCounts = new LinkedList<>();

        for (Consultation c : consultationList) {
            String docName = c.getDoctor().getName();
            String symptom = c.getSymptoms();

            updateFrequency(docName, doctorNames, doctorCounts);
            updateFrequency(symptom, symptomNames, symptomCounts);
        }

        // === Header ===
        System.out.println("\n               TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.println("                          CONSULTATION MODULE SUBSYSTEM");
        System.out.println("                                  KLINIK BAHAGIA");
        System.out.println("                        -----------------------------------");
        System.out.println("                         SUMMARY OF CONSULTATION REPORT");
        System.out.printf("Generated at: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("\n===============================================================================");

        // === Confidential Notice ===
        System.out.println("                          CONFIDENTIAL MEDICAL REPORT");
        System.out.println("===============================================================================\n");

        // === Combined Table: Doctor + Symptom Frequency ===
        int rows = Math.max(doctorNames.size(), symptomNames.size());
        System.out.printf("| %-23s | %-21s || %-23s | %-10s |\n",
                "Doctor Name", "Total Consultations", "Symptom", "Frequency");
        System.out.println("|-------------------------|-----------------------||-------------------------|------------|");

        for (int i = 0; i < rows; i++) {
            String doc = i < doctorNames.size() ? doctorNames.get(i) : "";
            String docCount = i < doctorCounts.size() ? String.valueOf(doctorCounts.get(i)) : "";

            String symp = i < symptomNames.size() ? symptomNames.get(i) : "";
            String sympCount = i < symptomCounts.size() ? String.valueOf(symptomCounts.get(i)) : "";

            System.out.printf("| %-23s | %-21s || %-23s | %-10s |\n",
                    doc, docCount, symp, sympCount);
        }

        System.out.println("\n===============================================================================");

        // === Graphical View ===
        System.out.println("\n                GRAPHICAL REPRESENTATION OF CONSULTATION MODULE\n");

        System.out.println("Top Consultations per Doctor");

        int max = 0;
        for (int i = 0; i < doctorCounts.size(); i++) {
            if (doctorCounts.get(i) > max) {
                max = doctorCounts.get(i);
            }
        }

        for (int level = max; level > 0; level--) {
            System.out.printf("%2d |", level);
            for (int i = 0; i < doctorCounts.size(); i++) {
                if (doctorCounts.get(i) >= level) {
                    System.out.print("   █   ");
                } else {
                    System.out.print("       ");
                }
            }
            System.out.println();
        }

        System.out.print(" 0 +");
        for (int i = 0; i < doctorCounts.size(); i++) {
            System.out.print("-------");
        }
        System.out.println();

// Print aligned labels under each bar
        System.out.print("     ");
        for (int i = 0; i < doctorNames.size(); i++) {
            String[] parts = doctorNames.get(i).split(" ");
            String label = parts.length > 1 ? parts[1] : parts[0]; // Prefer last name
            label = label.length() > 5 ? label.substring(0, 5) : label; // Truncate if too long

            int padding = (7 - label.length()) / 2; // Center within 7 spaces
            String leftPad = " ".repeat(padding);
            String rightPad = " ".repeat(7 - label.length() - padding);

            System.out.print(leftPad + label + rightPad);
        }

        System.out.println("\n\nNOTE:");
        System.out.println("- Includes both follow-up and initial consultations.");
        System.out.println("- Count includes active and completed sessions.");
        System.out.println("===============================================================================\nEND OF REPORT");
    }

    private void updateFrequency(String value, ListInterface<String> keys, ListInterface<Integer> counts) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equalsIgnoreCase(value)) {
                counts.replace(i, counts.get(i) + 1);
                return;
            }
        }
        keys.add(value);
        counts.add(1);
    }

    public static void print(ListInterface<Consultation> consultationList) {
        new ConsultationSummaryReport(consultationList).generateReport();
    }
}
