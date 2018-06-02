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
			case "heap":
				this.heapSort(dataset);
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
	
	
	private void heapSort(Integer[] unSortedArray){
		//unSortedArray = new Integer[]{9,2,12, 6, 3, 10}; // Test data during dev.
		
		// all the parent nodes can be calculated by n/2-1
		int n = unSortedArray.length;
		for (int i = n/2 - 1; i>=0; i--) { // iterating only over parents node.
			heapify(unSortedArray, n, i);  // gives the highest element in each parent node.
		}
		
		// At this stage each parent node is sorted, Highest would be at the root and lower value than the root
		// at each level, the leaf node might contain the element which can be greater than other parents node.
		// to calculate again we need to traverse from each element from botton to top at that too from right to left.
		
		for(int i = n-1; i> 0;i--) { // Iterating loop from last leaf node, from right to left 
			int swapHighest = unSortedArray[0]; // Swapping the highest element at root node to the last 
			unSortedArray[0] = unSortedArray[i];
			unSortedArray[i] = swapHighest;
			
			heapify(unSortedArray, n, 0); // sorting 
			
		}
		// Finally Done :)
	}
	
	private void heapify(Integer[] arr, int n,int i) {
		
		int max = i; // Assuming the current parent is having max value.
		int left = 2*i + 1; // left side nodes index
		int right = 2*i + 2; // right side nodes index
		
		// the equal to condition is not specified as if the value of the parent is same as the left/ right node there is no
		// need to swap, also during the sorting of leaf nodes with other parent node (i.e second loop in heapSort, this servers as the 
		// the barrier to swap the highest element again to the root node. Hence max values will be at the bootom.
		
		if(left < n && arr[left] > arr[max]) {
			max = left;
		}
		
		if(right < n && arr[right] > arr[max]) {
			max = right;
		}
		
		// If new max is found than we need to swap the values.
		if (max !=i) {
			int swap = arr[i];
			arr[i] = arr[max];
			arr[max] = swap;
			
			// At this stage we got the parent value as max, it is possible there are some parents whose value will be smaller than 
			// the current level parent, this recursion ensures that, the max value should travel up. 
			// During the second loop, this recursion rearranges the heap.
			heapify(arr, n, max);
		}
			
	}
}
