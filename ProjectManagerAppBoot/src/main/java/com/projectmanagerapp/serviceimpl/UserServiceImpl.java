package com.projectmanagerapp.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
	@Cacheable("users")
	public List<Users> getAllUserDetail() 
	{
		System.out.println("repo"+userRepo);
		return userRepo.getAllUsers();
	}

	@Override
	@Cacheable("users")
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
	@CacheEvict("users")
	public void updateUser(Users updatedUser) 
	{
		Users user = userRepo.findOne(updatedUser.getUserId());
		
		if(user==null)
		{
			throw new UserNotFoundException();
		}
		else
		{
			userRepo.save(updatedUser);
		}
		
		
	}

	@Override
	@CacheEvict("users")
	public void createUser(Users newUser) 
	{
		userRepo.save(newUser);		
	}

	@Override
	@CacheEvict("users")
	public void deleteUser(int userId) throws UserNotFoundException
	{
		// TODO Auto-generated method stub
		Users user = userRepo.findOne(userId);
		if(user==null)
		{
			throw new UserNotFoundException();
		}
		else
		{
			user.setIsDeleted(1);
			userRepo.save(user);			
		}
		
	}

}
