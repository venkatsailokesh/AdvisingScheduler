/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import uta.cse4361.businessobjects.AdvisorAccount;
import uta.cse4361.databases.DatabaseManager;
import uta.cse4361.databases.RelationalDatabaseImpl;
import uta.cse4361.interfaces.Constants;

/**
 *
 * @author Nabin
 */
public class CreateAdvisorAccountBean implements Constants{
    private String name = null;
    private String email= null;
    private String studentID = null;
    private String department = null;
    private int ID= 0;
    private String tempPassword = null;
    private int rank = 0;
    private int numberOfDays = 365;

    public CreateAdvisorAccountBean() {
    }

    
    public String Advisor(){
       String returnMessage = SUCCESS_MESSAGE;
       
       AdvisorAccount AA = new AdvisorAccount();
       boolean a = AA.initialize(this.name, this.email, this.department, this.tempPassword, this.rank);
       if (a == false)
           return this.CREATE_ADVISOR_FAIL;
       DatabaseManager dm = new DatabaseManager();
       returnMessage = dm.register(AA);
       return returnMessage;
    }
    
    public String Student(){
        String returnMessage = SUCCESS_MESSAGE;
        AdvisorAccount AA = new AdvisorAccount();
        boolean s = AA.initialize(this.name, this.email, this.department, this.studentID,
                this.tempPassword, this.numberOfDays);
        if (s == false){
            System.err.print(s);
            return this.CREATE_ADVISOR_FAIL;
        }
        DatabaseManager dm = new DatabaseManager();
        returnMessage = dm.register(AA);
        return returnMessage;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public int getID() {
        return ID;
    }

    public String getTempPassword() {
        return tempPassword;
    }

    public int getRank() {
        return rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

}
