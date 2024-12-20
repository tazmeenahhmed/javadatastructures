 /* 
  * CIST 004B1 Spring 2024
 * HW Week 2 Problem 2
 * Description: To the MethodRefDemo2.java program, add a new method to MyIntNum 
 * called hasCommonFactor(). Have it return true if its argument and the value 
 * stored in the invoking MyIntNum object have at least one factor in common. 
 * For example, 9 and 12 have a common factor (other than 1), which is 3, but 9 
 * and 16 have no common factor. Demonstrate hasCommonFactor() via a method reference.
 * Input: A number to check if it has a factor/common factor with another number
 * Output: If the input number has a factor/common factor with another number
 * Student: Tazmeen Ahmed
 * Known bugs: none
 * Date: 02/12/2024
 */


package ch19lambda;

interface IntPredicate {
	boolean test(int n);
}

class MyIntNum{
	private int v;
	
	MyIntNum(int x) {
		v = x;
	}
	
	int getNum() {
		return v;
	}
	
	// Return true if n is a factor of v.
	boolean isFactor(int n) {
		return (v % n) == 0;
	}
	
	boolean hasCommonFactor(int n) {
		int least = Math.min(n, v);
		for (int i = 2; i <= least; i++) {
			if ((v % i == 0) && (n % i == 0)) {
				return true;
			}
		}
		
		return false;

	}
}

public class HomeworkTwo {

	public static void main(String[] args) {
		
		boolean result;
		
		MyIntNum myNum = new MyIntNum(12);
		MyIntNum myNum2 = new MyIntNum(16);
		
		// Here, a method reference to isFactor on myNum is created.
		IntPredicate ip = myNum::isFactor;
		
		// Now, it is used to call isFactor() via test().
		result = ip.test(3);
		if (result) {
			System.out.println("3 is a factor of " + myNum.getNum());
		}
		
		// This time, a method reference to isFactor on myNum2 is created
		// and used to call isFactor() via test()
		ip = myNum2::isFactor;
		result = ip.test(3);
		if (!result) {
			System.out.println("3 is not a factor of " + myNum2.getNum());
		}
		
		// your work
		
		IntPredicate num = myNum::hasCommonFactor;

		result = num.test(9);
		if (result) {
			System.out.println("9 has a common factor with " + myNum.getNum());
		}
		
		num =  myNum2::hasCommonFactor;
		result = num.test(9);
		if (!result) {
			System.out.println("9 does not have a common factor with " + myNum2.getNum());
		}

	}

}

/*
 * output:
 * 3 is a factor of 12
 * 3 is not a factor of 16
 * 9 has a common factor with 12
 * 9 does not have a common factor with 16
 */
