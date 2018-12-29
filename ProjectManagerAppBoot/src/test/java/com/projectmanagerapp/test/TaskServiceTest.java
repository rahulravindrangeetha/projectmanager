package com.projectmanagerapp.test;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.repo.ProjectRepo;
import com.projectmanagerapp.repo.TaskRepo;
import com.projectmanagerapp.repo.UserRepo;
import com.projectmanagerapp.service.ProjectService;
import com.projectmanagerapp.service.TaskService;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.serviceimpl.ProjectServiceImpl;
import com.projectmanagerapp.serviceimpl.TaskServiceImpl;
import com.projectmanagerapp.serviceimpl.UserServiceImpl;
import com.projectmanagerapp.util.TaskNotFoundException;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest 
{

	private List<Project> projects = new ArrayList<Project>();
	
	private Project projectOne;
	
	private Project projectTwo;
	
	private Users userOne;
	
	private Users userTwo;
	
	private Task taskOne;
	
	private Task taskTwo;
	
	private Task taskThree;
	
	private Task taskFour;
	
	@InjectMocks
	TaskService taskService=new TaskServiceImpl();
	
	@Mock
	TaskRepo taskRepo;
	

	@Before
	public void setUpData()
	{
		initMocks(this);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		userOne= new Users("rahul","rg",23);
		userTwo= new Users("geetha","ck",34);
		taskOne=new Task("abc task",LocalDate.parse("11-12-2018",formatter),LocalDate.parse("17-12-2018",formatter),25,"Completed");
		taskTwo=new Task("abc task2",LocalDate.parse("18-12-2018",formatter),LocalDate.parse("25-12-2018",formatter),3,"InProgress");
		
		taskThree=new Task("xyz task",LocalDate.parse("01-07-2018",formatter),LocalDate.parse("17-10-2018",formatter),15,"Completed");
		taskFour=new Task("xyz task2",LocalDate.parse("18-10-2018",formatter),LocalDate.parse("22-11-2018",formatter),19,"Completed");
		
		projectOne=new Project("abc",LocalDate.parse("11-12-2018",formatter),LocalDate.parse("25-12-2018",formatter),20,userOne);
		projectOne.setTasks(new ArrayList());
		projectOne.getTasks().add(taskOne);
		projectOne.getTasks().add(taskTwo);
		projectOne.setProjectManager(userOne);
		
		projectTwo=new Project("xyz",LocalDate.parse("01-07-2018",formatter),LocalDate.parse("22-11-2018",formatter),1,userTwo);
		projectTwo.setTasks(new ArrayList());
		projectTwo.getTasks().add(taskThree);
		projectTwo.getTasks().add(taskFour);
		projectTwo.setProjectManager(userTwo);
		projects.add(projectOne);
		projects.add(projectTwo);	
			
	}
	
	@Test
	public void createProject_createAProjectTest()
	{
		taskService.createTask(taskOne);	
		verify(taskRepo).save(Mockito.any(Task.class));		
	}
	

	
	@Test
	public void getTask_getATask()
	{
		BDDMockito.given(taskRepo.findOne(Mockito.anyInt()))
		.willReturn(taskOne);

		Task task = taskService.getATask(1);

		assertEquals(task.getEndDate(),taskOne.getEndDate());
		assertEquals(task.getPriority(),taskOne.getPriority());
		assertEquals(task.getTaskManager(),taskOne.getTaskManager());
		assertEquals(task.getStartDate(),taskOne.getStartDate());
		assertEquals(task.getStatus(),taskOne.getStatus());		
		assertEquals(task.getParentTask(),taskOne.getParentTask());
	}
	
	@Test(expected=TaskNotFoundException.class)
	public void getProject_getAProjectNegativeTest()
	{
		BDDMockito.given(taskRepo.findOne(Mockito.anyInt()))
		.willReturn(null);

		Task task = taskService.getATask(1);
	}
	
	@Test
	public void updateTask_updateATask()
	{
		
		BDDMockito.given(taskRepo.findOne(Mockito.anyInt()))
		.willReturn(taskOne);
		
		taskOne.setPriority(19);
		
		taskService.updateTask(taskOne);
		
		Task task=taskService.getATask(1);

		assertEquals(task.getEndDate(),taskOne.getEndDate());
		assertEquals(task.getPriority(),taskOne.getPriority());
		assertEquals(task.getTaskManager(),taskOne.getTaskManager());
		assertEquals(task.getStartDate(),taskOne.getStartDate());
		assertEquals(task.getStatus(),taskOne.getStatus());		
		assertEquals(task.getParentTask(),taskOne.getParentTask());
		
		
	}
	
	@Test(expected=TaskNotFoundException.class)
	public void updateTask_updateATaskException()
	{
		
		BDDMockito.given(taskRepo.findOne(Mockito.anyInt()))
		.willReturn(null);
		
		taskOne.setPriority(19);
		
		taskService.updateTask(taskOne);
		
	}

	

}
