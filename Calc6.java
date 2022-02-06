package java_basics;

import java.util.ArrayList;
import java.util.List;

public class Calc6 {
	public static void main (String [] args) {
		
		double a = 1;
		double c = 1;
		double min = Math.pow(10, -8);
		List <Double> num = new ArrayList<Double>();
		while (c >= min) {
			c = Math.exp(-a);
			num.add(c);
			a++;
		}
		System.out.println(num.size());
		double sum = 0;
		int i = 0;
		for (i = 0; i < num.size(); i++) {
			sum = sum + num.get(i);	
		}
		System.out.println(sum);
	}

}
