package com.projectmanagerapp.service;

import java.util.List;

import com.projectmanagerapp.entity.ParentTask;
import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.ParentTask;
import com.projectmanagerapp.entity.Users;

public interface ParentTaskService 
{
	

	public void createParentTask(ParentTask parentTask);
	
	public List<ParentTask> getAllParentTask(int projectId);
	
	public void updateParentTask(ParentTask parentTask) ;

}
