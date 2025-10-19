package Class;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
public class Hospital {
	public static void main(String[] args) throws SQLException  {
		Scanner scn=new Scanner(System.in);                                                                                                                                        // step 2
		 
		
		
		
		final String url="jdbc:mysql://localhost:3306/Hospital";   
		final String username="root";
		final String pass="99941";
			Connection con=DriverManager.getConnection(url, username, pass);
			
			Patient p=new Patient(con, scn);                                                                                                                                    //  1st step 5 see what happen in back
			Doctor d=new Doctor(con,scn);
			Hospital h=new Hospital();
			
			int n=1;
			  while(n<=1) {   n=2;                                                                                                                                               // step 1
				System.out.println("  Welcome to Lakshmi Multi-Speciality Hospital");  
				System.out.println();
				System.out.println("1 Add Patients");
				System.out.println("2 Search Patient-Id");
				System.out.println("3 View Pateints");
				System.out.println("4 View Doctors");
				System.out.println("5 Search Doctors via Dept");
				System.out.println("6 Appointments");
				System.out.println("7 Show All Appointments7");
				System.out.println();
				System.out.println("Enter the choice");
				int choice= scn.nextInt();
				
				switch (choice)
				{
				    case 1:p.addPatient();                                                                                                                                      // step 5
				           break;
				           
				    case 2:System.out.println();
				           System.out.println("Enter phone number");
					       String s=scn.next();
					       System.out.println();
				           p.search_id(s);
				           break;
				           
				    case 3:System.out.println(); 
				           p.viewPatient();       
				           break;
				           
				    case 4:System.out.println(); 
				            d.viewDoctor();
				            break;
				            
				    case 5:System.out.println();
				           System.out.println("Enter Doctor Department");
				           String s2=scn.next();
				           System.out.println();
				           d.search_id(s2);
				           break;
				          
				    case 6:h.appointments(con,scn,p,d);
				    
				    case 7:h.showAppointments(con);
				  
				            
				    
				    	
				            
				} 
				
				System.out.println();

			}
			
			
	
	}

	    private void showAppointments(Connection con) {
	    	 
	    	String sql="select * from appointments";
	    	PreparedStatement pt;
			try {
				pt = con.prepareStatement(sql);
	    	
	    	 ResultSet r= pt.executeQuery();
	    	 System.out.printf("%-7s%-7s%-13s%-12s%-20s%-25s\n","S.No","P_id","PatientName","Doctor_id","Appointment_date","Appointment_time"); 
	    	 
	    	 while(r.next())
	    	 {
	    		 
	    		 System.out.printf("%-7d%-7d%-13s%-12d%-20s%-25s\n",r.getInt(1),r.getInt(2),r.getString(3).trim(),r.getInt(4),r.getString(5).trim(),r.getString(6).trim()); 
	    	 }
	    	  
	    	
			}
			
			catch(Exception e)
			{
				
			}
	 
	    	
		
		
	}

		public void appointments(Connection con,Scanner scn,Patient p,Doctor d)
	    {
	    	System.out.println("Enter the Patient Id");        // asking Id
	    	int p_id=scn.nextInt();
	    	
	    	String p_name= p.getPatientNameById(con,p_id);     // getting name by id
	    	
	    	System.out.println("Enter the Doctor Id");         // asking p_id
	    	int d_id=scn.nextInt();
	    	
	    	String date=validateDate(scn);                    // validate the Date
	    	
	    	String time =validateTime(scn);
	    	
	    	
	    
	    	 
	    	
	    	
	    	
	    	
	    	if( p.patientId(p_id)&& d.doctorId(d_id))
	    	{
	    		
	    
	    		if(checkAvailability(con,time,date,d_id))
	    		{
	    			String sql=" insert into appointments(Patient_id,Patient_name,Doctor_id,Appointment_date,Appointment_time) values (?,?,?,?,?)";
	    			try {
						PreparedStatement pt=con.prepareStatement(sql);
						pt.setInt(1, p_id);
						pt.setString(2,p_name);
						pt.setInt(3, d_id);
						pt.setString(4, date);
						pt.setString(5, time);
						
						int n=pt.executeUpdate();
						if(n>0) {
							System.out.println("Appointment is confirmed");
						}
						else System.out.println("Something issue");
					} catch (SQLException e) {
						e.printStackTrace();
					}
	    		
	    			
	    		}
	    		else 
	    		{
	    			System.out.println("Choose another time");
	    		}
	    	}
	    	else
	    	{
	    		System.out.println("There is no id");
	    	}
	    	
	    }   

		private boolean checkAvailability(Connection con,String time, String date, int d_id) {
						
			String sql="Select * from appointments where Appointment_date=? and  Appointment_time=? and  Doctor_id=?";
			try {
				PreparedStatement pt=con.prepareStatement(sql);
			pt.setString(1, date);
			pt.setString(2, time);
			pt.setInt(3, d_id);
			   
				ResultSet r=pt.executeQuery();
				if(!(r.next()))
				{
					return true;
				}
				else
				{
					return false;
				}
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			
			return false;
		}
		
		
		// validateDate
		private String validateDate(Scanner scn) {
		while(true)	
		 {	
			System.out.println("Enter the Appointment date YYYY-MM-DD");
			String date = scn.next().trim();
			
			try {
				LocalDate.parse(date);
				return date;
			}
			catch(Exception e)
			{
				System.out.println("Invalid date format. Try again.");
			}
		 }
		}
	
		
		private String validateTime(Scanner scn) {
			while(true)
			{
				System.out.println("Enter the time HH:MM:SS");
				String time=scn.next().trim();
				
				try {
					LocalTime.parse(time);
					return time;
				}
				catch(Exception e)
				{
					System.out.println("Invalid time format. Try again.");
				}
				
			}
			
		
		}
}
