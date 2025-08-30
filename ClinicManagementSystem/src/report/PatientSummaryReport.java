// File: report/PatientSummaryReport.java
package report;

import adt.ListInterface;
import entity.Patient;

import java.time.Period;
import java.time.LocalDate;
import java.util.*;

public class PatientSummaryReport {

    public static void generate(ListInterface<Patient> patients) {
        System.out.println("\n======================== PATIENT SUMMARY REPORT =========================\n");

        printPatientTable(patients);
        printPatientStatistics(patients);
        printAsciiGraphBySex(patients);

        System.out.println("\n==========================================================================\n");
        System.out.println("END OF THE REPORT");
    }

    private static void printPatientTable(ListInterface<Patient> patients) {
        System.out.printf("%-4s | %-20s | %-10s | %-3s | %-10s | %-10s | %-10s\n",
                "No.", "Name", "Patient ID", "Age", "Sex", "Registered", "Last Visit");
        System.out.println("-------------------------------------------------------------------------------------------");
        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            int age = calculateAge(p.getDateOfBirth());
            System.out.printf("%4d | %-20s | %-10s | %-3d | %-10s | %-10s | %-10s\n",
                    i + 1,
                    p.getName(),
                    p.getPatientID(),
                    age,
                    p.getSex(),
                    String.valueOf(p.getDateOfRegistration()),
                    String.valueOf(p.getLastVisitDate())
            );
        }
        System.out.println();
    }

    private static int calculateAge(LocalDate dob) {
        if (dob == null) return 0;
        return Period.between(dob, LocalDate.now()).getYears();
    }

    private static void printPatientStatistics(ListInterface<Patient> patients) {
        int count = patients.size();
        Set<String> uniqueSexes = new HashSet<>();
        int totalAge = 0;

        for (int i = 0; i < count; i++) {
            Patient p = patients.get(i);
            uniqueSexes.add(p.getSex().toLowerCase());
            totalAge += calculateAge(p.getDateOfBirth());
        }

        double averageAge = count > 0 ? (double) totalAge / count : 0;

        System.out.println("Total Registered Patients : " + count);
        System.out.println("Distinct Sex Categories   : " + uniqueSexes.size());
        System.out.printf("Average Age               : %.2f\n", averageAge);
    }

    private static void printAsciiGraphBySex(ListInterface<Patient> patients) {
        System.out.println("\nPatient Count by Sex:");

        Map<String, Integer> bySex = new HashMap<>();
        for (int i = 0; i < patients.size(); i++) {
            String sex = patients.get(i).getSex().toLowerCase();
            bySex.put(sex, bySex.getOrDefault(sex, 0) + 1);
        }

        bySex.forEach((sex, count) -> {
            System.out.printf("%-10s | %s (%d)\n",
                    sex.substring(0, 1).toUpperCase() + sex.substring(1),
                    "#".repeat(count),
                    count);
        });
    }
}