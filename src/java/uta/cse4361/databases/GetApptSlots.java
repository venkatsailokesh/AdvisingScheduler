/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import uta.cse4361.businessobjects.AppointmentSlot;
import uta.cse4361.businessobjects.Slot;

/**
 *
 * @author Andrew
 */
public class GetApptSlots extends RDBImplCommand{
    
    String sqlQuery = "SELECT * FROM APPTSLOT";
    
    public GetApptSlots(){}
    
    public void queryDB() throws SQLException{
        try {
            statement = conn.prepareStatement(sqlQuery);
            resultSet = statement.executeQuery();
            processResult();
        } catch (SQLException e) {
            System.out.println("failed");
            conn.close();
        } finally {
            statement.close();
        }
    }
    public void processResult(){
        try {
            result = new ArrayList<Slot>();
            while (resultSet.next()) {
                resultSet.getInt(1);
                java.util.Date d = new java.util.Date(((java.sql.Date)resultSet.getDate(2)).getTime());
                int startHour = resultSet.getInt(3);
                int startMin = resultSet.getInt(4);
                int advisorId = resultSet.getInt(5);
                int id = resultSet.getInt(6);
                Slot s = new AppointmentSlot(id, d, startHour, startMin);
                s.setAdvisorUserId(advisorId);
                ((ArrayList<Slot>) result).add(s);
            }
        } catch (SQLException e) {
            System.out.println("Get Avail Slots Failed");
            result = null;
        }
    }
}
