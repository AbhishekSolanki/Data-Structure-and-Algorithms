package com.dataqlo.algo.sorting;

import java.util.ArrayList;
import java.util.Arrays;

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
			case "quick":
				this.quickSort(dataset,0,dataset.length-1);
				break;
			case "radix":
				this.radixSort(dataset,dataset.length);
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

	private void selectionSort(Integer[] unsortedArray) {

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

	private void bubbleSort(Integer[] unsortedArray) {

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
	
	private void insertionSort(Integer[] unsortedArray) {
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
	
	
	
  	private void mergeSort(Integer[] unSortedArray, int low, int high) {
		
		if(low < high) {
			int mid = (high + low) / 2 ;
			// consequently divide the left and right array untill we get a single element.
			mergeSort(unSortedArray, low, mid);
			mergeSort(unSortedArray, mid+1, high);
			
			merge(unSortedArray, low, mid, high);
			
		}
	}
	
	private void merge(Integer[] array, int low, int mid, int high) {
		
		int leftArraySize = mid - low +1;
		int rightArraySize = high - mid;
		// create a left and right array temporarily.
		int[] leftArray = new int[leftArraySize];
		int[] rightArray = new int[rightArraySize];
		
		// insert the smallest value from left side of the array to the left array
		for(int i = 0; i < leftArraySize; i++) {
			leftArray[i] = array[low+i];
		}

		// insert the larger value from ride side of the array to the right array
		for(int j = 0; j < rightArraySize; j++) {
			rightArray[j] = array[mid +1 +j];
		}
		
		int i = 0, j = 0, k = 0;
		
		// compare the left and right array and insert the smallest vlaues to the k th position.
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
		
		// Remaining element from left and right array
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
	
	private void quickSort(Integer[] unSortedArray, int low, int high) {
		
		if(low < high) {
			// get the partition value and split the array into left and right subsequent parts
			int partition = quickSortPartitioner(unSortedArray, low, high);
			
			quickSort(unSortedArray, low, partition-1);
			quickSort(unSortedArray, partition+1, high);
		}
	}
	
	private int quickSortPartitioner(Integer[] arr, int low, int high) {
		// taking the last element as the pivot value
		int pivot = high;
		
		// i is the index position which actually calculated the partition point.
		int i = low - 1;
		for(int j = low; j < high; j++) {
			
			if(arr[j] <= pivot ) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		
		// i+1 is the position of the pivot, towards left lower values will be there and towards right higher value.
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		
		//return the parition value as mid point and call quickSort recursion towards left and right parts.
		return i+1;
	}
	
	
	private void radixSort(Integer[] unSortedArray, int arrayLength) {
		
		int mx = unSortedArray[0];
        for (int i = 1; i < arrayLength; i++)
            if (unSortedArray[i] > mx)
                mx = unSortedArray[i];
      
        for(int exp = 1; mx/exp >0 ; exp*=10) {
        this.countSort(unSortedArray,arrayLength,exp);
        }
       
        
	     for(int i=0;i<arrayLength;i++) {
	    	 System.out.print(" "+unSortedArray[i] );
	     }
	}

	
	private void countSort(Integer[] unSortedArray, int arrayLength, int exp) {
	      int output[] = new int[arrayLength];
	      int count[] = new int[arrayLength];
	      Arrays.fill(count, 0);
	      
	      // count the occurrence at each significant digit
	      for (int i =0; i < arrayLength; i++) {
	    	    count [ (unSortedArray[i]/exp)%10 ]++;
	      }
	      
	      //calculate the exact position
	      for (int i = 1; i < 10; i++)
	          count[i] += count[i - 1];
	      
	      //generate the output
	      for (int i = arrayLength - 1; i >= 0; i--)
	      {
	          output[count[ (unSortedArray[i]/exp)%10 ] - 1] = unSortedArray[i];
	          count[ (unSortedArray[i]/exp)%10 ]--;
	      }
	      
	      for (int i = 0; i < arrayLength; i++)
	          unSortedArray[i] = output[i];
	   
	      
	}
	

}