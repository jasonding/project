package com.project.web.action.activiti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.activiti.service.ActivitiService;
import com.project.web.action.BaseAction;
@Controller(value="LeaveAction")
@Scope(value="prototype")
public class LeaveAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ActivitiService activitiService;
	
	public String createLeave() {
		
		return "";
	}
	
	
}
