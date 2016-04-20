<%-- 
    Document   : index
    Created on : Sep 12, 2014, 2:12:40 PM
    Author     : Melissa
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="uta.cse4361.businessobjects.ApplicationControl"%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page contentType='text/html' pageEncoding='UTF-8'%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>UTA Advising</title>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1'>
    </head>

    <body>
        <jsp:include page='navigationbar.jsp' />
        <div id='wrapper'>
            <jsp:include page='header.jsp' />
             <% 
                if (session.getAttribute("passwordExpired") != null && session.getAttribute("passwordExpired").toString().equals("true")){
                    response.sendRedirect("EditAccount.jsp");
                }
                    int rank = -1;
                    int sessionid = -1;
                    if(!(session.getAttribute("id") == null)){
                    sessionid = Integer.parseInt((String)session.getAttribute("id"));
                }
                    if(!(session.getAttribute("rank") == null)){
                    rank = Integer.parseInt((String)session.getAttribute("rank"));
                }
             %>
            <table class='centerthis' style='margin: 0 auto;'>
                <tr>
                    <%
                            if(rank == -1){
                                out.print("<td style='width: 640px'>");
                            }
                            %>
                    <div id='leftAccordion'>
                       
                        <h3>Current Users</h3>
                        <div>
                            <%
                            if (rank == -1){
//                                out.print("Would you like to check on your current schedule?<br><br>"
                                out.print( " <input type='submit' value='Login to your account' id='loginBtn' class='btn btn-default'>"
                                        );
                            }
                            if (rank == 1){
                                out.print("Welcome administrator.");
                            }
                            if (rank == 0 || rank == 2){
                                out.print("Welcome faculty member.");
                            }
                            if (rank == 3){
                                out.print("Welcome Student.");
                            }
                            %>
                  
                        </div>

                    </div>
                            <%
                            if(rank == -1){
                                out.print("</td>"
                                        + "<td style='width: 640px'>"
                                        + "<div id='rightAccordion'>"
                                        + "<h3>Student</h3>"
                                        + "<div>"
                                        + "Would you like to schedule an appointment with an advisor?<br><br>"
                                        + "<form action='schedule.jsp'>"
                                        + "<input type='submit' value='Make an appointment' id='scheduleBtn' class='btn btn-default'>"
                                        + "</form>"
                                        + "<br>"
                                        + "<input type='submit' value='Request Student Account' id='requestBtn' class='btn btn-default'>"
                                        + "</form>"
                                        + "</div>"
                                        + "</div>"
                                        + "</td>");
                            }
                            %>
                </tr>
            </table>
               
        </div>

    </body>
    <jsp:include page='footer.jsp' />

    <script type='text/javascript' src='js/index.js'></script>
</html>
