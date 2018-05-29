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
	
	private void printSorted(Integer[] sortedArray) {
		for(int i=0;i<sortedArray.length;i++) {
			System.out.print(sortedArray[i]+" ");
		}
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
			case "merge":
				this.mergeSort(dataset,0,dataset.length-1);
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
		//printSorted(unsortedArray);
	}
	
	
	public void mergeSort(Integer[] unSortedArray, int low, int high) {
		
		if(low < high) {
			int mid = (high + low) / 2 ;
			mergeSort(unSortedArray, low, mid);
			mergeSort(unSortedArray, mid+1, high);
			
			merge(unSortedArray, low, mid, high);
			
		}
	}
	
	private void merge(Integer[] array, int low, int mid, int high) {
		
		int leftArraySize = mid - low +1;
		int rightArraySize = high - mid;
		
		int[] leftArray = new int[leftArraySize];
		int[] rightArray = new int[rightArraySize];
		
		for(int i = 0; i < leftArraySize; i++) {
			leftArray[i] = array[low+i];
		}
		
		for(int j = 0; j < rightArraySize; j++) {
			rightArray[j] = array[mid +1 +j];
		}
		
		int i = 0, j = 0, k = 0;
		
		while (i < leftArraySize && j < rightArraySize ) {
			
			if(leftArray[i] <= rightArray[j]) {
				array[k] = leftArray[i];
				i++;
			} else {
				array[k] = rightArray[j];
				j++;
			}
			k++;
		}
		
		while ( i < leftArraySize) {
			array[k] = leftArray[i];
			i++; k++;
		}
		
		while ( j < rightArraySize) {
			array[k] = rightArray[j];
			j++; k++;
		}
		//printSorted(array);
	}
}
