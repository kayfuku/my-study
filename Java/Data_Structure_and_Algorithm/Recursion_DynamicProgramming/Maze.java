// Explore 2D array for maze with recursion.
// Author: CtCI 6th p.345 + kei
// Date  : October 6, 2016


package whiteboard;


public class Maze {

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

		boolean path = getPath(maze2);
		System.out.println(path);		

	}
	
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
	
	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
	
}



























