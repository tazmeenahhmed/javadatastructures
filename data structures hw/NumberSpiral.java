/*
 * CIST 004B1 Spring 2024
 * HW Week 6 Problem 4
 * Description: Write a program to calculate (18273645,81726354) 
 * and (12345678,87654321). Cut and paste your 2 numbers into 
 * the source file as comments.
 * Input: (18273645,81726354) and (12345678,87654321)
 * Output: the 2 numbers located in the spiral at the input positions
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 03/14/2024
 */

package ch23sorting;
import java.util.*;

public class NumberSpiral {

	public static long spiral(long row, long column) {
		long atSpot = 0;
		
		long max = Math.max(column, row);
		long min = Math.min(column, row);
	
		long corner = 1;
		for (long i = 2; i <= max; i++) {
			corner = ((i-1)*2) + corner;
		} 
		
		long increment = max - min;
		if (max % 2 == 0) {
			
			if (row == min) {
				atSpot = corner - increment;
			} else {
				atSpot = corner + increment;
			}
			
		} else {
			
			if (row == min) {
				atSpot = corner + increment;
			} else {
				atSpot = corner - increment;
			}
		}
		
		return atSpot;
	}
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		String position = input.nextLine();
		String[] coordinate = position.split(",", 2);
		long row = Integer.parseInt(coordinate[0]);
		long column = Integer.parseInt(coordinate[1]);
		
		System.out.println(spiral(row, column));

	}

}

/* Output:
 * (18273645,81726354): 6679196792954254
 * (12345678,87654321): 7683279977625364
 */
