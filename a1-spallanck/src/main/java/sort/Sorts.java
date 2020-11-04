//Author: Sophie Pallanck
//Date: 4/23/20
//Purpose: This program implements several sorting algorithms to sort integer arrays;

package sort;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;

public class Sorts {
   
   // maintains a count of comparisons performed by this Sorts object
   private int comparisonCount;
   
   public int getComparisonCount() {
      return comparisonCount;
   }
   
   public void resetComparisonCount() {
      comparisonCount = 0;
   }
   
   /** Sorts A[start..end] in place using insertion sort
   * Precondition: 0 <= start <= end <= A.length */
   public void insertionSort(int[] A, int start, int end) {
      int i = start;
      while (i < end) {
         int j = i;
         while (j > start && A[j] < A[j - 1]) { //found a value to be swapped
            swap(A, j, j - 1);
            j--;
            comparisonCount++;
         }
         i++;
      }
   }
   
   /** Partitions A[start..end] around the pivot A[pivIndex]; returns the
   *  pivot's new index.
   *  Precondition: start <= pivIndex < end
   *  Postcondition: If partition returns i, then
   *  A[start..i] <= A[i] <= A[i+1..end]
   **/
   public int partition(int[] A, int start, int end, int pivIndex) {
      swap(A, start, pivIndex); //put our pivot at the beginning of array
      int pointer = start + 1;
      for (int i = pointer; i < end; i++) {
         if (A[i] <= A[start]) { //found a value smaller than pivot
            swap(A, i, pointer++);
         }
         comparisonCount++;
      }
      swap(A, start, pointer - 1); //swap pivot into correct position
      return pointer - 1;
   }
   
   /** use quicksort to sort the subarray A[start..end] */
   public void quickSort(int[] A, int start, int end) {
      if (end - start < 2) {
         return;
      }
      if (start < end) {
         int piv = partition(A, start, end, start);
         quickSort(A, start, piv);
         quickSort(A, piv + 1, end);
      }
   }
   
   /** merge the sorted subarrays A[start..mid] and A[mid..end] into
   *  a single sorted array in A. */
   public void merge(int[] A, int start, int mid, int end) {
      int[] copy = new int[A.length];
      for (int i = 0; i < A.length; i++) {
         copy[i] = A[i];
      }
      int i = start;
      int pointer = start; //scans through list
      int j = mid;
      while (i < mid && j < end) { //scan sub-lists, taking smaller of each
         if (copy[i] < copy[j]) {
            A[pointer] = copy[i];
            i++;
         } else {
            A[pointer] = copy[j];
            j++;
         }
         comparisonCount++;
         pointer++;
      }
      while (i < mid) { //copy any remaining values over
         A[pointer] = copy[i];
         i++;
         pointer++;
      }
      while (j < end) { //copy any remaining values over
         A[pointer] = copy[j];
         j++;
         pointer++;
      }
   }
   
   /** use mergesort to sort the subarray A[start..end] */
   public void mergeSort(int[] A, int start, int end) {
      if (end - start < 2) { //length of 1 is already sorted
         return;
      }
      if (start < end) {
         int mid = (start + end) / 2;
         mergeSort(A, start, mid);
         mergeSort(A, mid, end);
         merge(A, start, mid, end);
      }
   }
   
   /** Sort A using LSD radix sort. Uses counting sort to sort on each digit */
   public void radixSort(int[] A) {
      int n = A.length;
      int max = getMax(A, n);
      int min = getMin(A, n);
      int absMax = Math.max(Math.abs(min), max); //need to know # digits to sort through
      for (int pos = 1; (absMax / pos) > 0; pos*=10) {
         countSort(A, n, pos, max, min);
      }
   }
   
   /** Returns the largest value in A
   * Precondition: A is not null, n >= 0*/
   public int getMax(int[] A, int n) {
      int max = A[0];
      for (int i = 1; i < n; i++) {
         if (A[i] > max) {
            max = A[i];
         }
      }
      return max;
   }
   
   /** Returns the smallest value in A
   * Precondition: A is not null, n >= 0*/
   public int getMin(int[] A, int n) {
      int min = A[0];
      for (int i = 1; i < n; i++) {
         if (A[i] < min) {
            min = A[i];
         }
      }
      return min;
   }
   
   /** Sorts A digit by digit using Counting Sort algorithm
   * Precondition: A is not null
   Postcondition: A is sorted in ascending order */
   public void countSort(int[] A, int n, int pos, int max, int min) {
      int[] B = new int[n];
      int[] count = new int[0];
      if (min >= 0) { //No negative values
         min = 0;
         count = new int[10];
      } else { //Negative values in array
         int range = max - min + 1;
         count = new int[range];
      }
      for (int i = 0; i < count.length; i++) {
         count[i] = 0;
      }
      for (int i = 0; i < n; i++) { //build up count array with frequencies of digits
         count[((A[i] / pos) % 10) - min]++;
      }
      for (int i = 1; i < count.length; i++) { //update count with cumulative sum of elements
         count[i] += count [i - 1];
      }
      for (int i = n - 1; i >= 0; i--) { //place numbers in proper sorted index
         B[--count[(((A[i] / pos) % 10) - min)]] = A[i];
      }
      for (int i = 0; i < n; i++) {
         A[i] = B[i];
      }
   }
   
   /* return the 10^d's place digit of n */
   private int getDigit(int n, int d) {
      return (n / ((int)Math.pow(10, d))) % 10;
   }
   
   /** swap a[i] and a[j]
   *  pre: 0 < i, j < a.size
   *  post: values in a[i] and a[j] are swapped */
   public void swap(int[] a, int i, int j) {
      int tmp = a[i];
      a[i] = a[j];
      a[j] = tmp;
   }
   
}
