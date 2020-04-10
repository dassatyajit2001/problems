package com.problems.java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * Example 1:
 * 
 * Input: [[1,3],[2,6],[8,10],[15,18]] Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * 
 * 
 * Example 2:
 * 
 * Input: [[1,4],[4,5]] Output: [[1,5]] Explanation: Intervals [1,4] and [4,5]
 * are considered overlapping. NOTE: input types have been changed on April 15,
 * 2019. Please reset to default code definition to get new method signature.
 * 
 * 
 * @author satyajitdas
 *
 */
public class MergeIntervals {

	public static void main(String[] args) {
		int[][] intervals = { { 1, 3 }, { 8, 10 }, { 2, 6 }, { 15, 18 } };
		print(mergeIntervals(intervals));
	}

	private static void print(int[][] intervals) {
		for (int i = 0; i < intervals.length; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(intervals[i][j] + " ");
			}
			System.out.print(",");
		}
	}

	/**
	 * Brute force approach . Start with 1st interval, compare it with all other
	 * intervals If overlaps, remove the other interval and merge it to the 1st
	 * interval. Repeat this for the rest of intervals.This approach can't be better
	 * than O(n^2).
	 * 
	 * Step1: Sort the intervals increase order of time Step2: Push the 1st interval
	 * to a stack or array Step3: For each rest of intervals, check if the end of
	 * pushed interval and start of non pushed interval merge? Step4: If not then
	 * push it, else pop and update the stack's end time from the non-pushed end
	 * time Step5: at the end the stack contains merged intervals.
	 * 
	 * @return
	 */
	public static int[][] mergeIntervals(int[][] intervals) {
		sortIntervals(intervals);

		Stack<int[]> intervalStack = processMerge(intervals);
		int[][] res = new int[intervalStack.size()][];
		int size = intervalStack.size();
		for (int i = 0; i < size; i++) {
			res[i] = intervalStack.pop();
		}
		sortIntervals(res);
		return res;

	}

	private static Stack<int[]> processMerge(int[][] intervals) {
		// can be stack or arraylist
		Stack<int[]> intervalStack = new Stack<int[]>();
		intervalStack.push(intervals[0]);
		for (int i = 1; i < intervals.length; i++) {
			int[] temp = intervalStack.peek();
			if (temp[1] >= intervals[i][0]) {
				temp[1] = intervals[i][1];
				// intervalStack.push(temp);
			} else {
				// intervalStack.push(temp);
				intervalStack.push(intervals[i]);
			}
		}

		return intervalStack;
	}

	/**
	 * sorting the intervals
	 * 
	 * @param intervals
	 * @return
	 */
	public static int[][] sortIntervals(int[][] intervals) {
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		return intervals;
	}

}
