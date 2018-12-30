package com.projectmanagerapp.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.repo.TaskRepo;
import com.projectmanagerapp.service.TaskService;
import com.projectmanagerapp.util.TaskNotFoundException;

@Service
public class TaskServiceImpl implements TaskService 
{
	@Autowired
	TaskRepo taskRepo;

	@Override
	@Cacheable("tasks")
	public Task getATask(int taskId) throws TaskNotFoundException
	{
		Task task = taskRepo.findOne(taskId);
		if(task==null)
		{
			throw new TaskNotFoundException();
		}
		else
		{
			return task;
		}
	
	}

	@Override
	@CacheEvict(value = { "tasks", "projects" }, allEntries = true)
	public void updateTask(Task updatedTask) throws TaskNotFoundException
	{
		Task task = taskRepo.findOne(updatedTask.getTaskId());
		if(task==null)
		{
			throw new TaskNotFoundException();
		}
		else
		{
			taskRepo.save(updatedTask);
		}
		

	}

	@Override
	@CacheEvict(value = { "tasks", "projects" }, allEntries = true)
	public void createTask(Task newTask)
	{
		taskRepo.save(newTask);

	}

	@Override
	@CacheEvict(value = { "tasks", "projects" }, allEntries = true)
	public void endTask(Task endTask)
	{
		endTask.setStatus("Completed");
		taskRepo.save(endTask);
		
	}

}
