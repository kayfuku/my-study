// String should not be appended using '+' in loop.
// Author: Cracking the Coding Interview p.74
// Date  : September 17, 2016


public String joinWords(String[] words) {
    String sentence = "";
    for (String w : words) {
        // Bad practice! Use StringBuffer like code below. 
        sentence += w;
    }

    return sentence.toString();
}


// O(N)
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append("hello");
}



// Bad code! O(N^2) 
// since string is immutable in Java. 
String s = "";
for (int i = 0; i < n; i++) {
    s += "hello";
}




























