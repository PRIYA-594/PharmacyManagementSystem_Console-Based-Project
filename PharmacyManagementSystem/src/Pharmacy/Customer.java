package Pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Customer{
	private int c_id;
	private String name;
	private String email;
	private String Password;
	private String gender;
	private String ph_No;
	private int age;
	Customer(String email,String Password)
	{
		this.email=email;
		this.Password=Password;
	}
	void setName(String name)
	{
		this.name = name;
	}
	void setEmail(String email)
	{
		this.email=email;
	}
	void setPassword(String Password)
	{
		this.Password=Password;
	}
	void setGender(String gender)
	{
		this.gender=gender;
	}
	void setPh(String ph)
	{
		this.ph_No=ph;
	}
	void setAge(int age)
	{
		this.age=age;
	}
	int getAge()
	{
		return age;
	}
	String getPh()
	{
		return ph_No;
	}
	String getPassword()
	{
		return Password;
	}
	String getName()
	{
		return name;
	}
	String getEmail()
	{
		return email;
	}
	String getGender()
	{
		return gender;
	}
	private void customerAccess()throws Exception
	{
		CustomerDB cdb = new CustomerDB();
		int id = cdb.getCustId(getEmail());
		Medicines me = new Medicines();
		me.listOfMedicines();
		Bill pu = new Bill();
		pu.bill(id);
	}
	
	void customerSignUp() throws Exception
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter your Name");
		setName(s.nextLine());
		int e=0;
		do
		{
			System.out.println("Enter your email");
			setEmail(s.nextLine());
			if(!CustomerDB.isExistEmail(getEmail()))
			{
				if(isValidEmail(getEmail()))
				{
					e=1;
				}
				else
				{
					System.out.println("Email is not valid");
				}
			}
			else
			{
				System.out.println("Email already exists");
			}
		}while(e!=1);
		e=0;
		do {
			System.out.println("Enter your age");
			setAge(s.nextInt());
			if(getAge()>0 && getAge()<120)
			{
				e=1;
			}
			else
			{
				System.out.println("Age is not valid");
			}
		}while(e!=1);
		e=0;
		do {
			System.out.println("Enter your phone_no");
			setPh(s.next());
			if(getPh().length()>=10)
			{
				e=1;
			}
			else
			{
				System.out.println("Atleast 10 digit phone number");
			}
		}while(e!=1);	
		e=0;
		do
		{
			System.out.println("Enter the password");
			setPassword(s.next());
			if( isValidPassword(getPassword()))
			{
				e=1;
			}
			else
			{
				System.out.println("Password is not valid,try create a valid one");
			}
		}while(e!=1);
		System.out.println("Enter the confirm password");
		String confirmPassword=s.next();
		while(!getPassword().equals(confirmPassword))
		{
			System.out.println("Password and confirm Password should be same");
			confirmPassword = s.nextLine();
		}
		System.out.println("Enter your gender");
		setGender(s.next());
		
		CustomerDB.insertCust(getName(), getGender(), getAge(),getPh() , getEmail(), getPassword());
		
	}
	
	boolean isCustomer()throws Exception
	{
		Scanner s = new Scanner(System.in);
		if(CustomerDB.isValidCust(email,Password))
		{
			System.out.println("Valid");
			Customer ct = new Customer(email,Password);
			ct.customerAccess();
			return true;
		}
		else
		{
			System.out.println("Your credentials are incorrect, if you want to create an account enter yes");
			String choice=s.nextLine();
			if(choice.equals("yes"))
			{
				customerSignUp();
			}
		}
		return false;
	}
	private static boolean isValidEmail(String email)
	{
		String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	    Pattern pattern = Pattern.compile(EMAIL_REGEX);
	    //System.out.println(pattern.matcher(email).matches());
	    return pattern.matcher(email).matches();   
	}
	
	private static boolean isValidPassword(String a)
	{
		String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
		Pattern pattern = Pattern.compile(regexPattern);
	    return(pattern.matcher(a).matches());	
	}
	

}
