package com.project.jdkapi;


public class RandomTest {
	public static void main(String[] args) {
		/*Random random = new Random(10);
		for (int i = 0; i < 10; i++) {
			System.out.println(random.nextInt());
		}*/
		
		String str = "hello, world! ";
		StringBuilder sb = new StringBuilder();
		for(int i=str.length()-1;i>=0; i--) {
			sb.append(str.charAt(i));
		}
		System.out.println(sb.toString());
	}
}
