/*
 * CIST 004B1 Spring 2024
 * HW Week 4 Problem 2
 * Description: (Count occurrences of numbers entered). Write a program that
 * reads an unspecified # of integers and finds the one that has the most
 * occurrences. If not on but several numbers have the most occurrences, all 
 * of them should be reported.
 * Input: An unspecified number of integers
 * Output: Number(s) with the mist occurrences
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 02/21/2024
 */

package ch21setsandmaps;
import java.util.*;

public class CountOccurence {


	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Integer> numEnter = new ArrayList<>();
		
		while (true) {
			int currentNum = input.nextInt();
			if (currentNum == 0) {
				break;
			}
			numEnter.add(currentNum);
			
		}
		
		Map<Integer, Integer> numCount = new TreeMap<>();
		int highest = 1;
		
		for (int i = 0; i < numEnter.size(); i++) {
			int key = numEnter.get(i);
			if (!numCount.containsKey(key)) {
				numCount.put(key, 1);
			} else {
				int value = numCount.get(key);
				value++;
				numCount.put(key, value);
				if (value > highest) {
					highest = value;
				}
			}
		}
		
		ArrayList<Integer> highestNums = new ArrayList<>();
		
		for (int key : numCount.keySet()) {
			if (numCount.get(key) == highest) {
				highestNums.add(key);
			}
		}
		
		System.out.println("The number(s) that has the most occurences is: " + highestNums);

	}

}

/* Output:
 * 2
 * 3
 * 40
 * 3
 * 5
 * 4
 * -3
 * 3
 * 3
 * 2
 * 0
 * The number(s) that has the most occurences is: [3]
 * 
 * 
 * 
*/
