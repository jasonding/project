package com.project.test;

class A{
	{
		System.out.println(" class A second code block");
	}
	{
		System.out.println(" class A first code block");
	}
	static{
		System.out.println(" class A static second code block");
	}
	static{
		System.out.println(" class A static first code block");
	}
	public A() {
		System.out.println(" class A constructor is calling");
	}
}

class B extends A{
	{
		System.out.println(" class B first code block");
	}
	{
		System.out.println(" class B second code block");
	}
	static{
		System.out.println(" class B static first code block");
	}
	static{
		System.out.println(" class B static second code block");
	}
	public B() {
		System.out.println(" class B constructor is calling");
	}
	
}


public class InstanceSort {

	public static void main(String[] args) {
		new B();
	}

}
