package dao;

import adt.*;
import entity.*;
import java.io.*;

/**
 * Unified DAO for Clinic Management System
 * Stores all entities (Doctor, Patient, Consultation, Treatment) in one file: clinic.dat
 */
public class ClinicDAO {

    private String fileName = "clinic.dat"; // unified file

    // A wrapper class to hold everything in one file
    public static class ClinicData implements Serializable {
        private static final long serialVersionUID = 1L;

        public ListInterface<Doctor> doctorList = new LinkedList<>();
        public ListInterface<Patient> patientList = new LinkedList<>();
        public ListInterface<Consultation> consultationList = new LinkedList<>();
        public ListInterface<MedicalTreatment> treatmentList = new LinkedList<>();
    }

    // Save all data into one file
    public void saveToFile(ClinicData data) {
        File file = new File(fileName);
        try {
            boolean isNew = !file.exists(); // check if first time saving

            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(data);
            ooStream.close();

            if (isNew) {
                System.out.println("[INFO] clinic.dat created successfully!");
            } else {
                System.out.println("[INFO] clinic.dat updated successfully!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("\n[ERROR] File not found when saving.");
        } catch (IOException ex) {
            System.out.println("\n[ERROR] Cannot save to file.");
        }
    }

    // Load all data from file
    public ClinicData retrieveFromFile() {
        File file = new File(fileName);
        ClinicData data = new ClinicData();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            data = (ClinicData) oiStream.readObject();
            oiStream.close();
            System.out.println("[INFO] clinic.dat loaded successfully!");
        } catch (FileNotFoundException ex) {
            System.out.println("\n[INFO] No existing file. Starting with empty data.");
        } catch (IOException ex) {
            System.out.println("\n[ERROR] Cannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\n[ERROR] Class not found while reading file.");
        }
        return data;
    }
}
