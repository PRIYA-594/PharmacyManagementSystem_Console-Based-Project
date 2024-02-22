package Pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminDB {
	static boolean isValidAdmin(String email,String Password)throws Exception
	{
		Connection con = (Connection) DriverManager.getConnection(Db.url, "root", Db.passwordRoot);
		String querySelect = "select* from admin where a_email='"+email+"';";
		Statement st = (Statement) con.createStatement();
		ResultSet rs = st.executeQuery(querySelect);
		
		if(rs.next())
		{
			String password =rs.getString("password");
			if(password.equals(Password))
			{
				return true;
			}
			else
			{
				return false;
			}	
		}
		else
		{
			return false;
		}
	}
}
