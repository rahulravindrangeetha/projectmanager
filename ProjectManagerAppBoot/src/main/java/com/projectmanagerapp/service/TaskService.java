package com.projectmanagerapp.service;

import java.util.List;

import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.entity.Users;

public interface TaskService 
{
	
	public Task getATask(int taskId);
	
	public void updateTask(Task updatedTask);
	
	public void createTask(Task newTask);

}
