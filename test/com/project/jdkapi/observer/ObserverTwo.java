package com.project.jdkapi.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverTwo implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("观察者2已经执行" + arg);
	}

}
