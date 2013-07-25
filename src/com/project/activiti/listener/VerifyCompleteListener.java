package com.project.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Repository;

@Repository(value="verifyCompleteListener")
public class VerifyCompleteListener implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {
		System.out.println("任务已完成");
	}

}
