package com.dataqlo.algo.sorting;

import java.util.ArrayList;

public class SortingAlgo {

	private long startTime;

	public void startTime() {
		startTime = System.currentTimeMillis();
	}

	public long endTime() {

		long endTime   = System.currentTimeMillis();
		return (long) ((endTime - startTime));
	}
	public void stressAlgo(String Algorithm,Integer[] dataset, int iteration) {

		ArrayList<Long> avgTime = new ArrayList<>();
		for(int i=0;i<iteration;i++) {

			this.startTime();
			switch(Algorithm) {
			case "selection":
				this.selectionSort(dataset);
				break;
			case "bubble":
				this.bubbleSort(dataset);
				break;
			case "insertion":
				this.insertionSort(dataset);
				break;
			default:
				System.out.println("No Such Algo !");
			}
			avgTime.add(this.endTime());
		}
		double averageTime = avgTime.stream().mapToLong(i -> i).average().orElse(0);
		System.out.println(Algorithm+" \t"+averageTime);
		System.gc();
	}

	public void selectionSort(Integer[] unsortedArray) {

		int arrayLength = unsortedArray.length;

		for ( int i=0;i<arrayLength-1;i++) {

			for(int j =i+1;j<arrayLength;j++) {
				if(unsortedArray[j] <= unsortedArray[i]) {
					int temp = unsortedArray[j];
					unsortedArray[j] = unsortedArray[i];
					unsortedArray[i] = temp;
				}
			}
		}
	}

	public void bubbleSort(Integer[] unsortedArray) {

		int arrayLength = unsortedArray.length;

		for ( int i=0;i<arrayLength-1;i++) {
			boolean swap = false; // optimized version to skip the loops when array is sorted.
			for(int j =i+1;j<arrayLength-i-1;j++) {
				if(unsortedArray[j] <= unsortedArray[j+1]) {
					int temp = unsortedArray[j];
					unsortedArray[j] = unsortedArray[j+1];
					unsortedArray[j+1] = temp;
					swap=true;
				}
			}
			if(!swap) break;
		}
	}
	
	public void insertionSort(Integer[] unsortedArray) {
		int arrayLength= unsortedArray.length;
		
		for(int i=1;i<arrayLength-1;i++) {
			int key = unsortedArray[i];
			int j=i-1;
			while(j>=0 && unsortedArray[j] > key) {
				unsortedArray[j+1] = unsortedArray[j];
				j=j-1;
			}
		 unsortedArray[j+1] = key;
			
		}
	}
}
