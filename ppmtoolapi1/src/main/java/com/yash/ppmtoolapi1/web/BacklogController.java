package com.yash.ppmtoolapi1.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.ppmtoolapi1.domain.ProjectTask;
import com.yash.ppmtoolapi1.service.MapValidationErrorService;
import com.yash.ppmtoolapi1.service.ProjectTaskService;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/{project_Identifier}")
	public ResponseEntity<?> addToBacklog(@Valid @RequestBody ProjectTask projectTask,BindingResult result, 
			@PathVariable String  project_Identifier)
	{
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null)
			return errorMap;
		//System.out.print(project_Identifier);
		ProjectTask projectTask1= projectTaskService.addProjectTask(project_Identifier,projectTask);
		return new ResponseEntity<ProjectTask>(projectTask1,HttpStatus.CREATED);
	}
	
}
