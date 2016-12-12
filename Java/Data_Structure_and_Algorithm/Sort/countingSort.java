// Counting Sort. 
// Average, Best and Worst time: O(n + k)
// Space: O(k)

package whiteboard;

import java.util.Arrays;

public class Lab_whiteboard {

    public static void main(String[] args) {
        
        // Test countingSort2().
//      int[] array1 = { 3, 1, 2, 4, 3 };
//        int[] p = countingSort2(array1, 4);
//        System.out.println(Arrays.toString(p)); // [1, 2, 3, 3, 4]
//        
//        int[] array2 = { -1000, 1000 };
//        int[] p2 = countingSort2(array2, 1000);
//        //System.out.println(Arrays.toString(p2)); // Array index out of bounds. -1000
        
        
        // Test countingSort().
        Integer[] array = { 3, 6, 7, 10, 4, 3, 5, 7, 8, 10, 3, 4, 3, 9, 7, 3};
        countingSort(array);
        System.out.println(Arrays.toString(array));
        // [3, 3, 3, 3, 3, 4, 4, 5, 6, 7, 7, 7, 8, 9, 10, 10]
        
        // Test countingSortStableForObj().
        Person p1 = new Person("John1", 4);
        Person p2 = new Person("John3", 6);
        Person p3 = new Person("John4", 8);
        Person p4 = new Person("John2", 4);
        Person[] persons = new Person[]{p1, p2, p3, p4};
        Person[] sortedPersons = countingSortStableForObj(persons, 8);
        System.out.println(Arrays.toString(sortedPersons)); 
        // [John1, John2, John3, John4]   (Stable sorting)
        

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    // Stable Counting Sort for Objects. 
    // Average, Best and Worst time: O(n + k)
    // Space: O(k)
    // Author: CLRS p.160 + Aizu Book p.171 + kei
    // Date  : November 27, 2016
    public static Person[] countingSortStableForObj(Person[] persons, int k) {
        int[] counts = new int[k + 1];
        // Count.
        for (int i = 0; i < persons.length; i++) {
            int age = persons[i].age;
            counts[age]++;
        }
        System.out.println(Arrays.toString(counts));
        
        // Running Sum. 
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i - 1];
        }
        System.out.println(Arrays.toString(counts));

        // Get the result for stable sorting. 
        Person[] sortedPersons = new Person[persons.length];
        for (int i = persons.length - 1; i >= 0; i--) {
            int runningSum = counts[persons[i].age];
            sortedPersons[runningSum - 1] = persons[i];
            counts[persons[i].age]--;
        }
        //System.out.println(Arrays.toString(sortedPersons)); 
        // [John1, John2, John3, John4]   
        

        // Not stable sorting. 
//      for (int i = 0; i < persons.length; i++) {
//          int runningSum = counts[persons[i].age];
//          sortedPersons[runningSum - 1] = persons[i];
//          counts[persons[i].age]--;
//      }
//      //System.out.println(Arrays.toString(sortedPersons)); 
//      // [John2, John1, John3, John4]   
    

        return sortedPersons;
    }
    
    
    // Counting Sort for primitives. 
    // Average, Best and Worst time: O(n + k)
    // Space: O(k)
    // Author: internet + kei
    // Date  : November 27, 2016
    public static void countingSort(Integer[] array) { 
        if (array.length == 0) { return; } 
        
        // Determine minimum and maximum values. 
        Integer minValue = array[0]; 
        Integer maxValue = array[0]; 
        for (int i = 1; i < array.length; i++) { 
            if (array[i] < minValue) { 
                minValue = array[i]; 
            } else if (array[i] > maxValue) { 
                maxValue = array[i]; 
            } 
        } 
        
        countingSort(array, minValue, maxValue); 
    } 
    public static void countingSort(Integer[] array, int minValue, int maxValue) { 
        int[] count = new int[maxValue - minValue + 1]; 
        // Count the number of the same values. 
        for (int i = 0; i < array.length; i++) { 
            count[array[i] - minValue]++; 
        } 
        int sortedIndex = 0; 
        for (int i = 0; i < count.length; i++) { 
            while (count[i] > 0) { 
                array[sortedIndex++] = i + minValue; 
                count[i]--; 
            } 
        } 
    }
    
    
    // Sort the array A. O(n+k)
    // Pigeonhole sort? (Counting Sort)
    // This sort is effective if the value range is small. 
    // All the elements are in the set {0, 1, ... , k}.
    // The limitation here may be available memory.
    // Author: Codility + kei
    // Date  : November 6, 2015
    public static int[] countingSort2(int[] A, int k) {
        // Prepare an array that has the max value size.
        int[] count = new int[k + 1];
        
        // Count the elements in the array of counters.
        for (int i = 0; i < A.length; i++) {
            count[A[i]]++;
        }
        
        int p = 0;
        
        // Sort the array in ascending order. 
        // v is a value in the array A.
        for (int v = 0; v < count.length; v++) {
            // Iterate count[v] times.
            // p is a pointer.
            // Same value is next to each other.
            for (int j = 0; j < count[v]; j++) {
                A[p] = v;
                p++;
            }
            
        }
        
        return A;
    }


    


}
    
    
class Person {
    String name;
    int age;
    
    public Person(String s, int n) {
        this.name = s;
        this.age = n;
    }
    
    @Override
    public String toString() {
        return name;
    }       
}

























