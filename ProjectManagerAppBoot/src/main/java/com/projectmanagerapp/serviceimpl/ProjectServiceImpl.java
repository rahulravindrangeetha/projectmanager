package com.projectmanagerapp.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.repo.ProjectRepo;
import com.projectmanagerapp.repo.UserRepo;
import com.projectmanagerapp.service.ProjectService;
import com.projectmanagerapp.util.ProjectNotFoundException;

@Service
public class ProjectServiceImpl implements ProjectService 
{
	@Autowired
	private ProjectRepo projectRepo;

	@Override
	public List<Project> getAllProjects() 
	{
		List<Project> projects=projectRepo.findAll();
		for(Project project:projects)
		{
			List<Task> tasks=project.getTasks();
			if(tasks!=null)
			{
				List<Task> completedTasks=tasks.stream().filter(t -> t.getStatus().equalsIgnoreCase("Completed")).collect(Collectors.toList());
				project.setNoOfTasks(tasks.size());
				project.setCompleted(completedTasks.size());
			}
		}
		return projects;
	}

	@Override
	public Project getAProject(int projectId) throws ProjectNotFoundException
	{
		Project project=projectRepo.findOne(projectId);
		if(project==null)
		{
			throw new ProjectNotFoundException();
		}
		else
		{
			return project;
		}
	}

	@Override
	public void updateProject(Project updatedProject) 
	{
		projectRepo.save(updatedProject);
	}

	@Override
	public void createProject(Project newProject) 
	{
		projectRepo.save(newProject);

	}

	@Override
	public void suspendProject(int projectId) throws ProjectNotFoundException
	{
		Project project=projectRepo.findOne(projectId);
		if(project==null)
		{
			throw new ProjectNotFoundException();
		}
		else
		{
			projectRepo.suspendProject(projectId);
		}
	}

}
