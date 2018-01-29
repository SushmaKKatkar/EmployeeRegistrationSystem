import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
@WebServlet("/CheckOutPage")
public class CheckOutPage extends HttpServlet  {
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		
	   PrintWriter out = response.getWriter();
	   
	   try{
	   
			HttpSession session = request.getSession();
		       String emp_id = (String)session.getAttribute("emp_id");
		        out.println(emp_id);
				HttpSession sessions = request.getSession();
				int lastGenKey = (int) sessions.getAttribute("lastGenKey");
				System.out.println("jhfhdfhgff"+lastGenKey);
		
				
				//doPost(request, response);
			    Connection con=ConnectionProvider.getCon();
			
	            java.sql.Statement st=con.createStatement();
	           
	           
	            java.util.Date utilDate = new java.util.Date();
	           
	           
	           
	            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	            Date date = new Date();
	            System.out.println(dateFormat.format(date)); //2016/11/16 12:08:43
	           

	          
	           
	            out.print(emp_id);
	            out.println(new java.sql.Date(System.currentTimeMillis()));
	            st.executeUpdate("update LoginData SET out_time='"+dateFormat.format(date)+"' where id ='"+lastGenKey+"';");
				System.out.println("last key"+lastGenKey);

	          
	           st.close();
	           out.println("checked out successfully");
	         //response.sendRedirect("AdminPage.jsp"); 
			}catch(Exception e){
				out.println("error"+e);
			}
	   
    }
}
