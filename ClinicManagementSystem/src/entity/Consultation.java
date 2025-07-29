/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author User
 */
public class Consultation {
    private String symptoms;
    private String notes;
    private String nextAppointment;

    public Consultation(String symptoms, String notes, String nextAppointment) {
        this.symptoms = symptoms;
        this.notes = notes;
        this.nextAppointment = nextAppointment;
    }

    public String toString() {
        return "Symptoms: " + symptoms + ", Notes: " + notes + ", Next Appointment: " + nextAppointment;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Consultation)) return false;
        Consultation other = (Consultation) obj;
        return this.symptoms.equals(other.symptoms)
            && this.notes.equals(other.notes)
            && this.nextAppointment.equals(other.nextAppointment);
    }
}

