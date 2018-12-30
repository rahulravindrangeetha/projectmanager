package com.projectmanagerapp.test;



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

import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.repo.UserRepo;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.serviceimpl.UserServiceImpl;
import com.projectmanagerapp.util.UserNotFoundException;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest 
{

	private List<Users> users = new ArrayList<Users>();
	
	private Users userOne;
	
	private Users userTwo;
	
	@InjectMocks
	UserService userService=new UserServiceImpl();
	
	@Mock
	UserRepo userRepo;
	

	@Before
	public void setUpData()
	{
		initMocks(this);
		userOne= new Users("rahul","rg",23);
		userTwo= new Users("geetha","ck",34);
		users.add(userOne);
		users.add(userTwo);		
	}
	
	@Test
	public void getUsers_getAllUsersTest()
	{
		BDDMockito.given(userRepo.getAllUsers())
		.willReturn(users);

		List<Users> users = userService.getAllUserDetail();

		assertEquals(users.size(),2);		
	}
	
	@Test
	public void getUsers_getAUserTest()
	{
		BDDMockito.given(userRepo.findOne(Mockito.anyInt()))
		.willReturn(userOne);

		Users user = userService.getAUserDetail(1);

		assertEquals(user.getFirstName(),userOne.getFirstName());
		assertEquals(user.getLastName(),userOne.getLastName());
		assertEquals(user.getEmployeeId(),userOne.getEmployeeId());
		
		
	}
	
	@Test
	public void updateUser_updateAUserTest()
	{
		
		BDDMockito.given(userRepo.findOne(Mockito.anyInt()))
		.willReturn(userOne);
		
		userOne.setEmployeeId(111);
		
		userService.updateUser(userOne);
		
		BDDMockito.given(userRepo.findOne(Mockito.anyInt()))
		.willReturn(userOne);


		Users user = userService.getAUserDetail(1);

		assertEquals(user.getFirstName(),userOne.getFirstName());
		assertEquals(user.getLastName(),userOne.getLastName());
		assertEquals(user.getEmployeeId(),111);
		
		
	}
	
	@Test(expected = UserNotFoundException.class)
	public void updateUser_updateAUserNegativeTest()
	{
		
		BDDMockito.given(userRepo.findOne(Mockito.anyInt()))
		.willReturn(null);
		
		userOne.setEmployeeId(111);
		
		userService.updateUser(userOne);	
		
	}
	
	@Test
	public void createUser_createAUserTest()
	{
		userService.createUser(userOne);	
		verify(userRepo).save(Mockito.any(Users.class));		
	}
	
	@Test
	public void deleteUser_deleteAUserTest()
	{	
		BDDMockito.given(userRepo.findOne(Mockito.anyInt()))
		.willReturn(userOne);
		userService.deleteUser(userOne);
		verify(userRepo).save(Mockito.any(Users.class));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void getUsers_getAUserNegativeTest()
	{
		BDDMockito.given(userRepo.findOne(Mockito.anyInt()))
		.willReturn(null);

		Users user = userService.getAUserDetail(1);
		
	}
	
	

}
