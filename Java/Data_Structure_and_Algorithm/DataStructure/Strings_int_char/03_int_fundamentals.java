// Given two temperatures, return true if one is less than 0 and the other is greater than 100.

// Author: CodingBat + kei
// Date  : October 14, 2016
public boolean icyHot(int temp1, int temp2) {
  return ((temp1 < 0) && (temp2 > 100))
         /* You should think about the other way around. */
         || ((temp2 < 0) && (temp1 > 100));
}

// // Bad.
// public boolean icyHot(int temp1, int temp2) {
//   return (temp1 < 0) && (temp2 > 100);
// }


// We'll say that a number is "teen" if it is in the range 13..19 inclusive. Given 2 int values, return true if one or the other is teen, but not both.
// loneTeen(13, 99) → true
// loneTeen(21, 19) → true
// loneTeen(13, 13) → false

// Author: CodingBat + kei
// Date  : October 14, 2016
public boolean loneTeen(int a, int b) {
  // To clean code check if a is in the range in advance. 
  boolean aTeen = a >= 13 && a <= 19;
  boolean bTeen = b >= 13 && b <= 19;
  
  return (aTeen && !bTeen) || (!aTeen && bTeen);
}

// // Not so great. 
// public boolean loneTeen(int a, int b) {
//   return (a >= 13 && a <= 19 && (b < 13 || b > 19)) 
//          || (b >= 13 && b <= 19 && (a < 13 || a > 19));
// }

// // Bad.
// public boolean loneTeen(int a, int b) {
//   return (a >= 13 && a <= 19 && b < 13 && b > 19) 
//          || (b >= 13 && b <= 19 && a < 13 && a > 19);
// }


// Get last digit.
// ex.  17 --> 7, 7 --> 7, 113 --> 3
// Author: CodingBat + kei
// Date  : October 14, 2016
public int getLastDigit(int a) {
  return a % 10;
}


// Get arithmetic progression. 
// d is common difference. 
// ex.
// 0 2 4 6 8 10 12 14 ...
// 0 3 6 9 12 15 18 21 ...

// for loop.
int d = 3;
for (int i = 0; i < 100; i += d) {
  System.out.println(i);
}

// while loop.
int d = 3;
int i = 0;
while (i < 100) {
  System.out.println(i);
  i += d;
}


























