package Pharmacy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Medicines {
	int med_id;
	String med_cat;
	String name;
	String description;
	float price;
	int count;
	String mfg;
	String exp;
	float tot_price;
	int sup_id;
	Medicines()
	{
		
	}
	Medicines(int id)
	{
		this.med_id=id;
	}
	Medicines(int med_id,int count)
	{
		this.med_id=med_id;
		this.count=count;
	}
	Medicines(int med_id,String name,String description,String med_cat,float price,int count)
	{
		this.med_id=med_id;
		this.name=name;
		this.description=description;
		this.med_cat=med_cat;
		this.count=count;
		this.price=price;
	}
	
	Medicines(int med_id,String name,int count,float price,float tot_price)
	{
		this.med_id=med_id;
		this.name = name;
		this.count=count;
		this.price=price;
		this.tot_price=tot_price;
	}
	void setMedId(int id)
	{
		this.med_id=id;
	}
	int getMedId()
	{
		return med_id;
	}
	String getMedCat()
	{
		return med_cat;
	}
	String getName()
	{
		return name;
	}
	String getmfg()
	{
		return mfg;
	}
	void setMfg(String mfg)
	{
		this.mfg=mfg;
	}
	String getExp()
	{
		return exp;
	}
	void setExp(String exp)
	{
		this.exp=exp;
	}
	String getDescription()
	{
		return description;
	}
	float getPrice()
	{
		return price;
	}
	int getCount()
	{
		return count;
	}
	void setMedCat(String med_cat)
	{
		this.med_cat=med_cat;
	}
	void setName(String name)
	{
		this.name = name;
	}
	void setDescription(String description)
	{
		this.description=description;
	}
	void setPrice(float price)
	{
		this.price = price;
	}
	void setCount(int count)
	{
		this.count=count;
	}
	void setSupId(int sup_id)
	{
		this.sup_id=sup_id;
	}
	int getSupId()
	{
		return sup_id;
	}
	public String getString()
	{
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		return str;
	}
	void insertMedicine()throws Exception
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the medicine Category(tablet/tonic/other products)");
		setMedCat(s.nextLine());
		System.out.println("Enter name of the medicine");
		setName(s.nextLine());
		System.out.println("Enter the description of the medicine");
		setDescription(s.nextLine());
		System.out.println("Enter the price of the medicine");
		setPrice(s.nextFloat());
		System.out.println("Enter Manufacturing date (YYYY-MM-DD)");
		setMfg(s.next());
		System.out.println("Enter Expiry date (YYYY-MM-DD)");
		setExp(s.next());
		System.out.println("Enter the count avaiable");
		setCount(s.nextInt());
		System.out.println("Enter the supplier id");
		setSupId(s.nextInt());
		MedicinesDB.insertMediineDB(getMedCat(),getName(),getDescription(),getPrice(),getCount(),getmfg(),getExp(),getSupId());
		
	}
	void updateMedicine()throws Exception
	{
		System.out.println("Enter the medicine name or id which needs to be updated");
		Scanner s = new Scanner(System.in);
		String name = s.nextLine();
		
		int ch =0;
		String field="";
		String update = "";
		int contin = 0;
		boolean status = false;
		do {
			System.out.println("Enter which field needs to be updated");
			System.out.println("1.medicine Category\n2.description\n3.price\n4.count\n5.name\n6.Manufacturing date\n7.Expiry date");
			ch=s.nextInt();
			switch(ch)
			{
			case 1:
				field="med_cat";
				System.out.println("enter the medicine category");
				update = getString();;
				break;
			case 2:
				field="description";
				System.out.println("enter the description");
				update= getString();;
				break;
			case 3:
				field="price";
				System.out.println("enter the price");
				float price = s.nextFloat();
				update = Float.toString(price);
				break;
			case 4:
				field="count";
				System.out.println("enter the count");
				int count = s.nextInt();
				update = Integer.toString(count);
				break;
			case 5:
				field="name";
				System.out.println("enter the name");
				update = getString();
				break;
			case 6:
				field="mfg";
				System.out.println("Enter the mfg");
				update = getString();
				break;
			case 7:
				field="exp";
				System.out.println("Enter the exp");
				update = getString();
				break;
			default:
				System.out.println("Sorry choice is Invalid");
			}
			status=MedicinesDB.updateQy(name,field,update);
			if(status)
			{
				System.out.println("Updated Successfully");
			}
			else
			{
				System.out.println("Updation failed");
			}
			System.out.println("If you want to continue the operation press 1");
			contin=s.nextInt();
		}while(contin==1);
	}
	
	void deleteMedicine()
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the medicine name or id to be deleted");
		String str = s.nextLine();
		if(MedicinesDB.deleteMedicineDB(str))
		{
			System.out.println("Deletion Completed");
		}
		else
		{
			System.out.println("Deletion is failed");
		}
	}
	void expiryMed()throws Exception
	{
		ArrayList<Medicines> medi = MedicinesDB.expMedDB();
		displayingList(medi);
		
		
	}
	void delMedExp()throws Exception
	{
		MedicinesDB.delExpDB();
		
	}
	void showDetails()throws Exception
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the name of the medicine or it's id to show details");
		String str = s.nextLine();
		ArrayList<Medicines> medi = MedicinesDB.showDetailsDB(str);
		displayingList(medi);
	}
	void outOfStock()throws Exception
	{
		ArrayList<Medicines> medi = MedicinesDB.outOfStockDB();
		displayingList(medi);
		
	}
	void listOfMedicines()throws Exception
	{
		
		ArrayList<Medicines> medi = MedicinesDB.listOfMedicineDB();
		displayingList(medi);
		
	}
	
	
	static void displayFormat()
	{
		System.out.println("-----------------------------------------------------------------------------------");
		System.out.println("id\t\t\tName\t\tQuantity\t\tPrice\t\t\tTotal Price");
		System.out.println("-----------------------------------------------------------------------------------");
	}
	void display()
	{
		System.out.format(" %-9s          %-9s      %5d           %9.2f                %14.2f \n",med_id,name,count,price,tot_price);
	}
	static void medicineDisplay()
	{
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		System.out.println("Medicine_id\tName\t\tType\t\tDescription\t\t\t\t\tPrice\t\tQuantity");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
	}
	void displayMedicine()
	{
		int l = description.length();
		for(int i=l;i<40;i++)
		{
			description+=" ";
		}
		System.out.println("\t"+med_id+"\t"+name+"\t"+med_cat+"\t\t"+description+"\t"+price+"\t\t"+count);
	}
	
	
	
	int getSupIdFromMedId(int id)throws Exception
	{
		return MedicinesDB.getSupIdFromMedIdDB(id);
	}
	void orderMedicine()throws Exception
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the medicine id which needs to be ordered");
		Medicines me = new Medicines();
		me.setMedId(s.nextInt());
		if(MedicinesDB.isValidMedDB(me.getMedId()))
		{
			me.setSupId(me.getSupIdFromMedId(me.getMedId()));
			System.out.println("Enter the medicine count need to be ordered");
			int c = s.nextInt();
			Supplier sp = new Supplier(me.getSupId());
			sp.supplierDetailsForOrder();
			Order od = new Order(me.getMedId(),me.getSupId(),c);
			if(od.placeOrder())
			{
				System.out.println("Order placed...");
			}
			else
			{
				System.out.println("Order not placed");
			}
		}
		else
		{
			System.out.println("Invalid medicine id");
		}
	}
	boolean isValidMed()
	{
		return MedicinesDB.isValidMedDB(getMedId());
	}
	String getMedName()throws Exception{
		return MedicinesDB.getMedNameDB(getMedId());
	}
	float getMedPrice()throws Exception{
		return MedicinesDB.getMedPriceDB(getMedId());
	}
	boolean sufficient()throws Exception
	{
		return MedicinesDB.sufficientDB(getMedId(),getCount());
	}
	String getMedicineName()throws Exception
	{
		return MedicinesDB.getMedicineNameDB(getMedId());
	}
	void updateMedicineOrd()throws Exception
	{
		MedicinesDB.updateMedicineOrdDB(getCount(),getMedId());
	}
	void displayingList(ArrayList<Medicines> medi)
	{
		if(medi.isEmpty())
		{
			System.out.println("Null");
		}
		else
		{
			Medicines.medicineDisplay();
			for(Medicines m:medi)
			{
				m.displayMedicine();
			}
		}
	}
}
