
import java.io.*;
import java.util.*;

/**
 * @author Nathan Stark
 *
 *
 */
public class CS361Labs {
	//Scanner to read the lab files.
	private Scanner scan;
	//The array that is made from the lab file given.
	private static int[] arr;
	
	/**************************************************************** LAB 2 *********************************************************************************/
	
	/* The bin sort algorithm was written with the help of looking at the code from 
	 * https://github.com/skoliver89/CS361-Lab3/blob/master/Lab3.java yet it was
	 * modified by myself in order to fit other function and requierments to this lab.
	 * All comments and documentation were add to show understanding and to clearify the
	 * code and functionality.*/
	
	/**
	 * A signal method that implements bin sort algorithm.
	 * 
	 * @param arrB An int. array that the bin sort alg. will be applied to.
	 * @param lengthTo The length to which you want to apply the sort to.
	 */
	public void binSort(int[] arrB, int lengthTo){
		int n = getMax(arrB, lengthTo);					// Get the maximum value upto the index you want to go to.
		int[] bin = new int[n+1];						// Set the bin array to be one greater than the max value that was just found.
		int i;// this is just an indexer for the next few loops.
		
		for(i=0;i<=n;i++){
			bin[i] = 0;									// init. the bins to zero.
		}
		
		for(i=0;i<=lengthTo - 1;i++){
			bin[arrB[i]]++;								// Using increment the the index of bins for every value that is in arrB
		}												// up to the index that we are inspecting in arrB.
		
		int outIndex = 0;
		for(i=0;i<=n;i++){
			for(int j=0;j<bin[i];j++){					// Go through the bins and find out how many numbers are in it.
				arrB[outIndex]=i;						// Put the numbers from bin back into the array.
				outIndex++;
			}
		}
	}
	
	/* Much of the below code was helped and found on geeks for geeks https://www.geeksforgeeks.org/radix-sort/*/
	
	/**
     * A get method to return the maximum number held in the array
     * pasted.
     * 
     * @return The integer with the highest value in the array past.
     * @param arrR An array of integers.
     * @param n The length in the array to which you would like to go to find a max value.
     * The max param for this value will be restricted to is the length of the array.
     */
    private int getMax(int[] arrR, int n)
    {
        int mx = arrR[0];
        for (int i = 1; i < n; i++)
            if (arrR[i] > mx)
                mx = arrR[i];
        return mx;
    }
 
    /**
     * A function that uses counting sort to sort the array passed into arrC
     * 
     * @param arrC An array of integers.
     * @param n The length in the array to which you would like to go to find a max value.
     * The max param for this value will be restricted to is the length of the array.
     * @param dig The digit that countSort will use for the sorting.
     */
    private void countSort(int[] arrC, int n, int dig)
    {
        int output[] = new int[n];							// init. that output array to length n.
        int i;												// An index init. for the for loops.
        int count[] = new int[10];							// init. the count array for base ten digits.
        Arrays.fill(count,0);								// Fill the count array all zeros.

        for (i = 0; i < n; i++)								// Store the count of occurrences in count[]
            count[ (arrC[i]/dig)%10 ]++;
 
        for (i = 1; i < 10; i++)							// Change the values of count at index i so that it reflex
            count[i] += count[i - 1];						// how many digits that are less than or equal to that digit.
 
        
        for (i = n - 1; i >= 0; i--)						// Build the output array with the values of arrC 
        {													// divided by the digit we are using as an index to count[] that
            output[count[ (arrC[i]/dig)%10 ] - 1] = arrC[i];// will be used as an index for the output array that we will set to the arrC of index i.
            count[ (arrC[i]/dig)%10 ]--;					// Decrement what the integer in count array at the index we used for the out put array.
        }
 
        
        
        for (i = 0; i < n; i++){							// Copy the output array to arrC[], so that arr[] now
            arrC[i] = output[i];							// contains sorted numbers according to current digit
            }
    }
 
    // The main function to that sorts arr[] of size n using
    // Radix Sort
    public void radixsort(int[] arrR, int n)
    {
        
        int m = getMax(arrR, n);							// Find the maximum number in order to know number of digits needed.
 
        // Do counting sort for every digit. Note that instead
        // of passing digit number, dig is passed. dig is 10^i
        // where i is current digit number
        for (int dig = 1; m/dig > 0; dig *= 10){
        	countSort(arrR, n, dig);
        	}
    }
	
	/* Much of the below code was helped and found on geeks for geeks https://www.geeksforgeeks.org/merge-sort/*/
	/**************************************************************** LAB 1 *********************************************************************************/
	/**
	 * Sort an array with merge sort.
	 * 
	 * @param arrM The array that merge sort will be ran on.
	 * @param startIndex the starting index of the array.
	 * @param endIndex the length of the array minus one.
	 */
	public void auxMergeSort(int[] arrM, int startIndex, int endIndex){
		
		int midIndex; // set an index for the mid point. 
		
		if(startIndex < endIndex){
			midIndex = startIndex + (endIndex -startIndex)/2; // init. the midIndex marking the midpoint of the array.
			                                                  // From geeks for geeks I have changed my midIndex equation
			                                                  // as this should avoid over flow from large start and end 
			                                                  // indexes as that is what we will be using.
			
			auxMergeSort(arrM, startIndex, midIndex);         // Recursion. The method calls its self with
			auxMergeSort(arrM, midIndex + 1, endIndex);       // the midIndex in order to brake down the array.
			
			merge(arrM, startIndex, midIndex, endIndex);      //The arrays are then merged and sorted for this method.
		}
	}
	
	/**
	 * This is to merge and sort the array that is pasted into
	 * the auxMergeSort. Basically this method will do the heave lifting. 
	 * 
	 * @param arrM
	 * @param startIndex
	 * @param midIndex
	 * @param endIndex
	 */
	private void merge(int[] arrM, int startIndex, int midIndex, int endIndex) {
		
		int leftLength = midIndex - startIndex + 1;      // The length of the left array.
		int rightLength = endIndex - midIndex;           // The length of the right array.
		
		int[] leftArr = new int[leftLength];             // init. the arrays with set lengths
		int[] rightArr = new int[rightLength];           // that are found from two lines above.
		
		for(int i = 0;i<leftLength;i++){
			leftArr[i] = arrM[startIndex + i];           // Fill the left array with the data from the array to be sorted.
		}
		for(int j = 0;j<rightLength;j++){
			rightArr[j] = arrM[midIndex + 1 + j];        // Fill the right array with the data from the array to be sorted.
		}
		
		int i = 0, j = 0, k = startIndex;                // i is the index pointer for the left array and j is for the right array.
		                                                 // where k is the pointer for the array to be sorted.
		while(i < leftLength && j < rightLength){
			if(leftArr[i]<=rightArr[j]){                 // If the left index is less than right array index.
				arrM[k] = leftArr[i];                    // swap the left's number into the main array
				i++;
			}else {
				arrM[k] = rightArr[j];                   // If the index is equal to or more than the right array. 
				j++;                                     // enter the right arrays of that index number into the array main array. 
			}
			k++;
		}
		while(i<leftLength){                             // Keeping track of the indices while the left index is less than the length of the left array 
			arrM[k] = leftArr[i];                        //enter the left array number in current index into the main array 
			i++;
			k++;
		}
		while(j<rightLength){                            // similar to the above while loop we are just now doing the same with the right array.
			arrM[k] = rightArr[j];
			j++;
			k++;
		}
	}
	
	/**
	 * This is a quick sort method
	 * 
	 * @param arrQ the array to be sorted
	 * @param startIndex the starting index of the array.
	 * @param endIndex the length of the array minus one.
	 */
	public void auxQuickSort(int[] arrQ, int startIndex, int endIndex){
		int midIndex;
		
		if(startIndex<endIndex){
			midIndex = partition(arrQ, startIndex, endIndex);         // Use the partition method to find the midIndex
			auxQuickSort(arrQ, startIndex, midIndex - 1);             // Recursively call this method on the left side
			auxQuickSort(arrQ, midIndex, endIndex);                   // Call the method on the right side.
		}
	}
	
	/**
	 * This is the partition that is implemented by the quick sort algorithm.
	 * 
	 * @param endIndex The length of the array - 1. This is our first pivot.
	 * @param startIndex The beginning index to check.
	 * @param arrQ The array to be sorted.
	 * @return return the pointer that will mark the midIndex for the quick sort.
	 */
	private int partition(int[] arrQ, int startIndex, int endIndex){
		int pivot = arrQ[endIndex];                       // This is our pivot.
		int indexPointer = startIndex - 1;                // inti. a pointer as one less than the starting index
		
		for(int j=startIndex; j <= endIndex - 1; j++){
			if(arrQ[j] <= pivot){                         // If the number at j in the array is less than or equal to the endIndex of the array
				indexPointer++;                           // then do a swap.
				int temp = arrQ[indexPointer];            // this temp will hold the number at the indexed pointer for only this iteration.
				arrQ[indexPointer] = arrQ[j];
				arrQ[j] = temp;
			}
		}
		int temp = arrQ[indexPointer + 1];               // set a temp with the same purpose as the above temp.
		arrQ[indexPointer + 1] = arrQ[endIndex];         //swap the array pointer index of the last element of the array
		arrQ[endIndex] = temp;                           // set the last index of the array to what is stored in temp.
		
		return indexPointer + 1;
	}
	
	/**
	 * I was able to complete the below two methods with the help
	 * of https://github.com/AlexMolodyh/CS361/blob/master/Week%201/Lab1/SortingHelper.java
	 */
	
	/**
	 * This method is to check weather or not the array has been sorted correctly.
	 * 
	 * @param sortedArray The array we want to check to see if its sorted.
	 * @return true if the array passed has been sorted or is sorted to begin with false otherwise.
	 */
	public boolean flgIsSorted(int[] sortedArray){
		
		boolean sorted = auxFlgIsSorted(sortedArray, 0, sortedArray.length - 1);
		
		return sorted;
		
	}
	
	/**
	 * This is a private method that is recursive  helps flgIsSorted determine
	 * if an array is sorted.
	 * 
	 * @param sortedArray the array that we want to check
	 * @param startIndex The starting index that we want to check.
	 * @param endIndex The ending index that we want to check.
	 * @return true if the array is sorted false otherwise.
	 */
	private boolean auxFlgIsSorted(int[] sortedArray, int startIndex, int endIndex) {
		if(startIndex == endIndex){                        // Base case for this recursive method.
			return true;                                   // let the base case return true.
			}
		int midIndex = (startIndex + endIndex) / 2;        // the midpoint of the array passed.
		
		if(sortedArray[midIndex] <= sortedArray[midIndex + 1]){        // check the midpoint against the midpoint + 1.
			return auxFlgIsSorted(sortedArray, startIndex, midIndex) && auxFlgIsSorted(sortedArray, midIndex + 1, endIndex);// If the midpoints are sorted then,
		}                                                                                                                   // make recursive calls on this method to 
		                                                                                                                    // keep checking either side of the array.
		
		return false; // This will execute if the above to if statements fail.
	}

	/**
	 * This method is to read a file and fill an array that can be used in
	 * other methods.
	 * 
	 * @param fil Is set to be a file containing integers to fill an array.
	 * @throws FileNotFoundException If the file cannot be found the this Exception is thrown.
	 */
	public void fileToRead(File fil) throws FileNotFoundException{
		scan = new Scanner(fil);
		arr = new int[10000000];
		int i = 0;
		while(scan.hasNext()){
			arr[i] = scan.nextInt();
			i++;
		}
	}
	
	/**
	 * @return return the an int. array field for this class. This method can 
	 * be used to see if the array has been filled or what its been filled with.
	 */
	public int[] getArr(){
		return arr;
	}
	
	/**
	 *   Check the sum to make sure that is it the array we started with.
	 *   As we are given in the first lab the sum of the array for testing
	 *   if our code works.
	 */
	public void printSumOfArr(){
	
		long sumOfArr = 0;
		for(int i=0;i<arr.length;i++){
			sumOfArr += arr[i]; 
		}
		System.out.println("The sum of the array is " + sumOfArr + ".");
	}
	
	/**
	 * Print the last ten integers of the array.
	 */
	public void printLastTen(){
		for(int c=arr.length - 11;c<arr.length;c++){
			System.out.println(arr[c]);
			}
		}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		//Read in the file to set up radix sort==========================================================
		CS361Labs lab2RadixSort = new CS361Labs();
		try{
			lab2RadixSort.fileToRead(new File("../nstarklab2/lab3_data.txt"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
			}
		
		//Read in the file to set up bin sort=============================================================
		CS361Labs lab2BinSort = new CS361Labs();
		try{
			lab2BinSort.fileToRead(new File("../nstarklab2/lab3_data.txt"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
			}
		*/
		//Read in the file to set up quick sort=============================================================
		CS361Labs lab2QuickSort = new CS361Labs();
		try{
			lab2QuickSort.fileToRead(new File("../nstarklab2/lab3_data.txt"));
			} catch (FileNotFoundException e){
				e.printStackTrace();
				}
		
		
		/*********************************************************  BIN SORT  *******************************************************************************/
		
		/*
		int x = 1;
		for(int y = 1000; y <= arr.length; y  = y * 10){
		long binSortTime = System.nanoTime();
		lab2BinSort.binSort(arr,y);
			System.out.println("Bin sort ran " + x + ": " + (System.nanoTime() - binSortTime));
			x++;
		}
		
		if(lab2BinSort.flgIsSorted(arr)){
			System.out.println("The array was sorted using bin sort.");
		}else{
			System.out.println("It didn't work.");
		}
		*/
		
		/*********************************************************  BIN SORT  *******************************************************************************/

		/********************************************************* RADIX SORT *******************************************************************************/
		/*
		int x = 1;
		for(int y = 1000; y <= arr.length; y  = y * 10){
		long radixSortTime = System.nanoTime();
		lab2RadixSort.radixsort(arr,y);
			System.out.println("Radix sort ran " + x + ": " + (System.nanoTime() - radixSortTime));
			x++;
		}
		
		if(lab2RadixSort.flgIsSorted(arr)){
			System.out.println("The array was sorted using radix sort.");
		}else{
			System.out.println("It didn't work.");
		}
		*/
		/********************************************************* RADIX SORT *******************************************************************************/
		
		/********************************************************* QUICK SORT *******************************************************************************/
		
		int x = 1;
		for(int y = 1000; y <= arr.length; y  = y * 10){
		long quickSortTime = System.nanoTime();
		lab2QuickSort.auxQuickSort(arr, 0, y - 1);
			System.out.println("Quick sort ran " + x + ": " + (System.nanoTime() - quickSortTime));
			x++;
		}
		if(lab2QuickSort.flgIsSorted(arr)){
			System.out.println("The array was sorted using quick sort.");
		}else{
			System.out.println("It didn't work.");
		}
		
		/********************************************************* QUICK SORT *******************************************************************************/

		/****************************************************** MERGE SORT *********************************************************************************/
		/*
		int x = 1;
		for(int y = 1000; y <= arr.length; y  = y * 10){
			long mergeSortTime = System.nanoTime();
			lab1.auxMergeSort(arr, 0, y - 1);
			System.out.println("Merge sort ran " + x + ": " + (System.nanoTime() - mergeSortTime));
			x++;
		}
		if(lab1.flgIsSorted(arr)){
			System.out.println("The array was sorted using merge sort."); // this will print on the console if the array has been sorted.
		}else{
			System.out.println("It didn't work.");                        // this will print if the array is not sorted.
		}*/
		
		/*for(int i= arr.length - 11;i<arr.length;i++){ 
			System.out.println(arr[i]);
		}*/
		/****************************************************** MERGE SORT *********************************************************************************/
		//Below is a test method for the first lab or if you want to see the sum of the array.
		//lab1.printSumOfArr();
	}

	
}
