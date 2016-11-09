// Multiply two positive integers without using 
// the *(or /) operator.
// Author: (CtCI 6th 8.5) + kei
// Date  : October 11, 2016

package pack01;

public class Lab01 {

    public static void main(String[] args) {


        int a = 2;
        int b = 4;
        System.out.println(multipleRecur(a, b)); // 8


        System.out.println();
        System.out.println("done.");
        return;
    } // end of main().

    static int multipleRecur(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }

        return multipleRecur(a, b - 1) + a;
    }

}




























