package com.yash.ppmtoolapi1.exception;

public class ProjectNotFoundExceptionResponse {
	
	String projectNotFound;
	

	public ProjectNotFoundExceptionResponse(String projectNotFound) {
		super();
		this.projectNotFound=projectNotFound;
	}

	public String getProjectNotFound() {
		return projectNotFound;
	}

	public void setProjectNotFound(String projectNotFound) {
		this.projectNotFound = projectNotFound;
	}
	

}
