package java_basics;
import java.util.Scanner;

public class Calc2 {
	public static void main (String [] args) {
		Scanner input = new Scanner(System.in);
		
		int i = 2;
		int j = 2;
		int incorr_count = 0;
		int corr_count = 0;
		
		do {
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
			i++;
			if (i == 11) {
				i = 2;
				j++;
			}	
		} while (i <= 11 && j <= 10);
		
		
		System.out.println("correct: " + corr_count);
		System.out.println("incorrect: " + incorr_count);
		
		input.close();
	}

}
