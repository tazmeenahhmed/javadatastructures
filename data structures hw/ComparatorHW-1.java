/*
 * CIST 004B1 Spring 2024
 * HW Week 3 Problem 3
 * Description: Sort GeometricObject elements from an array. Sort string 
 * by their last character from an array.
 * Input: none
 * Output: A sorted GeometricObject elements array, a sorted
 * string array by last character
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 02/15/2024
 */

package txtch20comparator;
import java.util.Comparator;

public class ComparatorHW {
	
	public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {
		for (int i = 0; i < list.length - 1; i++) {
			
			int least = i;
			
			for (int j = i+1; j < list.length; j++) {
				if(comparator.compare(list[j], list[least]) < 0) { 
					least = j;
				}
			}
			
			E temp = list[least];
			list[least] = list[i]; 
			list[i] = temp;
			
			
		}
	}
	
	public static class LastLetterComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			String firstVar = s1.substring(s1.length()-1);
			String secondVar = s2.substring(s2.length()-1);
			
			if (firstVar.compareToIgnoreCase(secondVar) < 0)
			  return -1;
			else if (firstVar.compareToIgnoreCase(secondVar) == 0)
			  return 0;
			else
			  return 1;
		}
	}
	

	public static void main(String[] args) {
		GeometricObject[] list1 = {new Circle(5), new Rectangle(4,5), new Circle(5.5), 
				new Rectangle(2.4,5), new Circle(0.5), new Rectangle(4, 65), new Circle(4.5), 
				new Rectangle(4.4,1), new Circle(6.5), new Rectangle(4,5)};
		
		for (GeometricObject g : list1) {
			System.out.print(g.getArea() + " ");
		}
		
		System.out.println();
		
		selectionSort(list1, new GeometricObjectComparator());
		
		for (GeometricObject g : list1) {
			System.out.print(g.getArea() + " ");
		}
		
		System.out.println();
			
		String[] list2 = {"red", "blue", "green", "yellow", "orange", "pink"};
		
		for (String a : list2) {
			System.out.print(a + " ");
		}
		
		System.out.println();
		
		selectionSort(list2, new LastLetterComparator()); 
		
		for (String a : list2) {
			System.out.print(a + " ");
		}
			
	}
	
	/* output:
	 * 78.53981633974483 20.0 95.03317777109125 12.0 0.7853981633974483 260.0 63.61725123519331 4.4 132.73228961416876 20.0 
	 * 0.7853981633974483 4.4 12.0 20.0 20.0 63.61725123519331 78.53981633974483 95.03317777109125 132.73228961416876 260.0 
	 * red blue green yellow orange pink 
	 * red blue orange pink green yellow 
	 */

}




