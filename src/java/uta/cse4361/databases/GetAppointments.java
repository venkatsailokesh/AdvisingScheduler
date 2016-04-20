/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import uta.cse4361.businessobjects.Appointment;

/**
 *
 * @author Han
 */
public class GetAppointments extends RDBImplCommand {

    private String sqlQuery = "SELECT * FROM advising.APPOINTMENT\n" +
"WHERE YEAR(ApptDate) = YEAR(CURRENT_DATE - INTERVAL 1 MONTH) AND\n" +
" MONTH(ApptDate) = MONTH(CURRENT_DATE + INTERVAL 1 MONTH) OR\n" +
"  MONTH(ApptDate) = MONTH(CURRENT_DATE)";

    public GetAppointments() {
        super();
    }

    @Override
    public void queryDB() throws SQLException {
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

    @Override
    public void processResult() {
        try {
            result = new ArrayList<Appointment>();
            while (resultSet.next()) {
                Appointment appt = new Appointment();
                int id = resultSet.getInt("ApptID");
                Date date = resultSet.getDate("ApptDate");
                int sHour = resultSet.getInt("ApptStartHour");
                int sMinute = resultSet.getInt("ApptStartMin");
                int eHour = resultSet.getInt("ApptEndHour");
                int eMinute = resultSet.getInt("ApptEndMin");
                String type = resultSet.getString("ApptType");
                String description = resultSet.getString("Description");
                String sID = resultSet.getString("StudentID");
                String sName = resultSet.getString("StudentName");
                String sEmail = resultSet.getString("StudentEmail");
                String aName = resultSet.getString("AdvisorName");
                String sMajor = resultSet.getString("StudentMajor");
                String advisorEmail = resultSet.getString("AdvisorEmail");
                int advisorUserId = resultSet.getInt("AdvisorUserId");
                int studentUserId = resultSet.getInt("StudentUserId");
                appt.setApptID(id);
                appt.setAdvisorEmail(advisorEmail);
                appt.setAdvisorUserId(advisorUserId);
                appt.setStudentUserId(studentUserId);
                if (appt.initialize(sMajor, sName, sID, sEmail, aName, type, description, date, sHour, eHour, sMinute, eMinute)) {
                    ((ArrayList<Appointment>) result).add(appt);
                }
            }
        } catch (SQLException e) {
            System.out.println("GetAppointments Failed");
        }
    }

}
