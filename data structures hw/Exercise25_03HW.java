/*
 * CIST 004B1 Spring 2024
 * HW Week 8 Problem 1
 * Description: Implement inorder traversal without using recursion
 * Input: 10 integers 
 * Output: both lists tree and tree1 printed out in order
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 03/21/2024
 */

package chapter25;

import java.util.Scanner;

// import chapter25.Exercise25_03.BST.TreeNode;

public class Exercise25_03HW {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		BST<Integer> tree = new BST<>();
		// Prompt the user to enter 10 integers and store them in the tree
		for (int i = 0; i < 10; i++) {
			tree.insert(input.nextInt());
		}

		tree.inorder();
		System.out.println();
		tree.nonRecursiveInorder();
		
		System.out.println();
		
		BST<String> tree1 = new BST<>();
		tree1.insert("George");
		tree1.insert("Michael");
		tree1.insert("Tom");
		tree1.insert("Adam");
		tree1.insert("Jones");
		tree1.insert("Peter");
		tree1.insert("John");
		tree1.insert("Daniel");

		tree1.inorder();
		System.out.println();
		tree1.nonRecursiveInorder();
	}

	public static class BST<E extends Comparable<E>> {
	// public static class BST<E extends Comparable<E>> implements Tree<E> { 
		protected TreeNode<E> root;
		protected int size = 0;

		/** Create a default binary search tree */
		public BST() {
		}

		/** Create a binary search tree from an array of objects */
		public BST(E[] objects) {
			for (int i = 0; i < objects.length; i++)
				insert(objects[i]);
		}

		/** Return true if the element is in the tree */
		public boolean search(E e) {
			TreeNode<E> current = root; // Start from the root

			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					current = current.right;
				} else
					// element matches current.element
					return true; // Element is found
			}

			return false;
		}

		/**
		 * Insert element e into the binary search tree Return true if the element is
		 * inserted successfully
		 */
		public boolean insert(E e) {
			if (root == null)
				root = createNewNode(e); // Create a new root
			else {
				// Locate the parent node
				TreeNode<E> parent = null;
				TreeNode<E> current = root;
				while (current != null)
					if (e.compareTo(current.element) < 0) {
						parent = current;
						current = current.left;
					} else if (e.compareTo(current.element) > 0) {
						parent = current;
						current = current.right;
					} else
						return false; // Duplicate node not inserted

				// Create the new node and attach it to the parent node
				if (e.compareTo(parent.element) < 0)
					parent.left = createNewNode(e);
				else
					parent.right = createNewNode(e);
			}

			size++;
			return true; // Element inserted
		}

		protected TreeNode<E> createNewNode(E e) {
			return new TreeNode<E>(e);
		}

		/** Inorder traversal from the root */
		public void inorder() {
			inorder(root);
		}

		/** Inorder traversal from a subtree */
		protected void inorder(TreeNode<E> root) {
			if (root == null)
				return;
			inorder(root.left);
			System.out.print(root.element + " ");
			inorder(root.right);
		}

		/** Postorder traversal from the root */
		public void postorder() {
			postorder(root);
		}

		/** Postorder traversal from a subtree */
		protected void postorder(TreeNode<E> root) {
			if (root == null)
				return;
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.element + " ");
		}

		/** Preorder traversal from the root */
		public void preorder() {
			preorder(root);
		}

		/** Preorder traversal from a subtree */
		protected void preorder(TreeNode<E> root) {
			if (root == null)
				return;
			System.out.print(root.element + " ");
			preorder(root.left);
			preorder(root.right);
		}

		/** Inner class tree node */
		public static class TreeNode<E extends Comparable<E>> {
			E element;
			TreeNode<E> left;
			TreeNode<E> right;

			public TreeNode(E e) {
				element = e;
			}
		}

		/** Get the number of nodes in the tree */
		public int getSize() {
			return size;
		}

		/** Returns the root of the tree */
		public TreeNode<E> getRoot() {
			return root;
		}

		/** Returns a path from the root leading to the specified element */
		public java.util.ArrayList<TreeNode<E>> path(E e) {
			java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
			TreeNode<E> current = root; // Start from the root

			while (current != null) {
				list.add(current); // Add the node to the list
				if (e.compareTo(current.element) < 0) {
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					current = current.right;
				} else
					break;
			}

			return list; // Return an array of nodes
		}

		/**
		 * Delete an element from the binary search tree. Return true if the element is
		 * deleted successfully Return false if the element is not in the tree
		 */
		public boolean delete(E e) {
			// Locate the node to be deleted and also locate its parent node
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else
					break; // Element is in the tree pointed by current
			}

			if (current == null)
				return false; // Element is not in the tree

			// Case 1: current has no left children
			if (current.left == null) {
				// Connect the parent with the right child of the current node
				if (parent == null) {
					root = current.right;
				} else {
					if (e.compareTo(parent.element) < 0)
						parent.left = current.right;
					else
						parent.right = current.right;
				}
			} else {
				// Case 2: The current node has a left child
				// Locate the rightmost node in the left subtree of
				// the current node and also its parent
				TreeNode<E> parentOfRightMost = current;
				TreeNode<E> rightMost = current.left;

				while (rightMost.right != null) {
					parentOfRightMost = rightMost;
					rightMost = rightMost.right; // Keep going to the right
				}

				// Replace the element in current by the element in rightMost
				current.element = rightMost.element;

				// Eliminate rightmost node
				if (parentOfRightMost.right == rightMost)
					parentOfRightMost.right = rightMost.left;
				else
					// Special case: parentOfRightMost == current
					parentOfRightMost.left = rightMost.left;
			}

			size--;
			return true; // Element deleted
		}

		/** Obtain an iterator. Use inorder. */
		public java.util.Iterator<E> iterator() {
			return new InorderIterator();
		}

		// Inner class InorderIterator
		private class InorderIterator implements java.util.Iterator<E> {
			// Store the elements in a list
			private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
			private int current = 0; // Point to the current element in list

			public InorderIterator() {
				inorder(); // Traverse binary tree and store elements in list
			}

			/** Inorder traversal from the root */
			private void inorder() {
				inorder(root);
			}

			/** Inorder traversal from a subtree */
			private void inorder(TreeNode<E> root) {
				if (root == null)
					return;
				inorder(root.left);
				list.add(root.element);
				inorder(root.right);
			}

			/** Next element for traversing? */
			public boolean hasNext() {
				if (current < list.size())
					return true;

				return false;
			}

			/** Get the current element and move cursor to the next */
			public E next() {
				return list.get(current++);
			}

			/** Remove the current element and refresh the list */
			public void remove() {
				delete(list.get(current)); // Delete the current element
				list.clear(); // Clear the list
				inorder(); // Rebuild the list
			}
		}

		/** Remove all elements from the tree */
		public void clear() {
			root = null;
			size = 0;
		}

		/** Inorder traversal from the root */
		public void nonRecursiveInorder() {
			java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
			java.util.Stack<TreeNode<E>> stack = new java.util.Stack<TreeNode<E>>();

			if (root == null)
				return;

			stack.push(root);
			while (!stack.isEmpty()) {
				// peek the stack
				TreeNode<E> look = stack.peek();
				
				
				if (look.left != null) {
					stack.push(look.left);
					look.left = null;
				} else {
					list.add(look);
					stack.pop();
					if (look.right != null) {
						stack.push(look.right);
					}
				} 

				
				// if the node has a new left child node push the left child node to the stack
				// bc it should go before the node
				// else add the node to the list and pop the stack
				// and if there is a right child node, add the right child node to the stack
			}

			for (int i = 0; i < list.size(); i++)
				System.out.print(list.get(i).element + " ");
		}
	}
}

/* Output:
 * 23
 * 4
 * 567
 * 85434
 * 65
 * 234
 * 6576
 * 34423
 * 0
 * 6
 * 0 4 6 23 65 234 567 6576 34423 85434 
 * 0 4 6 23 65 234 567 6576 34423 85434 
 * Adam Daniel George John Jones Michael Peter Tom 
 * Adam Daniel George John Jones Michael Peter Tom 
 */
