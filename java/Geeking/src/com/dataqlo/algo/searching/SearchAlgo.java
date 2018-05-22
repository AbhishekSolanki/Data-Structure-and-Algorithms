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
			
			int randomSearch = (int) Math.ceil(Math.random() * 10000000);
			this.startTime();
			switch(Algorithm) {
			case "linear search":
				if(this.linearSearch(dataset, randomSearch)!=-1) hit++; else miss++;
				break;
			case "binary search":
				 if(this.binarySearch(dataset, 0, dataset.length-1, randomSearch)!=-1) hit++; else miss++;
				break;
			case "jump search":
				 if(this.jumpSearch(dataset, randomSearch)!=-1) hit++; else miss++;
				break;
			default:
				System.out.println("No Such Algo !");
			}
			avgTime.add(this.endTime());
		}
		double averageTime = avgTime.stream().mapToLong(i -> i).average().orElse(0);
		System.out.println(Algorithm+" \t"+averageTime+" \t"+hit+" \t"+miss);
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
	
	public int jumpSearch(Integer[] sortedNumbers, int x) {
		
		int arrayLength = sortedNumbers.length;
		
		// block size to be jumped to 
		int jump = (int)Math.floor(Math.sqrt(arrayLength));
		// store the previous block index for linear serach start
		int prevBlock = 0;
		
		// if element is present 
		while( sortedNumbers[Math.min(jump, arrayLength)-1] < x) {
			prevBlock = jump;
			jump += (int)Math.floor(Math.sqrt(arrayLength));
			if ( prevBlock > arrayLength) {
				return -1;
			}
		}
		
		// linear search between the block
		while( sortedNumbers[prevBlock] < x ) {
			prevBlock++;
			if(prevBlock == Math.min(jump, arrayLength)) {
				return -1;
			}
		}
		
		// element found
		if(sortedNumbers[prevBlock]==x) {
			return prevBlock;
		}
		
		return -1;
	}
	
}