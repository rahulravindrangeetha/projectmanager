package com.projectmanagerapp.repo;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Users;


@Repository("UserRepo")
public interface UserRepo extends JpaRepository<Users, Integer>
{
	@Query("from Users u where u.isDeleted=0")
	List<Users> getAllUsers();
	
	@Query("from Users u where u.isDeleted=0 and u.userId=?1")
    Users findOne(int userId);

	
}
