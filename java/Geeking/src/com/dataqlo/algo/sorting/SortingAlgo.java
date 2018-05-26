package com.dataqlo.algo.sorting;

import java.util.ArrayList;

public class SortingAlgo {
	
	private long startTime;

	public void startTime() {
		startTime = System.currentTimeMillis();
	}

	public long endTime() {

		long endTime   = System.currentTimeMillis();
		return (endTime - startTime)/1000;
	}
	public void stressAlgo(String Algorithm,Integer[] dataset, int iteration) {
		
		ArrayList<Long> avgTime = new ArrayList<>();
		int hit = 0,miss = 0;
		for(int i=0;i<iteration;i++) {

			this.startTime();
			switch(Algorithm) {
			case "selection":
				this.selectionSort(dataset);
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
	
}
