// (By Cracking Coding Interview p.234)
// Change decimal number(between 0 and 1 exclusive) from decimal notation 
// to binary notation.
// ex. 0.75 --> 0.11

public static void main(String[] args) {
    
    
    double num = 0.75;
    System.out.println(num);
    
    String binary = printBinary(num);
    System.out.println(binary);
    
    
    
    
    
    

    System.err.println("done.");
    return;
}


// ------------ printBinary() ---------------------------------------------
// Change decimal number(between 0 and 1 exclusive) from decimal notation 
// to binary notation.
public static String printBinary(double num) {
    
    if (num >= 1 || num <= 0) {
        return "ERROR";
    }
    
    // Make binary notation.
    StringBuilder binary = new StringBuilder();
    binary.append(".");
    
    while (num > 0) {
        // Terminate if it exceeds 32 digits.
        if (binary.length() >= 32) {
            return "ERROR";
        }
        
        double r = num * 2;
        if (r >= 1) {
            binary.append(1);
            num = r - 1;                
        } else {
            binary.append(0);
            num = r;
        }
    }
    
    return binary.toString();
}

































