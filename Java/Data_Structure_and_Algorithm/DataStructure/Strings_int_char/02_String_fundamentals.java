// Substring. 
// Given a string, return a new string where "not " has been added to the front. However, if the string already begins with "not", return the string unchanged. Note: use .equals() to compare 2 strings.

// You can use startsWith(), but if you are not allowed to use it, 
// you need to check how long the original string is.
// Author: CodingBat + kei
// Date  : October 14, 2016
public String notString(String str) {
    // if (str.startsWith("not")) {
    //     return str;
    // }

    // Note that end in substring(start, end) is index of one char after 
    // something you want. Not length! 
    if (str.length() >= 3 && str.substring(0, 3).equals("not")) {
        return str;
    } else {
      return "not " + str;
    }
  
}


// Given a string, return a new string where the first and last chars have been exchanged. 
// frontBack("code") → "eodc"
// frontBack("a") → "a"
// frontBack("ab") → "ba"

// Author: CodingBat + kei
// Date  : October 14, 2016
public String frontBack(String str) {
  // To get the length of String, use length(), not length field.
  // Consider the string can be null, empty string, or one character. 
  if (str == null || str.length() <= 1) {
    return str;
  }
  
  char first = str.charAt(0);
  char last = str.charAt(str.length() - 1);
  
  // Note that the end of middle substring. 
  return last + str.substring(1, str.length() - 1) + first;
  
}

// // Bad one.
// public String frontBack(String str) {
//   if (str == "" || str == null) {
//     return "";
//   }
//   if (str.length == 1) {
//     return str;
//   }
  
//   char first = str.charAt(0);
//   char last = str.charAt(str.length - 1);
  
//   return last + str.substring(1, str.length - 2) + first;
  
// }


//Given a string, we'll say that the front is the first 3 chars of the string. If the string length is less than 3, the front is whatever is there. Return a new string which is 3 copies of the front.
// front3("Java") → "JavJavJav"
// front3("Chocolate") → "ChoChoCho"
// front3("abc") → "abcabcabc"

// Author: CodingBat + kei
// Date  : October 14, 2016
public String front3(String str) {
  if (str == null) {
    return str;
  }
  
  String front;
  if (str.length() <= 2) {
    front = str;
  } else { 
  // You should use 'else' because in the if statement variable 'front' changes
  // and there is no return. 
    front = str.substring(0, 3);
  }
  
  return front + front + front;
}

// //Bad 1.
// public String front3(String str) {
//   if (str == null || str.length() <= 3) {
//     return str;
//   }
  
//   String front = str.substring(0, 3);
  
//   return front + front + front;
// }

// // Bad 2.
// public String front3(String str) {
//   if (str == null) {
//     return str;
//   }
  
//   String front;
//   if (str.length() <= 2) {
//     front = str;
//   }
  
//   front = str.substring(0, 3);
  
//   return front + front + front;
// }


// Given a string, take the first 2 chars and return the string with the 2 chars added at both the front and back, so "kitten" yields"kikittenki". If the string length is less than 2, use whatever chars are there.
// front22("kitten") → "kikittenki"
// front22("Ha") → "HaHaHa"
// front22("abc") → "ababcab"

// Author: CodingBat + kei
// Date  : October 14, 2016
public String front22(String str) {
  if (str == null) {
    return str;
  }
  
  String front;
  // Avoid hard-coding.
  int take = 2;
  if (str.length() <= take) {
    front = str;
  } else {
    front = str.substring(0, take);
  }
  
  return front + str + front;
}


// Do this Q over and over again!!
// Given a string, return a string made of the first 2 chars (if present), however include first char only if it is 'o' and include the second only if it is 'z', so "ozymandias" yields "oz".
// startOz("ozymandias") → "oz"
// startOz("bzoo") → "z"
// startOz("oxx") → "o"

// Author: CodingBat + kei
// Date  : October 14, 2016
public String startOz(String str) {
  if (str == null) {
    return null;
  }
  
  String strAns = "";
  // Parallel if, not if-else because some strs are in both case.
  // Avoid index out of bound. 
  if (str.length() >= 1 && str.charAt(0) == 'o') {
    strAns += str.charAt(0);
  }
  if (str.length() >= 2 && str.charAt(1) == 'z') {
    strAns += str.charAt(1);
  }
  
  return strAns;
}


// // Bad.
// public String startOz(String str) {
//   if (str == null || str.length() < 2) {
//     return null;
//   }
//   String strAns = "";
//   if (str.charAt(0) == 'o') {
//     strAns += 'o';
//   }
//   if (str.charAt(1) == 'z') {
//     strAns += 'z';
//   }
  
//   return strAns;
// }


// substring()
// substring(0, 0) returns empty string. 
System.out.println("abc".substring(0, 0)); // ""

String s = "abcdef";
// Get the last 2 chars in a string.
System.out.println(s.substring(s.length() - 2)); // ef
// You can put "len" into end when using loop. 
// But putting "len + 1" into end causes a disaster.
System.out.println(s.substring(s.length() - 2, s.length())); // ef


// startsWith()
String s = "a";
System.out.println(s.startsWith("a")); // true
s = "";
System.out.println(s.startsWith("a")); // false
s = "abc";
System.out.println(s.startsWith("")); // true



// new String() returns empty string. 
System.out.println(new String());          // ""
// new StringBuffer() returns empty string. 
System.out.println(new StringBuffer());    // ""


// Given a non-empty string and an int N, return the string made starting with char 0, and then every Nth char of the string. So if N is 3, use char 0, 3, 6, ... and so on. N is 1 or more.
// everyNth("Miracle", 2) → "Mrce"
// everyNth("abcdefg", 2) → "aceg"
// everyNth("abcdefg", 3) → "adg"

// Arithmetic progression. 
// Author: CodingBat + kei
// Date  : October 14, 2016
public String everyNth(String str, int n) {
  StringBuffer sb = new StringBuffer();
  for (int i = 0; i < str.length(); i += n) {
    sb.append(str.charAt(i));
  }

  return sb.toString();
}

// Not so great. 
/* str: "Miracle"
 * len: 7  n: 2
 * i p sb
 * 0 0 M
 * 1 2 Mr
 * 2 4 Mrc
 * 3 6 Mrce
 * 4 8
 *
 */   
// Author: kei
// Date  : October 14, 2016
public String everyNth(String str, int n) {
  
  int len = str.length();
  int i = 0, p = 0;
  StringBuffer sb = new StringBuffer();
  while (p <= len - 1) {
    sb.append(str.charAt(p));
    i++;
    p = n * i; 
  }
  
  return sb.toString();
}


// Count the number of "xx" in the given string. We'll say that overlapping is allowed, so "xxx" contains 2 "xx".
// countXX("abcxx") → 1
// countXX("xxx") → 2
// countXX("xxxx") → 3

// Arithmetic progression. 
// Author: CodingBat + kei
// Date  : October 14, 2016
int countXX(String str) {
  int count = 0;
  // When writing condition clause, think about this.
  // i should stop at the second to last char. 
  for (int i = 0; i <= str.length() - 2; i++) {
    if (str.substring(i, i + 2).equals("xx")) {
      count++;
    }
  }

  return count;
}


// Given 2 strings, a and b, return the number of the positions where they contain the same length 2 substring. So "xxcaazz" and "xxbaaz" yields 3, since the "xx", "aa", and "az" substrings appear in the same place in both strings.
// stringMatch("xxcaazz", "xxbaaz") → 3
// stringMatch("abc", "abc") → 2
// stringMatch("abc", "axc") → 0

// Author: CodingBat + kei
// Date  : October 16, 2016
public int stringMatch(String a, String b) {
  if (a == null || b == null) {
    return -1;
  }
  // Figure which string is shorter.
  int len = Math.min(a.length(), b.length());
  int count = 0;
  for (int i = 0; i < len - 1; i++) {
    if (a.charAt(i) == b.charAt(i) && a.charAt(i + 1) == b.charAt(i + 1)) {
      count++;
    }
  }
  
  return count;
}

// // Bad
// // Don't assume that len of a and b are the same! 
// public int stringMatch(String a, String b) {
//   if (a == null || b == null) {
//     return -1;
//   }
  
//   int count = 0;
//   for (int i = 0; i < a.length() - 1; i++) {
//     if (a.charAt(i) == b.charAt(i) && a.charAt(i + 1) == b.charAt(i + 1)) {
//       count++;
//     }
//   }
  
//   return count;
// }


// Given a string, return a version where all the "x" have been removed. Except an "x" at the very start or end should not be removed.
// stringX("xxHxix") → "xHix"
// stringX("abxxxcd") → "abcd"
// stringX("xabxxxcdx") → "xabcdx"

// Note that word "remove" can be regarded as "append except".
// Author: CodingBat + kei
// Date  : October 16, 2016
public String stringX(String str) {
  if (str == null || str == "" || str.length() <= 1) {
    return str;
  }
  
  StringBuffer sb = new StringBuffer();
  for (int i = 1; i < str.length() - 1; i++) {
    // Only append the char if it is not 'x'.
    if (str.charAt(i) != 'x') {
      sb.append(str.charAt(i));
    }
  }
  
  return str.charAt(0) + sb.toString() + str.charAt(str.length() - 1);
}





























