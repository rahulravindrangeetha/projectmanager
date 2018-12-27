package com.projectmanagerapp.service;

import java.util.List;

import com.projectmanagerapp.entity.Users;

public interface UserService 
{
	public List<Users> getAllUserDetail();
	
	public Users getAUserDetail(int userId);
	
	public void updateUser(Users updatedUser);

}
