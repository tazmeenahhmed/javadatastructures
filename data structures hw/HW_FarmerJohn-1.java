/*
 * CIST 004B1 Spring 2024
 * HW Week 13 Problem 3
 * Description: Farmer John wants to know the minimum length of fiber it takes to connect 
 * the entire set of files
 * Input: Number of farms, their distance from each other
 * Output: The minimum length
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 04/30/2024
 */

package chapter28;
import java.util.*;

public class HW_FarmerJohn {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		input.nextLine();
		WeightedGraph g = new WeightedGraph();
		
		for (int i = 0; i < N; i++) {
			g.addVertex(i);
		}
		
		
		for (int i = 0; i < N; i++) {
			String s = input.nextLine();
			String[] neighbors = s.split("\\s+");
			for (int j = 0; j < N; j++) {
				g.addEdge(j, i, Integer.parseInt(neighbors[j]));
			}
		}
		
		//g.printWeightedEdges();
		WeightedGraph.MST tree = g.getMinimumSpanningTree();
		System.out.println((int)tree.getTotalWeight());

	}

}

/*Input:
 * 4
0 4 9 21
4 0 8 17
9 8 0 16
21 17 16 0
 * 
 * 
 * Output:
 * 28
 */
