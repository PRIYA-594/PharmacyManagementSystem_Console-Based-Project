package Pharmacy;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SupplierDB {
	static void insertSupplierDB(String name,String ph,String mail)throws Exception
	{
		String str = "insert into supplier(sup_name,sup_ph,sup_mail) values('"+name+"','"+ph+"','"+mail+"')";
		Db.UpdateQuery(str);
	}
	static void updateSupplierDB(String field,String change,int sup_id)throws Exception
	{
		String qu="update supplier set "+field+"='"+change+"' where sup_id="+sup_id+"";
		Db.UpdateQuery(qu);
	}
	static ArrayList<Supplier> supplierDetailsDB()throws Exception
	{
		String query ="select * from supplier";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<Supplier> sup = new ArrayList<>();
		while(rs.next())
		{
			sup.add(new Supplier(rs.getInt("sup_id"),rs.getString("sup_name"),rs.getString("sup_ph"),rs.getString("sup_mail")));
			
		}
		return sup;
	}
	static ArrayList<Supplier> supplierDetailsForOrderDB(int sup_id)throws Exception
	{
		String query ="select * from supplier where sup_id="+sup_id+"";
		Statement st = (Statement)Db.con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<Supplier> sup = new ArrayList<>();
		while(rs.next())
		{
			sup.add(new Supplier(rs.getInt("sup_id"),rs.getString("sup_name"),rs.getString("sup_ph"),rs.getString("sup_mail")));
			
		}
		return sup;
	}
	static boolean validSupIdDB(int id)throws Exception
	{
		String qy = "select * from supplier where sup_id="+id+"";
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
}
