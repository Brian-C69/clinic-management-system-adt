package control;

import adt.LinkedList;
import entity.Doctor;

public class MaintainDoctor {
    private LinkedList<Doctor> doctorList = new LinkedList<>();
    
    public void addDoctor(Doctor d){
        doctorList.add(d);
    }
    
//    public boolean updateDoctor(int index, Doctor updatedDoctor){
//        
//    }
    
    public void displayAllDoctors(){
        for (int i = 0; i < doctorList.size(); i++) {
            System.out.println(i + ": " + doctorList.get(i));
        }
    }
}
