// File: report/PatientSummaryReport.java
package report;

import adt.ListInterface;
import adt.LinkedList;
import entity.Patient;

import java.time.Period;
import java.time.LocalDate;

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
        System.out.printf("%-4s | %-20s | %-10s | %-3s | %-10s | %-12s | %-12s\n",
                "No.", "Name", "Patient ID", "Age", "Sex", "Registered", "Last Visit");
        System.out.println("----------------------------------------------------------------------------------------------");

        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            int age = calculateAge(p.getDateOfBirth());
            System.out.printf("%4d | %-20s | %-10s | %-3d | %-10s | %-12s | %-12s\n",
                    i + 1,
                    p.getName(),
                    p.getPatientID(),
                    age,
                    p.getSex(),
                    p.getDateOfRegistration(),
                    p.getLastVisitDate() != null ? p.getLastVisitDate() : "N/A"
            );
        }
        System.out.println();
    }

    private static int calculateAge(LocalDate dob) {
        if (dob == null) return 0;
        return Period.between(dob, LocalDate.now()).getYears();
    }

    private static void printPatientStatistics(ListInterface<Patient> patients) {
        int totalPatients = patients.size();
        int totalAge = 0;

        ListInterface<String> uniqueSexes = new LinkedList<>();

        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            String sex = p.getSex().toLowerCase();

            if (!uniqueSexes.contains(sex)) {
                uniqueSexes.add(sex);
            }

            totalAge += calculateAge(p.getDateOfBirth());
        }

        double averageAge = totalPatients > 0 ? (double) totalAge / totalPatients : 0;

        System.out.println("Total Registered Patients : " + totalPatients);
        System.out.println("Distinct Sex Categories   : " + uniqueSexes.size());
        System.out.printf("Average Age               : %.2f\n", averageAge);
    }

    private static void printAsciiGraphBySex(ListInterface<Patient> patients) {
        System.out.println("\nPatient Count by Sex:");

        ListInterface<String> sexes = new LinkedList<>();
        ListInterface<Integer> counts = new LinkedList<>();

        for (int i = 0; i < patients.size(); i++) {
            String sex = patients.get(i).getSex().toLowerCase();
            boolean found = false;

            for (int j = 0; j < sexes.size(); j++) {
                if (sexes.get(j).equals(sex)) {
                    counts.replace(j, counts.get(j) + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                sexes.add(sex);
                counts.add(1);
            }
        }

        for (int i = 0; i < sexes.size(); i++) {
            String label = sexes.get(i).substring(0, 1).toUpperCase() + sexes.get(i).substring(1);
            int count = counts.get(i);
            System.out.printf("%-10s | %s (%d)\n", label, "#".repeat(count), count);
        }
    }
}
