	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	@SuppressWarnings("serial")
	@WebServlet("/LoginServlet")
	public class LoginServlet extends HttpServlet {
		protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
			
			 PrintWriter out = response.getWriter();
			 
			 
			String emp_id = request.getParameter("emp_id");
			String password = request.getParameter("password");
			String type = request.getParameter("type");
			
			String userdbEmp_id;
			String userdbPsw;
			
			if(type.equals("Employee")) {
				 
			try{
				Connection con=ConnectionProvider.getCon();
			    out.println(con);
				PreparedStatement ps=con.prepareStatement("select * from Employee where emp_id=? and password=?");
				ps.setString(1,emp_id);
				ps.setString(2,password);
				ResultSet rs=ps.executeQuery();
				
				if(rs.next()){ 	
					userdbEmp_id = rs.getString("emp_id");
					userdbPsw = rs.getString("password");
					if(emp_id.equals(userdbEmp_id) && password.equals(userdbPsw)){
						 HttpSession session = request.getSession();
				            session.setAttribute("emp_id", emp_id);
						response.sendRedirect("EmployeePage.html"); 
					}
				}
				else	
				out.print("Sorry, Employee ID or password error");
				response.sendRedirect("LoginForm.html");
				rs.close();
				ps.close(); 
				
				}catch(Exception e){
					out.print("Sorry, Employee ID or password error");
				}
			}
			else if(type.equals("Admin")) {
				try{
					Connection conn=ConnectionProvider.getCon();
					
					PreparedStatement ps=conn.prepareStatement("select * from Admin where emp_id=? and password=?");
					ps.setString(1,emp_id);
					ps.setString(2,password);
					ResultSet rs=ps.executeQuery();
					
					if(rs.next()){ 	
						userdbEmp_id = rs.getString("emp_id");
						userdbPsw = rs.getString("password");
						if(emp_id.equals(userdbEmp_id) && password.equals(userdbPsw)){
						response.sendRedirect("AdminPage.jsp"); 
						}
					}
					else {
					out.print("Sorry, Employee ID or password error");
					response.sendRedirect("LoginForm.html");
					rs.close();
					ps.close(); }
					}catch(Exception e){
						out.print("Sorry, Invalid Employee ID or password ");
					}
			}
		}
	}

