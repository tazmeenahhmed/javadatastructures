/*
 * CIST 004B1 Spring 2024
 * HW Week 11,12 Problem 1
 * Description: Test whether a graph is connected using files
 * Input: A file or url
 * Output: Number of vertices, the vertices edges, and if the graph is connected
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 04/25/2024
 */

package chapter28;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class IsConnected {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Enter a URL: ");
		Scanner input = new Scanner(System.in);
		String graphFile = input.nextLine();
		
		java.io.File file = new java.io.File(graphFile);
		Scanner reading = new Scanner(file);
		int N = Integer.parseInt(reading.nextLine());
		System.out.println("The number of vertices is " + N);
			
		UnweightedGraph g = new UnweightedGraph();
		
		for (int i = 0; i < N; i++) {
			g.addVertex(i);
		}
		for (int i = 0; i < N; i++) {
			String s = reading.nextLine();
			for(String single : s.split("\\s+")) {
				if (Integer.parseInt(single) != i) {
					g.addEdge(i, Integer.parseInt(single));
				}
			} 
		}
		
		g.printEdges();
		
		UnweightedGraph<String>.SearchTree dfs = g.dfs(g.getIndex(0)); 
		int dfsVertice = dfs.getNumberOfVerticesFound();
		if (dfsVertice == N) {
			System.out.println("The graph is connected.");
		} else {
			System.out.println("The graph is not connected.");
		}
		
	}

}

/* Output:
Enter a URL: 
GraphSample1.txt
The number of vertices is 6
0 (0): (0, 1) (0, 2) 
1 (1): (1, 0) (1, 3) 
2 (2): (2, 0) (2, 3) (2, 4) 
3 (3): (3, 1) (3, 2) (3, 4) (3, 5) 
4 (4): (4, 2) (4, 3) (4, 5) 
5 (5): (5, 3) (5, 4) 
The graph is connected.
*/
