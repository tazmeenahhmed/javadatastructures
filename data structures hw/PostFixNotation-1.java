/*
 * CIST 004B1 Spring 2024
 * HW Week 3 Problem 1
 * Description: Write a program to evaluate postfix expressions. Pass the 
 * expression as a command-line argument in one string.
 * Input: a postfix expression
 * Output: the computed result
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 02/15/2024
 */

package ch20datastructures;
import java.util.Scanner;
import java.util.*;

public class PostFixNotation {
	
	public static int postfix (String expression) {
		
		Stack<Integer> evaluate = new Stack<Integer>();
		
		for (int i = 0; i < expression.length(); i++) {
			
			char scan = expression.charAt(i);
			
			if (expression.substring(i, i+1).equals(" ")) {
				continue;
			}
			if (Character.isDigit(scan) == true) {
				int toNum = Integer.parseInt(expression.substring(i,i+1));
				evaluate.push(toNum);
			}
			else {
				if (expression.substring(i, i+1).equals("+")) {
					int one = evaluate.pop();
					int two = evaluate.pop();
					evaluate.push(two + one);
				}
				if (expression.substring(i, i+1).equals("-")) {
					int one = evaluate.pop();
					int two = evaluate.pop();
					evaluate.push(two - one);
				}
				if (expression.substring(i, i+1).equals("*")) {
					int one = evaluate.pop();
					int two = evaluate.pop();
					evaluate.push(two * one);
				}
				if (expression.substring(i, i+1).equals("/")) {
					int one = evaluate.pop();
					int two = evaluate.pop();
					evaluate.push(two / one);
				}
			}
		}
		
			
		return evaluate.peek();
			
	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter first expression to be evaluated: ");
		String expression = input.nextLine();
		
		System.out.println(postfix(expression));
		
		System.out.println("Enter second expression to be evaluated: ");
		String expression2 = input.nextLine();
		
		System.out.println(postfix(expression2));
		
		System.out.println("Enter third expression to be evaluated: ");
		String expression3 = input.nextLine();
		
		System.out.println(postfix(expression3));
		
	}
	

	/* 
	 * output:
	 * Enter first expression to be evaluated: 
     * 4 5 7 2 + - *
  	 * -16
     * Enter second expression to be evaluated: 
     * 4 2 + 3 5 1 - * +
     * 18
     * Enter third expression to be evaluated: 
     * 1 2 + 3 *
     * 9
	 */

}
