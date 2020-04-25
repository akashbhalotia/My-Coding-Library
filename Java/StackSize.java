/*
  Java recursion stack is pretty small in size, and this may cause issues sometimes. 
  
  Possible solutions: 
  1) Best: Try using the stack data structure.
  2) Implemnt Runnable. Create a thread and solve the question in the run method.
  
  https://letsdocp.wordpress.com/2018/06/22/java-in-competitive-programming/
*/

//created by Whiplash99
import java.io.*;
import java.util.*;
public class A implements Runnable
{
    private static void general() throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int i, N;


    }
    public void run(){try{general();} catch (Exception e){}}
    public static void main(String[] args) throws Exception
    {
        new Thread(null, new A(), "A", 1<<26);
    }
}
