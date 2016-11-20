// Count something using Map. 
// Memo  : A map is used to count something.


// Author: takahashi + kei
// Date  : October 12, 2016
import java.util.HashMap;

public class Lab01 {

    public static void main(String[] args) {

        
        String[] strs = new String[]{"a", "b", "a", "c", "b", "d", "a"};
        countStrings(strs);
        // a 3
        // b 2
        // c 1
        // d 1
        // done.
        
        
        System.out.println("done.");
        return;
    } // end of main().


    public static void countStrings(String[] s) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 0; i < s.length; i++) {
            if (!map.containsKey(s[i])) {
                map.put(s[i], 1);
            } else {
                map.put(s[i], map.get(s[i]) + 1);
            }
        }

        for (String key : map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }
    }

}




// Author: Kei Fukutani + other
// Date  : January 11, 2016

// Key: distance, Value: count
Map<Double, Integer> numDistanceMap = new HashMap<Double, Integer>()


for (int i = 0; i < stars.size() ; i++) {
    // Each star
    
    // Get the distance from all of the other stars.
    for (int j = 0; j < stars.size() ; j++) {
        if (i == j) {
            continue;
        }
        
        // Count numbers of the specific distance.
        distance = stars.get(i).getDistance(stars.get(j).x, stars.get(j).y);
        if (numDistanceMap.containsKey(distance)) {
            int v = numDistanceMap.get(distance);
            numDistanceMap.put(distance, ++v);
        } else {
            numDistanceMap.put(distance, 1);
        }   
    } // end for (int j = 0; j < stars.size() ; j++) {
    
    // Test for the Map numDistanceMap
//              System.out.println("i=" + i);
//              for (Map.Entry<Double, Integer> entry : numDistanceMap.entrySet()) {
//                  System.out.println(entry.getKey() + " " + entry.getValue());
//              }
    
    // Sum of nums of boomerangs on each distance
    for (Map.Entry<Double, Integer> entry : numDistanceMap.entrySet()) {
        // Each distance
        if (entry.getValue() >= 2) {
            // Combinations of 2 numbers out of 1 ... n. 
            // nC2 = n!/((n-2)!2!) 
            int n = entry.getValue();
            sumBoomerangs += 
                    getFactorial(n) / (getFactorial(n - 2) * getFactorial(2));                              
        }
    }
    
    numDistanceMap.clear();
} // end for (int i = 0; i < stars.size() ; i++) {































