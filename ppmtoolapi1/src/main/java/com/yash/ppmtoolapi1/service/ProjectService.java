package com.yash.ppmtoolapi1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yash.ppmtoolapi1.domain.Backlog;
import com.yash.ppmtoolapi1.domain.Project;
import com.yash.ppmtoolapi1.exception.PojectIdException;
import com.yash.ppmtoolapi1.exception.ProjectIdExceptionResponse;
import com.yash.ppmtoolapi1.repository.BacklogRepository;
import com.yash.ppmtoolapi1.repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired 
	BacklogRepository backlogRepository;
	
	public Project saveOrUpdateProject(Project project) {
		try {
			
			
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			//while saving project backlog  should be saved
			//if project created first time new backlog will be created
			if(project.getId()==null)
			{
				Backlog backlog =new Backlog();
				project.setBacklog(backlog); //one to one relationship
				backlog.setProject(project); //one to one relationship
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			//while updating project backlog should not be null
			//same projectIdentifier should be set in backlog
			if(project.getId()!=null)
			{
				System.out.print("in service");
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
				
			return projectRepository.save(project);
		}catch(Exception ex) {
			throw new PojectIdException("Project Id '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
		}
		
	}
	
	public Project findProjectByIdentifier(String projectId) {
		Project project=projectRepository.findByProjectIdentifier(projectId);
		if(project==null) {
			throw new PojectIdException("Project Id '"+projectId+"' does not exist");
		}
		return project;
	}
	
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectByIdentifier(String projectId) {
		Project project=projectRepository.findByProjectIdentifier(projectId);
		if(project==null) {
			throw new PojectIdException("Cannot delete project wid id '"+projectId+"' this project not exist");
		}
		projectRepository.delete(project);
		
	}
}
