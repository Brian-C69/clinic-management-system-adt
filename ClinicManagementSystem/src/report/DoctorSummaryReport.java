// File: report/DoctorSummaryReport.java
package report;

import adt.ListInterface;
import entity.Doctor;
import entity.Consultation;

import java.util.HashMap;
import java.util.Map;

public class DoctorSummaryReport {

    public static void generate(ListInterface<Doctor> doctors) {
        if (doctors == null || doctors.isEmpty()) {
            System.out.println("No doctor data to summarize.");
            return;
        }

        int total = doctors.size();
        Map<String, Integer> specCount = new HashMap<>();
        Map<String, Integer> genderCount = new HashMap<>();
        Map<String, Integer> statusCount = new HashMap<>();
        int totalConsultations = 0;

        for (int i = 0; i < doctors.size(); i++) {
            Doctor d = doctors.get(i);

            // Count by specialization
            specCount.put(d.getSpecialization(), specCount.getOrDefault(d.getSpecialization(), 0) + 1);

            // Count by gender
            genderCount.put(d.getGender(), genderCount.getOrDefault(d.getGender(), 0) + 1);

            // Count by status
            statusCount.put(d.getStatus(), statusCount.getOrDefault(d.getStatus(), 0) + 1);

            // Count consultations
            totalConsultations += (d.getConsultations() != null) ? d.getConsultations().size() : 0;
        }

        double avgConsults = total / (double) total == 0 ? 0 : totalConsultations / (double) total;

        System.out.println("\n===== Doctor Summary Report =====");
        System.out.println("Total Doctors: " + total);
        System.out.printf("Average Consultations: %.1f\n", avgConsults);

        System.out.println("\n--- Specialization Breakdown ---");
        for (Map.Entry<String, Integer> entry : specCount.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n--- Gender Breakdown ---");
        for (Map.Entry<String, Integer> entry : genderCount.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }

        System.out.println("\n--- Status Breakdown ---");
        for (Map.Entry<String, Integer> entry : statusCount.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }
}
