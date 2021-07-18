/**
 * @author: Varun Kashyap
 * FileName: CoursesSupportBean.java
 * Date: 06/28/2021
 * Description: Support bean to access course information in DB
 */

package me.varunkashyap;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;


@Named("CoursesSupportBean")
@SessionScoped

public class CoursesSupportBean implements Serializable {

	List<String> courseList;
	private String courseOption;
	private String courseSelected;
	List<String> courseNames;

	public CoursesSupportBean() {
		//call list method
		populateCourseList();
	}

	@Override
	public String toString() {
		return "CoursesSupportBean [courseList=" + courseList + ", courseOption=" + courseOption + ", courseSelected="
				+ courseSelected + "]";
	}
	
	private void populateCourseList() {
		courseList = new ArrayList<String>();
		Database database = new Database();
		database.setSearchCourses();
		// search courses database for courseID and title
		try (	ResultSet result = database.getResult();
				Statement statement = database.con.createStatement();){

			while (result.next()) {
			        String courseId = result.getString("Course_ID");
			        String courseName = result.getString("Course_Title");
			        courseOption = courseId + " " + courseName;
			        courseList.add(courseOption);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public String getCourseOption() {
		return courseOption;
	}

	public void setCourseOption(String courseOption) {
		this.courseOption = courseOption;
	}

	public List<String> getCourseList() {
		return courseList;
	}

	
	public String getCourseSelected() {
		return courseSelected;
	}

	public void setCourseSelected(String courseSelected) {
		this.courseSelected = courseSelected;
	}

	public List<String> getCourseNames() {
		return courseNames;
	}

	
	
}