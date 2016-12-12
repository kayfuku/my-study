// Array fundamentals.

// Author: kei
// Date  : October 20, 2016
int[] ints = new int[0];
System.out.println(Arrays.toString(ints)); // []

int[] ints = new int[1];
System.out.println(Arrays.toString(ints)); // [0]

int[] ints = new int[3];
System.out.println(Arrays.toString(ints)); // [0, 0, 0]


// In general, array lookup time is O(N) because you need to find 
// location of the value that you want to find. Or better yet, you 
// can do this time O(log N) using Binary Search if the array is 
// already sorted. Or even better, you can do this O(1) time actually. 
// Here is the solution. 
char[] word;
boolean[] flags = new boolean[128];
for (int i = 0; i < word.length; i++) {
    flags[word[i]] = true;
}
// At this point, you can determine whether given character is 
// in the word or not for O(1) time. 
if (flags[c]) {
    // Do something. 
}


// Author: kei
// Date  : October 13, 2016
package pack01;

import java.util.Arrays;

public class Lab01 {

    public static void main(String[] args) {

        int[] arr = new int[10];
        touchArray(arr);
        
        // Note that the array passed into touchArray() 
        // has changed by the method. 
        // You can use Arrays.toString() for debugging. 
        System.out.println(Arrays.toString(arr));
        // [3, 0, 0, 0, 0, 0, 0, 0, 0, 0]


        System.out.println("done.");
        return;
    } // end of main().

    public static void touchArray(int[] arr) {
        arr[0] = 3;
    }
}


// Given an array of ints, return true if one of the first 4 elements in the array is a 9. The array length may be less than 4.
// arrayFront9([1, 2, 9, 3, 4]) → true
// arrayFront9([1, 2, 3, 4, 9]) → false
// arrayFront9([1, 2, 3, 4, 5]) → false

// Author: kei
// Date  : October 14, 2016
public boolean arrayFront9(int[] nums) {
  // For-loop end condition varies depending on input length.
  int end = nums.length;
  if (end > 4) {
    end = 4;
  }

  // end varies depending on input length. 
  for (int i = 0; i < end; i++) {
    if (nums[i] == 9) {
      return true;
    }
  }
  
  return false;
}

// // Bad
// public boolean arrayFront9(int[] nums) {
//   for (int i = 0; i < nums.length; i++) {
//     if (i < 4 && nums[i] == 9) {
//       return true;
//     }
//     if (i == 4) {
//       return false;
//     }
//   }
//   return false;
// }


// Given an array of ints, return true if the sequence of numbers 1, 2, 3 appears in the array somewhere.
// array123([1, 1, 2, 3, 1]) → true
// array123([1, 1, 2, 4, 1]) → false
// array123([1, 1, 2, 1, 2, 3]) → true

// Author: kei
// Date  : October 16, 2016
public boolean array123(int[] nums) {
  if (nums == null || nums.length <= 2 /* This can be removed. */) {
    return false;
  }
  // As for condition clause, think about 2 ahead of i is still 
  // in the bound when writing 'len - 2'. 
  for (int i = 0; i < nums.length - 2; i++) {
    if (nums[i] == 1 && nums[i + 1] == 2 && nums[i + 2] == 3) {
      return true;
    }
  }
  
  return false;
}


// Copy array a. 
// Author: a topcoder.
// Date  : October 26, 2016
int[] copy = Arrays.copyOf(a, a.length);


// Sort array a. 
// Author: a topcoder.
// Date  : October 26, 2016
Arrays.sort(a);


// Author: someone in Qiita + kei
// Date  : October 26, 2016
Integer[] array = {5,3,1,2,4};
List<Integer> list = Arrays.asList(array);

Arrays.sort(array);                                   //ソート
Arrays.sort(array, Comparator.reverseOrder());        //降順になる
Arrays.sort(array, (x,y)->Integer.compare(y, x));     //コンパレータを渡してソート
Collections.sort(list);                               //ソート
Collections.sort(list, Collections.reverseOrder());   //逆順にソート
Collections.sort(list, (x,y)->Integer.compare(y, x)); //コンパレータを渡してソート
list.sort(Collections.reverseOrder());                //逆順にソート

Arrays.binarySearch(array, 3);     //二分探索
Collections.binarySearch(list, 3); //二分探索

Collections.reverse(list); //リストを逆順にする

Arrays.fill(array, 3); //配列を同じ値で埋める
Arrays.copyOf(array, array.length); //配列のコピーを返す
Arrays.copyOfRange(array, 0, 3);    //配列のコピーを返す

Arrays.toString(array);     //配列を文字列にして返す
Arrays.deepToString(array); //配列を文字列にして返す(多次元配列の中身も文字列化される)
list.toString();            //リストを文字列にして返す

Arrays.hashCode(array); //配列のハッシュを返す
list.hashCode();        //リストのハッシュを返す



// Array to List using Arrays.asList(). 
// Author: kei 
// Date  : October 30, 2016
String[] arrayStrings = {"a", "b", "c"};
// O(1)
ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(arrayStrings));
//System.out.println(arrayList.toString()); // [a, b, c]

ArrayList<Integer> arrayList2 = new ArrayList<Integer>(Arrays.asList(1, 2, 4));
//System.out.println(arrayList2.toString()); // [1, 2, 4]

int[] ints = new int[]{1, 2, 4};
ArrayList<Integer> arrayList3 = new ArrayList<Integer>(Arrays.asList(1, 2, 4));
//System.out.println(arrayList3.toString()); // [1, 2, 4]

// Arrays.asList() provides hashCode() automatically. 
HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
set.add(arrayList2);
ArrayList<Integer> arrayList4 = new ArrayList<Integer>();
arrayList4.add(1);
arrayList4.add(2);
arrayList4.add(4);
System.out.println(arrayList4.toString()); // [1, 2, 4]
System.out.println(set.contains(arrayList3)); // true
System.out.println(set.contains(arrayList4)); // true



// How to create an Object array. 
// Author: kei 
// Date  : November 27, 2016
Person p1 = new Person("John1", 16);
Person p2 = new Person("John3", 16);
Person p3 = new Person("John4", 20);
Person p4 = new Person("John2", 23);

Person[] persons = new Person[]{p1, p2, p3, p4};


// If the ArrayList length is 0, get(i) causes 
// IndexOutOfBoundsException instead of returning null. 
// Author: kei 
// Date  : November 28, 2016
ArrayList<Integer> aList = new ArrayList<>();
int n = (int) aList.get(0); // IndexOutOfBoundsException. 
System.out.println(n); 






























