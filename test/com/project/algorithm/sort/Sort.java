package com.project.algorithm.sort;
/**
 * 
 * 排序算法(升序)
 * @author jason
 * 2013-6-2
 */
public class Sort {
	private static int[] toSort = new int[]{55,11,1,12,34,5,6,7,-1,1,42,22,77,54,78};
	
	public static void main(String[] args) {
//		selectSort();
//		bubbleSort();
		insertSort();
		for (int i = 0; i < toSort.length; i++) {
			System.out.print(toSort[i] + " ");
		}
	}
	
	/**
	 * 冒泡排序
	 * 比较相邻的两个元素，大就交换位置，小则不作任何操作，直到所有未排序元素比较完
	 */
	public static void bubbleSort() {
		for(int i=toSort.length -1; i>0; i--) {
			for(int j=0; j<i ; j++) {
				if(toSort[j] > toSort[j+1]) {
					changeValue(j,j+1);
				}
			}
		}
	}
	/**
	 * 选择排序
	 * 选择一最小元素的位置，循环比较，若当前元素比指定元素小，记录其位置，最后交换位置中元素
	 */
	public static void selectSort() {
		for(int i=0; i<toSort.length -1; i++) {
			int mixPosition = i;
			
			for(int j=i+1; j<toSort.length; j++) {
				if(toSort[mixPosition] > toSort[j]) {
					mixPosition = j;
				}
			}
			
			if(mixPosition != i) {
				changeValue(mixPosition, i);
			}
		}
	}
	
	/**
	 * 插入排序
	 * 选定一个元素(其左边都是局部有序)，记录在临时变量内，将左边元素依次右移，直到左边一元素小于选定元素。
	 */
	public static void insertSort() {
		int temp = 0;
		for(int i=1; i<toSort.length -1; i++) {
			temp = toSort[i];
			int positon = i;
			for(int j=i;j>0; j--) {
				if(temp < toSort[j-1]) {
					toSort[j] = toSort[j-1];
					positon = j-1;
				}
			}
			toSort[positon] = temp;
		}
	}
	

	private static void changeValue(int i,int j) {
		toSort[i] = toSort[i] ^ toSort[j];
		toSort[j] = toSort[i] ^ toSort[j];
		toSort[i] = toSort[i] ^ toSort[j];
	}
}
