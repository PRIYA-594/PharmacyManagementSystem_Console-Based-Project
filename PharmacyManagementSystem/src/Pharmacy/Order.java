package Pharmacy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
	int ord_id;
	int med_id;
	int sup_id;
	int count;
	int state;
	Order()
	{
		
	}
	
	Order(int ord_id,int med_id,int sup_id,int count)
	{
		this.ord_id=ord_id;
		this.med_id=med_id;
		this.sup_id=sup_id;
		this.count=count;
	}
	Order(int med_id,int sup_id,int count)
	{
		this.med_id=med_id;
		this.sup_id=sup_id;
		this.count=count;
	}
	int getCount()
	{
		return count;
	}
	int getState()
	{
		return state;
	}
	int getMedId()
	{
		return med_id;
	}
	int getSupId()
	{
		return sup_id;
	}
	int getOrderId()
	{
		return ord_id;
	}
	void setOrderId(int id)
	{
		this.ord_id=id;
	}
	boolean placeOrder()
	{
		return OrderDB.placeOrderDB(med_id, sup_id, count);
	}
	void orderDetails()throws Exception
	{
		ArrayList<Order> al = OrderDB.orderDetailsDB();
		Order.displayFormat();
		for(Order od:al)
		{
			od.display();
		}
	}
	void updateOrderDetails()throws Exception
	{
		Scanner s= new Scanner(System.in);
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("enter the order id");
		Order od = new Order();
		od.setOrderId(s.nextInt());
		if(isValidOrdId(od.getOrderId()))
		{
			System.out.println("Does the ordered medicine delevired(yes/no)");
			String st = s.next();
			st=st.toLowerCase();
			String str="";
			if(st.equals("yes"))
			{
				OrderDB.updateStateDB(od.getOrderId());
				int count = getOrdCount(od.getOrderId());
				int mid = getOrdMid(od.getOrderId());
				Medicines me = new Medicines(mid,count);
				me.updateMedicineOrd();
				System.out.println("Order id :"+od.getOrderId()+"is delivered");
			}
			else
			{
				System.out.println("Order is not delivered");
			}
		}
		else
		{
			System.out.println("Order id is invalid");
		}
		
	}
	static void displayFormat()
	{
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("Order_id\tmedecine_id\tsupplier_id\tQuantity");
		System.out.println("-----------------------------------------------------------------------------------");
	}
	void display()
	{
		System.out.println(ord_id+"\t\t"+med_id+"\t\t"+sup_id+"\t\t"+count);
	}
	boolean isValidOrdId(int id)throws Exception
	{
		return OrderDB.isValidOrdIdDB(id);
	}
	int getOrdCount(int id)throws Exception
	{
		return OrderDB.getOrdCountDB(id);
	}
	int getOrdMid(int id)throws Exception
	{
		return OrderDB.getOrdMidDB(id);
	}
}
