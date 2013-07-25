package com.project.pool.thread;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.task.ITask;

public class ThreadPool {

	private static Logger logger = LoggerFactory.getLogger(ThreadPool.class);

	private static int maxTaskSize = 3;
	private static int initThreadSize = 3;
	private static int maxThreadSize = 5;
	
	private static int currentThreadCount = 0;

	private static LinkedList<ITask> commonTask = null;

	private static Lock lock = new  ReentrantLock();
	private static Condition full = lock.newCondition(); // 当任务列表有任务，唤醒线程执行任务,添加任务等待
	private static Condition empty = lock.newCondition(); // 当任务列表为空，唤醒列表添加任务，线程执行任务等待初始化
	
	static {
		commonTask = new LinkedList<ITask>();
		for (int i = 0; i < initThreadSize; i++) {
			new Thread(new WorkeThread()).start();
			currentThreadCount ++;
		}
	}

	public static ITask get() {
		ITask task = null;
		lock.lock();
		try {
			while (commonTask.isEmpty()) {
				logger.info(Thread.currentThread().getName() + "在等待");
				full.await(10,TimeUnit.SECONDS);
				if(currentThreadCount > initThreadSize) {
					removeThread(Thread.currentThread());
				}
			}
			task = commonTask.removeFirst();
			empty.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return task;
	}

	public static void addTasks(Collection<ITask> tasks) {
		for (ITask task : tasks) {
			put(task);
		}
	}
	
	public static void put(ITask task) {
		lock.lock();
		try {
			while (getTotalTask() >= maxTaskSize) {
				logger.info("commonTask达到最大值 " + getTotalTask() + "【" + Thread.currentThread() + "】等待");
				empty.await(10, TimeUnit.SECONDS);
				if(currentThreadCount < maxThreadSize) {
					addThread();
				}
			}
			commonTask.addLast(task);
			full.signal();
			logger.info("一个任务被添加commonTask={}",getTotalTask());
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	private static int getTotalTask() {
		int commonSize = commonTask.size();
		return commonSize;
	}

	private static void addThread() {
		Thread thread = new Thread(new WorkeThread());
		thread.start();
		logger.info(thread.getName() + "被增加");
		currentThreadCount ++;
	}

	private static void removeThread(Thread thread) {
		logger.info(Thread.currentThread().getName() + "被移除");
		thread.interrupt();
		currentThreadCount --;
	}

	static class WorkeThread implements Runnable {
		public void run() {
			try {
				if(Thread.interrupted()) throw new InterruptedException("线程已经被中断");
				while (true) {
					ITask task = ThreadPool.get();
					try {
						if(task != null) task.doTask();
					} catch (Exception e) {
						logger.error("执行任务异常{}",Thread.currentThread(),e);
					}
				}
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
				return ;
			}
		}
	}
}
