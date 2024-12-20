/*
 * CIST 004B1 Spring 2024
 * HW Week 14 Problem 3
 * Description: Write a program that inserts 30 random letters into 
 * a List<Character>. Perform a sorting of the list in ascending order,
 * descending order, and in ascending order with duplicates removed.
 * Input: n/a
 * Output: n/a
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 05/9/2024
 */

package chapter30;
import java.util.*;
import java.util.stream.Stream;

public class SortingRemoving {

	public static void main(String[] args) {
		
		Random random = new Random();
		List<Character> letters = new ArrayList<>();
		
		for (int i = 0; i < 30; i++) {
			letters.add((char)(random.nextInt(26) + 'a'));
		}
		
		System.out.println("Original list: " + letters);
		
		System.out.print("Sorted ascending: ");
		letters.stream().sorted(java.util.Comparator.naturalOrder())
			.forEach(e -> System.out.print(e + " "));
		
		System.out.println();
		
		System.out.print("Sorted descending: ");
		letters.stream().sorted(java.util.Comparator.naturalOrder())
			.sorted(java.util.Comparator.reverseOrder())
			.forEach(e -> System.out.print(e + " "));
		
		System.out.println();
		
		System.out.print("Sorted ascending & unique: ");
		letters.stream().distinct().sorted(java.util.Comparator.naturalOrder())
		.forEach(e -> System.out.print(e + " "));

	}

}

/*Output:
 
Original list: [l, o, d, x, n, v, i, g, q, v, q, p, i, y, c, f, a, o, d, b, i, q, r, g, j, r, z, m, c, p]
Sorted ascending: a b c c d d f g g i i i j l m n o o p p q q q r r v v x y z 
Sorted descending: z y x v v r r q q q p p o o n m l j i i i g g f d d c c b a 
Sorted ascending & unique: a b c d f g i j l m n o p q r v x y z 
 
 */
