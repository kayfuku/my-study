// Array to ArrayList
// Author: Cracking the Coding Interview p.74
// Date  : September 17, 2016

ArrayList<String> merge(String[] words, String[] more) {
    ArrayList<String> sentence = new ArrayList<String>();
    for (String s : words) {
        sentence.add(s);
    }
    for (String s : more) {
        sentence.add(s);
    }
    return sentence;
}


























