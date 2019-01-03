package com.projectmanagerapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.engine.spi.CascadeStyle;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="PARENT_TASK")
public class ParentTask implements Serializable
{
	@Id
	@Column(name="PARENT_ID")
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	@OneToMany(mappedBy="parentTask")
	@JsonIgnore
	private List<Task> tasks;
	
	@Column(name="PARENT_TASK")
	private String parentTaskDesc;
	
	@ManyToOne
	@JoinColumn(name="PROJECT_ID")
	private Project project;

	public int getId() 
	{
		return id;
	}
	
	public Project getProject() 
	{
		return project;
	}



	public void setProject(Project project)
	{
		this.project = project;
	}



	public void setParentTaskDesc(String parentTaskDesc) {
		this.parentTaskDesc = parentTaskDesc;
	}



	public void setId(int id) 
	{
		this.id = id;
	}

	public List<Task> getTasks()
	{
		return tasks;
	}

	public void setTasks(List<Task> tasks) 
	{
		this.tasks = tasks;
	}

	@Override
	public String toString() 
	{
		return "ParentTask [Id=" + id + ", tasks=" + tasks + ", parentTaskDesc=" + parentTaskDesc + "]";
	}

	public String getParentTaskDesc() 
	{
		return parentTaskDesc;
	}

	public void setParentTask(String parentTaskDesc)
	{
		this.parentTaskDesc = parentTaskDesc;
	}

	public ParentTask(String parentTaskDesc) 
	{
		super();
		this.parentTaskDesc = parentTaskDesc;
	}
	
	public ParentTask()
	{
		
	}
	
	
	
		
	
	

}
