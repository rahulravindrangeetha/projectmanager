package com.projectmanagerapp.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.projectmanagerapp.entity.ParentTask;
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.repo.ParentTaskRepo;
import com.projectmanagerapp.repo.TaskRepo;
import com.projectmanagerapp.service.ParentTaskService;


@Service
public class ParentTaskServiceImpl implements ParentTaskService 
{
	@Autowired
	ParentTaskRepo parentTaskRepo;
	
	@Autowired
	TaskRepo taskRepo;

	@Override
	public List<ParentTask> getAllParentTask(int projectId) 
	{
		// TODO Auto-generated method stub
		return parentTaskRepo.getAllParentTasks(projectId);
	}
	
	@Override
	public void updateParentTask(ParentTask parentTask) 
	{
		parentTaskRepo.save(parentTask);
	}
	
	@Override
	public void createParentTask(ParentTask parentTask) 
	{
		parentTaskRepo.save(parentTask);
		Task task = new Task();
		task.setTask(parentTask.getParentTaskDesc());
		task.setProject(parentTask.getProject());
		task.setParentTask(parentTask);
		List<Task>  tasks = new ArrayList<Task>();
		tasks.add(task);
		parentTask.setTasks(tasks);
		task.setIsParentTask(1);
		task.setStatus("InProgress");
		taskRepo.save(task);
	}

	
}
