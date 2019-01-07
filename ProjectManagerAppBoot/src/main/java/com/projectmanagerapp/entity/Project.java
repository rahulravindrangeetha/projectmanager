package com.projectmanagerapp.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
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
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projectmanagerapp.util.LocalDateDeserializer;

@Entity
@Table
public class Project implements Serializable
{
	@Id
	@Column(name="PROJECT_ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int projectId;
	
	@JsonIgnore
	@OneToMany(mappedBy="project",fetch=FetchType.LAZY)
	private List<Task> tasks;
	
	@JsonIgnore
	@OneToMany(mappedBy="project",fetch=FetchType.LAZY)
	private List<ParentTask> parentTasks;
	
	@Column(name="PROJECT")
	private String project;
	
	@Column(name="START_DATE")
	@JsonDeserialize(using=LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate startDate;
	
	@Column(name="END_DATE")
	@JsonDeserialize(using=LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate endDate;
	
	@Column(name="PRIORITY")
	private int priority;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Users projectManager;
	
	@Transient
	private int noOfTasks;
	
	@Transient
	private int completed;
	
	@Transient
	private boolean isProjectSuspended;
	
	public Users getProjectManager() 
	{
		return projectManager;
	}

	public boolean isProjectSuspended() 
	{
		return isProjectSuspended;
	}

	public void setProjectSuspended(boolean isProjectSuspended) 
	{
		this.isProjectSuspended = isProjectSuspended;
	}
	
	public List<ParentTask> getParentTasks() 
	{
		return parentTasks;
	}

	public void setParentTasks(List<ParentTask> parentTasks) 
	{
		this.parentTasks = parentTasks;
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
	
	

	public int getNoOfTasks()
	{
		return noOfTasks;
	}

	public void setNoOfTasks(int noOfTasks) 
	{
		this.noOfTasks = noOfTasks;
	}

	public int getCompleted() 
	{
		return completed;
	}

	public void setCompleted(int completed) 
	{
		this.completed = completed;
	}

	public Project(String project, LocalDate startDate, LocalDate endDate, int priority, Users projectManager) {
		super();
		this.project = project;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.projectManager = projectManager;
	}
	
	public Project(int projectId,String project,LocalDate startDate,LocalDate endDate,int priority)
	{
		this.projectId=projectId;
		this.project=project;
		this.startDate=startDate;
		this.endDate=endDate;
		this.priority=priority;
	}

	public Project()
	{
		
	}

	
	
	
	

}
