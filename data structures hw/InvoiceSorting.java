/*
 * CIST 004B1 Spring 2024
 * HW Week 14 Problem 1
 * Description: Manipulate a Steam<Invoice>. Sort objects by parts description, by price,
 * map each invoice to its partsdescription and to its quantity and sort it, map 
 * each invoice to its partsdescription and the value of the invoice and order result 
 * by value, modify d to select invoice in value range of $200 - $500
 * Input: n/a
 * Output: n/a
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 05/9/2024
 */

package chapter30;
import java.util.*;

public class InvoiceSorting {

	public static void main(String[] args) {
		
		ArrayList<Invoice> invoices = new ArrayList<>();
		invoices.add(new Invoice(83, "Electric sander", 7, 57.98));
		invoices.add(new Invoice(24, "Power saw", 18, 99.99));
		invoices.add(new Invoice(7, "Sledge hammer", 11, 21.50));
		invoices.add(new Invoice(77, "Hammer", 76, 11.99));
		invoices.add(new Invoice(39, "Lawn mower", 3, 79.50));
		invoices.add(new Invoice(68, "Screwdriver", 106, 6.99));
		invoices.add(new Invoice(56, "Jig saw", 21, 11.00));
		invoices.add(new Invoice(3, "Wrench", 34, 7.50));
		
		System.out.println("Sorted by parts description: ");
		invoices.stream().sorted(Comparator.comparing(Invoice::getPartDescription))
			.forEach(e -> System.out.println(e.toString()));
		
		System.out.println();
		
		System.out.println("Sorted by price: ");
		invoices.stream().sorted(Comparator.comparing(Invoice::getPrice))
			.forEach(e -> System.out.println(e.toString()));
		
		System.out.println();
		
		System.out.println("Mapped to part description & quantity, sorted by quantity: ");
		invoices.stream().sorted(Comparator.comparing(Invoice::getQuantity))
			.map(e -> "Description: " + e.getPartDescription() + " | Quantity: " + e.getQuantity())
			.forEach(System.out::println);
		
		System.out.println();
		
		System.out.println("Mapped to its Parts Description and the value of its invoice: ");
		invoices.stream().sorted(Comparator.comparing(e -> e.getPrice() * e.getQuantity()))
			.map(e -> "Description: " + e.getPartDescription() + " | Value: " + 
			e.getQuantity() * e.getPrice()).forEach(System.out::println);
		
		System.out.println();
		
		System.out.println("Values in range of $200 to $500");
		invoices.stream().sorted(Comparator.comparing(e -> e.getPrice() * e.getQuantity()))
			.filter(e -> e.getPrice() * e.getQuantity() < 500.00 && 
			e.getPrice() * e.getQuantity() > 200.00)
			.map(e -> "Description: " + e.getPartDescription() + " | Value: " + 
			e.getQuantity() * e.getPrice()).forEach(System.out::println);

	}

}

/*Output
 * 
 * 
 */
