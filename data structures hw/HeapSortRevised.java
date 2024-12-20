/*
 * CIST 004B1 Spring 2024
 * HW Week 6 Problem 3
 * Description: Rewrite heapsort by sorting the elements using the 
 * characteristics of a heap without having to have a separate heap.
 * Input: n/a
 * Output: n/a
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 03/14/2024
 */

package ch23sorting;
import java.util.Comparator;

public class HeapSortRevised {
	
	public static void makeHeap(int[] nodes, int size) {
		
		int currentIndex = size-1;
		for (int i = (currentIndex-1)/2; i >= 0; i--) {
			reheapifyDown(nodes, size, i);
		}
		
	}
	
	public static void reheapifyDown(int[] nodes, int bound, int current) {
		
		int parentIndex = current;
		int leftChildIndex = 2 * parentIndex + 1;
		int rightChildIndex = 2 * parentIndex + 2;
		
		int maxIndex = parentIndex;
		
		if (leftChildIndex < bound && nodes[leftChildIndex] > nodes[maxIndex]) {
			maxIndex = leftChildIndex;
		}
		
		if (rightChildIndex < bound && nodes[rightChildIndex] > nodes[maxIndex]) {
			maxIndex = rightChildIndex;
		}
		
		if (maxIndex != parentIndex) {
			int temp = nodes[maxIndex];
			nodes[maxIndex] = nodes[parentIndex];
			nodes[parentIndex] = temp;
			reheapifyDown(nodes, bound, maxIndex);
		}
	}
	
	
	public static void heapSort(int[] data, int n) {
		
		int unsorted;
		makeHeap(data, n);
		unsorted = n;
		
		while (unsorted > 1) {
			--unsorted;
			
			int temp = data[0];
			data[0] = data[unsorted];
			data[unsorted] = temp;
			
			makeHeap(data, unsorted);
		}
			
	}

	public static void main(String[] args) {
		int[] test1 = {-44, -5, -3, 3, 3, 1, -4, 0, 1, 2, 4, 5, 53};
		System.out.print("Unsorted: ");
		for (int num : test1) {
			System.out.print(num + " ");
		}
		
		System.out.println();
		
		heapSort(test1, test1.length);
		System.out.print("Sorted: ");
		for (int num : test1) {
			System.out.print(num + " ");
		}

	}

}

/* Output:
 * Unsorted: -44 -5 -3 3 3 1 -4 0 1 2 4 5 53 
 * Sorted: -44 -5 -4 -3 0 1 1 2 3 3 4 5 53 
 */
