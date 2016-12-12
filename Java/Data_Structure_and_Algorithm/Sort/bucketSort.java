// Bucket Sort. 
// Average case: O(N + k)
// Worst case  : O(N^2)
// Best case   : O(N)
// Space Complexity : O(n + k) 
// Author: Wikipedia(http://www.growingwiththeweb.com/2015/06/bucket-sort.html)
//         + kei
// Date  : September 28, November 27, 2016


private static final int DEFAULT_BUCKET_SIZE = 5;

public static void bucketSort(Integer[] array) {
    sort(array, DEFAULT_BUCKET_SIZE);
}

public static void bucketSort(Integer[] array, int bucketSize) {
    if (array.length == 0) {
        return;
    }

    // Get minimum and maximum values in the array in order to 
    // determine the number of buckets.
    Integer minValue = array[0];
    Integer maxValue = array[0];
    for (int i = 1; i < array.length; i++) {
        if (array[i] < minValue) {
            minValue = array[i];
        } else if (array[i] > maxValue) {
            maxValue = array[i];
        }
    }

    // Initialise buckets.
    // Elements should be distributed evenly. 
    int bucketCount = (maxValue - minValue) / bucketSize + 1;
    List<List<Integer>> buckets = new ArrayList<List<Integer>>(bucketCount);
    for (int i = 0; i < bucketCount; i++) {
        buckets.add(new ArrayList<Integer>());
    }

    // Distribute input array values into buckets
    for (int i = 0; i < array.length; i++) {
        // This way, you can distribute the values into 
        // buckets whose size are bucketSize. (?)
        buckets.get((array[i] - minValue) / bucketSize).add(array[i]);
    }

    // Sort buckets and place back into input array
    int currentIndex = 0;
    for (int i = 0; i < buckets.size(); i++) {
        // Get the ArrayList and convert it to Array.
        Integer[] bucketArray = new Integer[buckets.get(i).size()];
        bucketArray = buckets.get(i).toArray(bucketArray);
        // Insertion Sort. 
        insertionSort(bucketArray);
        // Get the sorted result. 
        for (int j = 0; j < bucketArray.length; j++) {
            array[currentIndex++] = bucketArray[j];
        }
    }


    // Insertion Sort.
    public static void insertionSort(Integer[] arr) {
        if (arr == null) {
            return;
        }

        int N = arr.length;
        for (int i = 1; i < N; ++i) {
            int j = i;
            while (j > 0 && arr[j - 1] > arr[j]) {
                // Swap.
                int t = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = t;
                --j;
            }
        }
    }
    

}






























