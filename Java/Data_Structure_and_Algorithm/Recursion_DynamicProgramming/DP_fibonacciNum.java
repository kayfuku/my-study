// Compute Fibonacci numbers with Dynamic Programming.
// Author: CS111C + CtCI 6th p.133 + kei
// Date  : October 5, 2016


package whiteboard;

public class DynamicProgramming {
	
	private static int numCalls = 0;

	public static void main(String[] args) {

		int result;
		int n = 10;
		
		// 1. inefficient solution.
		result = recursiveFibonacci(n);
		System.out.println(result);
		System.out.println(numCalls + " calls");
		System.out.println();
		result = 0;
		numCalls = 0;
		
		// 2. efficient solution.
		System.out.println("Dynamic Programming.");
		result = recursiveFibonacci_DP(n, new int[n + 1]);
		System.out.println(result);
		System.out.println(numCalls + " calls");
		System.out.println();
		result = 0;
		numCalls = 0;

		// 3. efficient solution.
		result = iterativeFibonacci(n);
		System.out.println(result);
		

	}
	
	
	// Recursive method. O(2^n) time, O(n) space.
	public static int recursiveFibonacci(int n) {
		if (n == 0 || n == 1) {
			// Start with 1.
			return 1;
		} else {
			numCalls += 2;
			return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
		}
	}
	
	// Recursive method. O(n) time, O(n) space.
	// Top-Down Dynamic Programming. (or Memoization)
	public static int recursiveFibonacci_DP(int n) {
		return recursiveFibonacci_DP(n, new int[n + 1]);
	}	

	public static int recursiveFibonacci_DP(int n, int[] memo) {
		if (n == 0 || n == 1) {
			// Start with 1.
			return 1;
		}
		// This could also be good.
		// if (n <= 1) {
		// 	// Start with 1.
		// 	return 1;
		// } 
		
		if (memo[n] == 0) {		
			// memo[n] is initial value, then compute fibonacci with recursion.
			//numCalls += 2;
			memo[n] = recursiveFibonacci_DP(n - 1, memo) 
			          + recursiveFibonacci_DP(n - 2, memo);
		}
		// recursiveFibonacci_DP(n, memo) is already computed before, 
		// so use it without recursion.
		return memo[n];		
	}


	// Iterative method. O(n)
	// Bottom-Up Dynamic Programming.
	public static int iterativeFibonacci(int n) {
		int secondTolast = 1;
		int last = 1;
		int next = 0;
		for (int i = 2; i <= n; i++) {
			next = secondTolast + last;
			secondTolast = last;
			last = next;
		}

		return next;
	}
}


























