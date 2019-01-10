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

import com.projectmanagerapp.entity.ParentTask;
import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.service.ParentTaskService;
import com.projectmanagerapp.service.ProjectService;
import com.projectmanagerapp.service.TaskService;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.util.ProjectNotFoundException;
import com.projectmanagerapp.util.TaskNotFoundException;
import com.projectmanagerapp.util.UserNotFoundException;


@RestController
@CrossOrigin
@RequestMapping(value="/parenttasks")
public class ParentTaskController 
{
	@Autowired
	private ParentTaskService parentTaskService;
	
	@RequestMapping(value="/{projectId}",method=RequestMethod.GET)
	public ResponseEntity getATask(@PathVariable("projectId") int projectId)
	{
		List<ParentTask> parentTasks = parentTaskService.getAllParentTask(projectId);
		return new ResponseEntity(parentTasks,HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity createAParentTask(@RequestBody ParentTask newParentTask) 
	{
		parentTaskService.createParentTask(newParentTask);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity updateParentTask(@RequestBody ParentTask updateParentTask) 
	{
		parentTaskService.updateParentTask(updateParentTask);
		return new ResponseEntity(HttpStatus.CREATED);
	}

}
