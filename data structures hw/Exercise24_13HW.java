/*
 * CIST 004B1 Spring 2024
 * HW Week 7 Problem 3
 * Description: Define an iterator class FibonacciIterator 
 * for iterating Fibonacci numbers
 * Input: n/a
 * Output: fibonacci numbers up to 1000000
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 03/15/2024
 */

package ch24implementations;

import java.util.Iterator;

public class Exercise24_13HW {
  public static void main(String[] args) {
    Iterator<Integer> iterator = new FibonacciIterator(1000000);
    while (iterator.hasNext()){
    	System.out.print(iterator.next() + " ");
    }
    // print out all Fibonacci numbers before 1000000
  }
  
  static class FibonacciIterator implements java.util.Iterator<Integer> {
    private int limit;
    int prev1 = 0;
    int prev2 = 1;
    
    public FibonacciIterator(int limit) {
      this.limit = limit;
    }
    
    @Override 
    public Integer next() {
      int fibNum = prev1 + prev2;
      prev2 = prev1;
      prev1 = fibNum;
    		  
      return fibNum;
    }
    
    @Override 
    public boolean hasNext() {
      //int nextFib = prev1 + prev2;	
      return prev1+prev2 <= limit;
    }
    
    @Override 
    public void remove() {
      throw new UnsupportedOperationException
        ("Method not supported");
    }
  }
  
}

/*
 * Output:
 * 
 * 
 * 
 * 
 */