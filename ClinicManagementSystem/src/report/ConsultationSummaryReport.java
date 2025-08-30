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

        // === Frequency collections ===
        ListInterface<String> doctorNames = new LinkedList<>();
        ListInterface<Integer> doctorCounts = new LinkedList<>();

        ListInterface<String> symptomNames = new LinkedList<>();
        ListInterface<Integer> symptomCounts = new LinkedList<>();

        for (Consultation c : consultationList) {
            String docName = c.getDoctor().getName();
            String symptom = c.getSymptoms();

            // Count doctors
            boolean doctorFound = false;
            for (int i = 0; i < doctorNames.size(); i++) {
                if (doctorNames.get(i).equalsIgnoreCase(docName)) {
                    doctorCounts.replace(i, doctorCounts.get(i) + 1);
                    doctorFound = true;
                    break;
                }
            }
            if (!doctorFound) {
                doctorNames.add(docName);
                doctorCounts.add(1);
            }

            // Count symptoms
            boolean symptomFound = false;
            for (int i = 0; i < symptomNames.size(); i++) {
                if (symptomNames.get(i).equalsIgnoreCase(symptom)) {
                    symptomCounts.replace(i, symptomCounts.get(i) + 1);
                    symptomFound = true;
                    break;
                }
            }
            if (!symptomFound) {
                symptomNames.add(symptom);
                symptomCounts.add(1);
            }
        }

        // === Print Header ===
        System.out.println("\n               TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.println("                          CONSULTATION MODULE SUBSYSTEM");
        System.out.println("                        -----------------------------------");
        System.out.println("                         SUMMARY OF CONSULTATION REPORT");
        System.out.printf("Generated at: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("\n===============================================================================");
        System.out.println("                          CONFIDENTIAL MEDICAL REPORT");
        System.out.println("===============================================================================\n");

        // === Doctor Frequency Table ===
        System.out.println("| Doctor Name           | Total Consultations |");
        System.out.println("|-----------------------|---------------------|");
        for (int i = 0; i < doctorNames.size(); i++) {
            System.out.printf("| %-21s | %-19d |\n", doctorNames.get(i), doctorCounts.get(i));
        }

        System.out.println();

        // === Symptom Frequency Table ===
        System.out.println("| Symptom               | Frequency           |");
        System.out.println("|-----------------------|---------------------|");
        for (int i = 0; i < symptomNames.size(); i++) {
            System.out.printf("| %-21s | %-19d |\n", symptomNames.get(i), symptomCounts.get(i));
        }

        System.out.println("\n===============================================================================");
        System.out.println("\n                GRAPHICAL REPRESENTATION OF CONSULTATION MODULE\n");

        // === Simple Text Graph for Top Doctor ===
        System.out.println("Top Consultations per Doctor");
        int max = 0;
        for (int i = 0; i < doctorCounts.size(); i++) {
            if (doctorCounts.get(i) > max) max = doctorCounts.get(i);
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
        System.out.println("\n     ");

        for (int i = 0; i < doctorNames.size(); i++) {
            System.out.printf("%-7s", doctorNames.get(i).split(" ")[1]);
        }
        System.out.println("\nEND OF REPORT");
        System.out.println("===============================================================================");
    }
    
    public static void print(ListInterface<Consultation> consultationList) {
    ConsultationSummaryReport report = new ConsultationSummaryReport(consultationList);
    report.generateReport();
}
}
