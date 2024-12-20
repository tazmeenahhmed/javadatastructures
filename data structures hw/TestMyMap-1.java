/*
 * CIST 004B1 Spring 2024
 * HW Week 10 Problem 1
 * Description: Test the implementation of MyMap
 * Input: inputting entries to the map
 * Output: printing out entries in the map
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 04/22/2024
 */

package chapter27;

public class TestMyMap {
	public static void main(String[] args) {
        MyMap2<Integer, String> map = new MyMap2();
        map.put(2, "Two");
        map.put(3, "Three");
        System.out.println("Entries in map: " + map);
        
        map.put(5, "Five");
        System.out.println("Entries in map: " + map);
        
        map.put(3, "New Three");
        System.out.println("Entries in map: " + map);
        
        map.put(11, "Eleven");
        System.out.println("Entries in map: " + map);

        System.out.println(map.get(2));
        System.out.println(map.get(3));
        System.out.println(map.get(5));
        
        System.out.println("Entries in map: " + map);

        map.put(11, "New Eleven");
        map.put(19, "Nineteen");
        map.remove(3);
        map.put(11, "Latest Eleven");
        System.out.println("Entries in map: " + map);
        
        map.clear();
        System.out.println("Entries in map: " + map);
      }
}

/* Output:
Entries in map: [{2, Two}{3, Three}]
Entries in map: [{5, Five}{2, Two}{3, Three}]
Entries in map: [{5, Five}{2, Two}{3, New Three}]
Entries in map: [{2, Two}{3, New Three}{5, Five}{11, Eleven}]
Two
New Three
Five
Entries in map: [{2, Two}{3, New Three}{5, Five}{11, Eleven}]
Entries in map: [{2, Two}{11, Latest Eleven}{5, Five}{11, New Eleven}{19, Nineteen}]
Entries in map: []
*/
