
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
@WebServlet("/CheckInPage")
public class CheckInPage extends HttpServlet  {
	
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
	   PrintWriter out = response.getWriter();
	   int lastGenKey=-1;
	   try{
	   
			HttpSession session = request.getSession();
		       String emp_id = (String)session.getAttribute("emp_id");
		        out.println(emp_id);
		        
			 
			//doPost(request, response);
			Connection con=ConnectionProvider.getCon();
			
	           java.sql.Statement st=con.createStatement();
	           java.util.Date utilDate = new java.util.Date();
	           
	           
	           
	           DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	           Date date = new Date();
	           System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
	           
	         
	           
	           out.print(emp_id);
	           out.println(new java.sql.Date(System.currentTimeMillis()));
	           st.executeUpdate("INSERT INTO LoginData(emp_id, date,in_time) VALUES ('"+emp_id+"','"+new java.sql.Date(utilDate.getTime())+"','"+ dateFormat.format(date)+"')", Statement.RETURN_GENERATED_KEYS);
	          
	           ResultSet rs = st.getGeneratedKeys();
	            while (rs.next()) {
	            	
	            lastGenKey = rs.getInt(1);
	                System.out.println("Key returned from getGeneratedKeys():"
	                        + rs.getInt(1));
	            } 
	           HttpSession sessions = request.getSession();
	            sessions.setAttribute("lastGenKey", lastGenKey);
	             System.out.println("sessions"+lastGenKey);

	           st.close();
	           
	         response.sendRedirect("EmployeePage.html"); 
			}catch(Exception e){
				out.println("error"+e);
			}
	}
}

