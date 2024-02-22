package Pharmacy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MedicinesDB {
	static void insertMediineDB(String medCat,String name, String description,float price,int count,String mfg,String exp,int sup_id)throws Exception
	{
		String query = "insert into medicines(med_cat,name,description,price,count,mfg,exp,sup_id) values('"+medCat+"','"+name+"','"+description+"',"+price+","+count+",'"+mfg+"','"+exp+"',"+sup_id+")";
		Db.UpdateQuery(query);
	}
	static boolean updateQy(String name,String field,String cat)
	{
		try {
			String query="update medicines set "+field+"='"+cat+"' where name='"+name+"' or med_id='"+name+"'";
			Db.UpdateQuery(query);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	static boolean deleteMedicineDB(String str)
	{
		try {
			String query="delete from medicines where name ='"+str+"' or med_id='"+str+"'";
			Db.UpdateQuery(query);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	static ArrayList<Medicines> expMedDB()throws Exception
	{
		String query = "select * from medicines where datediff(exp,CURDATE())<=20;";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<Medicines> medi = new ArrayList<>();
		while(rs.next())
		{
			int id = rs.getInt("med_id");
			String name = rs.getString("name");
			String des=rs.getString("description");
			String medCat = rs.getString("med_cat");
			float price = rs.getFloat("price");
			int count = rs.getInt("count");
			medi.add(new Medicines(id,name,des,medCat,price,count));
		}
		return medi;
	}
	static void delExpDB()throws Exception
	{
		String query = "select * from medicines where datediff(exp,curdate())<=15";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next())
		{
			String query1="update set count=count-"+rs.getInt("count")+" from medicines where med_id="+rs.getInt("med_id")+"";
			Db.UpdateQuery(query1);
		}
	}
	static ArrayList<Medicines> showDetailsDB(String str)throws Exception
	{
		Statement st = (Statement)Db.con.createStatement();
		String query="select * from medicines where name = '"+str+"' or med_id='"+str+"'";
		ResultSet rs = st.executeQuery(query);
		ArrayList<Medicines> medi = new ArrayList<>();
		while(rs.next())
		{
			int id = rs.getInt("med_id");
			String name = rs.getString("name");
			String des=rs.getString("description");
			String medCat = rs.getString("med_cat");
			float price = rs.getFloat("price");
			int count = rs.getInt("count");
			medi.add(new Medicines(id,name,des,medCat,price,count));
		}
		return medi;
	}
	static ArrayList<Medicines> outOfStockDB()throws Exception
	{
		String query = "select * from medicines where count<10";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<Medicines> medi = new ArrayList<>();
		while(rs.next())
		{
			int id = rs.getInt("med_id");
			String name = rs.getString("name");
			String des=rs.getString("description");
			String medCat = rs.getString("med_cat");
			float price = rs.getFloat("price");
			int count = rs.getInt("count");
			medi.add(new Medicines(id,name,des,medCat,price,count));
		}
		return medi;
	}
	static ArrayList<Medicines> listOfMedicineDB()throws Exception
	{
		String query="select * from medicines where datediff(exp,CURDATE())>20";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<Medicines> medi = new ArrayList<>();
		while(rs.next())
		{
			int id = rs.getInt("med_id");
			String name = rs.getString("name");
			String des=rs.getString("description");
			String medCat = rs.getString("med_cat");
			float price = rs.getFloat("price");
			int count = rs.getInt("count");
			medi.add(new Medicines(id,name,des,medCat,price,count));

		}
		return medi;
	}
	static boolean isValidMedDB(int med_id)
	{
		try {
			String query = "select * from medicines where med_id='"+med_id+"'";
			Statement st = (Statement)Db.con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(Exception e)
		{
			return false;
		}
	}
	static float getMedPriceDB(int med_id)throws Exception{
		String query = "select * from medicines where med_id='"+med_id+"'";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		float pr = 0.0f;
		while(rs.next())
		{
			pr=rs.getFloat("price");
		}
		return pr;
	}
	static String getMedNameDB(int med_id)throws Exception{
		String query = "select * from medicines where med_id='"+med_id+"'";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		String str="";
		while(rs.next())
		{
			str=rs.getString("name");
		}
		return str;
	}
	static boolean sufficientDB(int med_id,int count)throws Exception
	{
		String query = "select * from medicines where med_id='"+med_id+"'";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		int c=0;
		while(rs.next())
		{
			c =rs.getInt("count");
		}
		
		if(count>c)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	static void updateMedicineOrdDB(int count,int med_id)throws Exception
	{
		String str = "update medicines set count=count+"+count+" where med_id="+med_id+"";
		Db.UpdateQuery(str);
	}
	static String getMedicineNameDB(int med_id)throws Exception
	{
		String query = "select * from medicines where med_id="+med_id+"";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		if(rs.next())
		{
			return rs.getString("name");
		}
		else
		{
			System.out.println(".....");
			return null;
		}
	}
	static int getSupIdFromMedIdDB(int id)throws Exception
	{
		String str = "select * from medicines where med_id="+id+"";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(str);
		if(rs.next())
		{
			return rs.getInt("sup_id");
		}
		else
		{
			System.out.println("Invalid medicine id");
			return 0;
		}
	}
}

