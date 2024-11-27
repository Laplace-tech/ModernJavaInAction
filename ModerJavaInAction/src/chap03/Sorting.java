package chap03;

import java.util.Comparator;
import java.util.List;

import chap01.Apple;
import chap01.FilteringApples;

public class Sorting {

	public static void main(String... args) {
		
		List<Apple> inventory = FilteringApples.generateDataExample();
		
		Comparator<Apple> comparingByWeight = (a1, a2) -> a1.getWeight() - a2.getWeight();
		Comparator<Apple> comparingByColor = (a1, a2) -> a1.getColor().compareTo(a2.getColor());
//		Enum : compareTo(Enum e) =>  return self.ordinal - other.ordinal;
//		 										ㄴ> a1 	   -    a2
		Comparator<Apple> comparingByColorThenWeight = Comparator
				.comparing((Apple a) -> a.getColor())
				.thenComparing(comparingByWeight);

		inventory.sort(comparingByColorThenWeight);
		inventory.forEach(System.out::println);
		
	}

}
