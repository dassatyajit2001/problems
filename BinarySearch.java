package com.problems.java;

public class BinarySearch {

	public static void main(String[] args) {
		System.out.println(binarySearch(new int[]{1,4,6,8,9,10,15,17},19));
	}
	
	/**
	 * The below steps tells  an iterative way of finding an element using Binary search
	 * Prerequisite: elements should be in sorted in ascending order
	 * @param arr
	 * @param value
	 * @return
	 */
	public static int  binarySearch(int[] arr,int value)
	{
		// return -1 when array is null or empty
		if(arr==null || arr.length==0)
			return -1;
		//mid ,start and end are initialized
		int mid=arr.length/2, start=0, end=arr.length-1;
		
		//loops runs while start is less than or equal to end
		while(start<=end)
		{
			//if value is found in mid return the index
			if(value==arr[mid])
				return mid;
			
			// else if values is less than mid value then elements is present in 1st half
			//let the start be the same just move the end to mid-1 and recalculate mid
			else if(value<arr[mid])
			{
				end=mid-1;
				mid=(start+end)/2;
			}
			// else if value is greater than mid value then elements is present in 2nd half
			//let the end be the same just update the start to mid+1 and recalculate mid
			else
			{
				start=mid+1;
				mid=(start+end)/2;
			}
		}
		return -1;
	}
}
