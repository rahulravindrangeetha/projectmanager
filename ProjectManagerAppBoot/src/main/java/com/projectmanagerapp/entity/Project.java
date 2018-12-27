package com.projectmanagerapp.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Project 
{
	@Id
	@Column(name="PROJECT_ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int projectId;
	
	@OneToMany(mappedBy="project")
	private List<Task> tasks;
	
	@Column(name="PROJECT")
	private String project;
	
	@Column(name="START_DATE")
	@JsonDeserialize(using=LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	//@Convert(converter=DateConvertor.class)
	private LocalDate startDate;
	
	@Column(name="END_DATE")
	@JsonDeserialize(using=LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	//@Convert(converter=DateConvertor.class)
	private LocalDate endDate;
	
	@Column(name="PRIORITY")
	private int priority;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="USER_ID")
	private Users projectManager;
	
	public Users getProjectManager() 
	{
		return projectManager;
	}

	public void setProjectManager(Users projectManager) 
	{
		this.projectManager = projectManager;
	}

	
	public int getProjectId() 
	{
		return projectId;
	}

	public void setProjectId(int projectId) 
	{
		this.projectId = projectId;
	}

	public List<Task> getTasks() 
	{
		return tasks;
	}

	public void setTasks(List<Task> tasks) 
	{
		this.tasks = tasks;
	}

	public String getProject() 
	{
		return project;
	}

	public void setProject(String project)
	{
		this.project = project;
	}

	public LocalDate getStartDate() 
	{
		return startDate;
	}

	public void setStartDate(LocalDate startDate) 
	{
		this.startDate = startDate;
	}

	public LocalDate getEndDate() 
	{
		return endDate;
	}

	public void setEndDate(LocalDate endDate) 
	{
		this.endDate = endDate;
	}

	public int getPriority() 
	{
		return priority;
	}

	public void setPriority(int priority)
	{
		this.priority = priority;
	}

	@Override
	public String toString() 
	{
		return "Project [projectId=" + projectId + ", tasks=" + tasks + ", project=" + project + ", startDate="
				+ startDate + ", endDate=" + endDate + ", priority=" + priority + ", projectManager=" + projectManager
				+ "]";
	}

	
	
	
	

}
