/**
 * @author: Varun Kashyap
 * FileName: Status.java
 * Date: 06/28/2021
 * Description: Status EJB to access DB and return course/registrar information.
 */

package me.varunkashyap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class Status {
	List<String> allStatus;
	private String status;
	private String courseInfo;
	private String statusMessage;
	
	public Status() {
	}

	//return list of all courses in registrar DB
	public List<String> getAllStatus() {
		allStatus = new ArrayList<String>();
		Database database = new Database();
		database.setSearchRegistrarCourses();
		try (	ResultSet result = database.getResult();
				Statement statement = database.con.createStatement();){
			while (result.next()) {
			        String courseId = result.getString("Course_ID");
			        String courseName = result.getString("Course_Title");
			        Integer numRegistered = Integer.parseInt(result.getString("Number_Registered"));
			        courseInfo = "Course_Id: " + courseId + ". Course_Title: " + courseName + ". Number Registered: " + numRegistered;
			        allStatus.add(courseInfo);
			}
			statusMessage = "Status Report:";
		} catch (SQLException e) {
			statusMessage = "Sorry, unable to pull report for selected course(s).";
			e.printStackTrace();
		}
		return allStatus;
	}
	
	//return string of selected course
	public String getStatus(String cid) {
		Database database = new Database();
		database.setSearchRegistrarCourses();
		try (	ResultSet result = database.getResult();
				Statement statement = database.con.createStatement();){
			while (result.next()) {
			        String courseId = result.getString("Course_ID");
			        if (cid.equals(courseId)) {
				        String courseName = result.getString("Course_Title");
				        Integer numRegistered = Integer.parseInt(result.getString("Number_Registered"));
				        courseInfo = "Course_Id: " + courseId + ". Course_Title: " + courseName + ". Number Registered: " + numRegistered;
				        status = courseInfo;
				        statusMessage = "Status Report:";
				        break;
			        } else {
			        	statusMessage = "Sorry, unable to pull report for selected course(s).";
			        }
			}
		} catch (SQLException e) {
			statusMessage = "Sorry, unable to pull report for selected course(s).";
			e.printStackTrace();
		}
		return status;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
