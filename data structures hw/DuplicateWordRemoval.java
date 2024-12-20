/*
 * CIST 004B1 Spring 2024
 * HW Week 14 Problem 2
 * Description: Write a program that inputs a sentence from the user then 
 * determines and displays the unique words in alphabetical order. Treat
 * uppercase and lowercase the same.
 * Input: n/a
 * Output: n/a
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 05/9/2024
 */

package chapter30;
import java.util.*;
import java.util.stream.Stream;

public class DuplicateWordRemoval {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a sentence (no punctuation): ");
		String sentence = input.nextLine();
		
		String[] words = sentence.split(" ");
		Stream.of(words).map(e -> e.toLowerCase()).distinct()
			.sorted((e1,e2) -> e1.compareToIgnoreCase(e2))
			.forEach(e -> System.out.print(e + " "));

	}

}
/* Output:
 * Peter picked a bad picked pickle so Peter threw a pickle away
 * 
 * a away bad peter picked pickle so threw 
 * 
 * 
 */
