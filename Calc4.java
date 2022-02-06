package java_basics;

import java.util.ArrayList;
import java.util.List;

public class Calc4 {
	public static void main (String [] args) {
	
		double a = 1;
		double b = 1;
		double c = 1;
		List <Double> num = new ArrayList<Double>();
		for (a = 1; a <= 1000; a++) {
			for (b = 1; b <= 1000; b++) {
				c = 1/a + 1/b;
				num.add(c);
			}
		}
		System.out.println(num.size());

		double d = 0;
		int i = 0;
		for (i = 0; i < num.size(); i++) {
			d = d + num.get(i);
		}
		System.out.println(d);
	}
}
