package chap03;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chap01.Apple;
import chap01.Color;
import chap01.FilteringApples;

public class Lambdas {

	public static void main(String[] args) {
		
		List<Apple> inventory = FilteringApples.generateDataExample();
		inventory.forEach(System.out::println);
		
		System.out.println("*********** Green Apples **********");
		List<Apple> greenApples = FilteringApples.filter(inventory, FilteringApples::isGreenApple);
		greenApples.forEach(System.out::println);
		
		System.out.println("*********** Sorted Apples **********");
		Comparator<Apple> fuckingComparator = (a1, a2) -> {
			 // 첫 번째 기준: RED가 GREEN보다 우선
			int colorComparison = a1.getColor().compareTo(a2.getColor());
			if(colorComparison != 0) { // colorComparison != 0이면, a1과 a2의 색깔이 다르다는 뜻
				return a1.getColor() == Color.RED ? -1 : 1;
			}
			
			// 두 번째 기준 : 무게 오름차순 정렬
			return Integer.compare(a1.getWeight(), a2.getWeight());
		};
		
		Comparator<Apple> realCompatator = Comparator
				.comparing((Apple a) -> a.getColor() == Color.RED ? 0 : 1)
				.thenComparingInt(Apple::getWeight);
		
//		1. inventory.sort(Comparator<? super Apple> comparator);
//			ㄴ> inventory.sort(realCompatator);
		
// 		class Apple implements Comparable<Apple> { ... } ㄱ
//													      l
//														  V
//		2. List<Apple> sortedApples = inventory.stream().sorted().collect(Collectors.toList()); 
//		3. List<Apple> sortedApples = inventory.stream().sorted(realCompatator).collect(Collectors.toList());
		
		inventory.sort(Comparator.comparing((Apple a) -> a.getColor() == Color.RED ? 0 : 1)
				.thenComparing(apple -> apple.getWeight()));
		
		inventory.forEach(System.out::println);
	}
	
}
