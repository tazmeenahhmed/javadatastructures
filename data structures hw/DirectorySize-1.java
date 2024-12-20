/*
 * CIST 004B1 Spring 2024
 * HW Week 3 Problem 2
 * Description: Rewrite a recursive method to find directory size. Use queue.
 * Input: A file or directory
 * Output: The amount of bytes in the directory or file
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 02/15/2024
 */

package ch20datastructures;
import java.io.File;
import java.util.Comparator;
import java.util.Scanner;

public class DirectorySize {

	public static void main(String[] args) {
		
		System.out.print("Enter a directory or a file: ");
		Scanner input = new Scanner(System.in);
		String directory = input.nextLine();
		
		System.out.println(getSize(new File(directory)) + " bytes");

	}
	
	
	public static long getSize(File file){
		long size = 0; 
		java.util.Queue<File> queue = new java.util.LinkedList<>();
		queue.offer(file); 
		
		while (queue.size() > 0) {
			File t = queue.poll();
			if (t.isFile()) {
				size += t.length();
			} else {
				File[] files = file.listFiles();
				for (int i = 0; files != null && i < files.length; i++) {
					queue.offer(files[i]);
				}
			}
		}
		return size;
	}
	
	/*
	 * output:
	 * Enter a directory or a file: overlord
	 * 78 bytes
	 */

}
