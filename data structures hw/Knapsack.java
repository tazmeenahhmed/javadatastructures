/*
 * CIST 004B1 Spring 2024
 * HW Week 5 Problem 1
 * Description: You are a thief with a knapsack with a carrying capacity of 
 * KNAPSACK_CAPACITY pounds. You want to stuff as much value into the knapsack as possible.
 * There are NUMBER_ITEMS of items you can pick, weights between MIN_WEIGHT to MAX_WEIGHT and 
 * valued from MIN_VALUE to MAX_VALUE. What is the list of items you will stuff into your 
 * knapsack and what is the total value?
 * Input: none
 * Output: The total value of items added to the knapsack and a list of the items added
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 02/28/2024
 */

package ch22bigo;
import java.util.*;

public class Knapsack {

	public static void main(String[] args) {
		final int NUMBER_ITEMS = 10;
		final int MIN_WEIGHT = 1;
		final int MAX_WEIGHT = 5;
		final int MIN_VALUE = 50;
		final int MAX_VALUE = 100;
		final int KNAPSACK_CAPACITY = 20;
		Item[] items = generateItems(NUMBER_ITEMS, MIN_VALUE, MAX_VALUE, MIN_WEIGHT, MAX_WEIGHT);
		
		// your work
		int[][] cell = new int[NUMBER_ITEMS+1][KNAPSACK_CAPACITY+1];
		
		for (int i = 0; i < NUMBER_ITEMS+1; i++) {
			cell[i][0] = 0;
		}
		for (int j = 0; j < KNAPSACK_CAPACITY+1; j++) {  
			cell[0][j] = 0;
		}
		
		
		for (int i = 1; i < NUMBER_ITEMS+1; i++) {
			for (int j = 1; j < KNAPSACK_CAPACITY+1; j++) {
				
				Item currentItem = items[i-1];
				
				if (currentItem.weight > j) {
					cell[i][j] = cell[i-1][j];
				}
				
				else {
					int prevMax = cell[i-1][j];
					int currentValue = currentItem.value;
					if (currentItem.weight < j) {
						currentValue += cell[i-1][j - currentItem.weight];
					}
					
					cell[i][j] = Math.max(prevMax, currentValue);
				}
				
			}
		}
		
		ArrayList<Item> inSack = new ArrayList<>();
		
		int lbsLength = KNAPSACK_CAPACITY;
		for (int i = NUMBER_ITEMS; i >= 0 && lbsLength > 0; i--) {
			
			Item currentItem = items[i-1];
			
			if (cell[i][lbsLength] != cell[i-1][lbsLength]) {
				inSack.add(currentItem);
				lbsLength -= currentItem.weight;
			}
		}
		
		
		GridEntry results = new GridEntry(cell[NUMBER_ITEMS][KNAPSACK_CAPACITY], inSack);
		System.out.println(results);
		
		
	
	}
	
	static Item[] generateItems(int howMany, int lValue, int hValue, int lWeight, int hWeight) {
		//Item[] results = new Item[howMany];
		//for (int i = 0; i < howMany; i++) {
		//	int value = (int) (Math.random() * (hValue - lValue + 1)) + lValue;
		//	int weight = (int) (Math.random() * (hWeight - lWeight + 1)) + lWeight;
		//	results[i] = new Item("Item" + i, value, weight);
		//}
		
		Item[] results = new Item[10];
        results[0] = new Item("Item0", 91, 1);
        results[1] = new Item("Item1", 67, 2);
        results[2] = new Item("Item2", 97, 4);
        results[3] = new Item("Item3", 62, 4);
        results[4] = new Item("Item4", 86, 5);
        results[5] = new Item("Item5", 74, 1);
        results[6] = new Item("Item6", 98, 5);
        results[7] = new Item("Item7", 55, 3);
        results[8] = new Item("Item8", 60, 2);
        results[9] = new Item("Item9", 53, 4);
        
		return results;
	}

}


/* Output:
* Total Value: $573 Item8:60|2 Item6:98|5 Item5:74|1 Item4:86|5 Item2:97|4 Item1:67|2 Item0:91|1 
*/