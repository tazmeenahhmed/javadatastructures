/*
 * CIST 004B1 Spring 2024
 * HW Week 13 Problem 1
 * Description: Read a connected graph from  a file and display its minimum spanning tree + its total weight
 * the entire set of files
 * Input: A file containing a connected graph
 * Output: The files minimum spanning tree and its weight
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 04/30/2024
 */

package chapter28;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class HW_MST {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Enter a URL: ");
		Scanner input = new Scanner(System.in);
		String graphFile = input.nextLine();
		
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
		WeightedGraph.MST tree = g.getMinimumSpanningTree();
		System.out.println("Total weight in MST is " + tree.getTotalWeight());
		tree.printTree();


	}

}

/*Output:
 * Enter a URL: 
WeightedGraphSample.txt
The number of vertices is 12
0 (0): (0, 1, 807.0) (0, 3, 1331.0) (0, 5, 2097.0) 
1 (1): (1, 0, 807.0) (1, 2, 381.0) (1, 3, 1267.0) 
2 (2): (2, 1, 381.0) (2, 3, 1015.0) (2, 4, 1663.0) (2, 10, 1435.0) 
3 (3): (3, 0, 1331.0) (3, 1, 1267.0) (3, 2, 1015.0) (3, 4, 599.0) (3, 5, 1003.0) 
4 (4): (4, 2, 1663.0) (4, 3, 599.0) (4, 5, 533.0) (4, 7, 1260.0) (4, 8, 864.0) (4, 10, 496.0) 
5 (5): (5, 0, 2097.0) (5, 3, 1003.0) (5, 4, 533.0) (5, 6, 983.0) (5, 7, 787.0) 
6 (6): (6, 5, 983.0) (6, 7, 214.0) 
7 (7): (7, 4, 1260.0) (7, 5, 787.0) (7, 6, 214.0) (7, 8, 888.0) 
8 (8): (8, 4, 864.0) (8, 7, 888.0) (8, 9, 661.0) (8, 10, 781.0) (8, 11, 810.0) 
9 (9): (9, 8, 661.0) (9, 11, 1187.0) 
10 (10): (10, 2, 1435.0) (10, 4, 496.0) (10, 8, 781.0) (10, 11, 239.0) 
11 (11): (11, 8, 810.0) (11, 9, 1187.0) (11, 10, 239.0) 
Total weight in MST is 6513.0
Root is: 0
Edges: (0, 1) (1, 2) (2, 3) (3, 4) (4, 5) (7, 6) (5, 7) (10, 8) (8, 9) (4, 10) (10, 11) 
*/
