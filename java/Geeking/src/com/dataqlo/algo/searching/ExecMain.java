package com.dataqlo.algo.searching;

// Main class for searching algorithms, sample data is in sorted form.
public class ExecMain {
	
	//100 Mn sorted no.
	static int dataSize = 10000000; //10000000;
	static int iteration= 1000000;//1000000;
	static Integer sortedNumbers[] = new Integer[dataSize] ;
	
	
	public static void main(String args[]) {
		
		for(int i=0;i<dataSize; i++) {
			sortedNumbers[i]=i;
		}
		
		SearchAlgo searchAlgoObj = new SearchAlgo();
	
		//1 Mn iteration
		System.out.println("Algorithm\tTime(ns) \tHit\tMiss \n_________\t________ \t___\t____");
		searchAlgoObj.stressAlgorithm("linear", sortedNumbers, iteration);
		searchAlgoObj.stressAlgorithm("binary", sortedNumbers, iteration);
		searchAlgoObj.stressAlgorithm("jump", sortedNumbers, iteration);
		searchAlgoObj.stressAlgorithm("interpolation", sortedNumbers, iteration);
	}
	
}
