/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import uta.cse4361.businessobjects.AvailableSlot;
import uta.cse4361.businessobjects.Slot;

/**
 *
 * @author Han
 */
public class GetSlot extends RDBImplCommand{
    
    String sqlQuery = "SELECT * FROM SLOT";
    
    public GetSlot(){}
    
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
                int id = resultSet.getInt(1);
                java.util.Date d = new java.util.Date(((java.sql.Date)resultSet.getDate(2)).getTime());
                int startHour = resultSet.getInt(3);
                int startMin = resultSet.getInt(4);
                int advisorId = resultSet.getInt(5);
                Slot s = new AvailableSlot(d, startHour, startMin, id);
                s.setAdvisorUserId(advisorId);
                ((ArrayList<Slot>) result).add(s);
            }
        } catch (SQLException e) {
            System.out.println("Get Slots Failed");
            result = null;
        }
    }
    
}
