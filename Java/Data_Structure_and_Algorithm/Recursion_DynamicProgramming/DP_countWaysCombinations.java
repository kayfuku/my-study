// Count ways of combinations. 
// Dynamic Programming. 
// ex.  n: 50
//    25  10  5  1 
//     2   0  0  0  
//     1   1  3  0
//     1   2  1  0
//     0   1  1 35
//         ...
// Author: CtCI 8.11 p.363 + kei
// Date  : November 13, 14, 2016

package whiteboard;

import java.util.HashMap;


public class ForCopy {
    
    public static int[] d = new int[]{ 25, 10, 5, 1 };
    public static HashMap<Integer, HashMap<Integer, Integer>> mapAmountToSum = 
            new HashMap<>();

    public static void main(String[] args) {

        int n = 10;

        // Test calcNumBad().
//      System.out.println(calcNumBad(n)); // Warning! stack overflow.
        
        // Test calcNum().
        System.out.println(calcNum(n)); // 4

        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    

    // Author: CtCI 8.11 p.363 + kei
    // Date  : November 14, 2016
    public static int calcNum(int n) {
        return calcNum(n, 0);
    }
    private static int calcNum(int amount, int coin) {
        if (amount == 0) {
            return 1;
        }
        if (coin > 3) {
            return 0;
        }
        
        // Cut branches. 
        if (mapAmountToSum.containsKey(amount) 
                && mapAmountToSum.get(amount).containsKey(coin)) {
            return mapAmountToSum.get(amount).get(coin);
        }
        
        int sum = 0; 
        int denomAmount = d[coin];
        for (int i = 0; i * denomAmount <= amount; i++) {
            int amountRemaining = amount - denomAmount * i;
            
            sum += calcNum(amountRemaining, coin + 1);
        }
        
        // Memoization.
        HashMap<Integer, Integer> mapCoinToSum = new HashMap<>();
        mapCoinToSum.put(coin, sum);
        mapAmountToSum.put(amount, mapCoinToSum);
        
        return sum;
    }
    
    
    // I don't know why, but this also causes stack overflow. 
    // Author: CtCI 8.11 p.363 + kei
    // Date  : November 13, 2016
//    public static int calcNum(int n) {
//        int[] d = new int[]{ 25, 10, 5, 1 };
//        HashMap<Integer, HashMap<Integer, Integer>> mapAmountToSum = new HashMap<>();
//        return calcNum(n, d, 0, mapAmountToSum);
//    }
//    private static int calcNum(int amount, int[] d, int coin, 
//            HashMap<Integer, HashMap<Integer, Integer>> mapAmountToSum) {
//        if (amount == 0) {
//            return 1;
//        }
//        if (coin > 3) {
//            return 0;
//        }
////      if (coin >= d.length - 1) {
////          return 1;
////      }
//        
//        // Cut branches. 
//        if (mapAmountToSum.get(amount) != null) {
//            return mapAmountToSum.get(amount).get(coin);
//        }
//        
//        int sum = 0; 
//        int denomAmount = d[coin];
//        for (int i = 0; i * denomAmount <= amount; i++) {
//            int amountRemaining = amount - denomAmount * i;
//            // Why stack overflow? 
//            sum += calcNum(amountRemaining, d, coin++, mapAmountToSum);
//        }
//        
//        // Memoization.
//        HashMap<Integer, Integer> mapCoinToSum = new HashMap<>();
//        mapCoinToSum.put(coin, sum);
//        mapAmountToSum.put(amount, mapCoinToSum);
//        
//        return sum;
//    }
    

    
    
    
    // This is a bad code, which causes stack overflow! 
    // Author: kei
    // Date  : November 13, 2016
//    public static int calcNumBad(int n) {
//        int[] d = new int[]{ 25, 10, 5, 1 };
//        return calcNumBad(n, d, 0);
//    }
//    private static int calcNumBad(int n, int[] d, int coin) {
//        if (n == 0) {
//            return 1;
//        }
//        if (coin > 3) {
//            return 0;
//        }
//        
//        int sum = 0; 
//        int i = 0;
//        int denomAmount = d[coin];
//        while (i <= n / denomAmount) {      
//            sum += calcNumBad(n - denomAmount * i, d, coin++);
//            i++;
//        }
//        
//        return sum;
//    }
    

    
    
    
    
    
}

























