package com.dataqlo.algo.searching;

import com.dataqlo.algo.sorting.SortingAlgo;

// Main class for searching algorithms, sample data is in sorted form.
public class ExecMain {
	
	static int dataSize = 10000; 
	static int iteration= 100;
	static Integer sortedNumbers[] = new Integer[dataSize] ;
	static Integer unSortedNumbers[] = new Integer[dataSize] ;
	
	
	public static void main(String args[]) {
		
		for(int i=0;i<dataSize; i++) {
			sortedNumbers[i]=i;
			unSortedNumbers[i]=(int) Math.ceil(Math.random() * 10000);
			System.out.print(unSortedNumbers[i]+" ");
		}
		
		// Searching Algorithms
		/*SearchAlgo searchAlgoObj = new SearchAlgo();
		System.out.println("Algorithm\tTime(ns) \tHit\tMiss \n_________\t________ \t___\t____");
		searchAlgoObj.stressAlgorithm("linear", sortedNumbers, iteration);
		searchAlgoObj.stressAlgorithm("binary", sortedNumbers, iteration);
		searchAlgoObj.stressAlgorithm("jump", sortedNumbers, iteration);
		searchAlgoObj.stressAlgorithm("interpolation", sortedNumbers, iteration);
		searchAlgoObj.stressAlgorithm("exponential", sortedNumbers, iteration);
		searchAlgoObj.stressAlgorithm("ternary", sortedNumbers, iteration);*/
		
		
		// Sorting Algorithms
		SortingAlgo sortingAlgoObj = new SortingAlgo();
		System.out.println("Algorithm\tTime(ms) \n_________\t________ ");
		//sortingAlgoObj.stressAlgo("selection",unSortedNumbers,iteration);
		//sortingAlgoObj.stressAlgo("bubble",unSortedNumbers,iteration);
		//sortingAlgoObj.stressAlgo("insertion",unSortedNumbers,iteration);
		//sortingAlgoObj.stressAlgo("merge",unSortedNumbers,iteration);
		sortingAlgoObj.stressAlgo("heap",unSortedNumbers,iteration);
		
	}
	
}
