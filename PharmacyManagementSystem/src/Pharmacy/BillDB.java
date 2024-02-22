package Pharmacy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class BillDB {
	static void insertQyDB(int cust_id,float cost,int c)throws Exception
	{
		String query = "insert into bill(cust_id,amount,date,count,processed) values("+cust_id+","+cost+",CURDATE(),"+c+",0)";
		Db.UpdateQuery(query);
	}
	static void updateQyDB(int c,int id)throws Exception
	{
		String query="update medicines set count=count-"+c+" where med_id="+id+"";
		Db.UpdateQuery(query);
	}
	static void purchaseReportDB()throws Exception
	{
		String str = "select sum(amount) as 'tot_profitday' from bill where date=curdate()";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(str);
		while(rs.next())
		{
			System.out.println("Today's profit is : "+rs.getFloat("tot_profitday"));
		}
		
	}
	static int getCidDB(int id)throws Exception
	{
		String query ="select * from bill where bill_id="+id+"";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
		{
			return rs.getInt("cust_id");
		}
		else
		{
			
			return 0;
		}
		
	}
	static void updateprocessDB(int bill_id)throws Exception
	{
		String query="update bill set processed=1 where bill_id="+bill_id+"";
		Db.UpdateQuery(query);
	}
	static ArrayList<Bill> billingDB()throws Exception
	{
		String query="select* from bill where processed=0";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		Scanner s = new Scanner(System.in);
		ArrayList<Bill> billing = new ArrayList<>();
		while(rs.next())
		{
			billing.add(new Bill(rs.getInt("bill_id"),rs.getInt("cust_id"),rs.getInt("count"),rs.getFloat("amount"),rs.getString("date")));
		}
		return billing;
	}
}

