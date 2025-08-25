//package boundary;
//
//import control.*;
//import entity.*;
//import adt.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Scanner;
//
//public class ClinicUI {
//
//    private MaintainDoctor doctorCtrl = new MaintainDoctor();
//    private MaintainPatient patientCtrl = new MaintainPatient();
////    private ConsultationManager consultCtrl = new ConsultationManager();
//    private TreatmentManager treatCtrl = new TreatmentManager();
//
//    private DoctorUI doctorUI = new DoctorUI();
//    private PatientUI patientUI = new PatientUI();
//    private ConsultationUI consultUI = new ConsultationUI();
//    private MedicalTreatmentUI treatUI = new MedicalTreatmentUI();
//
//    private Scanner scanner = new Scanner(System.in);
//
//    public void run() {
//        int choice;
//        do {
//            System.out.println("\n===== Clinic Management System =====");
//            System.out.println("1. Doctor Management");
//            System.out.println("2. Patient Management");
//            System.out.println("3. Consultation Management");
//            System.out.println("4. Medical Treatment Management");
//            System.out.println("0. Exit");
//            System.out.print("Enter choice: ");
//            choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1 -> doctorMenu();
//                case 2 -> patientMenu();
////                case 3 -> consultationMenu();
//                case 4 -> treatmentMenu();
//            }
//        } while (choice != 0);
//
//        System.out.println("Exiting Clinic System...");
//    }
//
//    // ---------------- Doctor Menu ----------------
//    private void doctorMenu() {
//        int choice;
//        do {
//            choice = doctorUI.getMenuChoice();
//            switch (choice) {
//                case 1 -> doctorCtrl.displayAllDoctors();
//                case 2 -> {
//                    Doctor d = doctorUI.inputDoctorDetails();
//                    doctorCtrl.addDoctor(d);
//                }
//                case 3 -> {
//                    int index = doctorUI.inputDoctorIndex();
//                    Doctor updated = doctorUI.inputDoctorDetails();
//                    doctorCtrl.updateExistingDoctor(index, updated);
//                    // 🔹 Dynamic update: if doctor updated, reflect in consultations
//                    syncDoctorUpdates();
//                }
//                case 4 -> {
//                    int index = doctorUI.inputDoctorIndex();
//                    Doctor d = doctorCtrl.getDoctor(index);
//                    doctorCtrl.deleteDoctor(index);
//                    // 🔹 Remove consultations tied to deleted doctor
//                    consultCtrl.deleteConsultationsByDoctor(d);
//                }
//            }
//        } while (choice != 0);
//    }
//
//    // ---------------- Patient Menu ----------------
//    private void patientMenu() {
//        int choice;
//        do {
//            choice = patientUI.getMenuChoice();
//            switch (choice) {
//                case 1 -> patientCtrl.displayAllPatients();
//                case 2 -> {
//                    Patient p = patientUI.inputPatientDetails();
//                    patientCtrl.addPatient(p);
//                }
//                case 3 -> {
//                    int index = patientUI.inputPatientIndex();
//                    Patient updated = patientUI.inputPatientDetails();
//                    patientCtrl.updateExistingPatient(index, updated);
//                }
//                case 4 -> {
//                    int index = patientUI.inputPatientIndex();
//                    Patient p = patientCtrl.getPatient(index);
//                    patientCtrl.deletePatient(index);
//                    // 🔹 Remove consultations tied to deleted patient
//                    consultCtrl.deleteConsultationsByPatient(p);
//                }
//            }
//        } while (choice != 0);
//    }
//
//    // ---------------- Consultation Menu ----------------
////    private void consultationMenu() {
////        int choice;
////        do {
////            choice = consultUI.getMenuChoice();
////            switch (choice) {
////                case 1 -> consultCtrl.displayAllConsultations();
////                case 2 -> {
////                    // Select patient & doctor dynamically
////                    patientCtrl.displayAllPatients();
////                    int pIndex = patientUI.inputPatientIndex();
////                    Patient p = patientCtrl.getPatient(pIndex);
////
////                    doctorCtrl.displayAllDoctors();
////                    int dIndex = doctorUI.inputDoctorIndex();
////                    Doctor d = doctorCtrl.getDoctor(dIndex);
////
////                    Consultation c = new Consultation(
////                            consultUI.inputCo.jnsultationId(),
////                            consultUI.inputConsultationDateTime(),
////                            p, d,
////                            consultUI.inputSymptoms(),
////                            consultUI.inputDiagnosis(),
////                            new LinkedList<>(),
////                            consultUI.inputNotes(),
////                            consultUI.inputNextAppointment(),
////                            true,
////                            consultUI.inputDurationMinutes(),
////                            consultUI.inputConsultationFee(),
////                            consultUI.inputStatus()
////                    );
////
////                    consultCtrl.addConsultation(c);
////                    // 🔹 Update links in doctor and patient
////                    d.getConsultations().add(c);
////                }
////                case 3 -> {
////                    int index = consultUI.inputConsultationIndex();
////                    Consultation updated = consultCtrl.getConsultation(index);
////                    if (updated != null) {
////                        updated.setSymptoms(consultUI.inputSymptoms());
////                        updated.setDiagnosis(consultUI.inputDiagnosis());
////                        updated.setNotes(consultUI.inputNotes());
////                        updated.setNextAppointment(consultUI.inputNextAppointment());
////                        updated.setDurationMinutes(consultUI.inputDurationMinutes());
////                        updated.setConsultationFee(consultUI.inputConsultationFee());
////                        updated.setStatus(consultUI.inputStatus());
////                    }
////                }
////                case 4 -> {
////                    int index = consultUI.inputConsultationIndex();
////                    consultCtrl.deleteConsultation(index);
////                }
////            }
////        } while (choice != 0);
////    }
//
//    // ---------------- Treatment Menu ----------------
//    // ---------------- Treatment Menu ----------------
//private void treatmentMenu() {
//    int choice;
//    do {
//        choice = treatUI.getMenuChoice();
//        switch (choice) {
//            case 1 -> treatCtrl.displayAllTreatments();
//            case 2 -> {
//                // Link treatment to medicine
//                treatCtrl.displayAllMedicines();
//                int mIndex = treatUI.inputMedicineIndex();
//                Medicine m = treatCtrl.getMedicine(mIndex);
//
//                MedicalTreatment t = new MedicalTreatment(
//                        m,
//                        treatUI.inputDosage(),
//                        treatUI.inputDuration(),
//                        treatUI.inputInstructions(),
//                        treatUI.inputStartDate(),
//                        null, null // Later: link to patient/doctor if needed
//                );
//                treatCtrl.addTreatment(t);
//            }
//            case 3 -> {
//                int index = treatUI.inputTreatmentIndex();
//                MedicalTreatment updated = treatCtrl.getTreatment(index);
//                if (updated != null) {
//                    updated.setDosage(treatUI.inputDosage());
//                    updated.setDuration(treatUI.inputDuration());
//                    updated.setInstructions(treatUI.inputInstructions());
//                    updated.setStartDate(treatUI.inputStartDate());
//                }
//            }
//            case 4 -> {
//                int index = treatUI.inputTreatmentIndex();
//                treatCtrl.deleteTreatment(index);
//            }
//            case 5 -> medicineMenu(); // 🔹 New branch for medicine management
//        }
//    } while (choice != 0);
//}
//
//// ---------------- Medicine Sub-Menu ----------------
//private void medicineMenu() {
//    int choice;
//    do {
//        choice = treatUI.getMedicineMenuChoice();
//        switch (choice) {
//            case 1 -> treatCtrl.displayAllMedicines();
//            case 2 -> {
//                Medicine m = treatUI.inputMedicineDetails();
//                treatCtrl.addMedicine(m);
//            }
//            case 3 -> {
//                int index = treatUI.inputMedicineIndex();
//                Medicine updated = treatUI.inputMedicineDetails();
//                treatCtrl.updateMedicine(index, updated);
//            }
//            case 4 -> {
//                int index = treatUI.inputMedicineIndex();
//                treatCtrl.deleteMedicine(index);
//            }
//        }
//    } while (choice != 0);
//}
//
//    // ---------------- Helper to sync doctor edits ----------------
//    private void syncDoctorUpdates() {
//        for (int i = 0; i < consultCtrl.getSize(); i++) {
//            Consultation c = consultCtrl.getConsultation(i);
//            Doctor d = c.getDoctor();
//            Doctor refreshed = doctorCtrl.findDoctorById(d.getDoctorId());
//            if (refreshed != null) {
//                c.setDoctor(refreshed);
//            }
//        }
//    }
//}
