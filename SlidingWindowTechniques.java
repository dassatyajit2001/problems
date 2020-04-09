package com.problems.java;

import java.util.HashMap;
import java.util.Map;

/**
 * 2 types of techniques: 1) fixed length sliding window 2) Dynamically resize
 * window(growing and shrinking sliding window). more like a caterpillar Brute
 * force usually takes O(n*k) Better technique can be done in O(n) Example
 * contiguous ds->array,String,Linked List Example questions->
 * min,max,longest,shortest,contained May be we need to calculate something,
 * such as running average
 * 
 * Fixed Length-> eg->problems always deals with a fixed size
 * 
 * Dynamic length->eg->smallest sum(any window size) >= sum value S
 * 
 * Dynamic length with auxillary data structure eg-> longest substring with k
 * distinct characters
 * 
 * String permutation-> eg-> Given 2 strings is the 2nd string exists as a
 * permutation in the parent string
 * 
 * The commonalities-> Everything grouped sequentially longest,
 * smallest,contains,max/min
 * 
 * 
 * @author satyajitdas
 *
 */
public class SlidingWindowTechniques {

	public static void main(String[] args) {
//		System.out.println(maxSumInContiguousSubArray(new int[] { 2, 6, 4, 1, 9, 0, 3, 1 }, 3));
//		System.out.println(maxSumInContiguousSubArray(new int[] { 2, 6, 4, 1, 9, 0, 3, 1 }, 4));
//		System.out.println(maxSumInContiguousSubArray(new int[] { 2, 6, 4, 1, 9, 0, 3, 1 }, 8));
//		System.out.println(findSmallestSubArray(new int[] { 4, 2, 2, 7, 8, 1, 2, 8, 10 }, 6));
		System.out.println(longestSubstringWithKChars(new int[] { 4, 2, 2, 7, 8, 7, 2, 8, 10 }, 3));


	}

	/**
	 * Problems->
	 */

	/**
	 * this tells the maximum sum of fixed consecutive length in an array
	 */
	public static int maxSumInContiguousSubArray(int[] arr, int subLength) {
		if (arr == null || arr.length == 0 || subLength < 1 || subLength > arr.length) {
			return Integer.MIN_VALUE;
		}
		// initialize the sum
		int sum = 0, maxSum = 0;
		for (int i = 0; i < subLength; i++) {
			sum += arr[i];
		}
		maxSum = sum;
		for (int i = 1; i <= arr.length - subLength; i++) {
			maxSum = Math.max(maxSum, (sum - arr[i - 1] + arr[i + subLength - 1]));
			sum = (sum - arr[i - 1] + arr[i + subLength - 1]);
		}
		return maxSum;
	}

	/**
	 * this method finds the smallest subarray whose sum is >= a given value. This
	 * is dynamic length problem
	 */
	public static int findSmallestSubArray(int[] arr, int targetSum) {
		int start = 0, end = 0, minSum = Integer.MAX_VALUE, currentSum = 0;
		labelfor: for (; end < arr.length; end++) {
			currentSum += arr[end];
			labelwhile: while (currentSum >= targetSum) {
				if (end - start + 1 == 1) {
					break labelfor;
				}
				start++;
				currentSum -= arr[start];
				minSum = Math.min(minSum, currentSum);
			}

		}
		return end - start + 1;
	}

	/**
	 * to do this method finds the smallest subarray whose sum is <= a given value.
	 * This is dynamic length problem
	 */
	public static int findlongestSubArray(int[] arr, int targetSum) {
		int start = 0, end = 0, minSum = Integer.MAX_VALUE, currentSum = 0;
		labelfor: for (; end < arr.length; end++) {
			currentSum += arr[end];
			labelwhile: while (currentSum >= targetSum) {
				if (end - start + 1 == 1) {
					break labelfor;
				}
				start++;
				currentSum -= arr[start];
				minSum = Math.min(minSum, currentSum);
			}

		}
		return end - start + 1;
	}

	/**
	 * Longest substring length with K distinct characters
	 * 
	 */

	public static int longestSubstringWithKChars(int[] arr, int k) {
		Map<Integer, Integer> charMap = new HashMap<>();
		int start = 0, end = 0, maxLength = Integer.MIN_VALUE;
		for (; end < arr.length; end++) {
				updateMap(arr, charMap, end);			
				while(charMap.size()>k)
				{
					charMap.put(arr[start], charMap.get(arr[start]) - 1);
					if(charMap.get(arr[start])==0)
					{
						charMap.remove(arr[start]);	
					}
					start++;					
				}
				
				maxLength=Math.max(maxLength, end-start+1);
		}
		return maxLength;
	}

	private static void updateMap(int[] arr, Map<Integer, Integer> charMap, int end) {
		if (charMap.containsKey(arr[end])) {
			charMap.put(arr[end], charMap.get(arr[end]) + 1);
		}
		else {
			charMap.putIfAbsent(arr[end], 1);
		}
	}

	/**
	 * Smallest substring length with K distinct characters
	 * 
	 */
}
