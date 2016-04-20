/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

import sun.security.util.Password;

/**
 *
 * @author Andrew
 */
public class AdvisorAccount {
    
    private String name;
    private String email;
    private String department;
    private String studentID;
    private int ID;
    private String tempPassword;
    private int rank;
    private int numberOfDays;

    public AdvisorAccount(){
        this.studentID = "";
        this.numberOfDays = 365;
    }
    
    public boolean initialize(String name, String email, String department, String tempPassword, int rank){
        boolean result = true;
        
        if(name != null && !name.isEmpty() && 
                email != null && !email.isEmpty() && 
                department != null && !department.isEmpty() && 
                tempPassword != null && !tempPassword.isEmpty())
        {
            this.name = name;
            this.email = email;
            this.department = department;
            this.studentID = "0";
            this.tempPassword = hashPassword(tempPassword);
            this.rank = rank;
        }
        else
        {
            result = false;
        }
        
        return result;
    }
    
     public boolean initialize(String name, String email, String department, String studentID, String tempPassword){
        boolean result = true;
        if(name != null && !name.isEmpty() && 
                email != null && !email.isEmpty() && 
                department != null && !department.isEmpty() && 
                tempPassword != null && !tempPassword.isEmpty()&& 
                studentID != null && !studentID.isEmpty())
        {
            this.name = name;
            this.email = email;
            this.department = department;
            //this.tempPassword = hashPassword(tempPassword);
            this.tempPassword = tempPassword;
            this.studentID = studentID;
            this.rank = 3;
        }
        else
        {
            result = false;
        }
        
        return result;
    }

     public boolean initialize(String name, String email, String department, String studentID, String tempPassword,
                               int numberofDays){
        boolean result = true;
        if(name != null && !name.isEmpty() &&
                email != null && !email.isEmpty() &&
                department != null && !department.isEmpty() &&
                tempPassword != null && !tempPassword.isEmpty()&&
                studentID != null && !studentID.isEmpty())
        {
            this.name = name;
            this.email = email;
            this.department = department;
            this.tempPassword = hashPassword(tempPassword);
            this.studentID = studentID;
            this.numberOfDays = numberofDays;
            this.rank = 3;
        }
        else
        {
            result = false;
        }

        return result;
    }

    public boolean initialize(String name, String email, String department, String studentid, int ID, int rank){
        boolean result = true;

        if(name != null && !name.isEmpty() && 
                email != null && !email.isEmpty() && 
                department != null && !department.isEmpty())
        {
            this.name = name;
            this.email = email;
            this.department = department;
            this.studentID = studentid;
            this.rank = rank;
            this.ID = ID;
        }
        else
        {
            result = false;
        }
        
        return result;
    }
    
    public boolean initialize(String name, String email, String department, int ID, int rank){
        boolean result = true;
        
        if(name != null && !name.isEmpty() && 
                email != null && !email.isEmpty() && 
                department != null && !department.isEmpty())
        {
            this.name = name;
            this.email = email;
            this.department = department;
            this.rank = rank;
            this.ID = ID;
        }
        else
        {
            result = false;
        }
        
        return result;
    }

    
    public static String hashPassword(String tempPassword)
    {
        int hash = tempPassword.hashCode();
        return Integer.toString(hash);
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setDepartment(String department){
        this.department = department;
    }
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public void setTempPassword(String tempPassword){
        this.tempPassword = tempPassword;
    }
    
    public void setRank(int rank){
        this.rank = rank;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getDepartment(){
        return department;
    }
    
    public int getID(){
        return ID;
    }
    
    public String getTempPassword(){
        return tempPassword;
    }
    
    public int getRank(){
        return rank;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
