/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author User
 */
public class MedicalTreatment {
    private String medicine;
    private String dosage;
    private String duration;

    public MedicalTreatment(String medicine, String dosage, String duration) {
        this.medicine = medicine;
        this.dosage = dosage;
        this.duration = duration;
    }

    public String toString() {
        return "Medicine: " + medicine + ", Dosage: " + dosage + ", Duration: " + duration;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MedicalTreatment)) return false;
        MedicalTreatment other = (MedicalTreatment) obj;
        return this.medicine.equals(other.medicine)
            && this.dosage.equals(other.dosage)
            && this.duration.equals(other.duration);
    }
}

