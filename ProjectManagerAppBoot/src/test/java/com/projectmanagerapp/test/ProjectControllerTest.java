package com.projectmanagerapp.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectmanagerapp.controller.ProjectController;
import com.projectmanagerapp.controller.UserController;
import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.service.ProjectService;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.util.ProjectNotFoundException;
import com.projectmanagerapp.util.UserNotFoundException;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ProjectService projectService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	private List<Project> projects = new ArrayList<Project>();
	
	private Project projectOne;
	
	private Project projectTwo;
	
	private Users userOne;
	
	private Users userTwo;
	
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
		
		projectTwo=new Project("xyz",LocalDate.parse("01-07-2018",formatter),LocalDate.parse("22-11-2018",formatter),1,userTwo);
		projectTwo.setCompleted(2);
		projectTwo.setNoOfTasks(2);
		projectTwo.setTasks(new ArrayList());
		projectTwo.getTasks().add(taskThree);
		projectTwo.getTasks().add(taskFour);
		
		projects.add(projectOne);
		projects.add(projectTwo);
		
	}
	
	@Test
	public void getProject_shouldGetAllProjectInfo() throws Exception
	{
		
			BDDMockito.given(projectService.getAllProjects())
			.willReturn(projects);
			
			mockMvc.perform(MockMvcRequestBuilders.get("/projects"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].project",is("abc")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].startDate",is("11-12-2018")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].endDate",is("25-12-2018")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].priority",is(20)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].completed",is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].noOfTasks",is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].projectManager.firstName",is("rahul")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].projectManager.lastName",is("rg")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].projectManager.employeeId",is(23)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].project",is("xyz")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].startDate",is("01-07-2018")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].endDate",is("22-11-2018")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].priority",is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].completed",is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].noOfTasks",is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].projectManager.firstName",is("geetha")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].projectManager.lastName",is("ck")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].projectManager.employeeId",is(34)))
				;
	}
	
	@Test
	public void getUser_shouldGetAParticularUserInfo() throws Exception
	{

			
			BDDMockito.given(projectService.getAProject(Mockito.anyInt()))
			.willReturn(projectOne);
			
			mockMvc.perform(MockMvcRequestBuilders.get("/projects/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("project",is("abc")))
				.andExpect(MockMvcResultMatchers.jsonPath("startDate",is("11-12-2018")))
				.andExpect(MockMvcResultMatchers.jsonPath("endDate",is("25-12-2018")))
				.andExpect(MockMvcResultMatchers.jsonPath("priority",is(20)))
				.andExpect(MockMvcResultMatchers.jsonPath("completed",is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("noOfTasks",is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("projectManager.firstName",is("rahul")))
				.andExpect(MockMvcResultMatchers.jsonPath("projectManager.lastName",is("rg")))
				.andExpect(MockMvcResultMatchers.jsonPath("projectManager.employeeId",is(23)));
				
		
	}
	
	
	@Test
	public void getProject_thowProjectException() throws Exception
	{
			BDDMockito.given(projectService.getAProject(Mockito.anyInt()))
			.willThrow(new ProjectNotFoundException());
			
			mockMvc.perform(MockMvcRequestBuilders.get("/projects/1"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
				
	}
	
	@Test
	public void updateProject_updateAProjectInfo() throws Exception
	{

			BDDMockito.given(projectService.getAProject(Mockito.anyInt()))
			.willReturn(projectOne);
			
			mockMvc.perform(MockMvcRequestBuilders.get("/projects/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("project",is("abc")))
				.andExpect(MockMvcResultMatchers.jsonPath("startDate",is("11-12-2018")))
				.andExpect(MockMvcResultMatchers.jsonPath("endDate",is("25-12-2018")))
				.andExpect(MockMvcResultMatchers.jsonPath("priority",is(20)))
				.andExpect(MockMvcResultMatchers.jsonPath("completed",is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("noOfTasks",is(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("projectManager.firstName",is("rahul")))
				.andExpect(MockMvcResultMatchers.jsonPath("projectManager.lastName",is("rg")))
				.andExpect(MockMvcResultMatchers.jsonPath("projectManager.employeeId",is(23)));
			
			BDDMockito.given(projectService.getAProject(Mockito.anyInt()))
			.willReturn(projectOne);
			
			projectOne.setPriority(13);
			
			mockMvc.perform(MockMvcRequestBuilders.put("/projects/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(projectOne)))
	                .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void updateProject_updateAProjectInfoNegative() throws Exception
	{

			BDDMockito.given(projectService.getAProject(Mockito.anyInt()))
			.willReturn(null);
					
			projectOne.setPriority(13);
			
			mockMvc.perform(MockMvcRequestBuilders.put("/projects/1")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(projectOne)))
	                .andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void suspendProject_suspendAProject() throws Exception
	{
		BDDMockito.given(projectService.getAProject(Mockito.anyInt()))
		.willReturn(projectOne);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/projects/suspend/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(projectOne)))
                .andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	/*@Test
	public void createUser_userCreatedTest()
	{
		try 
		{		
			mockMvc.perform(MockMvcRequestBuilders.post("/users")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(userOne)))
	                .andExpect(MockMvcResultMatchers.status().isCreated());			
				
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
