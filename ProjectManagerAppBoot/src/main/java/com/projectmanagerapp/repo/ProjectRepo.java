package com.projectmanagerapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Users;


@Repository("ProjectRepo")
public interface ProjectRepo extends JpaRepository<Project, Integer>
{
	@Modifying
	@Query("UPDATE Task T SET T.status='Suspend' WHERE T.project.projectId = ?1")
	void suspendProject(int projectId);
}
