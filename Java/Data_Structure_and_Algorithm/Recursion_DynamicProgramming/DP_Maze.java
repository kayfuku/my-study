// Explore 2D array maze with recursion and 
// Dynamic Programming in order to display 
// path or count paths.
// Author: CtCI 6th 8.2 p.345 + kei
// Date  : October 6, 2016

package whiteboard;

import java.util.ArrayList;
import java.util.HashMap;


public class DP_Maze {
	
	private static int numCalls = 0;

	public static void main(String[] args) {
		
		
		boolean[][] maze = new boolean[][]
			       {{true, true, true, true}, 
                    {true, true, true, true},
                    {true, false, true, false}, 
                    {true, true, true, true}};
		
		boolean[][] maze2 = new boolean[][]
			       {{true, true, true, true}, 
                    {true, true, true, true},
                    {true, false, false, false}, 
                    {false, true, true, true}};
		
		boolean[][] maze3 = new boolean[][]
			       {{true, true, false, true}, 
	                {true, true, true, true},
	                {true, false, true, true}, 
	                {false, true, true, true}};

		boolean[][] maze4 = new boolean[][]
				   {{true, true, true, true}, 
	                {true, true, true, true},
	                {true, true, true, true}, 
	                {false, true, true, true}};

		// Test getPath().
//		boolean path = getPath(maze2);
//		System.out.println(path);	
		
		// Test getPath2(), or getPath2_DP().
//		//ArrayList<Point> path = getPath2(maze3);
//		ArrayList<Point> path = getPath2_DP(maze3);
//		System.out.println("path: ");
//		if (path != null) {
//			for (Point point : path) {
//				System.out.println(point);
//			}
//		} else {
//			System.out.println("No path exists.");
//		}
//		System.out.println(numCalls + " calls.");
//		numCalls = 0;
		
		// Test countPaths(), or countPaths_DP().
		//int count = countPaths(maze4);
		int count = countPaths_DP(maze4);
		System.out.println(count);
		System.out.println(numCalls + " calls.");
		numCalls = 0;
		

	} // end main(...)
	
	
	// Count paths with Dynamic Programming. O(row + col)
	public static int countPaths_DP(boolean[][] maze) {		
		if (maze == null || maze.length == 0) {
			return -1;
		}
		
		// For Memoization. 
		HashMap<Point, Integer> memo = new HashMap<Point, Integer>();

		// Goal point passed in. 
		return countPaths_DP(maze, maze.length - 1, maze[0].length - 1, memo);
	}
	private static int countPaths_DP(boolean[][] maze, int row, int col, 
			                        HashMap<Point, Integer> memo) {
		numCalls++;
		Point p = new Point(row, col);
		//System.out.println(p);
		
		// Base case.
		if (row < 0 || col < 0 || !maze[row][col]) {
			return 0;
		}
		if ((row == 0) && (col == 0) /* Start point. */) {
			// Memoization.
			memo.put(p, 1);
			return 1;
		}
		
		// Take advantage of memoization. 
		// Avoid getting into recursion.
		if (memo.containsKey(p)) {
			System.out.println("memo used.");
			return memo.get(p);
		}
		
		// General case.
		int count = countPaths_DP(maze, row - 1, col, memo)
				    + countPaths_DP(maze, row, col - 1, memo);
		// Memoization.
		memo.put(p, count);

		return count;
	}	
		
	
	// Count paths. O(2^(row + col))
	public static int countPaths(boolean[][] maze) {		
		if (maze == null || maze.length == 0) {
			return -1;
		}
		
		// Count paths from bottom-right point to top-left point.
		return countPaths(maze, maze.length - 1, maze[0].length - 1);
	}
	
	public static int countPaths(boolean[][] maze, int row, int col
			                      ) {
		numCalls++;
		
		// Base case.
		if (row < 0 || col < 0 || !maze[row][col]) {
			return 0;
		}
		if ((row == 0) && (col == 0)) {
			return 1;
		}
		
		// General case.
		return countPaths(maze, row - 1, col) + countPaths(maze, row, col - 1);
	}	
	
	
	// Display path with Dynamic Programming. DP necessary?
	// Well, DP doesn't seem to be necessary for this purpose. 
	public static ArrayList<Point> getPath2_DP(boolean[][] maze) {		
		if (maze == null || maze.length == 0) {
			return null;
		}
		
		ArrayList<Point> path = new ArrayList<Point>();
		// For Memoization. 
		HashMap<Point, Boolean> memo = new HashMap<Point, Boolean>();

		if (getPath2_DP(maze, maze.length - 1, maze[0].length - 1, path, memo)) {
			return path;
		}
		
		return null;
	}
	
	public static boolean getPath2_DP(boolean[][] maze, int row, int col, 
			                ArrayList<Point> path, HashMap<Point, Boolean> memo) {		
		numCalls++;
		Point p = new Point(row, col);
		System.out.println(p);

		// Base case.
		if (row < 0 || col < 0 || !maze[row][col]) {
			memo.put(p, false);
			return false;
		}
		if ((row == 0) && (col == 0)) {
			path.add(p);
			return true;
		}
		
		// Take advantage of memoization. Necessary?
		if (memo.containsKey(p)) {
			System.out.println("memo used.");
			return memo.get(p);
		}
		
		// General case.
		if (getPath2_DP(maze, row - 1, col, path, memo)
				|| getPath2_DP(maze, row, col - 1, path, memo)) {
			path.add(p);
			// Memoization.
			memo.put(p, true);
			return true;
		} else {
			// Memoization. 
			memo.put(p, false);
			return false;
		}
	}
	

	// Display path.
	public static ArrayList<Point> getPath2(boolean[][] maze) {		
		if (maze == null || maze.length == 0) {
			return null;
		}
		
		ArrayList<Point> path = new ArrayList<Point>();
		if (getPath2(maze, maze.length - 1, maze[0].length - 1, path)) {
			return path;
		}
		
		return null;
	}
	
	public static boolean getPath2(boolean[][] maze, int row, int col, 
			                      ArrayList<Point> path) {
		numCalls++;
		
		// Base case.
		if (row < 0 || col < 0 || !maze[row][col]) {
			return false;
		}
		if ((row == 0) && (col == 0)) {
			Point p = new Point(row, col);
			path.add(p);
			return true;
		}
		
		// General case.
		if (getPath2(maze, row - 1, col, path) || getPath2(maze, row, col - 1, path)) {
			Point p = new Point(row, col);
			path.add(p);
			return true;
			
		} else {
			
			return false;
		}
	}
	
	
	// Basic one.
	// Check if a path between bottom-right point and 
	// top-left point exists. 
	public static boolean getPath(boolean[][] maze) {
		boolean path = getPath(maze, maze.length - 1, maze[0].length - 1);
		
		return path;
	}

	public static boolean getPath(boolean[][] maze, int row, int col) {
		// Base case.
		if (row < 0 || col < 0 || !maze[row][col]) {
			return false;
		}
		if ((row == 0) && (col == 0)) {
			return true;
		}
		
		// General case.
		if (getPath(maze, row - 1, col) || getPath(maze, row, col - 1)) {
			
			return true;
			
		} else {
			
			return false;
		}
	}
	
	

}


class Point {
	int row, col;	
	
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	// Auto-generated.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
    
	// Auto-generated.
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
		return result;
	}
	
	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
	
}



























