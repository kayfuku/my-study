// Check whether or not the array has the value in it.
// Author: kei
// Date  : October 30, 2015

// An array for counting.
int[] count = new int[X + 1];

int pFrog = 0;

for (int t = 0; t < A.length; t++) {
    // Tip: Put the value into an element with the index corresponding to the value.
    //     so that you can check whether or not the array has the value in it. 
    count[A[t]]++; 
    
    // Check to see if the value there. If so, then do something. 
    while (count[pFrog + 1] > 0) {
        pFrog++;
        if (pFrog == X) {
            return t;
        }
    }           
}



