package com.projectmanagerapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.projectmanagerapp.entity.ParentTask;
import com.projectmanagerapp.entity.Project;
import com.projectmanagerapp.entity.Task;
import com.projectmanagerapp.entity.Users;


@Repository("ParentTaskRepo")
public interface ParentTaskRepo extends JpaRepository<ParentTask, Integer>
{
	@Query("from ParentTask pt where pt.project.projectId=?1")
	List<ParentTask> getAllParentTasks(int projectId);

}
