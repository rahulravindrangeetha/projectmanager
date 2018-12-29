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

import com.projectmanagerapp.entity.ParentTask;
import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.repo.ParentTaskRepo;
import com.projectmanagerapp.repo.ProjectRepo;
import com.projectmanagerapp.repo.TaskRepo;
import com.projectmanagerapp.repo.UserRepo;
import com.projectmanagerapp.service.ParentTaskService;
import com.projectmanagerapp.service.ProjectService;
import com.projectmanagerapp.service.TaskService;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.serviceimpl.ParentTaskServiceImpl;
import com.projectmanagerapp.serviceimpl.ProjectServiceImpl;
import com.projectmanagerapp.serviceimpl.TaskServiceImpl;
import com.projectmanagerapp.serviceimpl.UserServiceImpl;
import com.projectmanagerapp.util.TaskNotFoundException;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ParentTaskServiceTest 
{
	
private List<ParentTask> parentTasks = new ArrayList<ParentTask>();
	
	private Project projectOne;
	
	private Project projectTwo;
	
	private Users userOne;
	
	private Users userTwo;
	
	private Task taskOne;
	
	private Task taskTwo;
	
	private Task taskThree;
	
	private Task taskFour;
	
	private ParentTask parentTaskOne;
	
	private ParentTask parentTaskTwo;
	
	@InjectMocks
	ParentTaskService parentTaskService=new ParentTaskServiceImpl();
	
	@Mock
	ParentTaskRepo parentTaskRepo;
	

	@Before
	public void setUpData()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		parentTaskOne = new ParentTask("parent task1");
		parentTaskTwo = new ParentTask("parent task2");
		userOne= new Users("rahul","rg",23);
		userTwo= new Users("geetha","ck",34);
		taskOne=new Task("abc task",LocalDate.parse("11-12-2018",formatter),LocalDate.parse("17-12-2018",formatter),25,"InProgress");
		taskTwo=new Task("abc task2",LocalDate.parse("18-12-2018",formatter),LocalDate.parse("25-12-2018",formatter),3,"InProgress");
		
		taskThree=new Task("xyz task",LocalDate.parse("01-07-2018",formatter),LocalDate.parse("17-10-2018",formatter),15,"Completed");
		taskFour=new Task("xyz task2",LocalDate.parse("18-10-2018",formatter),LocalDate.parse("22-11-2018",formatter),19,"Completed");
		
		taskOne.setTaskManager(userOne);
		parentTaskOne.setTasks(new ArrayList());
		parentTaskOne.getTasks().add(taskOne);
		taskOne.setParentTask(parentTaskOne);
		projectOne=new Project("abc",LocalDate.parse("11-12-2018",formatter),LocalDate.parse("25-12-2018",formatter),20,userOne);
		projectOne.setCompleted(1);
		projectOne.setNoOfTasks(2);
		projectOne.setTasks(new ArrayList());
		projectOne.getTasks().add(taskOne);
		projectOne.getTasks().add(taskTwo);
		
		projectTwo=new Project("xyz",LocalDate.parse("01-07-2018",formatter),LocalDate.parse("22-11-2018",formatter),1,userTwo);
		projectTwo.setCompleted(2);
		projectTwo.setNoOfTasks(2);
		projectTwo.setTasks(new ArrayList());
		projectTwo.getTasks().add(taskThree);
		projectTwo.getTasks().add(taskFour);
		
		parentTaskTwo.setTasks(new ArrayList());
		parentTaskTwo.getTasks().add(taskTwo);
		taskTwo.setParentTask(parentTaskTwo);

		parentTasks.add(parentTaskOne);
		parentTasks.add(parentTaskTwo);
		
	}
	
	@Test
	public void createParentTask_createAParentTaskTest()
	{
		parentTaskService.createParentTask(parentTaskOne);	
		verify(parentTaskRepo).save(Mockito.any(ParentTask.class));		
	}
	

	
	@Test
	public void getTask_getATask()
	{
		BDDMockito.given(parentTaskRepo.findAll())
		.willReturn(parentTasks);

		List<ParentTask> parentTasks = parentTaskService.getAllParentTask();

		assertEquals(parentTasks.size(),2);
	}
	

}
