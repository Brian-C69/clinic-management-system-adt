/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import control.*;
import entity.*;
/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args) {
        // Consultation Module
        ConsultationManager cm = new ConsultationManager();
        cm.addConsultation(new Consultation("Fever", "Take rest", "2025-08-05"));
        cm.addConsultation(new Consultation("Cough", "Prescribed antibiotics", "2025-08-10"));

        System.out.println("All Consultations:");
        cm.displayAllConsultations();

        // Medical Treatment Module
        TreatmentManager tm = new TreatmentManager();
        tm.addTreatment(new MedicalTreatment("Paracetamol", "500mg", "5 days"));
        tm.addTreatment(new MedicalTreatment("Cough Syrup", "10ml", "7 days"));

        System.out.println("\nAll Medical Treatments:");
        tm.displayAllTreatments();
    }
}

