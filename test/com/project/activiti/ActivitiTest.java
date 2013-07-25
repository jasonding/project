package com.project.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActivitiTest {
	public void testActiviti() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProcessEngine processEngine = (ProcessEngine) context.getBean("processEngine");
		System.out.println(processEngine);
		System.out.println(processEngine.getRepositoryService());
	}
	
	@Test
	public void testSchoolTask() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProcessEngine processEngine = (ProcessEngineImpl) context.getBean("processEngine");
		RuntimeService runtimeService = processEngine.getRuntimeService();
		TaskService taskService = processEngine.getTaskService();
		Map<String,Object> variables = new HashMap<String, Object>();
		variables.put("task", "task is completed");
		runtimeService.startProcessInstanceByKey("schoolTaskProcess",variables );
		variables.clear();
		List<Task> list = taskService.createTaskQuery().taskAssignee("jason").list();
		for (Task task : list) {
			System.out.println(task.getName());
			System.out.println(task.getExecutionId());
			System.out.println(taskService.getVariables(task.getId()));
			taskService.setOwner(task.getId(), task.getAssignee());
			variables.put("agree", true);
			taskService.setVariablesLocal(task.getId(), variables);
			taskService.complete(task.getId(),variables);
			
		}
		variables.clear();
		list = taskService.createTaskQuery().list();
		for (Task task : list) {
			System.out.println(task.getName());
			System.out.println(task.getOwner());
			System.out.println(taskService.getVariablesLocal(task.getId()));
			variables.put("agree", false);
			taskService.setVariablesLocal(task.getId(), variables);
			taskService.complete(task.getId(),variables);
		}
	}
	
	public void testFinancial() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProcessEngine processEngine = (ProcessEngineImpl) context.getBean("processEngine");
		// Start a process instance 
	    String procId = processEngine.getRuntimeService().startProcessInstanceByKey("financialReport").getId(); 
	     
	    // Get the first task 
	    TaskService taskService = processEngine.getTaskService(); 
	    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list(); 
	    for (Task task : tasks) { 
	      System.out.println("Following task is available for accountancy group: " + task.getName()); 
	       
	      // claim it 
	      taskService.claim(task.getId(), "fozzie"); 
	    } 
	     
	    // Verify Fozzie can now retrieve the task 
	    tasks = taskService.createTaskQuery().taskAssignee("fozzie").list(); 
	    for (Task task : tasks) { 
	      System.out.println("Task for fozzie: " + task.getName()); 
	       
	      // Complete the task 
	      taskService.complete(task.getId()); 
	    } 
	     
	    System.out.println("Number of tasks for fozzie: "  
	            + taskService.createTaskQuery().taskAssignee("fozzie").count()); 
	     
	    // Retrieve and claim the second task 
	    tasks = taskService.createTaskQuery().taskCandidateGroup("management").list(); 
	    for (Task task : tasks) { 
	      System.out.println("Following task is available for accountancy group: " + task.getName()); 
	      taskService.claim(task.getId(), "kermit"); 
	    } 
	     
	    // Completing the second task ends the process 
	    for (Task task : tasks) { 
	      taskService.complete(task.getId()); 
	    } 
	     
	    // verify that the process is actually finished 
	    HistoryService historyService = processEngine.getHistoryService(); 
	    HistoricProcessInstance historicProcessInstance =  
	      historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult(); 
	    System.out.println("Process instance end time: " + historicProcessInstance.getEndTime()); 
	  } 
}
