package com.dataqlo.algo.searching;

import java.lang.reflect.Array;
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

			int randomSearch = (int) Math.ceil(Math.random() * 10000);
			this.startTime();
			switch(Algorithm) {
			case "linear":
				if(this.linearSearch(dataset, randomSearch)!=-1) hit++; else miss++;
				break;
			case "binary":
				if(this.binarySearch(dataset, 0, dataset.length-1, randomSearch)!=-1) hit++; else miss++;
				break;
			case "jump":
				if(this.jumpSearch(dataset, randomSearch)!=-1) hit++; else miss++;
				break;
			case "interpolation":
				if(this.interpolationSearch(dataset, 0, dataset.length-1, randomSearch)!=-1) hit++; else miss++;
				break;
			case "exponential":
				if(this.exponentialSearch(dataset,randomSearch)!=-1) hit++; else miss++;
				break;
			case "ternary":
				if(this.ternarySearch(dataset, 0, dataset.length-1, randomSearch)!=-1) hit++; else miss++;
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

	protected int linearSearch(Integer[] sortedNumbers, int x) {
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

	public int interpolationSearch(Integer[] sortedNumbers, int low, int high, int x) {

		if(high >= low ) {
			int pos = low+(high-low) / (sortedNumbers[high]-sortedNumbers[low])*(x-sortedNumbers[low]);

			if(sortedNumbers[pos] == x) {
				return pos;
			}

			if(sortedNumbers[pos] < x) {
				interpolationSearch(sortedNumbers,pos+1,high,x);
			}

			if(sortedNumbers[pos] > x) {
				interpolationSearch(sortedNumbers,low,pos-1,x);
			}


		}
		return -1;
	}

	public int exponentialSearch(Integer[] sortedNumbers, int x) {
		if(sortedNumbers[0] == x) {
			return 0;
		}

		int i=1;
		while(i< sortedNumbers.length-1 && sortedNumbers[i] <=x) {
			i=i*2;
		}
		return this.binarySearch(sortedNumbers, i/2, Math.min(i, sortedNumbers.length-1), x);
	}
	
	public int ternarySearch(Integer[] sortedNumbers, int low, int high, int x) {
		
		if( high >= low) {
			int mid = low +(high-low)/3;
			int mid2 = mid+(high-low)/3;
			
			if(sortedNumbers[mid]==x) {
				return mid;
			}
			
			if(sortedNumbers[mid2]==x) {
				return mid2;
			}
			// x in left the one third part 
			if(sortedNumbers[mid] > x ) {
				return  ternarySearch(sortedNumbers, low, mid-1, x);
			}
			// x in right in one third part 
			if(sortedNumbers[mid2] < x ) {
				return  ternarySearch(sortedNumbers, mid2+1, high, x);
			}
			
			// x in between 
			return ternarySearch(sortedNumbers, mid+1, mid2-1, x);
		}
		
		return -1;
	}

}