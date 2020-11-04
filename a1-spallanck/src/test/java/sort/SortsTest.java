//Author: Sophie Pallanck
//Date: 4/19/20
//Purpose: This program provides testing code for the Sorts.java class

package sort;

import static org.junit.Assert.*;
import org.junit.FixMethodOrder;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Random;
import java.util.Collections;
import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SortsTest {

  //////////////////////////////////////////
  // JUnit test cases for sorting methods //
  //////////////////////////////////////////

  /** Insertion sort tests */
  @Test
  public void test00Insertion() {
    int[] A = {8, 6, 7, 9, 4, 8, 1, 4, 10, 3};
    int[] Aorig = A.clone();
    Sorts s = new Sorts();
    s.insertionSort(A, 0, A.length);
    check(A, Aorig, 0, A.length);
  }

  @Test
  public void test01Insertion() {
    int[] A = getRandomArray(1000, 1);
    int[] Aorig = A.clone();
    Sorts s = new Sorts();
    s.insertionSort(A, 0, A.length);
    check(A, Aorig, 0, A.length);
  }

  @Test
  public void test02Insertion() {
    int[] A = getSortedArray(101);
    int[] Aorig = A.clone();
    Sorts s = new Sorts();
    s.insertionSort(A, 0, A.length);
    check(A, Aorig, 0, A.length);
  }

  @Test
  public void test03Insertion() {
    int[] A = getRandomArray(101, 3);
    int[] Aorig = A.clone();
    Sorts s = new Sorts();
    s.insertionSort(A, 7, 18);
    check(A, Aorig, 7, 18);
  }

  @Test
  public void test04Insertion() {
    int[] A = getRandomArray(1000, 4);
    int[] Aorig = A.clone();
    Sorts s = new Sorts();
    s.insertionSort(A, 0, 900);
    check(A, Aorig, 0, 900);
  }


  /** Merge and mergesort tests. */
  @Test
  public void test10Merge() {
    int[] A = getRandomArray(15, 10);
    int[] Aorig = A.clone();
    Arrays.sort(A, 0, A.length/2);
    Arrays.sort(A, A.length/2, A.length);

    Sorts s = new Sorts();
    s.merge(A, 0, A.length/2, A.length);
    check(A, Aorig, 0, A.length);

  }

  @Test
  public void test11Merge() {
    int[] A = getRandomArray(200, 11);
    int[] Aorig = A.clone();

    Arrays.sort(A, 0, 100);
    Arrays.sort(A, 100, A.length);
    Sorts s = new Sorts();
    s.merge(A, 0, 100, A.length);
    check(A, Aorig, 0, A.length);
  }

  @Test
  public void test12Mergesort() {
    int[] A = getRandomArray(64, 12);
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    s.mergeSort(A, 0, A.length);
    check(A, Aorig, 0, A.length);
  }

  @Test
  public void test13Mergesort() {
    int[] A = getRandomArray(1001, 13);
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    s.mergeSort(A, 0, A.length);
    check(A, Aorig, 0, A.length);
  }

  @Test
  public void test14Mergesort() {
    int[] A = getRandomArray(60, 14);
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    s.mergeSort(A, 1, A.length);
    check(A, Aorig, 1, A.length);
  }

  /** Partition and quicksort tests. */
  @Test
  public void test20Partition() {
    int[] A = getRandomArray(15, 20);
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    int pi = s.partition(A, 0, A.length, 0);
    assertTrue(isPartitioned(A, 0, A.length, pi));
    assertEquals(A[pi], Aorig[0]);
  }

  @Test
  public void test21Partition() {
    int[] A = getRandomArray(200, 21);
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    int pi = s.partition(A, 0, A.length, A.length-1);
    assertTrue(isPartitioned(A, 0, A.length, pi));
    assertEquals(A[pi], Aorig[A.length-1]);
  }

  @Test
  public void test22Partition() {
    int[] A = getRandomArray(200, 22);
    int[] Aorig = A.clone();
    Sorts s = new Sorts();
    int pi = s.partition(A, 100, 200, 104);
    assertTrue(isPartitioned(A, 100, 200, pi));
    assertEquals(A[pi], Aorig[104]);
  }

  @Test
  public void test23Quicksort() {
    int[] A = getRandomArray(1001, 23);
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    s.quickSort(A, 0, A.length);
    check(A, Aorig, 0, A.length);
  }

  @Test
  public void test24Quicksort() {
    int[] A = getRandomArray(100, 24);
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    s.quickSort(A, 4, 10);
    check(A, Aorig, 4, 10);
  }


  /** Tests for radix sort. */
  @Test
  public void test30Radix() {
    int[] A = {1, 8, 3, 4, 9, 6, 5, 2};
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    s.radixSort(A);
    check(A, Aorig, 0, A.length);
  }

  @Test
  public void test31Radix() {
    int[] A = getRandNonnegArray(193, 32);
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    s.radixSort(A);
    check(A, Aorig, 0, A.length);

  }

  @Test
  public void test32Radix() {
    int[] A = getSortedArray(103);
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    s.radixSort(A);
    check(A, Aorig, 0, A.length);
  }

  @Test
  public void test33Radix() {
    int[] A = new int[]{-1, 4, -6, -8, -4, -6, 3, -7, -7};
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    s.radixSort(A);
    check(A, Aorig, 0, A.length);
  }

  @Test
  public void test34Radix() {
    int[] A = new int[]{-183, 4, -6, -8, -4, -6, 3, -7, -7};
    int[] Aorig = A.clone();

    Sorts s = new Sorts();
    s.radixSort(A);
    check(A, Aorig, 0, A.length);
  }

  /////////////////////////////////////////////
  // Helper methods for testing sort methods //
  /////////////////////////////////////////////

  /** Asserts that sorted is a correctly sorted copy of orig.
   *  Precondition: sorted and orig are not null, have the same length, and
   *   0 <= start <= end <= sorted.length */
  private static void check(int[] sorted, int[] orig, int start, int end) {
    assertTrue(isSorted(sorted, start, end));
    assertTrue(sameElements(sorted, orig, start, end));
  }

  /** Returns true if and only if A[start..end] is sorted in ascending order.
   * Preconditon: A is not null, and 0 <= start <= end <= A.length.
   **/
  private static boolean isSorted(int[] A, int start, int end) {
    if (end - start <= 1) { //empty array & 1 element array are sorted
       return true;
    }
    int prev = A[start]; 
    for (int i = start + 1; i < end; i++) {
       if (A[i] < prev) {
          return false;
       } else {
          prev = A[i];
       }
    }
    return true;
  }


  /** Returns true if and only if A[start..end] and B[start..end] contain
   * exactly the same elements. 
   * Precondition: A and B are not null, and:
   *    0 <= start <= end <= A.length = B.length 
   **/
  public static boolean sameElements(int[] A, int[] B, int start, int end) {
    HashMap<Integer, Integer> inventory = new HashMap<>();
    for (int i = start; i < end; i++) {
       if (!inventory.containsKey(A[i])) {
          inventory.put(A[i], 1); //add values we haven't seen to Map
       } else {
          inventory.put(A[i], inventory.get(A[i]) + 1); //we've seen this value before
       }
    }
    for (int i = start; i < end; i++) {
       if (!inventory.containsKey(B[i])) { 
          return false;
       }
       if (inventory.get(B[i]) == 0) { //B has too many of this val
          return false; 
       }
       inventory.put(B[i], inventory.get(B[i]) - 1);
    }
    return true;
  }


  /** Returns true if and only if A[start..end] is partitioned around the
   * element at A[pi].
   * In other words, A[start..pi] <= A[pi] <= A[pi+1..end] */
  public static boolean isPartitioned(int[] A, int start, int end, int pi) {
    for (int i = start; i < pi; i++) { //first half should be smaller than pi 
       if (A[i] > A[pi]) {
          return false;
       }
    }
    for (int j = pi + 1; j < end; j++) { //second half should be larger than pi
       if (A[j] < A[pi]) {
          return false;
       }
    }
    return true;
  }



  //////////////////////////////////////////
  // Helper methods for generating arrays //
  //////////////////////////////////////////

  /** Returns a sorted array of size n with elements 0..n */
  private static int[] getSortedArray(int n) {
    int[] A = new int[n];
    for (int i = 0; i < A.length; i++) {
      A[i] = i;
    }
    return A;
  }

  /** Returns an array of length n filled with random integers in [0,n-1]
   * (inclusive) */
  private static int[] getRandNonnegArray(int n, long seed) {
    int[] A = new int[n];
    Random rand = new Random(seed);
    for (int i = 0; i < n; i++) {
      A[i] = rand.nextInt(n);
    }
    return A;
  }


  /** Returns an array of length n filled with random integers in [-n,n]
   * (inclusive) */
  private static int[] getRandomArray(int n, long seed) {
    int[] A = new int[n];
    Random rand = new Random(seed);
    for (int i = 0; i < n; i++) {
      A[i] = rand.nextInt(2*n+1) - n;
    }
    return A;
  }

}
