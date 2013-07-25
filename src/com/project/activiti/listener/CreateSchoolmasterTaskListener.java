package com.project.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Repository;

@Repository(value="schoolmasterTaskListener")
public class CreateSchoolmasterTaskListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("创建任务通知到了");
		delegateTask.setOwner("schoolMaster");
		delegateTask.setAssignee("schoolMaster");
	}
}
