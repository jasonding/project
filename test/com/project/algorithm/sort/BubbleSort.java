package com.project.algorithm.sort;
/**
 * 
 * @author jason
 * 2013-6-2
 */
public class BubbleSort {
	public static void main(String[] args) {
		
		int[] toSort = new int[]{11,1,12,34,5,6,7,-1,1,55};
		
		for(int i=toSort.length -1; i>0; i--) {
			for(int j=0; j<i ; j++) {
				if(toSort[j] > toSort[j+1]) {
					toSort[j] = toSort[j] ^ toSort[j+1];
					toSort[j+1] = toSort[j] ^ toSort[j+1];
					toSort[j] = toSort[j] ^ toSort[j+1];
				}
			}
		}
		
		for (int i = 0; i < toSort.length; i++) {
			System.out.print(toSort[i] + " ");
		}
	}
}
