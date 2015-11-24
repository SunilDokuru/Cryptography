# Extended Eucledian Algorithm


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExtendedEucledianAlgorithm {
    
    public static void main(String[] args){
        System.out.println("Testing Problem 1b  ---  extendedEuclidGCD: \n");
        Scanner sc = new Scanner(System.in);
        long[] xy = new long[2];
        long a, b;
        System.out.println("Enter a: ");a = sc.nextLong();
        System.out.println("Enter b: ");b = sc.nextLong();
        long gcd = extendedEuclidGCD(a, b,xy);
        System.out.print("gcd("+a+","+b+") = " +gcd +", and "+gcd+" = "+"("+xy[0]+")"+"("+a+")"+" + "+"("+xy[1]+")"+"("+b+")");
    }
    public static long extendedEuclidGCD(long a, long b, long [] xy) {
        List<Long> xk = new ArrayList();
        List<Long> yk = new ArrayList();
        List<Long> rk = new ArrayList();
        long u = 0, v = 1;
        xk.add(0, v); xk.add(1, u); yk.add(0, u); yk.add(1, v); //Initial Conditions
        
        int caret = 2, track = 2;
        rk.add(0, a); rk.add(1, b); // Storing User inputed Long Integers
        long quotient;
        boolean continueL = true;
        while(continueL) {
            quotient = a/b;
            rk.add(caret++, a%b);
                a = rk.get(caret-2);
                b = rk.get(caret-1);
            
                xk.add(track, (xk.get(track-2) - (xk.get(track-1)*quotient)));
                yk.add(track, (yk.get(track-2) - (yk.get(track-1)*quotient)));
                track++;
            if(rk.get(caret-1) == 0)
                continueL = false;
            
        }
        xy[0] = xk.get(track-2);
        xy[1] = yk.get(track-2);
        
    return rk.get(caret-2);
    }
}
