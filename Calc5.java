package java_basics;

import java.util.ArrayList;
import java.util.List;

public class Calc5 {
	public static void main (String [] args) {
		
		double a = 1;
		double b = Math.E;
		double c = 0;
		double min = Math.pow(10, -8);
		List <Double> num = new ArrayList<Double>();
		for (a = 1; a <= 100; a++) {
			c = Math.pow(b, -a);
			num.add(c);
			if (c <= min) {
				a = 101;
				System.out.println(num.size());
			}	
		}

		double sum = 0;
		int i = 0;
		for (i = 0; i < num.size(); i++) {
			sum = sum + num.get(i);	
		}
		System.out.println(sum);
	}

}
