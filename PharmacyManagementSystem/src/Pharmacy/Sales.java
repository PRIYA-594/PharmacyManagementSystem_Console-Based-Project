package Pharmacy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Sales {
	int sales_id;
	int cust_id;
	int med_id;
	int count;
	float amt;
	String date;
	Sales()
	{
		amt=0.0f;
	}
	Sales(int id)
	{
		cust_id=id;
	}
	Sales(int med_id,int count,float amt)
	{
		this.med_id=med_id;
		this.count=count;
		this.amt=amt;
	}
	void setCustId(int id)
	{
		cust_id=id;
	}
	int getCustId()
	{
		return cust_id;
	}
	int getCount()throws Exception
	{
		return SalesDB.getCountDB();
	}
	float getAmt()throws Exception
	{
		return SalesDB.getAmtDB();
	}
	boolean salesExist(int mid)throws Exception
	{
		return SalesDB.salesExistDB(mid);
	}
	void billDetails()throws Exception
	{
		ArrayList<Sales> al = SalesDB.billDetailsDB(getCustId());
		System.out.println("Customer Id: "+getCustId());
		Sales.displayFormat();
		for(Sales sl:al)
		{
			sl.display();
		}
		System.out.println("The bill is processed successfully");
		
	}
	static void displayFormat()
	{
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("med_id\t\tcount\t\tPrice");
		System.out.println("-----------------------------------------------------------------------------------");
	}
	void display()
	{
		System.out.println(med_id+"\t"+count+"\t\t"+amt);
	}
	void salesReport()throws Exception
	{
		SalesDB.salesReportDB();	
	}
}
