package Pharmacy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDB {
	static boolean placeOrderDB(int med_id,int sup_id,int count)
	{
		try
		{
			String qy = "INSERT INTO `order` (med_id,sup_id,state,count) VALUES ("+med_id+","+sup_id+",0,"+count+")";
			Db.UpdateQuery(qy);
			return true;
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
	}
	static ArrayList<Order> orderDetailsDB()throws Exception
	{
		String qy ="select * from `order` where state=0";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(qy);
		ArrayList<Order> al = new ArrayList<>();
		while(rs.next())
		{
			al.add(new Order(rs.getInt("order_id"),rs.getInt("med_id"),rs.getInt("sup_id"),rs.getInt("count")));
		}
		return al;
	}
	static void updateStateDB(int ord_id)throws Exception
	{
		String str = "update `order` set state=1 where order_id="+ord_id+"";
		Db.UpdateQuery(str);
	}
	static boolean isValidOrdIdDB(int id)throws Exception
	{
		String qy ="select * from `order` where order_id="+id+"";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(qy);
		if(rs.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	static int getOrdCountDB(int id)throws Exception
	{
		String qy ="select * from `order` where order_id="+id+"";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(qy);
		if(rs.next())
		{
			return rs.getInt("count");
		}
		else
		{
			return 0;
		}
	}
	static int getOrdMidDB(int id)throws Exception
	{
		String qy ="select * from `order` where order_id="+id+"";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(qy);
		if(rs.next())
		{
			return rs.getInt("med_id");
		}
		else
		{
			return 0;
		}
	}
}
