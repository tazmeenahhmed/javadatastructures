package hw;
import java.util.ArrayList;

/* 19.3 Write the following method that returns a new ArrayList. 
 * The new list contains the non-duplicate elements from original list.
 * 
 */

/*
 * 19.6 Write a generic method that returns the maximum element
 * in a two-dimensional array.
 */

public class oophw {
	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list){
		
		for (int i = 0; i < list.size()-1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i) == list.get(j)) {
					list.remove(j);
				}
			}
		}
		
		
		return list;
	}
	
	public static <E extends Comparable<E>> E max(E[][] list) {
		
		E max = list[0][0];
		
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].length; j++) {
				//System.out.println(list[i][j]);
				if (list[i][j].compareTo(max) > 0) {
					max = list[i][j];
				}
			}
		}
		
		
		return max;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(14);
		list.add(24);
		list.add(14);
		list.add(42);
		list.add(25);
		
		ArrayList<Integer> newList = removeDuplicates(list);
		System.out.println(newList);
		
		Integer[][] numbers = {{1,2,3}, {4,7,6}};
		System.out.println(max(numbers));
	}
}
