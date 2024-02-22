package Pharmacy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Bill extends Sales{
	public int bill_id;
	public int cust_id;
	public int count;
	public float amount;
	public String date;
	Bill()
	{
		amount=0.0f;
	}
	Bill(int bill_id,int cust_id,int count,float amount,String date)
	{
		this.bill_id=bill_id;
		this.cust_id=cust_id;
		this.count=count;
		this.amount=amount;
		this.date=date;
	}
	void setBillId(int bill_id)
	{
		this.bill_id=bill_id;
	}
	void setCustId(int cust_id)
	{
		this.cust_id=cust_id;
	}
	int getCustId()
	{
		return cust_id;
	}
	int getBillId()
	{
		return bill_id;
	}
	void bill(int cust_id)throws Exception
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the id of the medicine to bill it");
		int ch =0;
		int mid=0;
		int count =0;
		String query="";
		int c=0;
		float cost = 0.0f;
		ArrayList<Integer> st = new ArrayList<>();
		ArrayList<Integer> cou = new ArrayList<>();
		
		ArrayList<Medicines> med = new ArrayList<>();
		do
		{
			System.out.println("-------------------------------");
			System.out.println("Enter the medicine id:");
			mid=s.nextInt();
			System.out.println("Enter the required count");
			count=s.nextInt();
			Medicines me = new Medicines(mid,count);
			System.out.println("-------------------------------");
			int f=0;
			if(me.isValidMed())
			{
				if(me.sufficient())
				{
					cost+=count*me.getMedPrice();
					c+=count;
					st.add(mid);
					cou.add(count);
					f=0;
				}
				else
				{
					System.out.println("The available count of medicine is not suffient to your requirement");
					f=1;
				}
			}
			else
			{
				System.out.println("The medicine id is not in the list of medicines");
			}
			if(f!=1)
			{
				float price = me.getMedPrice();
				med.add(new Medicines(mid,me.getMedName(),count,me.getMedPrice(),count*price));
			}
			System.out.println("Do you want to add the medicine ");
			System.out.println("To continue purchasing press 1 otherwise press any number to get the bill");
			ch = s.nextInt();
			
		}while(ch==1);
		System.out.println("\t\tBill\t\t");
		System.out.println("Customer id: "+cust_id);
		System.out.println("-----------------------------");
		Medicines.displayFormat();
		for(Medicines m:med)
		{
			m.display();
		}
		System.out.println("\t\t\t\t\t\t\t\t\t Total Amount "+cost);
		float sgst = cost*18/100;
		float cgst = cost*18/100;
		System.out.println("\t\t\t\t\t\t\t\t\t Sgst(%) "+sgst);
		System.out.println("\t\t\t\t\t\t\t\t\t Cgst(%) "+cgst);
		cost+=sgst+sgst;
		System.out.println("\t\t\t\t\t\t\t\t\t Total "+cost);
		BillDB.insertQyDB(cust_id,cost,c);
		for(int i=0;i<cou.size();i++)
		{
			Medicines mb = new Medicines(st.get(i),cou.get(i));
			BillDB.updateQyDB(cou.get(i), st.get(i));
			SalesDB.salesQyDB(cou.get(i),st.get(i),cou.get(i)*mb.getMedPrice(),cust_id);
			System.out.println();
		}
		System.out.println("\t\t\t\t\tThank you for purchasing in our medical shop\t\t\t\t");
		System.out.println("\t\t\tPlease wait for the pharmacist to process through the bill and provide medicine");
		
		
	}
	void billing()throws Exception
	{
		Scanner s = new Scanner(System.in);
		ArrayList<Bill> billing = BillDB.billingDB();
		if(billing.isEmpty())
		{
			System.out.println("No bills....");
		}
		else
		{
			Bill.displayFormat();
			for(Bill b:billing)
			{
				b.display();
			}
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println("Enter the Bill_id you want to process");
			int bill_id = s.nextInt();
			Bill bil= new Bill();
			bil.setBillId(bill_id); 
			bil.setCustId(bil.getCid());
			if(bil.getCid()!=0)
			{
				Sales sl = new Sales(bil.getCustId());
				sl.billDetails();
				BillDB.updateprocessDB(bill_id);
			}
			else
			{
				System.out.println("The bill id is not valid");
			}
		}
	}
	int getCid()throws Exception
	{
		return BillDB.getCidDB(getBillId());
		
	}
	static void displayFormat()
	{
		System.out.println("--------------------------------------------------------------------------------------------");
		System.out.println("Bill_id\t\tcust_id\t\tcount\t\t\tTotal Price\t\tDate");
		System.out.println("--------------------------------------------------------------------------------------------");
	}
	void display()
	{
		System.out.println("\t"+bill_id+"\t\t"+cust_id+"\t\t"+count+"\t\t"+amount+"\t\t"+date);
	}
	void purchaseReport()throws Exception
	{
		BillDB.purchaseReportDB();
		
	}

	
	
}
