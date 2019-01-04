package com.projectmanagerapp.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.projectmanagerapp.entity.ParentTask;
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
		newTask.setStatus("InProgress");
		taskRepo.save(newTask);

	}

	@Override
	@CacheEvict(value = { "tasks", "projects" }, allEntries = true)
	public void endTask(Task endTask)
	{
		endTask.setStatus("Completed");
		taskRepo.save(endTask);
		
	}

	@Override
	public List<Task> getAllTasks(int projectId) 
	{
		List<Task> tasks=taskRepo.getAllTasks(projectId);
		List<Task> noParentTask=tasks.stream().filter(t -> t.getParentTask()==null).collect(Collectors.toList());
		List<Task> withParentTask=tasks.stream().filter(t -> t.getParentTask()!=null).collect(Collectors.toList());
		for(Task task:noParentTask)
		{
			task.setParentTask(new ParentTask("This Task has no Parent"));
		}
		tasks.clear();
		tasks.addAll(noParentTask);
		tasks.addAll(withParentTask);
		
		return tasks;
	}

}
