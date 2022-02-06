package java_basics;
import java.util.Scanner;

public class Calc1 {
	public static void main (String [] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("enter number 1: ");
		double number1 = input.nextDouble();
		
		System.out.print("enter operator: ");
		String op = input.next();
		
		System.out.print("enter number 2: ");
		double number2 = input.nextDouble();
		
		if (op.equals("+")) {
			System.out.println(number1 + number2);
		} else if (op.equals("-")) {
			System.out.println(number1 - number2);
		} else if (op.equals("*")) {
			System.out.println(number1 * number2);
		} else if (op.equals("/")) {
			System.out.println(number1 / number2);
		} else if (op.equals("^")) {
			System.out.println(Math.pow(number1, number2));
		} else {
			System.out.println("invalid operator");
		}
		
		input.close();
	}

}
