package com.projectmanagerapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.repo.UserRepo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepoTest
{
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	UserRepo userRepo;
	
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
	public void getUser_getAllUserDetail()
	{
		Users savedUserOne = entityManager.persistAndFlush(userOne);
		Users savedUserTwo = entityManager.persistAndFlush(userTwo);
		List<Users> users= userRepo.getAllUsers();
		assertEquals(users.size(),2);	
		assert(users.contains(savedUserOne));	
		assert(users.contains(savedUserTwo));	
		
	}
	
	@Test
	public void getUser_getAUserDetail()
	{
		Users savedUserOne = entityManager.persistAndFlush(userOne);
		Users user= userRepo.findOne(savedUserOne.getUserId());
		assertEquals(user.getEmployeeId(),userOne.getEmployeeId());	
		assertEquals(user.getFirstName(),userOne.getFirstName());
		assertEquals(user.getLastName(),userOne.getLastName());
	}
	
	@Test
	public void updateUser_updateUserTest()
	{
		Users savedUserOne = entityManager.persistAndFlush(userOne);
		Users user= userRepo.findOne(savedUserOne.getUserId());
		assertEquals(user.getEmployeeId(),userOne.getEmployeeId());	
		assertEquals(user.getFirstName(),userOne.getFirstName());
		assertEquals(user.getLastName(),userOne.getLastName());
		
		user.setEmployeeId(111);
		
		userRepo.save(user);
		
		Users updatedUser= userRepo.findOne(savedUserOne.getUserId());
		assertEquals(updatedUser.getEmployeeId(),111);	
		assertEquals(updatedUser.getFirstName(),userOne.getFirstName());
		assertEquals(updatedUser.getLastName(),userOne.getLastName());		
	}
	
	@Test
	public void getUser_getAUserDetailNegative()
	{
		Users user= userRepo.findOne(3);
		assert(user==null);
	}
	

}
