/*
 * CIST 004B1 Spring 2024
 * HW Week 11,12 Problem 2
 * Description: Bajtek is learning to skate on ice. He's a beginner so his only mode of transportation is 
 * pushing off from a snow drift to the north, east, south, or west and sliding until he lands in another snow 
 * drift. He has noticed that in this way it's impossible to get from some snow drifts to some other by any
 * sequence of moves. He now wants to heap up some additional snow drifts so that he can get from any 
 * snow drift to any other one. He asked you to find the minimal number of snow drifts needed to be created. 
 * Input: N for n number of lines and then coordinates per line
 * Output: Minimum snow drifts needed
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 04/26/2024
 */

package chapter28;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class IceSkating {

	public static void main(String[] args) throws IOException {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			ArrayList<Node> vertices = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st2 = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st2.nextToken());
				int y = Integer.parseInt(st2.nextToken());
				Node dest = new Node(y,x);
				vertices.add(dest);
			}
			
			HashMap<Node, HashSet<Node>> graph = new HashMap<>();
			for (int i = 0; i < N; i++) {
				Node coordinate = vertices.get(i);
				graph.put(coordinate, new HashSet<>());
				int x = coordinate.x;
				int y = coordinate.y;
				for (int j = i+1; j < N; j++) {
					Node adjacent = vertices.get(j);
					int x2 = adjacent.x;
					int y2 = adjacent.y;
					if (x == x2 || y == y2) {
				        graph.get(coordinate).add(adjacent);
					}
				}
			}
			
			HashSet<Node> visited = new HashSet<>();
			int count = 0;
			for (Node key : graph.keySet()) {
				if (!visited.contains(key)) {
					dfs(graph, visited, key);
					count++;
				}
			}
			
			System.out.println(count-1);
	}
	
	static void dfs (HashMap<Node, HashSet<Node>> graph, HashSet<Node> visited, Node node) {
		Stack<Node> stack = new Stack<>();
		visited.add(node);
		stack.push(node);
		
		while (!stack.isEmpty()) {
			Node current = stack.pop();
			for (Node adjacent : graph.get(current)) {
				if(!visited.contains(adjacent)){
					stack.push(adjacent);
					visited.add(adjacent);
				}
			}
		}
	}

	
	static class Node {
		int y; 
		int x;
		
		Node (int y, int x){
			this.x = x;
			this.y = y;
		}
		
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}

}

/*Output:
 * 2
 * 1 2 
 * 2 1
 * 
 * 1 (<- output for input)
 * 
 * 2 
 * 2 1
 * 4 1
 * 
 * 0 (<- output for input)
 */
