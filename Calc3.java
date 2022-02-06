package java_basics;

import java.util.Scanner;

public class Calc3 {
	public static void main (String [] args) {
		Scanner input = new Scanner(System.in);
		
		int incorr_count = 0;
		int corr_count = 0;
		
		for (int i = 2; i <= 10; i++) {
			for (int j = 2; j <= 10; j++) {
				System.out.print(i + " x " + j + " = ");
				int res = input.nextInt();
				
				if (i * j == res) {
					System.out.println("correct");
					corr_count++;
				} else {
					System.out.println("incorrect");
					System.out.println(i + " x " + j + " = " + (i * j));
					incorr_count++;
				}
			}
		}
		
		System.out.println("correct: " + corr_count);
		System.out.println("incorrect: " + incorr_count);
		
		input.close();
	}
}
