//Author: Sophie Pallanck
//Date: 4/23/20
/*Purpose: This program prompts a user for a type of sorting algorithm
and size of array and tells them the number of comparisons it took for the
specified sorting algorithm to sort the array */

package sort;

import java.util.Random;
import java.util.Scanner;

public class SortsDriver {
   
   /** Prompts user to choose a type of sort to perform and the size of
   *  array to sort on
   Precondition: User input is valid */
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      Random r = new Random();
      Sorts sorts = new Sorts();
      System.out.print("Enter sort (i[nsertion], q[uick], m[erge], r[adix], a[ll]): ");
      String choice = input.next();
      System.out.print("Enter n (size of array to sort): ");
      int size = input.nextInt();
      int[] randomArray = generateRandom(size, r);
      int[] copyArray = deepCopy(randomArray);
      if (choice.equalsIgnoreCase("i")) {
         sorts.insertionSort(copyArray, 0, size);
         display(randomArray, copyArray, false, "i", "insertion", sorts);
      } else if (choice.equalsIgnoreCase("m")) {
         sorts.mergeSort(copyArray, 0, size);
         display(randomArray, copyArray, false, "m", "merge", sorts);
      } else if (choice.equalsIgnoreCase("q")) {
         sorts.quickSort(copyArray, 0, size);
         display(randomArray, copyArray, false, "q", "quick", sorts);
      } else if (choice.equalsIgnoreCase("r")) {
         sorts.radixSort(copyArray);
         display(randomArray, copyArray, false, "r", "radix", sorts);
      } else { //user must have selected 'all' option
         sorts.insertionSort(copyArray, 0, size);
         display(randomArray, copyArray, false, "a", "insertion", sorts); //passes new copy of random array
         System.out.println();
         sorts.quickSort(copyArray = deepCopy(randomArray), 0, size);
         display(randomArray, copyArray, true, "a", "quick", sorts);
         System.out.println();
         sorts.mergeSort(copyArray = deepCopy(randomArray), 0, size);
         display(randomArray, copyArray, true, "a", "merge", sorts);
         sorts.radixSort(copyArray = deepCopy(randomArray));
      }
   }
   
   /** Takes an unsorted array, a sorted array, a Sorts object, two Strings
   *  representing the type of sort that has been done, and a boolean that keeps
   *  track of if unsorted array has been printed, and displays the arrays
   *  and the number of comparisons done on each array
   *  Precondition: unsorted and sorted are not null */
   public static void display(int[] unsorted, int[] sorted, boolean beenDone,
   String choice, String name, Sorts sorts) {
      if (unsorted.length > 20 && !choice.equalsIgnoreCase("a")) {
         System.out.println("Comparisons: " + sorts.getComparisonCount());
      } else if (unsorted.length > 20) { //user has picked 'all' option
         System.out.println(name + ": " + sorts.getComparisonCount());
      } else {
         if (!beenDone) { //checks if the unsorted array has been printed yet
            System.out.print("Unsorted: [");
            printArray(unsorted);
            System.out.println("]");
         }
         if (choice.equalsIgnoreCase("a")) {
            System.out.println(name + ": " + sorts.getComparisonCount());
         }
         System.out.print("Sorted: [");
         printArray(sorted);
         System.out.println("]");
         if (!choice.equalsIgnoreCase("a")) {
            System.out.println("Comparisons: " + sorts.getComparisonCount());
         }
      }
      sorts.resetComparisonCount();
   }
   
   /** Takes an integer representing the size of the array
   *  and a Random object and returns an array of specified size
   *  with randomly generated ingeters from -size..size+1
   *  Precondition: size >= 0 */
   public static int[] generateRandom(int size, Random r) {
      int[] randArr = new int[size];
      for (int i = 0; i < randArr.length; i++) {
         randArr[i] = r.nextInt(size * 2 + 1) - size;
      }
      return randArr;
   }
   
   /** Takes an array and returns a copy of the array passed in
   *  Precondition: originalArray is not null */
   public static int[] deepCopy(int[] originalArray) {
      int[] newCopy = new int[originalArray.length];
      for (int i = 0; i < originalArray.length; i++) {
         newCopy[i] = originalArray[i];
      }
      return newCopy;
   }
   
   /** Takes an array and prints out the elements of that array
   *  Precondition: list is not null */
   public static void printArray(int[] list) {
      for (int i = 0; i < list.length; i++) {
         System.out.print(" " + list[i]);
      }
   }
}
