
/*************************************************************************
 *
 *  Pace University
 *
 *  Course: CS 242 Algorithms and Computing Theory
 *  Author: Pooja Panchakshari, Poorva Shelake, Gurjinder Oberoi
 *  Collaborators:  
 *  References: 
 *
 *  Assignment: 5
 *  Problem: Testing running times of Bucket Sort and Quick Sort
 *  Description: This Problem measures running time for Bucket Sort and Quick Sort
 *				
 *  Input: number of entries
 *  Output: running times to sort

 *
 *  Visible data fields:
 * none.
 *
 * Visible methods:
 * public static int partition(float arr[], int low, int high) -->Quick Sort
 * public static void QuickSort(float arr[], int low, int high) --> Quick Sort
 * public static float[] sort(float[] a, int maxVal) --> Bucket Sort
 * static float[] generateSeq(float[] sequence)	--> Generate Uniform numbers
 *
 *   Remarks
 *   -------
 *  Chart of running times observed in nanoseconds:
 * 
 *  Size     |    Bucket Sort           |    Quick Sort
 *  ------------------------------------------------------------
 *  1000      |        971631          |    151390	 
 *  ------------------------------------------------------------
 *  10000     |        5058296         |    1914181		
 *  ------------------------------------------------------------
 *  100000    |        32604796         |   10482236	
 *  ------------------------------------------------------------
 *  1000000   |        839950358        |    123739222		
 *  ------------------------------------------------------------
 *  10000000  |        11621835946     	|    1477965323	
 *  ------------------------------------------------------------
 *
 *
 * Observation:

 Quick Sort
Quick Sort is a dividing the problems into the smaller instances of the original problem and solving those  sub problems recursively. 
 So divide and conquer is optimized solution for this problem as it takes O(N lg(N)) time.
 In following program, it recursively finds out the left max sum and right max sum. It finds out the cross max sum in about O(N) time. and then It chooses the maximum among three. 
 When we tried to increase the input size in N*10 at each pass, Time taken by the program doesn't vary exponentially but varies almost in the multiplication of N. lg(N) is almost a constant value and therefore, time increases with increase in value of N. The problem is that its worst-case performance is O(n^2) which makes it as slow as bubble sort. The worst case happens when pivot is chosen as first or as last element in sorted data. To decrease the time complexity the best choice is to choose pivot as random.
Bucket Sort
Bucket Sort distributes the elements of the given input into respected buckets, after putting all the elements into the buckets and then each bucket is sorted individually and this is done by using different sorting algorithm, we can whichever sorting algorithm that we want so we use quick sort.
The complexity of  bucket sort algorithm does not depend on the input. However in the average case the complexity of the algorithm is O(n + k) where n is the length of the input sequence, while k is the number of buckets. And then the worst case complexity increases to O(n^2).
As bucket sort tacked time to insert the element and then performs sorting algorithm on in, it is slower than quick sort as we give same input to the both of the sorting algorithms.
Example: for input size 1000 quick sort is a lot faster than bucket sort, quick sort takes time 151390 to sort the given elements as bucket sort takes 971631 which is a lot more as bucket sort need time for storing the elements in the bucket.


 *
 *************************************************************************/
import java.util.*;
 
public class SortBucketQuick
{
	/////////////////////////////////////////////
	//Quick Sort
	/////////////////////////////////////////////
	public static int partition(float arr[], int low, int high)
	{
		float pivot = arr[low]; 
		
		int i = (low + 1); // index of smaller element
		for (int j=low + 1 ; j<= high; j++)
		{
			// If current element is smaller than or
			// equal to pivot
			if (arr[j] < pivot)
			{
				
 
				// swap arr[i] and arr[j]
				float temp;
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = (float)temp;
				i++;
			}
		}
 
		// swap arr[i+1] and arr[high] (or pivot)
		float temp = arr[i - 1];
		arr[i - 1] = arr[low];
		arr[low] = temp;
 
		return i - 1;
	}
	 

	public static void QuickSort(float arr[], int low, int high)
	{
		if (low < high)
		{
			/* pi is partitioning index, arr[pi] is 
			  now at right place */
			int pi = partition(arr, low, high);
 
			// Recursively QuickSort elements before
			// partition and after partition
			QuickSort(arr, low, pi-1);
			QuickSort(arr, pi+1, high);
		}
	}
	//////////////////////////////////////////////////////////////////////////
	//Bucket Sort
	//////////////////////////////////////////////////////////////////////////
 
	public static float[] sort(float[] a, int maxVal) 
	{
		float [][] bucket=new float[maxVal][30];
		float [] seq = new float[maxVal];

		for (int i=0; i<bucket.length; i++) {
			for(int j = 0; j < 30; j++)
				bucket[i][j]=0;
		}

		int k = 0;
		for (int i=0; i < a.length; i++) 
		{
			//int idx = (int)((float)a.length * a[i]);
			int idx = (int)((float)a.length * a[i]);
			bucket[idx][0]++;
			k = (int)bucket[idx][0];
			if (k < 30)
				bucket[idx][k] = a[i];
			
		}

		int outPos=0;
		int i = 0;
		//for (int i=0; i<bucket.length; i++) 
		while( i <bucket.length)
		{
			if(bucket[i][0] != 0.0f)
			{
				QuickSort(bucket[i],1,(int)bucket[i][0]);		
				for (int j=1; j < bucket[i][0] + 1; j++) 
				{
				int idx = (int)((float)i/a.length);
				seq[outPos++]=bucket[i][j];// (float)i/a.length;
				}
			}
			i++;
		}

		return seq;
	}
   
   static float[] generateSeq(float[] sequence)
	{

		int i = 0;
		float t,u,r;		
		Random rand = new Random();
		
		while( i < sequence.length) 
		{
			float temp = (float)rand.nextFloat() * 1.0f;
			t = 2.0f * 3.14f ;
			t = t * temp;
			u = rand.nextFloat() * rand.nextFloat();
			//System.out.println("\nu and t "+u+" "+t+" "+temp);
			if (u > 1)
				r = 2.0f - u;
			else
				r = u;
			float x = Math.abs(r * (float)Math.cos(t));
			float y = Math.abs(r * (float)Math.sin(t));
			//System.out.println("\nx and y and r "+x+" "+y+" "+r);
			temp = x*x + y*y;
			//System.out.println("\ntemp "+temp);
			temp = (float)Math.sqrt(temp);
			//System.out.println("\ntemp "+temp);
			
			if (temp < 1.0f)
					sequence[i++] = temp;
		}	
		return sequence;
	}
	
	static void printSequence(float[] sorted_sequence) 
    {
        for (int i = 0; i < sorted_sequence.length; i++)
            System.out.print(sorted_sequence[i] + " ");
    }
 
 
   public static void main(String args[]) 
   {
		int N = 10;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter array size: ");
		int size = input.nextInt();
		System.out.println();
		
        float[] data = new float[size];
 
		data = generateSeq(data);

		//System.out.println("\nOriginal Sequence: ");
		//printSequence(data);
		long startTime = System.nanoTime();
		sort(data,data.length);
		System.out.println("Bucket Sort Time " + (System.nanoTime() - startTime) + " nanoseconds.");
		//System.out.println("\nSorted Sequence: ");
		//printSequence(data);
		
		data = generateSeq(data);
		
		//System.out.println("\nOriginal Sequence: ");
		//printSequence(data);
		
		startTime = System.nanoTime();
	    QuickSort(data, 0, data.length-1);
		System.out.println("Quick Sort Time " + (System.nanoTime() - startTime) + " nanoseconds.");
		
		//System.out.println("\nSorted Sequence: ");
		//printSequence(data);
   }

}
