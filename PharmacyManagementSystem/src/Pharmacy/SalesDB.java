package Pharmacy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SalesDB {
	static int getCountDB()throws Exception
	{
		String query="select* from sales where date=CURDATE()";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs.getInt("count");
	}
	static float getAmtDB()throws Exception
	{
		String query="select* from sales where date=CURDATE()";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs.getInt("total_amount");
	}
	static boolean salesExistDB(int mid)throws Exception
	{
		String query="select* from sales where date=CURDATE() and med_id="+mid+"";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs.next();
	}
	static void salesQyDB(int c,int mid,float price,int cust_id)throws Exception
	{
		String query ="insert into sales(med_id,count,date,total_amount,cust_id,fl) values("+mid+","+c+",CURDATE(),"+price+","+cust_id+",0)";
		Db.UpdateQuery(query);	
		
	}
	static void salesReportDB()throws Exception
	{
		String query="select count(sales_id) as 'countOfSales' from sales where date=CURDATE()";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
		{
			System.out.println("No Of sales (today):"+rs.getInt("countOfSales"));
		}
		
	}
	static ArrayList<Sales> billDetailsDB(int cust_id)throws Exception
	{
		String query="select * from sales where cust_id="+cust_id+" and fl=0";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<Sales> al = new ArrayList<>();
		
		while(rs.next())
		{
			int id = rs.getInt("med_id");
			int sid = rs.getInt("sales_id");
			query = "update sales set fl=1 where sales_id="+sid+"";
			Db.UpdateQuery(query);
			int count = rs.getInt("count");
			float amt = rs.getFloat("total_amount");
			al.add(new Sales(id,count,amt));
		}
		return al;
	}
}
