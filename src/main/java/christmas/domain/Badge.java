package christmas.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum Badge {
	
	NOTHING("없음", 0),
	STAR("별", 5_000),
	TREE("트리", 10_000),
	SANTA("산타", 20_000);
	
	private final String viewName;
	private int standardAmount;

	Badge(String viewName, int standardAmount) {
		this.viewName = viewName;
		this.standardAmount = standardAmount;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	private int getStandardAmount() {
		return standardAmount;
	}
	
	public static String judgeBadge(int amount) {
		Badge badge = Arrays.stream(Badge.values())
				.filter(value -> (value.getStandardAmount() < amount))
				.max(Comparator.comparing(Badge::getStandardAmount))
				.orElse(NOTHING);
		return badge.getViewName();
	}

}
