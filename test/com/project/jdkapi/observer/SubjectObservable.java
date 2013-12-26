package com.project.jdkapi.observer;

import java.util.Observable;

/**
 * 被观察者，标记为可以被观察的
 * @author djs
 *
 */
public class SubjectObservable extends Observable {

	public void doSomething(Object args) {
		System.out.println("执行业务");
		super.setChanged();
		super.notifyObservers(args);
		
	}
	
	public static void main(String[] args) {
		SubjectObservable observable = new SubjectObservable();
		observable.addObserver(new ObserverTwo());
		observable.addObserver(new ObserverOne());
		
		observable.doSomething("aaa");
	}
}
