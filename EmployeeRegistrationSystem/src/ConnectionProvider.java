
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	static Connection con=null;
	static Connection conn=null;
	static{
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee","root","equest123");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Admin","root","equest123");
			}catch(Exception e){}
	}
	public static Connection getCon(){
		return con;
	}
}

