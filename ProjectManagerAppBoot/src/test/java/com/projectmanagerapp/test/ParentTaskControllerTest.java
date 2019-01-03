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
import com.projectmanagerapp.controller.ParentTaskController;
import com.projectmanagerapp.controller.ProjectController;
import com.projectmanagerapp.controller.TaskController;
import com.projectmanagerapp.controller.UserController;
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

@RunWith(SpringRunner.class)
@WebMvcTest(ParentTaskController.class)
public class ParentTaskControllerTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	ParentTaskService parentTaskService;
	
	@Autowired
	ObjectMapper objectMapper;
	
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
	public void createParentTask_createAParentTaskTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/parenttasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(parentTaskOne)))
                .andExpect(MockMvcResultMatchers.status().isCreated());		
				
		
	}
	
	@Test
	public void getParentTask_getAllParentTask() throws Exception
	{

		BDDMockito.given(parentTaskService.getAllParentTask(1))
		.willReturn(parentTasks);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/parenttasks/1"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[0].parentTaskDesc",is("parent task1")))
			.andExpect(MockMvcResultMatchers.jsonPath("$.[1].parentTaskDesc",is("parent task2")))
			;
	}
}
