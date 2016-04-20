/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.databases;

import java.sql.SQLException;

/**
 *
 * @author Andrew
 */
public class ValidateLogin extends RDBImplCommand {
    
    private String email;
    private String password;
    private String sqlQuery = "SELECT * FROM USER WHERE UserEmail = ? AND UserPassword = ?";
    private String sqlQuery1 = "SELECT * FROM USER WHERE UserEmail = ?";
    
    
    public ValidateLogin(String email, String password){
        this.email = email;
        this.password = password;
    }
    
    public void queryDB() throws SQLException{
        try{
            statement = conn.prepareStatement(sqlQuery);
            statement.setString(1, email);
         //   password="46792755";
            statement.setString(2, password);
            
            resultSet = statement.executeQuery();
//            if(resultSet.next()){
//                int ID = resultSet.getInt("UserID");
//                int rank = resultSet.getInt("UserRank");
//                result = "" + ID + rank;
//            }
            processResult();
            if(result.toString().equalsIgnoreCase("Invalid login"))
            {
                statement = conn.prepareStatement(sqlQuery1);
                statement.setString(1, email);
                 if(resultSet.next()){
                  userFlag=true;    
                     
                 
            }
                 else
                     userFlag=false;
            }
        }
        catch(SQLException e){
        System.out.println("ValidateLogin Failed");
            conn.close();
        } finally {
            statement.close();
        }
    }
    
    @Override
    public void processResult(){
       
        try{
           result = "Invalid login";
            
            if(resultSet.next()){
                int ID = resultSet.getInt("UserID");
                int rank = resultSet.getInt("UserRank");
                this.result = "" + ID + rank;
                System.out.println("before result set"+result);
            }
            System.out.println("resulset"+result);
        }
        catch(SQLException e){
            System.out.println("ValidateLogin failed");
        }
    }
}
