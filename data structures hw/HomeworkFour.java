/*
 * CIST 004B1 Spring 2024
 * HW Week 2 Problem 4
 * Description: Create two functions to calculate 
 * sin and cos using recursion
 * Input: numbers to find the sin and cos of
 * Output: the sin and cos of the input 
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 02/12/2024
 */


package ch19lambda;

import java.util.Scanner;

public class HomeworkFour {
	
	public static double sine(double x) {
		
		if (x > -0.005 || x < 0.005) {
			return x - (Math.pow(x, 3)/6);
		}

		
		return 2 * sine(x) * cos(x);
	}
	
	public static double cos(double x) {
		if (x > -0.005 || x < 0.005) {
			return 1 - (Math.pow(x, 2)/2);
		}
		
		return 1 - 2 * Math.pow(sine(x), 2);
	}

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter sine number: ");
		double s = input.nextDouble();
		System.out.println("Sin of " + s + " is: " + sine(s));
		System.out.println("Check answer. Sine of " + s + " is: " + Math.sin(s));
		
		System.out.println();
		
		System.out.println("Enter cosine number: ");
		double c = input.nextDouble();
		System.out.println("Cosine of " + c + " is: " + cos(c));
		System.out.println("Check answer. Cosine of " + c + " is: " + Math.cos(c));
	

	}

}

/*
 * Enter sine number: 0.5
 * Sin of 0.5 is: 0.4791666666666667
 * Check answer. Sine of 0.5 is: 0.479425538604203
 * 
 * Enter cosine number: 0.5
 * Cosine of 0.5 is: 0.875
 * Check answer. Cosine of 0.5 is: 0.8775825618903728
 */
