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
import com.projectmanagerapp.repo.UserRepo;
import com.projectmanagerapp.service.ProjectService;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.serviceimpl.ProjectServiceImpl;
import com.projectmanagerapp.serviceimpl.UserServiceImpl;
import com.projectmanagerapp.util.ProjectNotFoundException;
import com.projectmanagerapp.util.UserNotFoundException;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest 
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
	ProjectService projectService=new ProjectServiceImpl();
	
	@Mock
	ProjectRepo projectRepo;
	

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
	public void getProject_getAllProjectTest()
	{
		BDDMockito.given(projectRepo.findAll())
		.willReturn(projects);

		List<Project> projects = projectService.getAllProjects();

		assertEquals(projects.size(),2);	
		assert(projects.contains(projectOne));
		assert(projects.contains(projectTwo));
		assert(projects.get(0).getNoOfTasks()==2);
		assert(projects.get(0).getCompleted()==1);
		assert(projects.get(1).getNoOfTasks()==2);
		assert(projects.get(1).getCompleted()==2);
		
	}
	
	@Test
	public void getProject_getAProjectTest()
	{
		BDDMockito.given(projectRepo.findOne(Mockito.anyInt()))
		.willReturn(projectOne);

		Project project = projectService.getAProject(1);

		assertEquals(project.getEndDate(),projectOne.getEndDate());
		assertEquals(project.getCompleted(),projectOne.getCompleted());
		assertEquals(project.getNoOfTasks(),projectOne.getNoOfTasks());
		assertEquals(project.getPriority(),projectOne.getPriority());
		assertEquals(project.getProjectManager(),projectOne.getProjectManager());
		assertEquals(project.getStartDate(),projectOne.getStartDate());
		assertEquals(project.getTasks().size(),2);		
	}
	
	@Test(expected=ProjectNotFoundException.class)
	public void getProject_getAProjectNegativeTest()
	{
		BDDMockito.given(projectRepo.findOne(Mockito.anyInt()))
		.willReturn(null);

		Project project = projectService.getAProject(1);
	}
	
	@Test
	public void updateProject_updateAProjectTest()
	{
		
		BDDMockito.given(projectRepo.findOne(Mockito.anyInt()))
		.willReturn(projectOne);
		
		projectOne.setPriority(19);
		
		projectService.updateProject(projectOne);
		
		Project project=projectService.getAProject(1);

		assertEquals(project.getCompleted(),projectOne.getCompleted());
		assertEquals(project.getEndDate(),projectOne.getEndDate());
		assertEquals(project.getNoOfTasks(),projectOne.getNoOfTasks());
		assertEquals(project.getStartDate(),projectOne.getStartDate());
		assertEquals(project.getCompleted(),projectOne.getCompleted());
		assertEquals(project.getPriority(),19);
		
		
	}
	
	@Test
	public void suspendProject_suspendProjectTest()
	{	
		BDDMockito.given(projectRepo.findOne(Mockito.anyInt()))
		.willReturn(projectOne);
		projectService.suspendProject(1);
		verify(projectRepo).suspendProject((Mockito.any(Integer.class)));
	}
	
	/*@Test
	public void createUser_createAUserTest()
	{
		userService.createUser(userOne);	
		verify(userRepo).save(Mockito.any(Users.class));		
	}
	
	*/
	

}
