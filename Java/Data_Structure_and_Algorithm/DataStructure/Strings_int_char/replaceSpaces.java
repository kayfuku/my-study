// Replace spaces with "%20".
// Author: Cracking the Coding Interview p.175
// Date  : January 31, 2016


public void replaceSpaces(char[] str, int length) {
    // Count spaces.
    int spaceCount = 0;
    for (int i = 0; i < length; i++) {
        if (str[i] == '') {
            spaceCount++;
        }
    }

    // Make a new char array. 
    int newLength;
    newLength = length + spaceCount * 2;
    str[newLength] = '\0';
    for (int i = length - 1; i >= 0; i--) {
        if (str[i] == ' ') {
            str[newLength - 1] = '0';
            str[newLength - 2] = '2';
            str[newLength - 3] = '%';
            newLength = newLength - 3;
        } else {
            str[newLength - 1] = str[i];
            newLength--;
        }
    }
}
