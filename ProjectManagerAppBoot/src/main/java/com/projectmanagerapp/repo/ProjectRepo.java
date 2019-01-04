package com.projectmanagerapp.repo;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Users;


@Repository("ProjectRepo")
public interface ProjectRepo extends JpaRepository<Project, Integer>
{
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query("UPDATE Task T SET T.status='Suspended' WHERE T.project.projectId = ?1")
	void suspendProject(int projectId);
	
	@Query("select distinct new Project(p.projectId,p.project) from Project p join p.tasks as T with T.status<>'Suspended'")
	List<Project> getAllNonSuspendedProject();

}
