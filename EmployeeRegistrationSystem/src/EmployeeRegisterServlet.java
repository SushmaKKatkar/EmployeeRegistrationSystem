import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/EmployeeRegisterServlet")
public class EmployeeRegisterServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		 PrintWriter out = response.getWriter();
		String emp_id = request.getParameter("emp_id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String doj = request.getParameter("doj");
		String phone = request.getParameter("phone");
		
		try{
			Connection con=ConnectionProvider.getCon();
	           java.sql.Statement st=con.createStatement();
	           st.executeUpdate("insert into Employee(emp_id,name,password,doj,phone) values('"+emp_id+"','"+name+"','"+password+"','"+doj+"',"+phone+")");
	       
	           st.close();
	           
	         response.sendRedirect("AdminPage.jsp"); 
			}catch(Exception e){
				out.println("error");
			}		
	}
		
}

