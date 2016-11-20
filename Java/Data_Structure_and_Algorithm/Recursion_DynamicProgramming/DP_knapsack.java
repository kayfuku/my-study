// Knapsack problem. 
// Author: TopCoder book p.181 + kei
// Date  : November 14, 2016

package whiteboard;

import java.util.Arrays;
import java.util.HashMap;

public class ForCopy {
    
    public static int maxWeight = 10;
    public static int[] ws = { 3, 4, 1, 2, 3 };
    public static int[] vs = { 2, 3, 2, 3, 6 };
    
    // Memoization, array or hash table. 
    // For knapsack1().
    public static int[][] dp = new int[ws.length + 1][maxWeight + 1];
    // For knapsack2().
    public static HashMap<Integer, HashMap<Integer, Integer>> idToValue = 
            new HashMap<>();
    

    public static void main(String[] args) {

        // Test knapsack1().
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(knapsack1(0, maxWeight)); // 14


        // Test knapsack2().
        System.out.println(knapsack2(0, maxWeight)); // 14

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    
    // This uses a hash table for memo. 
    public static int knapsack2(int id, int w) {
        
        if (w == 0) { return 0; }
        if (id >= ws.length) { return 0; }
          
        if (idToValue.containsKey(id) && idToValue.get(id).containsKey(w)) { 
            return idToValue.get(id).get(w); 
        }
          
        int value = Math.max(
                knapsack2(id + 1, w), /* Not selected */
                knapsack2(id + 1, w - ws[id]) + vs[id] /* Selected */
                    );
        
        // Memoization. 
        HashMap<Integer, Integer> weightToValue = new HashMap<>();
        weightToValue.put(w, value);
        idToValue.put(id, weightToValue);
        
        return value;
        
    }
    

    // This uses an 2d array for memo. 
    public static int knapsack1(int id, int w) {
        if (w == 0) { return 0; }
        if (id >= ws.length) { return 0; }
        
        if (dp[id][w] != -1) { return dp[id][w]; }
        
        // Memoization. 
        dp[id][w] = Math.max(
                knapsack1(id + 1, w), /* Not selected */
                knapsack1(id + 1, w - ws[id]) + vs[id] /* Selected */
                    );
        
        return dp[id][w];
    }
    
    
    
    
    
}



























