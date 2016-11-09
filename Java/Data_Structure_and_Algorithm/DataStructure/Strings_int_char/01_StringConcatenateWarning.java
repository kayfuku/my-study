// String should not be appended using '+' in loop.
// Author: Cracking the Coding Interview p.74
// Date  : September 17, 2016


public String joinWords(String[] words) {
    String sentence = "";
    for (String w : words) {
        // Bad practice! Use StringBuffer!
        sentence += w;
    }

    return sentence.toString();
}







