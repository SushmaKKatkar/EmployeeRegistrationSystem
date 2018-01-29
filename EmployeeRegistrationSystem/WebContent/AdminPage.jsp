<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<button type="button" onclick="window.location.href='EmployeeRegister.html'">New Employee</button>
 <br>
 <br>
 <h3>Employee details</h3>
<%

	
	try{
      
        String connectionURL = "jdbc:mysql://localhost:3306/Employee";
        Connection connection = null;
        Statement statement = null;
    
        ResultSet rs = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, "root", "equest123");

        statement = connection.createStatement();
        String QueryString = "SELECT name,emp_id,doj,phone from Employee";

        rs = statement.executeQuery(QueryString);
        
%>

<TABLE cellpadding="10" border="1" style="background-color: #ffcccc;">
<TR style= "background-color:  #ff1a1a">
<TD>NAME</TD>
<TD>Employee ID</TD>
<TD>Date of Joining</TD>
<TD>Phone</TD>
</TR>

<%

while (rs.next()) {
	
%>

<TR>

<TD><%=rs.getString(1)%></TD>

<TD><%=rs.getString(2)%></TD>

<TD><%=rs.getString(3)%></TD>

<TD><%=rs.getString(4)%></TD>

</TR>

<% } %>

<%

// close all the connections.
rs.close();

statement.close();




} catch (Exception ex) {
	
%>

</font>

<font size="+3" color="red"></b>

<% 

out.println("Unable to connect to database.");

}

%>

</TABLE>
</font>
</body>
</html>