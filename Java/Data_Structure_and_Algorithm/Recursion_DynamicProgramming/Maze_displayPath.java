// Explore 2D array for maze with recursion and 
// display path.
// Author: CtCI 6th p.345 + kei
// Date  : October 6, 2016


package whiteboard;

import java.util.ArrayList;


public class Maze_displayPath {

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

		
		ArrayList<Point> path = getPath2(maze);
		
		if (path != null) {
			for (Point point : path) {
				System.out.println(point);
			}
		} else {
			System.out.println("No path exists.");
		}
		
		

	}
	
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
	
	
}


class Point {
	int row, col;	
	
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
	
}



























