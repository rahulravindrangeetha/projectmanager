package com.projectmanagerapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.repo.ProjectRepo;
import com.projectmanagerapp.repo.UserRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepoTest
{
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	ProjectRepo projectRepo;
	
	private Users userOne;
	
	private Users userTwo;
	
	private List<Project> projects = new ArrayList<Project>();
	
	private Project projectOne;
	
	private Project projectTwo;
	
	private Task taskOne;
	
	private Task taskTwo;
	
	private Task taskThree;
	
	private Task taskFour;
	
	@Before
	public void setUpData()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		userOne= new Users("rahul","rg",23);
		userTwo= new Users("geetha","ck",34);
		taskOne=new Task("abc task",LocalDate.parse("11-12-2018",formatter),LocalDate.parse("17-12-2018",formatter),25,"Completed");
		taskTwo=new Task("abc task2",LocalDate.parse("18-12-2018",formatter),LocalDate.parse("25-12-2018",formatter),3,"InProgress");
		
		taskThree=new Task("xyz task",LocalDate.parse("01-07-2018",formatter),LocalDate.parse("17-10-2018",formatter),15,"Completed");
		taskFour=new Task("xyz task2",LocalDate.parse("18-10-2018",formatter),LocalDate.parse("22-11-2018",formatter),19,"Completed");
		
		projectOne=new Project("abc",LocalDate.parse("11-12-2018",formatter),LocalDate.parse("25-12-2018",formatter),20,userOne);
		projectOne.setCompleted(1);
		projectOne.setNoOfTasks(2);
		projectOne.setTasks(new ArrayList());
		projectOne.getTasks().add(taskOne);
		projectOne.getTasks().add(taskTwo);
		taskOne.setProject(projectOne);
		taskTwo.setProject(projectOne);
		
		projectTwo=new Project("xyz",LocalDate.parse("01-07-2018",formatter),LocalDate.parse("22-11-2018",formatter),1,userTwo);
		projectTwo.setCompleted(2);
		projectTwo.setNoOfTasks(2);
		projectTwo.setTasks(new ArrayList());
		projectTwo.getTasks().add(taskThree);
		projectTwo.getTasks().add(taskFour);
		taskThree.setProject(projectTwo);
		taskFour.setProject(projectTwo);
		
		projects.add(projectOne);
		projects.add(projectTwo);	
	}
	
	@Test
	public void getProject_getAllProject()
	{
		Users savedUserOne = entityManager.persistAndFlush(userOne);
		Users savedUserTwo = entityManager.persistAndFlush(userTwo);
		Project savedProjectOne = entityManager.persistAndFlush(projectOne);
		Project savedProjectTwo = entityManager.persistAndFlush(projectTwo);
		Task savedTaskOne = entityManager.persistAndFlush(taskOne);
		Task savedTaskTwo = entityManager.persistAndFlush(taskTwo);
		Task savedTaskThree = entityManager.persistAndFlush(taskThree);
		Task savedTaskFour = entityManager.persistAndFlush(taskFour);
		List<Project> projects= projectRepo.findAll();
		assertEquals(projects.size(),2);	
		assert(projects.contains(savedProjectOne));	
		assert(projects.contains(savedProjectTwo));	
		assert(savedProjectOne.getTasks().size()==2);
		assert(savedProjectTwo.getTasks().size()==2);
		
	}
	
	@Test
	public void suspendProject_suspendAllTasks()
	{
		Users savedUserOne = entityManager.persistAndFlush(userOne);
		Project savedProjectOne = entityManager.persistAndFlush(projectOne);
		
		Task savedTaskOne = entityManager.persistAndFlush(taskOne);
		Task savedTaskTwo = entityManager.persistAndFlush(taskTwo);

		Project project= projectRepo.findOne(savedProjectOne.getProjectId());

		assert(savedProjectOne.getTasks().size()==2);
		assert(!project.getTasks().get(0).getStatus().equalsIgnoreCase("Suspended"));
		assert(!project.getTasks().get(1).getStatus().equalsIgnoreCase("Suspended"));
		projectRepo.suspendProject(savedProjectOne.getProjectId());
		
		Project project2=projectRepo.findOne(savedProjectOne.getProjectId());
		assert(project2.getTasks().get(0).getStatus().equalsIgnoreCase("Suspended"));
		assert(project2.getTasks().get(1).getStatus().equalsIgnoreCase("Suspended"));
		
		
	}
	
	
	

}
