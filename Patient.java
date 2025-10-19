package Class;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Patient   {
	
	private  Connection con ;
	private Scanner scn ;                                                                                               // step 4
	
	public Patient (Connection con,Scanner scn)
	{
		this.con=con;
		this.scn=scn;  
	}
	
	
	// Add Patient
	public void addPatient()                                                                         //step 3
	{
		
		System.out.println("Enter the patient name");
		String name=scn.next();
		 
		System.out.println("Enter the patient age");
		int age=scn.nextInt();
		
		System.out.println("Enter the patient Gender");
		String gender=scn.next();
		
		System.out.println("Enter the patient Mobile_No");
		String mob_no=scn.next();
		
		String sql= "insert into patients(Patient_name ,Patient_age,Patient_gender,Patient_no) values(?,?,?,?)" ;
		try {
			PreparedStatement pt=con.prepareStatement(sql);
			pt.setString(1, name);
			pt.setInt(2, age);
			pt.setString(3, gender);
			pt.setString(4, mob_no);
			
			int n=pt.executeUpdate();
			if(n>0)
			{
				System.out.println("Successfully registered");
			}
			else {
				System.out.println("Not registered Something issue");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();		
		}
	}
	
	// Search Patient Id
	public void search_id(String s) 
	{
		                           
		try {
			
			String sql= "select * from Patients where patient_no=?";
			PreparedStatement pt = con.prepareStatement(sql);
		   	pt.setString(1, s);
			 ResultSet r=pt.executeQuery();
			
			 if(r.next())
			 {
				 System.out.println("PATIENT");
				 System.out.printf("%-10s %-15s %-10s %-10s %-15s\n","S.No","Name","Age","Gender","Number"); 
				 System.out.printf("%-10d %-15s %-10d %-10s %-15s\n",r.getInt(1),r.getString(2),r.getInt(3),r.getString(4),r.getString(5)); 
			 }
			 else 
			 {
				 System.out.println("Phone Number is not there ");
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// View all Patients
	public void viewPatient()
	{
       try {
			
			String sql= "select * from Patients ";
			PreparedStatement pt = con.prepareStatement(sql);
			 ResultSet r=pt.executeQuery();
			 System.out.println("PATIENTS TABLE");
			 System.out.printf("%-5s%-10s%-10s%-10s%-10s\n","S.No","Name","Age","Gender","Number"); 
			 while(r.next())
			 {
				System.out.printf("%-5d%-10s%-10s%-10s%-10s\n",r.getInt(1),r.getString(2),r.getInt(3),r.getString(4),r.getString(5)); 
			 }
			
	      	}
       catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean patientId(int id)
    {
		String sql="Select *from patients where patient_id=?";
		try {
			PreparedStatement pt=con.prepareStatement(sql);
			pt.setInt(1, id);
			ResultSet n=pt.executeQuery();
			while(n.next())			{
				return true;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
	   	
	}


	public String getPatientNameById(Connection con2, int p_id) 
	{
		String sql="Select Patient_name from Patients where Patient_id=?";
		try {
			PreparedStatement pt=con2.prepareStatement(sql);
		  pt.setInt(1, p_id);
		  
		  ResultSet r=pt.executeQuery();
		  while(r.next())
		  {
			  return r.getString(1);          // mention the set column index, not table column
			  
		  }
				  
		  
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
				return null;
	}
	
	
	
}
