package com.yash.ppmtoolapi1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.ppmtoolapi1.domain.Backlog;
import com.yash.ppmtoolapi1.domain.ProjectTask;
import com.yash.ppmtoolapi1.repository.BacklogRepository;
import com.yash.ppmtoolapi1.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
@Autowired
private BacklogRepository backlogRepository;

@Autowired 
private ProjectTaskRepository projectTaskRepository;


public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

	//Exception Project Not Found
	
	Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
	
	projectTask.setBacklog(backlog);
	//System.out.print("pt"+backlog.getPTsequence());
	Integer backLogSequence = backlog.getPTsequence();
	backLogSequence++;
	backlog.setPTsequence(backLogSequence);
	projectTask.setProjectSequence(projectIdentifier+"-"+backLogSequence);
	projectTask.setProjectIdentifier(projectIdentifier);
	
	if(projectTask.getPriority()==null) {
		projectTask.setPriority(3);
	}
	if(projectTask.getStatus()==" " || projectTask.getStatus()==null) {
		projectTask.setStatus("TODO");
	}
	return projectTaskRepository.save(projectTask);
	
}


}
