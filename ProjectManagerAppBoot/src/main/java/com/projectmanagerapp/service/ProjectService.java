package com.projectmanagerapp.service;

import java.util.List;

import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Users;

public interface ProjectService 
{
	public List<Project> getAllProjects();
	
	public Project getAProject(int projectId);
	
	public void updateProject(Project updatedProject);
	
	public void createProject(Project newProject);

	public void suspendProject(int projectId);

}
