import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 */

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
	
	/* Much of the below code was helped and found on geeks for geeks https://www.geeksforgeeks.org/merge-sort/*/
	
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
	 * @param args
	 */
	public static void main(String[] args) {
		CS361Labs lab1 = new CS361Labs();
		try{
			lab1.fileToRead(new File("../nstarklab1/lab1 data file/lab1_data.txt"));
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
		if(arr.length - 1 ==9999999){
				System.out.println("The array is of proper length.");
			}
		/********************************************************* QUICK SORT *******************************************************************************/
		
		int x = 1;
		for(int y = 1000; y <= arr.length; y  = y * 10){
		long quickSortTime = System.nanoTime();
			lab1.auxQuickSort(arr, 0, y - 1);
			System.out.println("Merge sort ran " + x + ": " + (System.nanoTime() - quickSortTime));
			x++;
		}
		if(lab1.flgIsSorted(arr)){
			System.out.println("The array was sorted using quick sort.");
		}else{
			System.out.println("It didn't work.");
		}
		

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
		
		// Check the sum to make sure that is it the array we started with.
		long sumOfArr = 0;
		for(int i=0;i<arr.length;i++){
			sumOfArr += arr[i]; 
		}
		System.out.println("The sum of the array is " + sumOfArr + ".");
	}

	
}
