package chapter42;

import java.util.ArrayList;

public class Tree24<E extends Comparable<E>> implements Tree<E> {
	private Tree24Node<E> root;
	private int size;

	/** Create a default 2-4 tree */
	public Tree24() {
	}

	/** Create a 2-4 tree from an array of objects */
	public Tree24(E[] elements) {
		for (int i = 0; i < elements.length; i++)
			insert(elements[i]);
	}

	@Override /** Search an element in the tree */
	public boolean search(E e) {
		Tree24Node<E> current = root; // Start from the root
		while (current != null) {
			if (matched(e, current)) { // Element is in the node
				return true; // Element found
			} else {
				current = getChildNode(e, current); // Search in a subtree
			}
		}

		return false; // Element is not in the tree
	}

	/** Return true if the element is found in this node */
	private boolean matched(E e, Tree24Node<E> node) {
		for (int i = 0; i < node.elements.size(); i++)
			if (node.elements.get(i).equals(e))
				return true; // Element found
		return false; // No match in this node
	}

	/** Locate a child node to search element e */
	private Tree24Node<E> getChildNode(E e, Tree24Node<E> node) {
		if (node.child.size() == 0)
			return null; // node is a leaf
		int i = locate(e, node); // Locate the insertion point for e
		return node.child.get(i); // Return the child node
	}

	@Override /**
				 * Insert element e into the tree Return true if the element is inserted
				 * successfully
				 */
	// self: inserting into a leaf
	public boolean insert(E e) {
		if (root == null)
			root = new Tree24Node<E>(e); // Create a new root for element
		else {
			// Locate the leaf node for inserting e
			Tree24Node<E> leafNode = null;
			Tree24Node<E> current = root;
			while (current != null)
				if (matched(e, current)) {
					return false; // Duplicate element found, nothing inserted
				} else {
					leafNode = current;
					current = getChildNode(e, current); // inch worm
				}

			// current is null, leafNode is parent of current
			// Insert the element e into the leaf node, leaf node may be a 2-node, 3-node or 4-node
			insert(e, null, leafNode); // The right child of e is null
		}
		size++; // Increase size
		return true; // Element inserted
	}

	/** Insert element e into node u */
	// self: insert into a node during splitting
	private void insert(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> u) {
		// Get the search path that leads to element e
		ArrayList<Tree24Node<E>> path = path(e);
		for (int i = path.size() - 1; i >= 0; i--) {
			if (u.elements.size() < 3) { // u is a 2-node or 3-node
				insert23(e, rightChildOfe, u); // Insert e to node u
				break; // Not full node, No further insertion to u's parent needed
			} else { // u is 4-node
				Tree24Node<E> v = new Tree24Node<E>(); // Create a new node
				E median = split(e, rightChildOfe, u, v); // Split u, got the median back
				if (u == root) {
					root = new Tree24Node<E>(median); // New root
					root.child.add(u); // u is the left child of median
					root.child.add(v); // v is the right child of median
					break; // No further insertion to u's parent needed
				} else {
					// Use new values for the next iteration in the for loop
					e = median; // Element to be inserted to parent
					rightChildOfe = v; // Right child of the element
					u = path.get(i - 1); // New node to insert element
					// now go back up to for loop and add e with rightChildOfe to u
				}
			}
		}
	}

	/** Insert element to a 2- or 3- and return the insertion point */
	// called by private void insert(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> u) 
	private void insert23(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> node) {
		int i = this.locate(e, node); // Locate where to insert
		node.elements.add(i, e); // Insert the element into the node using ArrayList add
		if (rightChildOfe != null)
			// one more element in node.elements,
			// therefore one more element in node.child at index (i+1), a right child
			node.child.add(i + 1, rightChildOfe); // Insert the child link
	}

	/** Split a 4-node u into u and v and insert e to u or v */
	// 4-node has 3 elements
	// u may be a leaf node if the addition comes down from the root
	// u may be a non-leaf node if the addition from below overflows to add element
	// to parent
	// takyiu: e is added to u. u is being split.
	private E split(E e, Tree24Node<E> rightChildOfe, Tree24Node<E> u, Tree24Node<E> v) {
		// v is a new node, u.elements have 3 elements 0,1,2
		// Move the last element in node u to node v
		v.elements.add(u.elements.remove(2));
		E median = u.elements.remove(1); // now u only has element 0.
		// Split children for a non-leaf node
		// Move the last two children in node u to node v
		if (u.child.size() > 0) { // not a leaf, figure 42.7(a) and (b)
			// remove u's last 2 children and give them to v
			// second last child of u
			v.child.add(u.child.remove(2)); // takyiu: v has c2 as first child
			// last child of u
			v.child.add(u.child.remove(2)); // takyiu: v has c3 as second child, now u has c0 and c1 only
		}
		// Insert e into a 2- or 3- node u or v.
		// use Figure 42.6(a)
		if (e.compareTo(median) < 0)
			insert23(e, rightChildOfe, u);
		else
			insert23(e, rightChildOfe, v);
		return median; // Return the median element, back in insert a new node is created
	}

	/** Return a search path that leads to element e */
	private ArrayList<Tree24Node<E>> path(E e) {
		ArrayList<Tree24Node<E>> list = new ArrayList<Tree24Node<E>>();
		Tree24Node<E> current = root; // Start from the root
		while (current != null) {
			list.add(current); // Add the node to the list
			if (matched(e, current)) {
				break; // Element found
			} else {
				current = getChildNode(e, current);
			}
		}
		return list; // Return an array list of nodes from root to e
	}

	@Override /** Delete the specified element from the tree */
	public boolean delete(E e) {
		// Locate the node that contains the element e
		Tree24Node<E> node = root;
		while (node != null)
			if (matched(e, node)) {
				delete(e, node); // Delete element e from node
				size--; // After one element deleted
				return true; // Element deleted successfully
			} else {
				node = getChildNode(e, node);
			}
		return false; // Element not in the tree
	}

	/** Delete the specified element from the node */
	// e is in the node
	private void delete(E e, Tree24Node<E> node) {
		if (node.child.size() == 0) { // e is in a leaf node
			// Get the path that leads to e from the root
			ArrayList<Tree24Node<E>> path = path(e);
			node.elements.remove(e); // Remove element e
			if (node == root) { // Special case
				if (node.elements.size() == 0)
					root = null; // Empty tree
				return; // Done
			}
			validate(e, node, path); // Check underflow node for leaf node
			// 1. e has just been deleted from node
			// 2. path is from root to the node where e was
		} else { // e is in an internal node
			// Locate the rightmost element in the left subtree of the node
			int index = locate(e, node); // Index of e in node.elements
			Tree24Node<E> current = node.child.get(index); // left child
			while (current.child.size() > 0) { // while not leaf node
				current = current.child.get(current.child.size() - 1); // right most leaf node 
			}
			// get rightmost element of left child
			E rightmostElement = current.elements.get(current.elements.size() - 1);
			// Get the path that leads to e from the root
			ArrayList<Tree24Node<E>> path = path(rightmostElement);
			// Replace the deleted element with the rightmost element
			node.elements.set(index, current.elements.remove(current.elements.size() - 1));
			validate(rightmostElement, current, path); // Check underflow for non-leaf node
			// 1. rightmostElement has just been deleted from node current
			// 2. path is from root to node where rightmostElement was
		}
	}

	/** Perform transfer and fusion operations if necessary */
	// check underflow, e has just been removed from u
	private void validate(E e, Tree24Node<E> u, ArrayList<Tree24Node<E>> path) {
		// start with node u that may be empty
		for (int i = path.size() - 1; u.elements.size() == 0; i--) {
			Tree24Node<E> parentOfu = path.get(i - 1); // Get parent of u
			// what is e's child index from in parentOfu
			int k = locate(e, parentOfu); // Index of e in the parent node
			// Check two siblings
			if (k > 0 && parentOfu.child.get(k - 1).elements.size() > 1) {
				// can do left sibling transfer
				leftSiblingTransfer(k, u, parentOfu);
			} else if (k + 1 < parentOfu.child.size() && parentOfu.child.get(k + 1).elements.size() > 1) {
				// can do right sibling transfer
				rightSiblingTransfer(k, u, parentOfu);
			} else if (k - 1 >= 0) { // Fusion with a left sibling
				// Get left sibling of node u
				Tree24Node<E> leftNode = parentOfu.child.get(k - 1);
				// Perform a fusion with left sibling on node u
				leftSiblingFusion(k, leftNode, u, parentOfu);
				// Done when root becomes empty
				if (parentOfu == root && parentOfu.elements.size() == 0) {
					root = leftNode;
					break;
				}
				u = parentOfu; // Back to the loop to check the parent node
			} else { // Fusion with right sibling (right sibling must exist)
				// Get left sibling of node u
				Tree24Node<E> rightNode = parentOfu.child.get(k + 1);
				// Perform a fusion with right sibling on node u
				rightSiblingFusion(k, rightNode, u, parentOfu);
				// Done when root becomes empty
				if (parentOfu == root && parentOfu.elements.size() == 0) {
					root = rightNode;
					break;
				}
				u = parentOfu; // Back to the loop to check the parent node
			}
		}
	}

	/** Locate the insertion point of the element in the node */
	private int locate(E o, Tree24Node<E> node) {
		for (int i = 0; i < node.elements.size(); i++) {
			if (o.compareTo(node.elements.get(i)) <= 0) {
				return i;
			}
		}
		return node.elements.size();
	}

	/** Perform a transfer with a left sibling */
	// e has just been removed from u. u is empty.
	// k is e's child index from its parentOfu
	private void leftSiblingTransfer(int k, Tree24Node<E> u, Tree24Node<E> parentOfu) {
		// Move an element from the parent to u
		// u is empty at this point
		u.elements.add(0, parentOfu.elements.get(k - 1));
		// Move an element from the left node to the parent
		Tree24Node<E> leftNode = parentOfu.child.get(k - 1);
		// last element in leftNode
		parentOfu.elements.set(k - 1, leftNode.elements.remove(leftNode.elements.size() - 1));
		// Move the child link from left sibling to u
		// needed if leftNode and u are not leaf nodes
		if (leftNode.child.size() > 0)
			u.child.add(0, leftNode.child.remove(leftNode.child.size() - 1));
	}

	/** Perform a transfer with a right sibling */
	private void rightSiblingTransfer(int k, Tree24Node<E> u, Tree24Node<E> parentOfu) {
		// Transfer an element from the parent to u
		u.elements.add(parentOfu.elements.get(k));
		// Transfer an element from the right node to the parent
		Tree24Node<E> rightNode = parentOfu.child.get(k + 1);
		parentOfu.elements.set(k, rightNode.elements.remove(0));
		// Move the child link from right sibling to the node
		if (rightNode.child.size() > 0)
			u.child.add(rightNode.child.remove(0));
	}

	/** Perform a fusion with a left sibling */
	private void leftSiblingFusion(int k, Tree24Node<E> leftNode, Tree24Node<E> u, Tree24Node<E> parentOfu) {
		// Transfer an element from the parent to the left sibling
		leftNode.elements.add(parentOfu.elements.remove(k - 1));
		// Remove the link to the empty node
		parentOfu.child.remove(k); // node u
		// Adjust child links for non-leaf node
		if (u.child.size() > 0)
			leftNode.child.add(u.child.remove(0));
	}

	/** Perform a fusion with a right sibling */
	private void rightSiblingFusion(int k, Tree24Node<E> rightNode, Tree24Node<E> u, Tree24Node<E> parentOfu) {
		// Transfer an element from the parent to the right sibling
		rightNode.elements.add(0, parentOfu.elements.remove(k));
		// Remove the link to the empty node
		parentOfu.child.remove(k);
		// Adjust child links for non-leaf node
		if (u.child.size() > 0)
			rightNode.child.add(0, u.child.remove(0));
	}

	@Override /** Get the number of nodes in the tree */
	public int getSize() {
		return size;
	}

	@Override /** Preorder traversal from the root */
	public void preorder() {
		preorder(root);
	}

	/** Preorder traversal from a subtree */
	private void preorder(Tree24Node<E> root) {
		if (root == null)
			return;
		for (int i = 0; i < root.elements.size(); i++)
			System.out.print(root.elements.get(i) + " ");
		for (int i = 0; i < root.child.size(); i++)
			preorder(root.child.get(i));
	}

	@Override /** Inorder traversal from the root */
	public void inorder() {
		// Left as exercise
	}

	/** Postorder traversal from the root */
	public void postorder() {
		// Left as exercise
	}

	@Override /** Return true if the tree is empty */
	public boolean isEmpty() {
		return root == null;
	}

	@Override /** Remove all elements from the tree */
	public void clear() {
		root = null;
		size = 0;
	}

	@Override /** Return an iterator to traverse elements in the tree */
	public java.util.Iterator<E> iterator() {
		// Left as exercise
		return null;
	}

	/** Define a 2-4 tree node */
	protected static class Tree24Node<E extends Comparable<E>> {
		// elements has maximum three values
		ArrayList<E> elements = new ArrayList<E>(3);
		// Each has maximum four children
		ArrayList<Tree24Node<E>> child = new ArrayList<Tree24Node<E>>(4);

		/** Create an empty Tree24 node */
		Tree24Node() {
		}

		/** Create a Tree24 node with an initial element */
		Tree24Node(E o) {
			elements.add(o);
		}
	}
}