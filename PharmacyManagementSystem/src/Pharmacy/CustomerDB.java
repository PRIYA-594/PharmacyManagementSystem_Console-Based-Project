package Pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerDB {
	int getCustId(String email)
	{
		try
		{
			String query = "select * from customer where C_email='"+email+"'";
			Statement st = (Statement)Db.con.createStatement();
			ResultSet rs = st.executeQuery(query);
			System.out.println(rs.next());
			return rs.getInt("c_id");
		}
		catch(Exception e)
		{
			return 0;
		}
	}
	static boolean isValidCust(String email,String Password)throws Exception
	{
		Connection con = (Connection) DriverManager.getConnection(Db.url, "root", Db.passwordRoot);
		String querySelect = "select* from customer where C_email='"+email+"';";
		Statement st = (Statement) con.createStatement();
		ResultSet rs = st.executeQuery(querySelect);
		if(rs.next())
		{

			String password =rs.getString("C_password");
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
	static boolean isExistEmail(String email)throws Exception
	{
		Connection con = (Connection) DriverManager.getConnection(Db.url, "root", Db.passwordRoot);
		Statement st = (Statement) con.createStatement();
		String query = "select C_email from customer where C_email = '"+email+"';";
		ResultSet res = st.executeQuery(query);
		return (res.next());
	}
	static void insertCust(String name,String gender,int age,String ph,String vemail,String Password)throws Exception
	{
		String query = "insert into customer(name,gender,age,phone_no,C_email,c_password) values('"+name+"','"+gender+"',"+age+",'"+ph+"','"+vemail+"','"+Password+"')";
		Db.UpdateQuery(query);
	}
}
