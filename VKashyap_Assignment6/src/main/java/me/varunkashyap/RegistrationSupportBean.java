/**
 * @author: Varun Kashyap
 * FileName: RegistrationSupportBean.java
 * Date: 06/28/2021
 * Description: Support bean to access registrar information in DB
 */

package me.varunkashyap;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;

import javax.faces.context.FacesContext;

@Named(value = "RegistrationSupportBean")
@SessionScoped
public class RegistrationSupportBean implements Serializable {
	private Integer courseCapacity;
	private String studentSelectionOption;
	private String resultText;
	private Integer resultCheck;
	private String courseName;
	private Integer registerCount;
	private CoursesSupportBean courseBean;
	private String courseSelected;
	
	@Inject
	//Injects courses support bean
	public RegistrationSupportBean(@Named("CoursesSupportBean") CoursesSupportBean courseBean) {
		//initializeCourseCapacity();
		this.courseBean = courseBean;
		//resultText = null;
		//searchDB();
	}
	
	private void initializeCourseCapacity() {
		courseCapacity = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("CourseCapacity")); 
	}
	
	//logic to search the database and generate output
	private void searchDB() {
		studentSelectionOption = courseBean.getCourseSelected();
		String[] parts = studentSelectionOption.split("\\s+");
		String courseNum = parts[0];
		
		Database database = new Database();
		database.setSearchRegistrar();
		
		// query registrar database
		try (	ResultSet result = database.getResult();
				Statement statement = database.con.createStatement();){

			while (result.next()) {
				String courseId = result.getString("Course_ID"); 
				Integer numRegistered = Integer.parseInt(result.getString("Number_Registered")); 
				if(courseNum.equals(courseId)){ 
			           // check if course capacity is still under limit
					if (numRegistered > courseCapacity) {
			        	   registerCount = 0;
			        	   resultText = "Sorry, the registration to this course has been closed based on availability.";
			           } else {
			        	   resultCheck = 1;
			        	   registerCount = 1;
			        	   
			        	   //update registrar database with number registered
			        	   statement.executeUpdate("UPDATE Registrar SET Number_Registered="+registerCount+" WHERE Course_Id='"+courseNum+"'; ");
			            
			           }
					break;
			        } else {
			        	resultText = "course not found";
			        }
			}  
			       
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		if (resultCheck ==1) {
			database.setSearchCourses();
			// query courses database
			try (	ResultSet result = database.getResult();
					Statement statement = database.con.createStatement();){
				
				while (result.next()) {
					String courseIdCoursesDB = result.getString("Course_Id"); 
					String courseTitleCoursesDB = result.getString("Course_Title");
					// get appropriate course ID and course title for output
					if(courseNum.equals(courseIdCoursesDB)){ 
							courseName = courseTitleCoursesDB;
							resultText = "You have been registered to " + courseNum + " " + courseName;
				           break; 
				        } else {
				        	resultText = "course not found";
				        }
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Integer getCourseCapacity() {
		return courseCapacity;
	}

	public void setCourseCapacity(Integer courseCapacity) {
		this.courseCapacity = courseCapacity;
	}

	public String getStudentSelectionOption() {
		return studentSelectionOption;
	}

	public void setStudentSelectionOption(String studentSelectionOption) {
		this.studentSelectionOption = studentSelectionOption;
	}

	public String getResultText() {
		initializeCourseCapacity();
		resultText = null;
		searchDB();
		return resultText;
	}

	public void setResultText(String resultText) {
		this.resultText = resultText;
	}

	public Integer getResultCheck() {
		return resultCheck;
	}

	public void setResultCheck(Integer resultCheck) {
		this.resultCheck = resultCheck;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getRegisterCount() {
		return registerCount;
	}

	public void setRegisterCount(Integer registerCount) {
		this.registerCount = registerCount;
	}

	public String getCourseSelected() {
		return courseSelected;
	}

	public void setCourseSelected(String courseSelected) {
		this.courseSelected = courseSelected;
	}

	
	
	
}
