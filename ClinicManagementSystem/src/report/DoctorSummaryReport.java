// File: report/DoctorSummaryReport.java
package report;

import adt.ListInterface;
import adt.LinkedList;
import entity.Doctor;
import entity.Consultation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DoctorSummaryReport {

    public static void generate(ListInterface<Doctor> doctors) {
        if (doctors == null || doctors.isEmpty()) {
            System.out.println("No doctor data to summarize.");
            return;
        }

        int totalDoctors = doctors.size();
        int totalConsultations = 0;

        ListInterface<String> specializations = new LinkedList<>();
        ListInterface<Integer> specCounts = new LinkedList<>();

        ListInterface<String> genders = new LinkedList<>();
        ListInterface<Integer> genderCounts = new LinkedList<>();

        ListInterface<String> statuses = new LinkedList<>();
        ListInterface<Integer> statusCounts = new LinkedList<>();

        for (int i = 0; i < totalDoctors; i++) {
            Doctor d = doctors.get(i);

            // Count specialization
            updateFrequency(d.getSpecialization(), specializations, specCounts);

            // Count gender
            updateFrequency(d.getGender().toLowerCase(), genders, genderCounts);

            // Count status
            updateFrequency(d.getStatus().toLowerCase(), statuses, statusCounts);

            // Count consultations
            if (d.getConsultations() != null) {
                totalConsultations += d.getConsultations().size();
            }
        }

        double avgConsult = totalDoctors > 0 ? (double) totalConsultations / totalDoctors : 0;

        // ===== Report Header =====
        System.out.println("\n======================== DOCTOR SUMMARY REPORT =========================\n");
        System.out.printf("Generated at: %s\n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.printf("\nTotal Registered Doctors   : %d\n", totalDoctors);
        System.out.printf("Total Consultations        : %d\n", totalConsultations);
        System.out.printf("Average Consultations/Doctor: %.2f\n", avgConsult);

        // ===== Specialization Breakdown =====
        System.out.println("\nSpecialization Breakdown:");
        for (int i = 0; i < specializations.size(); i++) {
            System.out.printf("  %-20s : %d\n", specializations.get(i), specCounts.get(i));
        }

        // ===== Gender Breakdown =====
        System.out.println("\nGender Breakdown:");
        for (int i = 0; i < genders.size(); i++) {
            System.out.printf("  %-10s : %d\n", capitalize(genders.get(i)), genderCounts.get(i));
        }

        // ===== Status Breakdown =====
        System.out.println("\nStatus Breakdown:");
        for (int i = 0; i < statuses.size(); i++) {
            System.out.printf("  %-10s : %d\n", capitalize(statuses.get(i)), statusCounts.get(i));
        }

        // ===== ASCII Graph =====
        System.out.println("\nDoctor Count by Specialization:");
        for (int i = 0; i < specializations.size(); i++) {
            String spec = specializations.get(i);
            int count = specCounts.get(i);
            System.out.printf("  %-20s | %s (%d)\n", spec, "#".repeat(count), count);
        }

        System.out.println("\n========================================================================\n");
        System.out.println("END OF THE REPORT");
    }

    private static void updateFrequency(String value, ListInterface<String> keys, ListInterface<Integer> counts) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equalsIgnoreCase(value)) {
                counts.replace(i, counts.get(i) + 1);
                return;
            }
        }
        keys.add(value);
        counts.add(1);
    }

    private static String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
