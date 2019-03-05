import java.io.*;
import java.util.Random;
import java.util.Scanner;
class DivideConquer
{
     static int maxSum;
     static int leftMaxSum;
     static int rightMaxSum;
     static int crossMaxSum;

     public static void main(String args[])
     {
    	 	Scanner input = new Scanner(System.in);
         System.out.print("Enter array size: ");
         int size = input.nextInt();
         System.out.println();
         
         // create the array (the numbers do not really matter)
         int[] numbers = new int[size];
         Random rand = new Random();
         for(int i=0; i<numbers.length; i++){
             // we want the numbers sorted for binary search
             // so why not just the numbers 0,1,...,size-1
             numbers[i]=rand.nextInt();
         }
         
         // store the time now
         long startTime = System.nanoTime();
         System.out.println("Brute Force");
         System.out.println("Max Sub Array Sum = "+max_sum(numbers));
         // display the time elapsed
 		System.out.println("The time taken by it is " + (System.nanoTime() - startTime) + " nanoseconds.");
 		
         
 		startTime = System.nanoTime();
 		findMaximumSubArray(numbers, 0 , numbers.length - 1);
 		System.out.println("Divide & conquer");
 		System.out.println("Max Sub Array Sum = "+maxSum);
         // display the time elapsed
 		System.out.println("The time taken by it is " + (System.nanoTime() - startTime) + " nanoseconds.");
 		
     }
     public static int max_sum(int arr[])
 	{
         int max, sum, i, j;

 		max = Integer.MIN_VALUE;
         for (i = 0; i < arr.length; i++)
         {
             sum = 0;
             for (j = i; j < arr.length; j++)
             {
                 sum = sum + arr[j];
                 if (sum > max)
                     max = sum;
             }
         }
         return max;
     }
 	public static void findMaximumSubArray(int arr[],int low,int high)
 	{
 		int mid;
 		if(low == high)
 		{
 			maxSum = arr[low];
 			return ;
 		}
 		
 		mid=(low + high)/2;

 		findMaximumSubArray(arr,low,mid);
 		leftMaxSum=maxSum;

 		findMaximumSubArray(arr,mid+1,high);
 		rightMaxSum=maxSum;

 		findMaxCrossingSubArray(arr,low,mid,high);

 		if(leftMaxSum >= rightMaxSum && leftMaxSum >= crossMaxSum)
 		{
 			maxSum = leftMaxSum;
 			return;
 		}
 		if(rightMaxSum >= leftMaxSum && rightMaxSum >= crossMaxSum)
 		{
 			maxSum = rightMaxSum;
 			return;
 		}
 		if(crossMaxSum >= rightMaxSum && crossMaxSum >= leftMaxSum)
 		{
 			maxSum = crossMaxSum;
 			return;
 		}
 	}
 	public static void findMaxCrossingSubArray(int arr[],int low,int mid,int high)
 	{
 		int i;
 		int leftSum, rightSum, sum;
 	   
 		sum=0;;
 		leftSum=Integer.MIN_VALUE;

 		for(i = mid;i >= low; i--)
 		{
 			sum = sum + arr[i];
 			if(sum > leftSum)
 			{
 				 leftSum = sum;
 			}
 		}

 		sum=0;
 		rightSum=Integer.MIN_VALUE;
 		
 		for(i = mid+1; i <= high; i++)
 		{
 			sum = sum + arr[i];
 			if(sum > rightSum)
 			{
 				 rightSum = sum;
 			}
 	   }

 	   crossMaxSum = leftSum + rightSum;
 	}
}
