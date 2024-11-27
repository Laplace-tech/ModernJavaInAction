package chap01;

import java.util.Comparator;

public class Apple implements Comparable<Apple>{

	private int weight;
	private Color color;
	
	public Apple(int weight, Color color) {
		this.weight = weight;
		this.color = color;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public String toString() {
		return String.format("Apple {color = %s, weight = %d}", color, weight);
	}

	@Override
	public int compareTo(Apple o) {
		// 1차 정렬 기준 : Color
		int colorComparison = o.getColor().compareTo(color);

		if (colorComparison != 0) {
			return colorComparison;
		}

		// 2차 정렬 기준 : weight
		return Integer.compare(weight, o.getWeight());
	}

}
