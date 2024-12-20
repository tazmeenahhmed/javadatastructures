/*
 * CIST 004B1 Spring 2024
 * HW Week 13 Problem 2
 * Description: Find the shortest path from reading a connected graph in a file
 * Input: A file containing a connected graph
 * Output: The graphs shortest path
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 04/30/2024
 */

package chapter28;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class HW_ShortestPath {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Enter a URL: ");
		Scanner input = new Scanner(System.in);
		String graphFile = input.nextLine();
		
		System.out.println("Enter two vertices (integer indexes)");
		int a = Integer.parseInt(input.next());
		int b = Integer.parseInt(input.next());
		
		java.io.File file = new java.io.File(graphFile);
		Scanner reading = new Scanner(file);
		int N = Integer.parseInt(reading.nextLine());
		System.out.println("The number of vertices is " + N);
			
		WeightedGraph g = new WeightedGraph();
		
		for (int i = 0; i < N; i++) {
			g.addVertex(i);
		}
		
		for (int i = 0; i < N-1; i++) {
		    String s = reading.nextLine();
		    String[] triplets = s.split("[\\|]");
		    for (String triplet : triplets) {
		    	String[] parts = triplet.split("[,]");
		    	int vertex = Integer.parseInt(parts[0].trim());
		    	int edge = Integer.parseInt(parts[1].trim());
		    	int weight = Integer.parseInt(parts[2].trim());
		    	g.addEdge(vertex, edge, weight);
		    	g.addEdge(edge, vertex, weight);
		    }
		}
		
		g.printWeightedEdges();
		WeightedGraph<Integer>.ShortestPathTree tree = g.getShortestPath(b);
		List<Integer> path = tree.getPath(a);
		System.out.print("A path from " + a + " to " + b + ": ");
		for (Integer s: path) {
		      System.out.print(s + " ");
		}


	}

}
