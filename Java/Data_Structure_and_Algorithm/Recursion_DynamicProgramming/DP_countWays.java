// Dynamic Programming.
// Count how many possible ways to add 1, 2, or 3 to sum 
// until sum gets to n. 
// ex. n: 6
// (3, 3), (3, 2, 1), (3, 1, 2), (3, 1, 1, 1), (2, 3, 1), 
// (2, 2, 2), (2, 2, 1, 1), (2, 1, 3), (2, 1, 2, 1), 
// (2, 1, 1, 1, 1), ...

// Author: CtCI 6th 8.1 p.343 + kei
// Date  : October 5, 2016


package whiteboard;

import java.util.Arrays;

public class DP_CountWays {

	public static void main(String[] args) {
		
		int n = 6;
		int res;
		
		//res = countWays_recur(n);
		res = countWays_DP(n);
		
		System.out.println(res); // 24

	}


	// Simple implementation, but inefficient. O(3^n)
	int countWays_recur(int n) {
		if (n < 0) {
			// Thinking about case n = 1, 
			// f(1) = f(0) + f(-1) + f(-2)
			// You don't need to count the case n = -1 or -2.
			return 0;
		} else if (n == 0) {
			// When n = 0, count is 1.
			return 1;
		} else {
			// Add up all the number of cases. 
			// Each of the case, you select 1, 2, or 3 to add. 
			return countWays_recur(n - 1) + countWays_recur(n - 2) 
			       + countWays_recur(n - 3);
		}
	}


	// Memoization Solution. O(n) 
	int countWays_DP(int n) {
		// For memoization.
		int[] memo = new int[n + 1];
		Arrays.fill(memo, -1);
		return countWays_DP(n, memo);
	}

	int countWays_DP(int n, int[] memo) {
		if (n < 0) {
			return 0;
		} else if (n == 0) {
			return 1;
		} else if (memo[n] > -1) {
			// Memoization. 
			return memo[n];
		} else {
			memo[n] = countWays_DP(n - 1, memo) + countWays_DP(n - 2, memo) 
			          + countWays_DP(n - 3, memo);
			return memo[n];
		}
	}

}





























