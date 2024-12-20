/*
 * CIST 004B1 Spring 2024
 * HW Week 11,12 Problem 3
 * Description: Snakes and Ladders is a game played on a 10X10 board, the goal is to get from square 1 
 * to square 100. On each turn players roll a six-sided dice and move forward a number of squares equal 
 * to the result. If they land on a square that represents a snake's head, they will be transported to 
 * the tail; if they land on the foot of a ladder, they will be transported to the head of the ladder. For 
 * the following board, find the smallest number of turns it takes to win. 
 * Input: n/a
 * Output: Minimum moves it takes to win
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 04/25/2024
 */

package chapter28;
import java.util.*;


class State {
	int square;
	int turn;
	
	public State(int square, int turn) {
		this.square = square;
		this.turn = turn;
	}
	
	public String toString() {
		return "(" + square + "," + turn + ")";
	}
}

public class SnakesAndLadders {

	public static void main(String[] args) {
		
		int[][] s = new int[][] { { 17, 13 }, { 52, 29 }, { 57, 40 }, { 62, 22 }, { 88, 18 }, { 95, 51 }, { 97, 79}, {87, 74}};
        int[][] l = new int[][] { { 3, 21 }, { 8, 30 }, { 28, 84 }, { 58, 77 }, { 75, 86 }, { 80, 100 }, { 90, 91 } };
        HashMap<Integer, Integer> snakes = new HashMap<>(); 
        for (int i = 0; i < s.length; i++)
            snakes.put(s[i][0], s[i][1]); 
        HashMap<Integer, Integer> ladders = new HashMap<>();
        for (int j = 0; j < l.length; j++)
            ladders.put(l[j][0], l[j][1]);
        System.out.println(minTurns(snakes, ladders));
		
	}

	private static int minTurns(HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders) {
		HashMap<Integer, Integer> board = new HashMap<>();
		for (int i = 0; i < 101; i++) {
			board.put(i,  i);
		}
		for (Map.Entry<Integer,Integer> element : snakes.entrySet()) {
			board.put(element.getKey(), element.getValue());
		}
		for (Map.Entry<Integer,Integer> element : ladders.entrySet()) {
			board.put(element.getKey(), element.getValue());
		}
		
		Queue<State> queue = new LinkedList<>();
		HashSet<Integer> visited = new HashSet<>();
		
		int end = 100;
		State firstMove = new State(1, 0);
		
		queue.offer(firstMove);
		
		while(!queue.isEmpty()) {
			State current = queue.poll();
			if (current.square == end) {
				return current.turn;
			}
			for(int i = current.square + 1; i <= current.square + 6; i++) {
				
				if (i > 100) {
					return current.turn + 1;
				}
				
				int boardSquare = board.get(i);
				if(!visited.contains(boardSquare)) {
					State next = new State(boardSquare, current.turn + 1);
					queue.offer(next);
					visited.add(boardSquare);
				}
			}
		}
		
		return -1;
	}
}

/*Output:
 * 6
 * 
 * w. {87, 74}:
 * 5
 */
		
