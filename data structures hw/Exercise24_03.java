/*
 * CIST 004B1 Spring 2024
 * HW Week 7 Problem 2
 * Description: Implement TwoWayLinkedList and its iterators
 * Input: 5 integers
 * Output: different functions of twowaylinkedlist put to use
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 03/15/2024
 */

/*
 * Output:
 * [2.0, 3.0, 4.0, 5.0]
 * [1.0, 2.0, 3.0, 4.0, 5.0]
 * [1.0, 2.0, 10.55, 3.0, 4.0, 5.0]
 * [1.0, 2.0, 10.55, 4.0, 5.0]
 * 1.0 2.0 10.55 4.0 5.0 
 * 5.0 4.0 10.55 2.0 
 */


package ch24implementations;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.Scanner;

import ch24implementations.Exercise24_02HW.MyList;

public class Exercise24_03 {
  public static void main(String[] args) {
    new Exercise24_03();
  }
  
  public Exercise24_03() {
    TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();
    System.out.print("Enter five numbers: ");
    Scanner input = new Scanner(System.in);
    double[] v = new double[5];
    for (int i = 0; i < 5; i++) 
      v[i] = input.nextDouble();

    list.add(v[1]);
    list.add(v[2]);
    list.add(v[3]);
    list.add(v[4]);
    System.out.println(list);
    list.add(0, v[0]);
    System.out.println(list);
    list.add(2, 10.55);
    System.out.println(list);
    list.remove(3);
    System.out.println(list);

    java.util.ListIterator<Double> iterator1 = list.listIterator();
    while (iterator1.hasNext())
      System.out.print(iterator1.next() + " ");

    java.util.ListIterator<Double> iterator2 = list.listIterator(list.size() - 1);
    System.out.println();
    while (iterator2.hasPrevious())
      System.out.print(iterator2.previous() + " ");
  }
}

class TwoWayLinkedList<E> implements MyList<E> {
  private Node<E> head, tail;
  private int size;

  /** Create a default list */
  public TwoWayLinkedList() {
  }

  /** Create a list from an array of objects */
  public TwoWayLinkedList(E[] objects) {
    for (E e : objects)
      add(e);
  }

  /** Return the head element in the list */
  public E getFirst() { // implemented
	  if (size == 0) {
			return null;
		} else {
			return head.element;
		}
  }

  /** Return the last element in the list */
  public E getLast() { // implemented
	  if (size == 0) {
			return null;
		} else {
			return tail.element;
		}
  }

  /** Add an element to the beginning of the list */
  public void addFirst(E e) { // implemented
    Node<E> newNode = new Node<E>(e); // Create a new node
    newNode.next = head; // newNode points next to head
    newNode.previous = null;
    head = newNode; // newNode is now the new head
    size++;
    if (tail == null) {
    	tail = head;
    }
   }

  /** Add an element to the end of the list */
  public void addLast(E e) { // implemented
    Node<E> newNode = new Node<E>(e); // Create a new for element e

    Node<E> temp = tail; // For a two-way linked list
    
    if (tail == null) {
		head = tail = newNode; // The new node is the only node in list
	} else {
		tail.next = newNode; // tail points to newNode
	    newNode.previous = tail; // newNode's previous points to tail
	    tail = newNode; // newNode becomes tail
	}

	size++; // Increase size
  }

  /**
   * Add a new element at the specified index in this list The index of the
   * head element is 0
   */
  public void add(int index, E e) { // implemented
    if (index == 0) {
      addFirst(e);
    }
    else if (index >= size) {
      addLast(e);
    } 
    else {
      Node <E> current = head;
      for (int i = 0; i < index-1; i++) {
    	  current = current.next;
      }
      Node<E> newNode = new Node<>(e); // replacement node
      newNode.next = current.next; // connect its next to currents next
      (current.next).previous = newNode; // connect currents previous to newNode
      current.next = newNode; // connect currents next to newNode
      newNode.previous = current; // connect newNodes previous to current
      size++;
      
    }
  }

  /**
   * Remove the head node and return the object that is contained in the
   * removed node.
   */
  public E removeFirst() { // implemented
    if (size == 0) {
      return null;
    } else {
      Node<E> temp = head;
      
      (head.next).previous = null; // empty the future head's prev first
      head = head.next; // turn next into head
		size--;
		if (head == null) {
			tail = null;
		}
      
      return temp.element;
    }
  }

  /**
   * Remove the last node and return the object that is contained in the
   * removed node.
   */
  public E removeLast() { // implemented
    if (size == 0) {
      return null;
    } else if (size == 1){
    	Node<E> temp = head;
		head = tail = null;
		size = 0;
		return temp.element;
    } else {
    	Node<E> temp = tail;
		tail = tail.previous; // sets the one before tail as new tail
		tail.next = null; // disconnects new tail from anything afterwards
		size--;
		
		return temp.element;
    }
  }

  /**
   * Remove the element at the specified position in this list. Return the
   * element that was removed from the list.
   */
  public E remove(int index) { // implemented
    if (index < 0 || index >= size) {
      return null;
    } else if (index == 0) {
      return removeFirst();
    } else if (index == size - 1) {
      return removeLast();
    } else {
      Node<E> current = head;
      for (int i = 0; i < index-1; i++) {
    	  current = current.next;
      }
      Node<E> remove = current.next; // the element after where the loop lands is to be removed
      current.next = remove.next; // where the loop landed will be connected to remove's after
      (remove.next).previous = current; // remove's after will connect to where the loop landed
      size--;
      
      return remove.element;
      
    }
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != null) {
        result.append(", "); // Separate two elements with a comma
      } else {
        result.append("]"); // Insert the closing ] in the string
      }
    }

    return result.toString();
  }

  /** Clear the list */
  public void clear() {
    head = tail = null;
  }

  /** Return true if this list contains the element o */
  public boolean contains(Object e) { // implemented
    //System.out.println("Implementation left as an exercise");
	Node<E> current = head;
	while (current != null) {
		if (current.element.equals(e)) {
			return true;
		} else {
			current = current.next;
		}
	}
	return false;
  }

  /** Return the element from this list at the specified index */
  public E get(int index) { // implemented
    //System.out.println("Implementation left as an exercise");
	Node<E> current = head;
	if (index == 0) {
		return head.element;
	} else {
		for (int i = 1; i <= index; i++) {
		current = current.next;
		}
	}
	return current.element;
  }

  /**
   * Return the index of the head matching element in this list. Return -1 if
   * no match.
   */
  public int indexOf(Object e) { // implemented
    //System.out.println("Implementation left as an exercise");
	Node<E> current = head;
	for (int i = 0; i < size; i++) {
		if (current.element.equals(e)) {
			return i;
		}
			current = current.next;
		}
	return -1;
  }

  /**
   * Return the index of the last matching element in this list Return -1 if
   * no match.
   */
  public int lastIndexOf(Object e) { // implemented
    //System.out.println("Implementation left as an exercise");
	int update = -1;
	Node<E> current = head;
	for (int i = 0; i < size; i++) {
		if (current.element.equals(e)) {
			update = i;
		}
		current = current.next;
		}
		
	return update;
  }

  /**
   * Replace the element at the specified position in this list with the
   * specified element.
   */
  public E set(int index, E e) { // implemented
    // System.out.println("Implementation left as an exercise");
	if (index == 0) {
		head.element = e;
	} else {	
		Node<E> current = head;
		for (int i = 1; i <= index; i++) {
			current = current.next;
		}
		current.element = e;
	}
	return null;
  }

  private class LinkedListIterator implements java.util.ListIterator<E> {
    private Node<E> current = head; // Current index

    public LinkedListIterator() {
    }
    
    public LinkedListIterator(int index) {
      if (index < 0 || index >= size)
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
          + size);
      for (int nextIndex = 0; nextIndex < index; nextIndex++)
        current = current.next;
    }
    
    public void setLast() {
  	current = tail;
    }
    
    @Override
    public boolean hasNext() {
      return (current != null);
    }

    @Override
    public E next() {
      E e = current.element;
      current = current.next;
      return e;
    }

    @Override
    public void remove() { // implemented
      //System.out.println("Implementation left as an exercise");
      if (current == head) {
    	  removeFirst();
      } else if (current == tail) {
    	  removeLast();
      } else {
    	  (current.next).previous = current.previous;
    	  (current.previous).next = current.next;
    	  size--;
      }
     
    }

    @Override
    public void add(E e) { // implemented
      //System.out.println("Implementation left as an exercise");
      if (current == head) {
    	  addFirst(e);
      } else if (current == tail) {
    	  addLast(e);
      } else {
    	  Node<E> temp = current.next;
    	      
          current.next = new Node<>(e); // creation of the new node, current's next points to it
          (current.next).previous = current; // where the loop landed = newNode's previous
          (current.next).next = temp; // the next of the landed node becomes the next of newNode
          temp.previous = current.next;
          size++;

      }
    }

    @Override
    public boolean hasPrevious() {
      return current != null;
    }

    @Override
    public int nextIndex() {
      //System.out.println("Implementation left as an exercise");
      if (current == null) {
    	  return size;
      }

      int index = 0;
      Node<E> traverse = head;
    	  
      while (traverse != null && traverse != current){
    	  traverse = traverse.next;
    	  index++;
      }
      return index;
    }

    @Override
    public E previous() {
      E e = current.element;
      current = current.previous;
      return e;
    }

    @Override
    public int previousIndex() {
      //System.out.println("Implementation left as an exercise");
      if (current == null) {
    	  return -1;
      }

      int index = size - 1;
      Node<E> traverse = tail;
    	  
      while (traverse != null && traverse != current){
    	  traverse = traverse.previous;
    	  index--;
      }
      return index;
    }

    @Override
    public void set(E e) {
      current.element = e; // TODO Auto-generated method stub
    }
  }

  private class Node<E> {
    E element;
    Node<E> next;
    Node<E> previous;

    public Node(E o) {
      element = o;
    }
  }

  @Override
  public int size() {
    return size;
  }

  public ListIterator<E> listIterator() {
	LinkedListIterator it = new LinkedListIterator();
	return it;
  }
  
  public ListIterator<E> listIterator(int index) {
    LinkedListIterator it = new LinkedListIterator(index);
	return it;
  }

  @Override
  public Iterator<E> iterator() {
    // TODO Auto-generated method stub
    return null;
  }
}

