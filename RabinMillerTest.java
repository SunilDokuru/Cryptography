/*
* Implementing RabinMiller Test using BigIntegers, and ArrayLists. RSA Key is generation.
*/

package encyptionalgorithms;
// Sunil Kumar Reddy Dokuru
// CS-355, Project 2, spring 2015
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RabinMillerTest
{
    public static final int NUM_TRIALS = 30; 
    public static final BigInteger ZERO = new BigInteger("0");
    public static final BigInteger ONE = new BigInteger("1");
    public static final BigInteger TWO = new BigInteger("2");
    public static final int SHIFT = 54;
    public static final BigInteger ONE_HUNDRED = new BigInteger("100");
 
    public static final int PROBABLY_PRIME = 0;
    public static final int COMPOSITE_NO_FACTOR = 1;
    public static final int COMPOSITE_FACTOR_FOUND = 2;

    public static BigInteger factor;

    public static Scanner input;
    public static Scanner kbd = new Scanner(System.in);
    public static PrintWriter output;
   //*************************************************************************************

    public static void main(String[] args) throws IOException {

        //testInputFile();
        generate_RSA_key();
    }

    //*************************************************************************************
    // This algorithm performs the Rabin-Miller test for integer n with value a, 1 < a < n-1
    // If boolean "print" is true, use "output.println(....);" to print to output file
    //  Otherwise, do not print to any file.
    public static int RabinMillerTest(BigInteger n, BigInteger a, boolean print) {

        BigInteger k, m, iterator, TWO_COMPL, b0, b1;
        BigInteger count = ZERO;
        BigInteger temp = n.subtract(ONE);
        while(temp.mod(TWO).equals(ZERO)) {
            count = count.add(ONE);
            temp = temp.divide(TWO);
        }
        k = count;
        m = temp;
        if(print) {
            output.println("n is: "+n);
            output.println("k is: "+k);
            output.println("m is: "+m);
            output.println("random a is: "+a);
        }
        TWO_COMPL = n.subtract(ONE);
        b0 = a.modPow(m, n);
        if(b0.equals(ONE) || b0.equals(TWO_COMPL)){
            if(print)
                output.print("Probably Prime");
            return 0;
        }
        else {
            for(iterator = ONE; iterator.compareTo(k) <= 0;) {
                b1 = b0.modPow(TWO, n);
                if(b1.equals(ONE)) {
                    factor = GCD(b0.subtract(ONE),n);
                return 2;
                } else if(b1.equals(TWO_COMPL)) {
                    output.print("Probably Prime");
                return 0;
                }
                if(iterator.equals(k) && !b1.equals(ONE)) 
                    return 1;
                iterator = iterator.add(ONE);
                b0 = b1;
            }
        }
    return -1;
    } // end MillerRabinTest********************************************


    //*************************************************************************************  
    // generateRandomPrime
        public static BigInteger generateRandomPrime(int numBits, Random rand, boolean print) {

        BigInteger n, a;String bigInteger = "1"; BigInteger temp;
        int trialNums = 0;
        boolean continueLoop = true;
        int [] results = new int[3];
        // Pick a random 'n' to check its primality with minimum number of bits as numBits
        do {
           trialNums = 0;
            temp = new BigInteger(numBits-2, rand);
            bigInteger += temp+""+"1";
        } while(bigInteger.length() > Math.pow(2,numBits));
        n = new BigInteger(bigInteger);
        
        // Picks a random 'a' from 1 < a < n-1 and checks the primality of 'n' for 30 times  
            while(trialNums < NUM_TRIALS)
            {   
                int count;
                do {
                    a = new BigInteger(numBits, rand);
                } while (a.compareTo(ONE) <= 0 || a.compareTo(n.subtract(ONE)) >= 0);
                count = RabinMillerTest(n, a, false);
                if(count == 0) {
                  trialNums++;
                  results[0]++; }
                
                else{ n = n.add(TWO); trialNums = 0;}
            }
            
        
        
        
    return n;
 }
        //********************************************************************************
        public static String bigIntegerToMessage(BigInteger m)
	{
		int i;
		char ch;
		String message = "";
		
		while (m.compareTo(ZERO) > 0)
		{
			ch = (char) (int) (m.mod(ONE_HUNDRED).intValue() + SHIFT);
			m = m.divide(ONE_HUNDRED);
			message = ch + message;
		}
		return message;
	}// END of BigIntegertoMessage

    //*************************************************************************************
    public static void generate_RSA_key() throws IOException {

        Random rand = new Random();
                    BigInteger p, q, e, d, m, n, c, phi_n;
        boolean print = false, loop = true;

                    System.out.print("Now, for generate_RSA_key, please enter the name of the output file:  ");
                    String outputFileName = kbd.nextLine();
                    FileWriter fwriter = new FileWriter(outputFileName);
                    output = new PrintWriter(fwriter);
                    p = generateRandomPrime(1500, rand, print); //q = p;
                    q = generateRandomPrime(1500, rand, print);
        System.out.println("p = " + p);
        System.out.println("q = " + q);
        phi_n = p.subtract(ONE).multiply(q.subtract(ONE));
        n = p.multiply(q);
        do {
            do {
                e = new BigInteger(500, rand);
            }while(e.toString(2).length() > Math.pow(2,500));
            BigInteger factor1;
            factor1 = GCD(e, phi_n);
            if(factor1.equals(ONE))
                loop = false;
        } while(loop); 
        d = e.modInverse(phi_n);
        System.out.println("d = " + d);
        System.out.println("e = " + e);
        
        String message;System.out.println("Enter the message you want to encrypt");
        message = kbd.nextLine();
        m = messageToBigInteger(message);
        c = m.modPow(e, n);
        System.out.println("c = " + c);System.out.println("Message = " + m);
        output.println();
        output.println("In this order, here are e, d, p, q, and n: ");   
        output.println();
BigInteger msg = m.modPow(e.multiply(d), n);

        System.out.println("Message is: " +bigIntegerToMessage(msg));
                    output.println(e);
        output.println(d);
                    output.println(p);
                    output.println(q);
                    output.println(n);			
                    output.println();
        output.close();
      } // end generate_RSA_key
    
    
    //*************************************************************************************
    //messageToBigInteger -- convert a message to BigInteger
    public static BigInteger messageToBigInteger(String message)
	{
		int i;
		String temp = "" ;
		
		for (i = 0; i < message.length(); i++)
			temp += ((int) message.charAt(i) - SHIFT);

		return new BigInteger(temp);
	} // Endof messageToBigInteger method			

    //*************************************************************************************
    // testInputFile--read numbers from file and call Rabin-Miller with many a's to test for primality.
    public static void testInputFile() throws IOException {

        int numBits, trialNum;
        int [] results;
        BigInteger n, a;
        String base2Str;
              Random rand = new Random();

        System.out.print("Enter the name of the input file to test integers:  ");
        String inputFileName = kbd.nextLine();

        FileReader fReader = new FileReader(inputFileName);
                    input = new Scanner(fReader);

                    System.out.print("Enter the name of the output file for test results:  ");
                    String outputFileName = kbd.nextLine();
                    FileWriter fwriter = new FileWriter(outputFileName);
                    output = new PrintWriter(fwriter);

        String line = input.nextLine();
        while (!line.equals(""))
        {
          n = new BigInteger(line);   
          output.println();
          output.println("TESTING NEW integer: n = " + n);
          System.out.println("TESTING NEW integer: n = " + n + "\n");

          base2Str = n.toString(2);
          numBits = base2Str.length();

          trialNum = 0;
          results = new int[3];

          while (trialNum < NUM_TRIALS)
          {
            trialNum++;
            output.println();
            output.println("Trial # " + trialNum); 
            do {
               a = new BigInteger(numBits, rand);
            } while (a.compareTo(ONE) <= 0 || a.compareTo(n.subtract(ONE)) >= 0);
            results[RabinMillerTest(n, a, true)]++;
          }

          output.println();
          output.println("Summary for n = " + n);
          output.println("(out of " + NUM_TRIALS + " trials):");
          output.println("Probably Prime:  " + results[PROBABLY_PRIME]);
          output.println("Composite (no factor):  " + results[COMPOSITE_NO_FACTOR]);
          output.println("Composite (found):  " + results[COMPOSITE_FACTOR_FOUND]);
          output.print("Result:  ");

          if (results[COMPOSITE_FACTOR_FOUND] > 0)
            output.println("This integer is composite, with factor: " + factor);

          else if (results[COMPOSITE_NO_FACTOR] > 0)
            output.println("This integer is composite, but no factors were found.");

          else
            output.println("This integer is most probably prime.");

          output.println();  
          output.println();
          output.println();

          line = input.nextLine();
        }

        System.out.println("Bye!");

      output.close();    

      }// testInputFile()

        private static BigInteger GCD(BigInteger a, BigInteger b) {
                    BigInteger[] xy = new BigInteger[2];
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
        }//END of GCD
    }// END of ClassS

