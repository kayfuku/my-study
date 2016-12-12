// Enum fundamentals. 
// Author: Yuuki Book 2 p.363 + kei
// Date  : November 28, 2016

package whiteboard;

import java.util.Arrays;



public class Lab_whiteboard {
    
    
    // Declaration. 
    public enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    public static void main(String[] args) {

        // Assignment. 
        Direction dir = Direction.EAST;
        
        // Comparison. 
        if (dir == Direction.EAST) {
            System.out.println("Go east."); // Go east. 
        }
        
        // Switch statement. 
        dir = Direction.SOUTH;
        switch (dir) {
        case NORTH:
            System.out.println("Go north.");
            break;
        case EAST:
            System.out.println("Go east.");
            break;
        case SOUTH:
            System.out.println("Go south."); // Go south. 
            break;
        case WEST:
            System.out.println("Go west.");
            break;
        default:
            break;
        }
        
        // toString(). 
        System.out.println(dir.toString()); // SOUTH
        
        // Convert a string to enum type using valueOf(). 
        // This is case-sensitive. "west" causes an error. 
        dir = Direction.valueOf("WEST");
        System.out.println(dir.toString()); // WEST 
        
        // values(). 
        Direction[] dirs = Direction.values();
        System.out.println(Arrays.toString(dirs)); // [NORTH, EAST, SOUTH, WEST]

        // Iteration. 
        for (Direction d : dirs) {
            System.out.println(d); // NORTH EAST SOUTH WEST
        }

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    

    
    
    
    
    
    
    
    
    
    
    
    
    
}

























