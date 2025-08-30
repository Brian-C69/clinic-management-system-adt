// File: report/ConsultationSummaryReport.java
package report;

import adt.ListInterface;
import entity.Consultation;
import entity.MedicalTreatment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ConsultationSummaryReport {

    public static void generate(ListInterface<Consultation> consultations) {
        if (consultations == null || consultations.isEmpty()) {
            System.out.println("No consultation data to summarize.");
            return;
        }

        int totalConsultations = consultations.size();
        int totalTreatments = 0;
        double totalFees = 0;
        int followUpCount = 0;

        Map<String, Integer> statusMap = new HashMap<>();
        Map<String, Integer> doctorMap = new HashMap<>();
        Map<String, Integer> patientMap = new HashMap<>();

        for (int i = 0; i < consultations.size(); i++) {
            Consultation c = consultations.get(i);

            if (c.getTreatments() != null) {
                totalTreatments += c.getTreatments().size();
            }

            totalFees += c.getConsultationFee();
            if (c.isFollowUp()) followUpCount++;

            String status = c.getStatus();
            statusMap.put(status, statusMap.getOrDefault(status, 0) + 1);

            String doctorName = c.getDoctor().getName();
            doctorMap.put(doctorName, doctorMap.getOrDefault(doctorName, 0) + 1);

            String patientName = c.getPatient().getName();
            patientMap.put(patientName, patientMap.getOrDefault(patientName, 0) + 1);
        }

        double avgFee = totalFees / totalConsultations;
        double avgTreatmentPerConsult = (double) totalTreatments / totalConsultations;

        System.out.println("======================================================================");
        System.out.println(" TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY");
        System.out.println("                CLINIC MODULE SUBSYSTEM");
        System.out.println("              CONSULTATION SUMMARY REPORT");
        System.out.println("======================================================================");
        System.out.println("Generated at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("----------------------------------------------------------------------");

        System.out.printf("%-30s: %d\n", "Total Consultations", totalConsultations);
        System.out.printf("%-30s: %d\n", "Total Treatments Given", totalTreatments);
        System.out.printf("%-30s: %d\n", "Follow-up Consultations", followUpCount);
        System.out.printf("%-30s: %.2f\n", "Total Fees Collected", totalFees);
        System.out.printf("%-30s: %.2f\n", "Average Fee per Consultation", avgFee);
        System.out.printf("%-30s: %.2f\n", "Average Treatments per Consultation", avgTreatmentPerConsult);

        System.out.println("\n--- Consultations by Status ---");
        statusMap.forEach((k, v) -> System.out.printf("%-20s: %d\n", k, v));

        System.out.println("\n--- Top Doctors by Consultation Count ---");
        doctorMap.entrySet().stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .limit(5)
            .forEach(entry -> System.out.printf("%-20s: %d\n", entry.getKey(), entry.getValue()));

        System.out.println("\n--- Top Patients by Consultation Count ---");
        patientMap.entrySet().stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .limit(5)
            .forEach(entry -> System.out.printf("%-20s: %d\n", entry.getKey(), entry.getValue()));

        System.out.println("\n======================================================================");
        System.out.println(" END OF CONSULTATION SUMMARY REPORT");
        System.out.println("======================================================================");
    }
}
