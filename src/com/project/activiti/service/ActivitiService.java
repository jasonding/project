package com.project.activiti.service;

import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="activitiService")
public class ActivitiService {
	private ProcessEngine processEngine;

	@Autowired
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}
	protected ProcessEngine getProcessEngine() {
		return processEngine;
	}

	
}
