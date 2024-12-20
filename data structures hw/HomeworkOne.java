/*
 * CIST 004B1 Spring 2024
 * HW Week 2 Problem 1
 * Description: Create a block lambda that computes the factorial 
 * of an integer. Use the provided NumericFunc interface. 
 * Input: a number to find the factorial of
 * Output: the factorial of the input
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 02/12/2024
 */


package ch19lambda;

interface NumericFunc {
	int func(int n);
}

public class HomeworkOne {
	public static void main (String args[]) {
		
		NumericFunc factorial = x -> {
			
			int total = x;
			
			for (int i = 1; i < x; i++) {
				total *= i;
			}
				
			return total;
		};
		
		
		System.out.println("The factoral of 3 is " + factorial.func(3));
		System.out.println("The factoral of 5 is " + factorial.func(5));
		System.out.println("The factoral of 9 is " + factorial.func(9));
	}

}

/*
 * output:
 * The factoral of 3 is 6
 * The factoral of 5 is 120
 * The factoral of 9 is 362880
 */
