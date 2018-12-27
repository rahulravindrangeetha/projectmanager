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

import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.util.UserNotFoundException;


@RestController
@CrossOrigin
public class ProjectManagerControl 
{
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity getAllUsers()
	{
		List<Users> users = userService.getAllUserDetail();
		return new ResponseEntity(users,HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/{userId}",method=RequestMethod.GET)
	public ResponseEntity getAUserDetail(@PathVariable("userId") int userId) throws UserNotFoundException
	{
		Users user = userService.getAUserDetail(userId);
		return new ResponseEntity(user,HttpStatus.OK);
	}
	
	@RequestMapping(value="/users",method=RequestMethod.PUT)
	public ResponseEntity updateAUser(@RequestBody Users updatedUser) throws UserNotFoundException
	{
		userService.updateUser(updatedUser);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@ExceptionHandler()
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void userNotFoundHandler(UserNotFoundException unfe) 
	{
		
	}

}
