// Check if a string has all unique chars.
// Character code is ASCII.
// Author: Cracking the Coding Interview p.172 + kei.
// Date  : February 14, 2016


public boolean hasUniqueChars(String str) {
    if (str.length() > 256) {
        return false;
    }

    boolean[] checker = new boolean[256];
    for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        if (checker[c]) {
            return false;
        }
        checker[c] = true;
    }

    return true;
}

