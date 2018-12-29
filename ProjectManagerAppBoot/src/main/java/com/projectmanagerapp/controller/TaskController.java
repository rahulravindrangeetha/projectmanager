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
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.service.ProjectService;
import com.projectmanagerapp.service.TaskService;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.util.ProjectNotFoundException;
import com.projectmanagerapp.util.TaskNotFoundException;
import com.projectmanagerapp.util.UserNotFoundException;


@RestController
@CrossOrigin
@RequestMapping(value="/tasks")
public class TaskController 
{
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value="/{taskId}",method=RequestMethod.GET)
	public ResponseEntity getATask(@PathVariable("taskId") int taskId) throws ProjectNotFoundException
	{
		Task task = taskService.getATask(taskId);
		return new ResponseEntity(task,HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity updateATask(@RequestBody Task updatedTask) throws ProjectNotFoundException
	{
		Task task = taskService.getATask(updatedTask.getTaskId());
		if(task==null)
		{
			throw new TaskNotFoundException();
		}
		else
		{
			taskService.updateTask(updatedTask);
			return new ResponseEntity(HttpStatus.OK);
		}	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity createATask(@RequestBody Task newTask) 
	{
		taskService.createTask(newTask);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	
	@ExceptionHandler()
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void taskNotFoundHandler(TaskNotFoundException tnfe) 
	{
		
	}
	
	

}
