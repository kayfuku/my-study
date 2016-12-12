// Calculate the median of an array. 
// Author: internet + kei
// Date  : November 24, 2016


// O(N log N) solution. 
double calcMedian(double[] numArray) {
    Arrays.sort(numArray);
    double median;
    if (numArray.length % 2 == 0) {
        return ((double) numArray[numArray.length / 2] + 
                (double) numArray[numArray.length / 2 - 1]) / 2;
    } else {
        return (double) numArray[numArray.length / 2];
    }
}


// O(N) solution using partition. 
// Refer to findKthSmallest_partition.java  


Sorting the array is unnecessary and inefficient. There's a variation of the QuickSort (QuickSelect) algorithm which has an average run time of O(n); if you sort first, you're down to O(n log n). It actually finds the nth smallest item in a list; for a median, you just use n = half the list length. Let's call it quickNth (list, n).

The concept is that to find the nth smallest, choose a 'pivot' value. (Exactly how you choose it isn't critical; if you know the data will be thoroughly random, you can take the first item on the list.)

Split the original list into three smaller lists:

- One with values smaller than the pivot.
- One with values equal to the pivot.
- And one with values greater than the pivot.

You then have three cases:

1. The "smaller" list has >= n items. In that case, you know that the nth smallest is in that list. Return quickNth(smaller, n).
2. The smaller list has < n items, but the sum of the lengths of the smaller and equal lists have >= n items. In this case, the nth is equal to any item in the "equal" list; you're done.
3. n is greater than the sum of the lengths of the smaller and equal lists. In that case, you can essentially skip over those two, and adjust n accordingly. Return quickNth(greater, n - length(smaller) - length(equal)).
Done.

If you're not sure that the data is thoroughly random, you need to be more sophisticated about choosing the pivot. Taking the median of the first value in the list, the last value in the list, and the one midway between the two works pretty well.

If you're very unlucky with your choice of pivots, and you always choose the smallest or highest value as your pivot, this takes O(n^2) time; that's bad. But, it's also very unlikely if you choose your pivot with a decent algorithm.




































