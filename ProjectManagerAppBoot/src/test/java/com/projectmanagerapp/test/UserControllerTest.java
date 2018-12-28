package com.projectmanagerapp.test;

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
import com.projectmanagerapp.controller.UserController;
import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.util.UserNotFoundException;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest 
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	private List<Users> users = new ArrayList<Users>();
	
	private Users userOne;
	
	private Users userTwo;
	
	@Before
	public void setUpData()
	{
		userOne= new Users("rahul","rg",23);
		userTwo= new Users("geetha","ck",34);
		users.add(userOne);
		users.add(userTwo);		
	}
	
	@Test
	public void getUser_shouldGetAllUserInfo() throws Exception
	{

			BDDMockito.given(userService.getAllUserDetail())
			.willReturn(users);
			
			mockMvc.perform(MockMvcRequestBuilders.get("/users"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].firstName",is("rahul")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].lastName",is("rg")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0].employeeId",is(23)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].firstName",is("geetha")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].lastName",is("ck")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.[1].employeeId",is(34)))
				;
				
	}
	
	@Test
	public void getUser_shouldGetAParticularUserInfo() throws Exception
	{
			BDDMockito.given(userService.getAUserDetail(Mockito.anyInt()))
			.willReturn(userOne);
			
			mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("firstName",is("rahul")))
				.andExpect(MockMvcResultMatchers.jsonPath("lastName",is("rg")))
				.andExpect(MockMvcResultMatchers.jsonPath("employeeId",is(23)));
	}
	
	@Test
	public void getUser_thowUserException() throws Exception
	{

			
			BDDMockito.given(userService.getAUserDetail(Mockito.anyInt()))
			.willThrow(new UserNotFoundException());
			
			mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
				
	}
	
	@Test
	public void updateUser_updateAUserInfo() throws Exception
	{
			BDDMockito.given(userService.getAUserDetail(Mockito.anyInt()))
			.willReturn(userOne);
			
			mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("firstName",is("rahul")))
				.andExpect(MockMvcResultMatchers.jsonPath("lastName",is("rg")))
				.andExpect(MockMvcResultMatchers.jsonPath("employeeId",is(23)));
			
			BDDMockito.given(userService.getAUserDetail(Mockito.anyInt()))
			.willReturn(userOne);
			
			userOne.setEmployeeId(22);
			
			mockMvc.perform(MockMvcRequestBuilders.put("/users")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(userOne)))
	                .andExpect(MockMvcResultMatchers.status().isOk());
				
	}
	
	@Test
	public void deleteUser_deleteAUser() throws Exception
	{
	
			mockMvc.perform(MockMvcRequestBuilders.delete("/users")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(userOne)))
	                .andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void createUser_userCreatedTest() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/users")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(userOne)))
	                .andExpect(MockMvcResultMatchers.status().isCreated());						
		
	}

}
