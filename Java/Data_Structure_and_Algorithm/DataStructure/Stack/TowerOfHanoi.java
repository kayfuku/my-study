// Tower of Hanoi.
// Author: Cracking the Coding Interview p.209 + kei.
// Date  : September 20, 2016

package whiteboard;

import java.util.Deque;
import java.util.LinkedList;

public class TowerOfHanoi {

	public static void main(String[] args) {
		// Num of disks.
		int n = 5;
		// Create 3 towers.
		Tower[] towers = new Tower[3];
		for (int i = 0; i < 3; i++) {
			towers[i] = new Tower(i);
		}
		
		// Add disks to tower 0.
		for (int i = n; i >= 1; i--) {
			towers[0].addDisk(i);
		}
		
		// Move all the disks to tower 3.
		towers[0].moveDisks(n, towers[2], towers[1]);
	}
}


class Tower {
	// Use Deque as a stack.
	private Deque<Integer> disks;
	private int towerId;
	
	public Tower(int i) {
		disks = new LinkedList<Integer>();
		towerId = i;
	}
	
	public int getTowerId() {
		return towerId;
	}
	
	public void addDisk(int d) {
		if (!disks.isEmpty() && disks.getFirst() < d) {
			System.out.println("Error placing disk " + d);
		} else {
			disks.push(d);
		}
	}
	
	public void moveTopTo(Tower t) {
		if (disks == null) {
			System.out.println("Empty tower " + t.getTowerId());
		} else {
			int d = disks.pop();
			t.addDisk(d);
			System.out.println("Move disk " + d + " from tower " + getTowerId()
					+ " to " + t.getTowerId());		
		}		
	}
	
	// n: Num of disks to be moved from top.
	public void moveDisks(int n, Tower destination, Tower buffer) {
		// Recursion.
		// n == 0 case can be base case.
		if (n > 0) {
			// Move n - 1 disks to the buffer tower.
			moveDisks(n - 1, buffer, destination);
			// Move n-th disk to the destination.
			moveTopTo(destination);
			// Move n - 1 disks from buffer tower to the destination.
			buffer.moveDisks(n - 1, destination, this);	
		}
	}
	
	
	
}






















