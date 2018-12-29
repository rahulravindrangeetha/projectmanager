package com.projectmanagerapp.entity;

import java.io.Serializable;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.projectmanagerapp.util.LocalDateDeserializer;

@Entity
@Table
public class Task implements Serializable
{
	@Id
	@Column(name="TASK_ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int taskId;
	
	@ManyToOne
	@JoinColumn(name="PARENT_ID")
	private ParentTask parentTask;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="PROJECT_ID")
	private Project project;
	
	@Column(name="TASK")
	private String task;
	
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
	
	@Column(name="STATUS")
	private String status;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Users taskManager;
	
	@Transient
	private boolean isDisabled;
	
	
	
	public Task(String task, LocalDate startDate, LocalDate endDate, int priority, String status) 
	{
		super();
		this.task = task;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.status = status;
	}
	
	public Task()
	{
		
	}

	public int getTaskId()
	{
		return taskId;
	}

	public void setTaskId(int taskId) 
	{
		this.taskId = taskId;
	}

	public ParentTask getParentTask()
	{
		return parentTask;
	}

	public void setParentTask(ParentTask parentTask)
	{
		this.parentTask = parentTask;
	}

	public Project getProject() 
	{
		return project;
	}

	public void setProject(Project project) 
	{
		this.project = project;
	}

	public String getTask()
	{
		return task;
	}

	public void setTask(String task) 
	{
		this.task = task;
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

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public Users getTaskManager() 
	{
		return taskManager;
	}

	public void setTaskManager(Users taskManager)
	{
		this.taskManager = taskManager;
	}

	public boolean isDisabled() 
	{
		return isDisabled;
	}

	public void setDisabled(boolean isDisabled) 
	{
		this.isDisabled = isDisabled;
	}

	@Override
	public String toString() 
	{
		return "Task [taskId=" + taskId + ", parentTask=" + parentTask + ", project=" + project + ", task=" + task
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority + ", status=" + status
				+ ", taskManager=" + taskManager + ", isDisabled=" + isDisabled + "]";
	}

	


}
