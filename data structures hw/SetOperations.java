/*
 * CIST 004B1 Spring 2024
 * HW Week 4 Problem 1
 * Description: (Perform set operations on hash sets). Create two hash sets,
 * find their union, difference, and intersection. 
 * Input: none
 * Output: A union, difference, and intersected version of two lists
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 02/21/2024
 */

package ch21setsandmaps;
import java.util.*;

public class SetOperations {

	public static void main(String[] args) {
		
		HashSet<String> list1 = new HashSet<>();
		list1.add("George");
		list1.add("Jim");
		list1.add("John");
		list1.add("Blake");
		list1.add("Kevin");
		list1.add("Michael");
		System.out.println("Set 1 contains: " + list1);
		
		
		HashSet<String> list2 = new HashSet<>();
		list2.add("George");
		list2.add("Katie");
		list2.add("Kevin");
		list2.add("Michelle");
		list2.add("Ryan");
		System.out.println("Set 2 contains: " + list2);
		
		
		
		HashSet<String> firstClone1 = new HashSet<>();
		firstClone1 = (HashSet)list1.clone();
		
		firstClone1.addAll(list2);
		System.out.println("The union of the two sets are: " + firstClone1);
		
		
		
		HashSet<String> secondClone1 = new HashSet<>();
		secondClone1 = (HashSet)list1.clone();
		HashSet<String> secondClone2 = new HashSet<>();
		secondClone2 = (HashSet)list2.clone();
		
		secondClone1.removeAll(list2);
		secondClone2.removeAll(list1);
		secondClone1.addAll(secondClone2);
		System.out.println("The difference between the two sets are: " + secondClone1);
		
		
		
		HashSet<String> thirdClone1 = new HashSet<>();
		thirdClone1 = (HashSet)list1.clone();
		
		thirdClone1.retainAll(list2);
		System.out.println("The intersection between the two sets are: " + thirdClone1);
		
		
	}

}

/* Output:
 * Set 1 contains: [Kevin, George, Blake, Michael, John, Jim]
 * Set 2 contains: [Michelle, Kevin, Ryan, George, Katie]
 * The union of the two sets are: [Michelle, Kevin, Ryan, George, Katie, Blake, Michael, John, Jim]
 * The difference between the two sets are: [Michelle, Ryan, Katie, Blake, Michael, John, Jim]
 * The intersection between the two sets are: [Kevin, George]
 */
