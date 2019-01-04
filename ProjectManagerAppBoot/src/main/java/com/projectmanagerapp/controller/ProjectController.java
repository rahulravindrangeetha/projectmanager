package com.projectmanagerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.service.ProjectService;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.util.ProjectNotFoundException;
import com.projectmanagerapp.util.UserNotFoundException;


@RestController
@CrossOrigin
@RequestMapping(value="/projects")
public class ProjectController 
{
	@Autowired
	private ProjectService projectService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity getAllProjects()
	{
		List<Project> projects = projectService.getAllProjects();
		return new ResponseEntity(projects,HttpStatus.OK);
	}
	
	@RequestMapping(value="/nonsuspendedproject",method=RequestMethod.GET)
	public ResponseEntity getAllNonSuspendedProjects()
	{
		List<Project> projects = projectService.getAllNonSuspendedProjects();
		return new ResponseEntity(projects,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{projectId}",method=RequestMethod.GET)
	public ResponseEntity getAProjectDetail(@PathVariable("projectId") int projectId) throws ProjectNotFoundException
	{
		Project project = projectService.getAProject(projectId);
		return new ResponseEntity(project,HttpStatus.OK);
	}
	
	@RequestMapping(value="/{projectId}",method=RequestMethod.PUT)
	public ResponseEntity updateAProject(@RequestBody Project updatedProject,@PathVariable("projectId") int projectId) throws ProjectNotFoundException
	{
		Project project = projectService.getAProject(projectId);
		if(project==null)
		{
			throw new ProjectNotFoundException();
		}
		else
		{
			projectService.updateProject(updatedProject);
			return new ResponseEntity(HttpStatus.OK);
		}	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity createAProject(@RequestBody Project newProject) 
	{
		projectService.createProject(newProject);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/suspend/{projectId}",method=RequestMethod.PUT)
	public ResponseEntity suspendProject(@PathVariable("projectId") int projectId) throws UserNotFoundException
	{
		projectService.suspendProject(projectId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@ExceptionHandler()
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void projectNotFoundHandler(ProjectNotFoundException pnfe) 
	{
		
	}
	
	

}
