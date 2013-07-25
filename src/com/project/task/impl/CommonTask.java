package com.project.task.impl;

import com.project.task.ITask;

public class CommonTask implements ITask {
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CommonTask(int id) {
		super();
		this.id = id;
	}

	public void doTask() {
		System.out.println(this.getId() + "任务被执行");
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
	}
}
