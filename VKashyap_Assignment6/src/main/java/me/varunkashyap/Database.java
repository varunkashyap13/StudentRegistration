/**
 * @author: Varun Kashyap
 * FileName: Database.java
 * Date: 06/28/2021
 * Description: Class to access H2 Database. 
 * Contains methods to implement queries on Student, Courses, and Registrar tables.
 */

package me.varunkashyap;

import java.sql.*;
import java.sql.Connection;
import java.io.IOException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

public class Database{ 
	
	public  Connection con; 
	public  Statement stm; 
	private String sit = ""; 
    private DataSource ds;
    private ResultSet res;
	
	 public Database() { 
		Hashtable env = new Hashtable();
	    env.put(Context.INITIAL_CONTEXT_FACTORY, 
	    		"org.wildfly.naming.client.WildFlyInitialContextFactory");
	    try {
	    	// Get a Connection to the database
	    	Context context = new InitialContext(env);
	        ds = (DataSource) context.lookup ("java:jboss/datasources/H2_784_JNDI");
	        con = ds.getConnection();
	        stm = con.createStatement(); 
	        sit = "Connection successfully executed"; 
	    } catch(Exception e) {
	            e.printStackTrace();
	            sit = "Could not connect to the database" + e.getMessage();
		} 
	 } 
	 public String getSituation(){ 
		return sit; 
		} 
	 
	 //query for student table
	 public void setSearchStudent() { 
		 try { 
			 res = stm.executeQuery("select * from Student"); 
			 
			 } catch (SQLException e){ 
				 e.printStackTrace(); 
				 } 
		 } 
	 
	//query for courses table
	 public void setSearchCourses() { 
		 try { 
			 res = stm.executeQuery("select * from Courses"); 
			 
			 } catch (SQLException e){ 
				 e.printStackTrace(); 
				 } 
		 }
	 
	//query for registrar table
	 public void setSearchRegistrar() { 
		 try { 
			 res = stm.executeQuery("select * from Registrar"); 
			 
			 } catch (SQLException e){ 
				 e.printStackTrace(); 
				 } 
		 }
	 
	 public void setSearchRegistrarCourses() { 
		 try { 
			 res = stm.executeQuery("SELECT COURSES.COURSE_ID, COURSES.COURSE_TITLE, REGISTRAR.NUMBER_REGISTERED FROM COURSES INNER JOIN REGISTRAR ON COURSES.COURSE_ID=REGISTRAR.COURSE_ID;"); 
			 System.out.println("inside setSearchRegistrarCourses() try");
			 System.out.println("res: " + res);
			 } catch (SQLException e){ 
				 System.out.println("inside setSearchRegistrarCourses() catch");
				 e.printStackTrace(); 
				 } 
		 }
	 
	 public ResultSet getResult() { 
		 return res; 
		 } 		
}