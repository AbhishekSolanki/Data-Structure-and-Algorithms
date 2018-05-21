package com.dataqlo.algo.searching;

import java.util.ArrayList;

public class SearchAlgo {

	private long startTime;

	public void startTime() {
		startTime = System.nanoTime();
	}

	public long endTime() {

		long endTime   = System.nanoTime();
		return endTime - startTime;
	}

	public void stressAlgorithm(String Algorithm, Integer[] dataset,int iteration) {
		ArrayList<Long> avgTime = new ArrayList<>();
		int hit = 0,miss = 0;
		for(int i=0;i<iteration;i++) {
			
			int randomSearch = (int) Math.ceil(Math.random() * 100000000);
			this.startTime();
			switch(Algorithm) {
			case "linear search":
				if(this.linearSearch(dataset, randomSearch)!=-1) hit++; else miss++;
				break;
			case "binary search":
				 if(this.binarySearch(dataset, 0, dataset.length-1, randomSearch)!=-1) hit++; else miss++;
				break;
			default:
				System.out.println("No Such Algo !");
			}
			avgTime.add(this.endTime());
		}
		double averageTime = avgTime.stream().mapToLong(i -> i).average().orElse(0);
		System.out.println(Algorithm+"\t"+averageTime+"\t"+hit+"\t"+miss);
		System.gc();
	}

	public int linearSearch(Integer[] sortedNumbers, int x) {
		for(int i=0;i<sortedNumbers.length-1;i++) {
			if(sortedNumbers[i]==x) {
				return i;
			}
		}
		return -1;
	}
	
	public int binarySearch(Integer[] sortedNumbers, int low, int high, int x) {
		startTime();
		if ( high >= low ) {	
			int mid = low + (high-low)/2;

			if ( sortedNumbers[mid] == x) 
				return mid;

			if( sortedNumbers[mid] > x) 
				return binarySearch(sortedNumbers, low, mid-1, x);

			if( sortedNumbers[mid] < x ) { 
				return binarySearch(sortedNumbers, mid+1, high, x);
			}

		}

		return -1;
	}


}