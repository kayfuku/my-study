// Find k closest stars. 
// Author: EPI 11.4 p.166 + kei
// Date  : November 28, 2016

package whiteboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;



public class Lab_whiteboard {

    public static void main(String[] args) {

        Star star1 = new Star("star1", 1.0, 1.0, 1.0);
        Star star2 = new Star("star2", 1.2, 1.0, 1.2);
        Star star3 = new Star("star3", 2.3, 2.4, 1.5);
        Star star4 = new Star("star4", 2.5, 3.0, 2.5);
        List<Star> stars = new ArrayList<>(Arrays.asList(star1, star2, star3, star4));
        Iterator<Star> iter = stars.iterator();
        List<Star> res = findClosesetKStars(3, iter);
        System.out.println(res.toString()); // [star1, star2, star3]

        

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    // O(n log k) time, O(k) space. 
    // k << n. 
    public static List<Star> findClosesetKStars(int k, Iterator<Star> stars) {
        // Max-Heap.
        PriorityQueue<Star> maxHeap = 
                new PriorityQueue<>(k, Collections.reverseOrder());
        
        // Fill the heap. 
        for (int i = 0; i < k; i++) {
            maxHeap.add(stars.next());
        }
        
        // Keep storing k closest stars through the iteration. 
        while (stars.hasNext()) {
            Star star = stars.next();
            if (star.compareTo(maxHeap.peek()) < 0) {
                maxHeap.poll();
                maxHeap.add(star);
            }
        }
        
        List<Star> result = new ArrayList<Star>(maxHeap);
        Collections.sort(result);
        return result;
    }
    
    
}

class Star implements Comparable<Star> {
    private String name;
    private double x, y, z;
    
    public Star(String n, double x, double y, double z) {
        this.name = n;
        this.x = x;
        this.y = y;
        this.z = z;     
    }
    
    public double distance() {
        return x * x + y * y + z * z;
    }
    
    @Override
    public int compareTo(Star star) {
        return Double.compare(this.distance(), star.distance());        
    }
    
    @Override
    public String toString() {
        return name;
    }
    
}
























