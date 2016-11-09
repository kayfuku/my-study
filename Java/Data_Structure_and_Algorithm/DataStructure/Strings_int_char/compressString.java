// Compress String.
// Author: Cracking the Coding Interview p.176 + kei.
// Date  : January 31, 2016
// ex.  "aabcccccaaa" >> "a2b1c5a3"


String compressString(String str) {

    // Check if the compressed String size is greater than original.
    int size = countCompressedSize(str);
    if (size >= str.length()) {
        return str;
    }

    // Compress using StringBuffer because it's in loop.
    StringBuffer strCompressed = new StringBuffer();
    char last = str.charAt(0);
    int count = 1;
    // i starts with 1 because you wanna compare with the last one.
    for (int i = 1; i < str.length(); i++) {
        if (str.charAt(i) == last) {
            // Character repeated.
            count++;
        } else {
            // Character changed. 
            strCompressed.append(last);
            strCompressed.append(count);
            last = str.charAt(i);
            // Initialize the counter.
            count = 1;
        }        
    }
    strCompressed.append(last);
    strCompressed.append(count);

    return strCompressed.toString();
}


int countCompressedSize(String str) {
    char last = str.charAt(0);
    int size = 0;
    int count = 1;
    for (int i = 1; i < str.length(); i++) {
        if (str.charAt(i) == last) {
            count++;
        } else {
            last = str.charAt(i);
            // Convert int to String and then 
            // get the num of digits as a String.
            size += 1 + String.valueOf(count).length();
            // Initialize the counter.
            count = 1;
        }        
    }

    size += 1 + String.valueOf(count).length();

    return size;
}





















