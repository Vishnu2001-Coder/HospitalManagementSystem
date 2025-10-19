package Class;

import java.sql.*;
import java.util.Scanner;

public class Doctor {
	
	private  Connection con ;
	private Scanner scn ;                                                                                               // step 4
	public Doctor (Connection con,Scanner scn)
	{
		this.con=con;
		this.scn=scn;
	}

	// View all Doctors
		public void viewDoctor()
		{
	       try {
				
				String sql= "select * from Doctors ";
				PreparedStatement pt = con.prepareStatement(sql);
				 ResultSet r=pt.executeQuery();
				 System.out.println("DOCTOR TABLE");
				 System.out.printf("%-5s %-15s %-15s\n","S.No","Name","Dept"); 
				 while(r.next())
				 {
					System.out.printf("%-5d %-15s %-15s\n",r.getInt(1),r.getString(2),r.getString(3)); 
				 }
				
		      	}
	       catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		// Search Doctor Id
		public void search_id(String s) 
		{
			                           
			try {
				
				String sql= "select * from Doctors where Dept=?";
				PreparedStatement pt = con.prepareStatement(sql);
			   	pt.setString(1, s);
				 ResultSet r=pt.executeQuery();
				
				 if(r.next())
				 {
					 System.out.println("Doctor");
					 System.out.printf("%-5s %-15s %-15s\n","S.No","Name","Dept"); 
					 System.out.printf("%-5d %-15s %-15s\n",r.getInt(1),r.getString(2),r.getString(3)); 
				 }
				 else 
				 {
					 System.out.println("Invalid Department ");
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
    
		public boolean doctorId(int id)
	    {
			String sql="Select *from doctors where doctor_id=?";
			try {
				PreparedStatement pt=con.prepareStatement(sql);
				pt.setInt(1, id);
				ResultSet n=pt.executeQuery();
				while(n.next())
				{
					return true;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			return false;
		   	
	    }
		
}
		    

