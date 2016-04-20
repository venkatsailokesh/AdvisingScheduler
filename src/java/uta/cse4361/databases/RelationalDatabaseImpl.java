/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.businessobjects.ApplicationControl;
import uta.cse4361.businessobjects.Appointment;
import uta.cse4361.businessobjects.Slot;

/**
 *
 * @author Han
 */
public class RelationalDatabaseImpl implements DatabaseImpInterface{
    
    public RelationalDatabaseImpl() {
        
    }

    @Override
    public String saveSlots(ArrayList<Slot> slots) {
        RDBImplCommand saveSlots = new SaveSlots(slots);
        saveSlots.execute();
        ArrayList<Integer> savedSlots = (ArrayList<Integer>)saveSlots.getResult();
        if(savedSlots.size() == slots.size()){
            return "";
        }
        return "failed";
    }

    @Override
    public String saveAppointment(Appointment appt) {
        RDBImplCommand saveAppointment = new SaveAppointment(appt);
        saveAppointment.execute();
        return (String)saveAppointment.getResult();
    }

    @Override
    public String modifyAppointment(int id, Appointment appt) {
        if (appt == null) {
            RDBImplCommand deleteAppointment = new DeleteAppointment(id);
            deleteAppointment.execute();
            return (String)deleteAppointment.getResult();
        } else {
            RDBImplCommand editAppointment = new EditAppointment(id, appt);
            editAppointment.execute();
            return (String)editAppointment.getResult();
        }
    }

    @Override
    public String modifySlot(Date d, int startHour, int endHour, int startMin, int endMin, int slotID) {
        RDBImplCommand modifySlot = new DeleteSlot(d, startHour, endHour, startMin, endMin, slotID);
        modifySlot.execute();
        return (String) modifySlot.getResult();
    }

    @Override
    public ArrayList<Appointment> getAppointments() {
        RDBImplCommand getAppointments = new GetAppointments();
        getAppointments.execute();
        return (ArrayList<Appointment>) getAppointments.getResult();
        
    }

    @Override
    public Appointment getAppointment(int apptID) {
        RDBImplCommand getAppointment = new GetAppointment(apptID);
        getAppointment.execute();
        return (Appointment) getAppointment.getResult();
    }
    
    @Override
    public ArrayList<Slot> getSlot(){
        RDBImplCommand getSlots = new GetSlot();
        getSlots.execute();
        return (ArrayList<Slot>) getSlots.getResult();
    }
    @Override
    public ArrayList<Slot> getApptSlots(){
        RDBImplCommand getApptSlot = new GetApptSlots();
        getApptSlot.execute();
        return (ArrayList<Slot>) getApptSlot.getResult(); 
    }
    
    @Override
    public ArrayList<Slot> getAvailSlots(){
        RDBImplCommand getAvailSlot = new GetAvailSlots();
        getAvailSlot.execute();
        return(ArrayList<Slot>) getAvailSlot.getResult();
    }
    
    @Override
    public ArrayList<Slot> getAvailSlotsByTime(Date d, int startHour, int endHour, int startMin, int endMin){
        RDBImplCommand getAvailSlot = new GetAvailSlotsByTime(d, startHour, endHour, startMin, endMin);
        getAvailSlot.execute();
        return(ArrayList<Slot>) getAvailSlot.getResult();
        //return null;
    }
    
    @Override
    public String register(AdvisorAccount aa){
        RDBImplCommand register = new RegisterAdvisor(aa);
        register.execute();
        return (String)register.getResult();
    }

    @Override
    public String updateStudent(AdvisorAccount aa){
        RDBImplCommand updateStudent = new UpdateStudent(aa);
        updateStudent.execute();
        return (String)updateStudent.getResult();
    }

    @Override
    public String updateAdvisor(AdvisorAccount aa){
        RDBImplCommand updateAdvisor = new UpdateAdvisor(aa);
        updateAdvisor.execute();
        return (String)updateAdvisor.getResult();
    }

    @Override
    public String deleteAdvisor(String aa){
        RDBImplCommand deleteAdvisor = new DeleteAdvisor(aa);
        deleteAdvisor.execute();
        return (String)deleteAdvisor.getResult();
    }

    @Override
    public boolean checkPasswordExpired(String email, int numberOfDays){
        RDBImplCommand checkPasswordExpired = new CheckPasswordExpired(email,
                numberOfDays);
        checkPasswordExpired.execute();
        return (Boolean)checkPasswordExpired.getResult();
    }
    
    @Override
    public String validate(String email, String password){
        
        RDBImplCommand validate = new ValidateLogin(email, password);
        validate.execute();
        return (String)validate.getResult();
    }
    
    @Override
    public AdvisorAccount getAccount(String email){
        RDBImplCommand getAccount = new GetAdvisor(email);
        getAccount.execute();
        return (AdvisorAccount)getAccount.getResult();
    }

    @Override
    public ArrayList<String> getAdvisorEmails(){

        RDBImplCommand getAdvisorEmails = new GetAdvisorEmails();
        getAdvisorEmails.execute();
        return (ArrayList<String>) getAdvisorEmails.getResult();
    }

    @Override
    public ApplicationControl getApplicationControl(){

        RDBImplCommand getApplicationControl = new GetApplicationControl();
        getApplicationControl.execute();
        return (ApplicationControl) getApplicationControl.getResult();
    }

    @Override
    public boolean saveApplicationControl(ApplicationControl applicationControl){

        RDBImplCommand saveApplicationControl = new SaveApplicationControl(applicationControl);
        saveApplicationControl.execute();
        return (Boolean) saveApplicationControl.getResult();
    }

    @Override
    public List<AdvisorAccount> getAccounts() {
        RDBImplCommand getAccounts = new GetAdvisors();
        getAccounts.execute();
        return (List<AdvisorAccount>) getAccounts.getResult();
    }
    @Override
    public ArrayList<String> GetSearchDataFromDatabase(String searchdata)
    {
        RDBImplCommand gsd = new GetSearchedData(searchdata);
        gsd.execute();
        return (ArrayList<String>)gsd.getResult(); 
    }

    @Override
    public String insertfeedback(String emailid, String feedback) 
    {
        InsertFeedBack insertfeedback=new InsertFeedBack(emailid, feedback);
        insertfeedback.execute();
        return "done";
    }

    @Override
    public String insertbug(String emailid, String bug) 
    {
        InsertBug insertbug=new InsertBug(emailid,bug);
        insertbug.execute();
        return "done";
    }
}
