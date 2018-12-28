package com.projectmanagerapp.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanagerapp.entity.Users;
import com.projectmanagerapp.repo.UserRepo;
import com.projectmanagerapp.service.UserService;
import com.projectmanagerapp.util.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	private UserRepo userRepo;
	

	@Override
	public List<Users> getAllUserDetail() 
	{
		System.out.println("repo"+userRepo);
		return userRepo.getAllUsers();
	}

	@Override
	public Users getAUserDetail(int userId) throws UserNotFoundException
	{
		Users user = userRepo.findOne(userId);
		
		if(user==null)
		{
			throw new UserNotFoundException();
		}
		else
		{
			return user;
		}
		
	}

	@Override
	public void updateUser(Users updatedUser) 
	{
		userRepo.save(updatedUser);
		
	}

	@Override
	public void createUser(Users newUser) 
	{
		userRepo.save(newUser);		
	}

	@Override
	public void deleteUser(Users userToBeDeleted) throws UserNotFoundException
	{
		// TODO Auto-generated method stub
		Users user = userRepo.findOne(userToBeDeleted.getUserId());
		if(user==null)
		{
			throw new UserNotFoundException();
		}
		else
		{
			userToBeDeleted.setIsDeleted(1);
			userRepo.save(userToBeDeleted);			
		}
		
	}

}
