/*
 * CIST 004B1 Spring 2024
 * HW Week 8 Problem 2
 * Description: You are given a sequence of distinct integers as input. 
 * Notice the order is important. You are to: (1) build a binary search tree.
 * (2) output the following:
 * (2.1) the height, n, of the tree on line 1
 * (2.2) for the next n+1 lines, for each level, the sum of all the node values on that level.
 * Input: a bst
 * Output: a height of the tree at level one and the sum of all node values
 * on levels n+1 lines
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 03/21/2024
 */

package chapter25;
import java.util.*;
import chapter25.Exercise25_03HW.BST.TreeNode;
import chapter25.Exercise25_03HW.BST;

public class BSTTwo {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		BST<Integer> tree = new BST<>();
		for (int i = 0; i < 6; i++) {
			tree.insert(input.nextInt());
		}
		nodeLevel(tree);
		
		System.out.println();
		
		BST<Integer> tree2 = new BST<>();
		tree2.insert(10);
		tree2.insert(12);
		tree2.insert(5);
		tree2.insert(4);
		tree2.insert(20);
		tree2.insert(8);
		tree2.insert(7);
		tree2.insert(15);
		tree2.insert(13);
		nodeLevel(tree2);
		
		System.out.println();
		
		BST<Integer> tree3 = new BST<>();
		nodeLevel(tree3);
		
	}
	
	
	public static <E extends Comparable<E>> void nodeLevel (BST<E> tree) {
		
		ArrayList<Integer> lvlAdd = new ArrayList<>();
		Queue<TreeNode<E>> level = new LinkedList<>();
		level.offer(tree.getRoot());
		int numNodes = 0;
		int height = -1;
		
		if (tree.getRoot() == null) {
			System.out.println(height);
			return;
		}
		
		while (true) {
			
			numNodes = level.size();
			
			if (numNodes == 0) {
				break;
			}
			
			int tempAdd = 0;
			height++;
			while (numNodes > 0) {
				TreeNode<E> look = level.peek();
				level.poll();
				tempAdd += (int)look.element;
				
				if (look.right != null) {
					level.offer(look.right);
				}
				if (look.left != null) {
					level.offer(look.left);
				}
				numNodes--;
			}
			lvlAdd.add(tempAdd);
		}
		
		System.out.println(height);
		for (int i : lvlAdd) {
			System.out.println(i);
		}
			
	}
		
	
}
/* Output:
 * 7
 * 5
 * 10
 * -1
 * 6
 * 25
 * 
 * 2
 * 7
 * 15
 * 30
 * 
 * 4
 * 10
 * 17
 * 32
 * 22
 * 13
 * 
 * -1
 */
