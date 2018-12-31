package com.projectmanagerapp.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.projectmanagerapp.entity.ParentTask;
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.repo.ParentTaskRepo;
import com.projectmanagerapp.service.ParentTaskService;

@Service
public class ParentTaskServiceImpl implements ParentTaskService 
{
	@Autowired
	ParentTaskRepo parentTaskRepo;

	@Override
	@Cacheable("parentTask")
	public List<ParentTask> getAllParentTask() 
	{
		// TODO Auto-generated method stub
		return parentTaskRepo.findAll();
	}

	@Override
	@CacheEvict("parentTask")
	public void createParentTask(ParentTask parentTask) 
	{
		parentTaskRepo.save(parentTask);
	}

	
}