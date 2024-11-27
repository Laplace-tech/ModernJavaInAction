package chap01;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FilteringApples {

	public static boolean isGreenApple(Apple apple) {
		return apple.getColor() == Color.GREEN;
	}

	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight() > 150;
	}

	public static <T> List<T> filter(Collection<T> collection, Predicate<T> predicate) {
		return collection.stream()
				.filter(predicate)
				.collect(Collectors.toList());
	}

	public static List<Apple> generateDataExample() {
		List<Apple> inventory = IntStream.rangeClosed(0, 9)
				.mapToObj(integer -> new Apple((int)(50 + Math.random() * 200), 
						Math.random() > 0.5 ? Color.GREEN : Color.RED))
//				.toList(); 수정 불가능한 ArrayList를 반환
				.collect(Collectors.toList()); //  수정 가능한 ArrayList 반환
		
		Collections.shuffle(inventory);
		return inventory;
	}	
	
	public static void main(String[] args) {
		
		List<Apple> inventory = generateDataExample();
		
		System.out.println("*************** HEAVY APPLE SET ***************");
		List<Apple> heavyAppleList = filter(inventory, FilteringApples::isHeavyApple);
		heavyAppleList.stream().forEach(System.out::println);
		
		System.out.println("*************** GREEN APPLE SET ***************");
		List<Apple> greenAppleList = filter(inventory, FilteringApples::isGreenApple);
		greenAppleList.stream().forEach(System.out::println);
		
		System.out.println("*************** GREEN & Heavy APPLE SET ***************");
		Predicate<Apple> predicate = apple -> apple.getColor() == Color.GREEN && apple.getWeight() > 180;
		List<Apple> greenAndHeavyAppleList = filter(inventory, predicate);
		greenAndHeavyAppleList.forEach(System.out::println);
	}
	
}
