// Sunil Kumar Reddy Dokuru

// Project3, CS-355, spring 2015

// You write verifyHamCycle
// You write verifyIsomorphism
// You write randomPermutation
// You write permuteGraph
// You write permuteHamCycle
// You write findHamCycle

/*
 *  In this program I write procedures to: verifyHamCycle, verifyIsomorphism, randomPermutation, permuteGraph, 
 *  permuteHamCycle, and findHamCycle in the given files.
 */
import java.util.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Graphs {
 
  public static Scanner inputA, inputB, inputC, inputD;
  public static Scanner kbd = new Scanner(System.in);
  public static Random rand = new Random(); 
       

//**************************************************************************************
public static boolean verifyHamCycle(boolean [][] G, int [] c, int n) {
      HashMap map = new HashMap();
      int count = 0;
      for(int i = 0; i < n; i++) {
        
          if(!map.containsValue(c[i])){map.put(i, c[i]);  count++;}
      }
     
      int temp1, temp2;
      if(count == n) {
      for(int i = 0; i < n - 1; i++) {
          temp1 = c[i];
          temp2 = c[i + 1];
          for(int j = 0; j < n; j++)
              if(G[temp1][temp2] && G[temp2][temp1]) {}
              else
                  return false;
      }}
      else return false;
  return true;
  }
//**************************************************************************************
  public static boolean verifyIsomorphism(boolean [][] G, boolean [][] H, int [] a, int n) {

   
   ArrayList<Integer> list = new ArrayList();

      for(int i = 0; i < n; i++) {
         for(int j = 0; j < n; j++)
            if(G[i][j])
               list.add(j);
              
         int[] check = new int[list.size()];
         int[] checkH;
         for(int k = 0; k < list.size(); k++)
            check[k] = a[list.get(k)];
         list.clear();
         for(int k = 0; k < H.length; k++)
            if(H[a[i]][k])
               list.add(k);
         checkH = new int[list.size()];
         for(int k = 0; k < list.size(); k++)
            checkH[k] = list.get(k);
         if(check.length == checkH.length){
            for(int k = 0; k < check.length; k++) {
                if(H[a[i]][check[k]]) {}
             else
                 return false;
            }
         }
         else
            return false;
         list.clear();
      }
    return true;
  }
 
//**************************************************************************************
  public static int [] randomPermutation(int n)  {
 
  // You write this!

    int [] a = new int[n];
    for(int i = 0; i < n; i++) {
      int r = rand.nextInt(i+1);
      a[i] = a[r];
      a[r] = i;
    }
    return a;
  }

//**************************************************************************************
  public static boolean [][] permuteGraph(boolean [][] G, int [] perm, int n)  {
    
  // You write this!
    boolean [][] H = new boolean[n][n];
    for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
            H[perm[i]][perm[j]] = (G[i][j]);
           
    return H;
   
  }
//**************************************************************************************
  public static int [] permuteHamCycle(int [] c, int [] a, int n)  {
    
    int [] pc = new int[n];
    for(int i  =0; i < n; i++)
        pc[i] = a[c[i]];
    return pc;

  }


//**************************************************************************************
  public static int [] findHamCycle(boolean [][] G, int n)  {

      int [] c = new int[n];  ArrayList<Integer> list = new ArrayList();
      boolean condition;
      int i = 0, j;int count = 0;
   
      while(count < G.length) {
      condition = false;
         for(j = 0; j < G.length; j++) {
            if(G[i][j]) {
               if(!list.contains(Integer.parseInt(i+"")) || !list.contains(Integer.parseInt(i+""))) {
                  condition = true;
                  if(!list.contains(Integer.parseInt(i+"")))  { list.add(i); count++;} //System.out.print(" "+i);
                  if(!list.contains(Integer.parseInt(j+"")))  { list.add(j); count++;} //System.out.print(" "+j);
                  break;
               }
            }
        }if(condition) i = j; else i++;
        
      }  
      for(i = 0; i < list.size(); i++)
         c[i] = list.get(i);

      if(nextPerm(c,n))
      return c;
  return null;
  }

//*************************************************************************************
    public static void main(String[] args) throws IOException {

    int i = 0, j = 0, n = 0; // n = # vertices
    int [] a = null, c = null, pc = null;  // ham cycles or random permutations
    boolean [][] G = null;
    boolean [][] H = null;
    boolean pause = false;
   
    //outputArray (randomPermutation(8)); Check this
 
    System.out.println("My name is Sunil Kumsar Reddy Dokuru, CS-355, Asst 3\n");
    System.out.print("Do you want to pause after each output?  ");
    char ans = kbd.nextLine().charAt(0);
    pause = (ans == 'y' || ans == 'Y');
    setUpFiles();
   
 
    System.out.println("Part A:  Verifying Hamiltonian Cycles.\n");
   
    while (inputA.hasNextInt()) {
   
      n = inputA.nextInt();
   
      // "c" is holding a potential Hamiltonain Cycle 
      c = getCycle(inputA, n);
      G = getGraph(inputA, n);
   
      System.out.println("Graph G on " + n + " vertices, with potential Hamiltonaian Cycle: ");
      outputArray(c, n);
   
      if (verifyHamCycle(G, c, n))
        System.out.println("This IS INDEED a Hamiltonian Cycle in G.");
      else
        System.out.println("This is NOT a Hamiltoninan Cycle in G.");
       
      if (pause) {
        System.out.println("Hit Enter...");
        kbd.nextLine();
      }
   }  // end  while (inputA.hasNextInt()) {
       

    System.out.println("\nPart B:  Verifying Isomorphism.\n");

    while (inputB.hasNextInt()) {
   
      n = inputB.nextInt();
   
      // "a" is holding a potential isomorphism between G and H 
      G = getGraph(inputB, n);
      a = getCycle(inputB, n);
      H = getGraph(inputB, n);
   
      System.out.println("Graph G and H on " + n + " vertices, with potential isomorphism:");
      outputArray(a, n);
   
      if (verifyIsomorphism(G, H, a, n))
        System.out.println("Graphs G and H are INDEED ISOMORPHIC!");
      else
        System.out.println("Graphs G and H are NOT isomorphic");
       
      if (pause) {
        System.out.println("Hit Enter...");
        kbd.nextLine();
      }
   }  // end  while (inputB.hasNextInt()) {
       
       

    System.out.println("\nPart C:  Permuting Graphs, Cycles, and Verifying.\n");

    while (inputC.hasNextInt()) {
   
      n = inputC.nextInt();
 
      // "c" is holding a Hamiltonian Cycle of G 
      c = getCycle(inputC, n);
      G = getGraph(inputC, n);

      System.out.println("Given graph G on " + n + " vertices, with hamiltonian cycle:");
      outputArray(c, n);
     
      a = randomPermutation(n);
      H = permuteGraph(G, a, n);
      pc = permuteHamCycle(c, a, n);
     
      System.out.println("Permuted Hamiltonian Cycle pc:");
      outputArray(pc, n);
     
      System.out.println("H = G permuted by a is...\n" );
      outputGraph(H, n);
     
      System.out.print("\nCheck:  H isomorphic to G:  ");
      System.out.println(verifyIsomorphism(G, H, a, n));
     
      System.out.print("pc is a Hamiltonian Cycle in H:  ");
      System.out.println(verifyHamCycle(H, pc, n));
            
      if (pause) {
        System.out.println("Hit Enter...");
        kbd.nextLine();
      }
   }  // end  while (inputC.hasNextInt()) {


     
    System.out.println("\nPart D:  Using Brute Force to find Hamiltonian Cycle.\n");

    while (inputD.hasNextInt()) {
   
      n = inputD.nextInt();
      System.out.println("Graph on " + n + " vertices...");
     
   
      G = getGraph(inputD, n);
     
      c = new int [n];
     
      c = findHamCycle(G, n);
      System.out.print("This graph on " + n + " vertices ");
      if (c[0] == -1)
        System.out.println("does NOT have any Hamiltonian Cycle.");
      else  {
        System.out.println("DOES HAVE a Hamiltonian Cycle:");
        outputArray(c, n);
      }
      System.out.println();
     
      if (pause) {
        System.out.println("Hit Enter...");
        kbd.nextLine();
      }
   }  // end  while (inputD.hasNextInt()) {

  
   System.out.println("\nBye!");
  
 } // end main
   


 
//**************************************************************************************
  public static void setUpFiles() throws IOException  {
   
    FileReader fReader = new FileReader("IsCycleCorrect.txt");
        inputA = new Scanner(fReader);
    fReader = new FileReader("IsPermutationCorrect.txt");
        inputB = new Scanner(fReader);
    fReader = new FileReader("GraphsAndCyclesToPermute.txt");
        inputC = new Scanner(fReader);
    fReader = new FileReader("BruteForceAttempt.txt");
        inputD = new Scanner(fReader);
  }

//**************************************************************************************
  public static int [] getCycle(Scanner input, int n)  {

    int [] c = new int[n];
    for (int i = 0; i < n; i++)
      c[i] = input.nextInt();
    return c;
  }
   
//**************************************************************************************
  public static boolean [][] getGraph(Scanner input, int n)  {

    boolean [][] G = new boolean[n][n];
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        G[i][j] = (input.nextInt() == 1);
    return G;
  }
   
 
//**************************************************************************************
  public static boolean nextPerm(int [] p, int n) {
    int k = -1, m = -1, i = 0, j = 0, temp = 0;
   
    for (i = n - 2; i >= 0; i--)
      if (p[i] < p[i+1]) {
        k = i;
        break;
      }
 
    if (k == -1)
      return false;
     
    for (i = n - 1; i >= 0; i--)
      if (p[k] < p[i]) {
        m = i;
        break;
      }

    temp = p[k];
    p[k] = p[m];
    p[m] = temp;
   
    int [] A = new int[n];
   
    for (i = k+1, j = n-1;  i < n;  i++, j--)
      A[i] = p[j];
    for (i = k+1; i < n; i++)
      p[i] = A[i];
 
    return true;
  }
  
//**************************************************************************************
  public static void outputArray(int [] a, int n)  {
    int i;
    System.out.println();
    for (i = 0; i < n; i++)

{}
  }
   
//**************************************************************************************
  public static void outputGraph(boolean [][] G, int n)   {
    int i, j;      
    for (i = 0; i < n; i++) {
      for (j = 0; j < n; j++)
        if (G[i][j])
          System.out.print("1 ");
        else
          System.out.print("0 ");
      System.out.println();
    }  
  }
//**************************************************************************************

} // end class


