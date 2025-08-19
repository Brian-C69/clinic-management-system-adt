package entity;

import adt.LinkedList;
import adt.ListInterface;
import java.io.Serializable;

public class ClinicData implements Serializable {
    private static final long serialVersionUID = 1L;

    private ListInterface<Doctor> doctors = new LinkedList<>();
    private ListInterface<Patient> patients = new LinkedList<>();
    private ListInterface<Consultation> consultations = new LinkedList<>();
    private ListInterface<MedicalTreatment> treatments = new LinkedList<>();

    public ListInterface<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ListInterface<Doctor> doctors) {
        this.doctors = doctors;
    }

    public ListInterface<Patient> getPatients() {
        return patients;
    }

    public void setPatients(ListInterface<Patient> patients) {
        this.patients = patients;
    }

    public ListInterface<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(ListInterface<Consultation> consultations) {
        this.consultations = consultations;
    }

    public ListInterface<MedicalTreatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(ListInterface<MedicalTreatment> treatments) {
        this.treatments = treatments;
    }
}
