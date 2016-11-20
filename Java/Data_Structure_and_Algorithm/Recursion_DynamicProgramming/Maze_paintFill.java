// Fill the surrounding area with a new color. 
// Author: CtCI 8.10 p.361 + kei
// Date  : November 13, 2016

package whiteboard;

public class Lab_whiteboard {

    public static void main(String[] args) {

        
        Color[][] screen = new Color[][]
                   {{Color.Black, Color.Black, Color.Green, Color.Black}, 
                    {Color.Green, Color.Green, Color.Green, Color.Green},
                    {Color.Black, Color.Green, Color.Green, Color.Green}, 
                    {Color.Green, Color.Green, Color.Black, Color.Green}};
        
        // Test paintFill()
        //paintFill(screen, 1, 3, Color.White);
        System.out.println(paintFill2(screen, 1, 3, Color.White)); // true
        
        for (int r = 0; r < screen.length; r++) {
            for (int c = 0; c < screen[0].length; c++) {
                System.out.print(screen[r][c] + " ");
            }
            System.out.print("\n");
        }
//      Black Black White Black 
//      White White White White 
//      Black White White White 
//      White White Black White 

        
        
        
        System.out.println();
        System.out.println("done.");
        return;
    } // end main().
    
    
    public static enum Color { Black, White, Red, Yellow, Green }

    public static void paintFill(Color[][] screen, int r, int c, Color nColor) {
        if (screen[r][c] == nColor) {
            return;
        }
        paintFill(screen, r, c, screen[r][c], nColor);
    }
    private static void paintFill(Color[][] screen, int r, int c, Color oColor, Color nColor) {
        if (r < 0 || r >= screen.length || c < 0 || c >= screen[0].length) {
            return;
        }
        if (screen[r][c] != oColor) {
            return;
        }
        
        screen[r][c] = nColor; 
        // DFS. 
        paintFill(screen, r - 1, c, oColor, nColor); 
        paintFill(screen, r + 1, c, oColor, nColor); 
        paintFill(screen, r, c - 1, oColor, nColor); 
        paintFill(screen, r, c + 1, oColor, nColor);    
    }
    
    
    // Another version returning true if it changed the color. 
    public static boolean paintFill2(Color[][] screen, int r, int c, Color nColor) {
        if (screen[r][c] == nColor) {
            return false;
        }
        return paintFill2(screen, r, c, screen[r][c], nColor);
    }
    private static boolean paintFill2(Color[][] screen, int r, int c, Color oColor, Color nColor) {
        if (r < 0 || r >= screen.length || c < 0 || c >= screen[0].length) {
            return false;
        }
        if (screen[r][c] != oColor) {
            return false;
        }
        
        screen[r][c] = nColor; 
        paintFill(screen, r - 1, c, oColor, nColor); 
        paintFill(screen, r + 1, c, oColor, nColor); 
        paintFill(screen, r, c - 1, oColor, nColor); 
        paintFill(screen, r, c + 1, oColor, nColor);    

        return true;
    }

    
    
}





































































