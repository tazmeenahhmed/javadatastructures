/*
 * CIST 004B1 Spring 2024
 * HW Week 2 Problem 3
 * Description: Create two functions to calculate the binomial
 * non-negative integers n and k. Recursion review.
 * Input: two numbers to find their binomial coefficient
 * Output: the binomial coefficient of the two input numbers
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 02/12/2024
 */


package ch19lambda;
import java.util.Scanner;

public class HomeworkThree {
	
	public static int cr(int n, int k) {
		
		if (k == 0 || k == n) {
			return 1;
		}
		
		if (k > n) {
			return 0;
		}
		
		return cr(n - 1, k -1) + cr(n - 1, k);
		
	}
	
	public static int ci (int n, int k) {
		
		if (k == 0 || k == n) {
			return 1;
		} 
		
		else if (k > n) {
			return 0;
		}
		
		else {
			int cnk = 1;
			for (int i = 0; i < k; i++) {
				cnk *= n - i;
				cnk /= i + 1;
			}
			return cnk;
		}
	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter first recursive number: ");
		int rFirst = input.nextInt();
		System.out.println("Enter second recursive number: ");
		int rSecond = input.nextInt();
		System.out.println("Recursive binomial coefficient results: " + cr(rFirst, rSecond));
		
		System.out.println();
		
		System.out.println("Enter first iterative number: ");
		int iFirst = input.nextInt();
		System.out.println("Enter second iterative number: ");
		int iSecond = input.nextInt();
		System.out.println("Iterative binomial coefficient results: " + ci(iFirst, iSecond));

	}

}

/*
 * output:
 * Enter first recursive number: 5
 * Enter second recursive number: 3
 * Recursive binomial coefficient results: 10
 * 
 * Enter first iterative number: 5
 * Enter second iterative number: 3
 * Iterative binomial coefficient results: 10
 */
