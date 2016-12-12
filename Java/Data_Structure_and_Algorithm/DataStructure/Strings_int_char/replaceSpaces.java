// Replace spaces with "%20".
// Author: Cracking the Coding Interview p.175
// Date  : January 31, December 6, 2016


public void replaceSpaces(char[] str, int trueLength) {
    // Count spaces.
    int spaceCount = 0;
    for (int i = 0; i < trueLength; i++) {
        if (str[i] == ' ') {
            spaceCount++;
        }
    }

    // Make a new char array. 
    int index = trueLength + spaceCount * 2;
    if (trueLength < str.length) {
        str[trueLength] = '\0'; // End array. 
    }
    for (int i = trueLength - 1; i >= 0; i--) {
        if (str[i] == ' ') {
            str[index - 1] = '0';
            str[index - 2] = '2';
            str[index - 3] = '%';
            index = index - 3;
        } else {
            str[index - 1] = str[i];
            index--;
        }
    }
}

































