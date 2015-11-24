# Extended Eucledian Algorithm using BigIntegers using Java


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.math.BigInteger;

public class ExtendedEucledianAlgorithmUsingBigIntegers {
    
    public static void main(String[] args){
        System.out.println("Testing Problem  --  BigInteger Euclid GCD:");
        Scanner sc = new Scanner(System.in);
        BigInteger[] xy = new BigInteger[2];
        BigInteger a, b, u;
        u = new BigInteger("0");
        System.out.println("Enter a: ");a = sc.nextBigInteger();
        System.out.println("Enter b: ");b = sc.nextBigInteger();
        if(b.compareTo(u) == 0)
            System.out.println("!!BYE!!");
        else{
            BigInteger gcd = extendedEuclidGCD(a, b,xy);
          System.out.print("gcd("+a+","+b+") = " +gcd +", and "+gcd+" = "+"("+xy[0]+")"+"("+a+")"+" + "+"("+xy[1]+")"+"("+b+")");
    }}
    public static BigInteger extendedEuclidGCD(BigInteger a, BigInteger b, BigInteger [] xy) {
        List<BigInteger> xk = new ArrayList();
        List<BigInteger> yk = new ArrayList();
        List<BigInteger> rk = new ArrayList();
        BigInteger u, v;
        u = new BigInteger("0");
        v = new BigInteger("1");
        xk.add(0, v); xk.add(1, u); yk.add(0, u); yk.add(1, v); //Initial Conditions
        
        int caret = 2, track = 2;
        rk.add(0, a); rk.add(1, b); // Storing User inputed Long Integers
        BigInteger quotient;
        boolean continueL = true;
        while(continueL) {
            quotient = a.divide(b);
            rk.add(caret++, a.remainder(b));
                a = rk.get(caret-2);
                b = rk.get(caret-1);
            
                xk.add(track, (xk.get(track-2).subtract(xk.get(track-1).multiply(quotient))));
                yk.add(track, (yk.get(track-2).subtract(yk.get(track-1).multiply(quotient))));
                track++;
                int res = rk.get(caret-1).compareTo(u);
            if(res == 0)
                continueL = false;
            
        }
        xy[0] = xk.get(track-2);
        xy[1] = yk.get(track-2);
        
    return rk.get(caret-2);
    }
}
