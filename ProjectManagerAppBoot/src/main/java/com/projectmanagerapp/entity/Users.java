package com.projectmanagerapp.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projectmanagerapp.util.LocalDateDeserializer;

@Entity
@Table
public class Users implements Serializable
{
	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int userId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMPLOYEE_ID")
	private int employeeId;
	
	@Column(name="IS_DELETED")
	private int isDeleted;
		
	@OneToMany(fetch=FetchType.LAZY,mappedBy="taskManager")
	private List<Task> task;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="projectManager")
	private List<Project> project;

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	

	public int getIsDeleted() 
	{
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) 
	{
		this.isDeleted = isDeleted;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public int getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(int employeeId) 
	{
		this.employeeId = employeeId;
	}

	public List<Task> getTask() 
	{
		return task;
	}

	public void setTask(List<Task> task) 
	{
		this.task = task;
	}

	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) 
	{
		this.project = project;
	}

	public Users(String firstName, String lastName, int employeeId) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
	}
	
	public Users()
	{
		
	}

	@Override
	public String toString() 
	{
		return "Users [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", employeeId="
				+ employeeId + ", isDeleted=" + isDeleted + ", task=" + task + ", project=" + project + "]";
	}
	
	

	
	
	

}
