// (By Cracking Coding Interview p.237)
// This shows how to make the following bits. 
// 
// 1) 1 only on p-th bit, all the rest are 0. 
// ex. a == 00001000 (p == 3)

// 2a) All bits from LSB to (p - 1)-th bit are 1.
// ex. b == 00000111 (p == 3)

// 2b) Put 1s int num from LSB to (p - 1)-th bit.

// 3a) All bits from LSB to (p - 1)-th bit are 0. 
// ex. mask == 11111000 (p == 3)

// 3b) Clear from LSB to (p - 1)-th bit. 
// ex.      num == 10111111 
//         mask == 11111000 (p == 3)
//   numCleared == 10111000  


public static void main(String[] args) {
        
        
    int num = Integer.parseInt("11111011", 2);
    System.out.println(Integer.toBinaryString(num));
    int p = 3;
    
    // 1) 1 only on p-th bit, all the rest are 0. 
    // ex. a == 00001000 (p == 3)
    int a = 1 << p;

    // 2a) All bits from LSB to (p - 1)-th bit are 1.
    // ex. b == 00000111 (p == 3)
    int b = a - 1;

    // 2b) Put 1s int num from LSB to (p - 1)-th bit.
    int numPut1s = num | b;

    // 3a) All bits from LSB to (p - 1)-th bit are 0. 
    // ex. mask == 11111000 (p == 3)
    int mask = ~b;
    // But next one is much easier!   
    // ex. mask2 == 11111000 (p == 3)  
    int mask2 = ~0 << p;  

    // 3b) Clear from LSB to (p - 1)-th bit. 
    // ex.      num == 10111111 
    //         mask == 11111000 (p == 3)
    //   numCleared == 10111000  
    int numCleared = num & mask;

    
    System.out.println(Integer.toBinaryString(num));

    
    // Write 3b) at the same line. 
    num &= ~((1 << p) - 1);
    
    
    

    System.err.println("done.");
    return;
}





























