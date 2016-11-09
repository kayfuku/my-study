// Build Patterns with cutting some branches of recursion tree.
// Build parentheses pattern. 
// ex. ((())), ()(()), (())(), ()()(), (()())
// Author: CtCI 6th 8.9 p.360 + kei
// Date  : October 25, 2016

// Build patterns. 
//
//          /     \    index      o -> '('   c -> ')'
//         o       c     0        x -> branch cutting (some omitted)
//       /   \      x  
//      o     c          1
//     /|    /  \
//    o c   o    c       2
//   x| |\  |\    x
//    c o c o c          3
//    | | | | |\
//    c c o c o c        4
//    | | | | |  x
//    c c c c c          5
//
ArrayList<String> buildPatterns(int count) {
    char[] str = new char[count * 2];
    ArrayList<String> patterns = new ArrayList<String>();
    buildPatterns(patterns, count, count, str, 0);
    return patterns;
}
void buildPatterns(ArrayList<String> patterns, int leftRem, int rightRem, 
                   char[] str, int index) {
    // Cut some branches of recursion tree.
    if (leftRem < 0 || leftRem > rightRem) {
        return;
    }

    if (leftRem == 0 && rightRem == 0) {
        // Base case. Out of both '(' and ')'.
        // This str is what we want. 
        patterns.add(String.valueOf(str));
    } else {
        // General case. 
        // Two recursion means two selections on each index or level.
        // So if it were not for cutting branches, 2^n patterns would be built. 
        str[index] = '(';
        buildPatterns(patterns, leftRem - 1, rightRem, index + 1);

        str[index] = ')';
        buildPatterns(patterns, leftRem, rightRem - 1, index + 1);
    }
}































