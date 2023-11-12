package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Counter {
	
	private static int totalOrderAmount;
	private static int discountAmount;
	
	private static Map<Menu, Integer> orders = new HashMap<>();
	
	public static void storeOrder(Menu menu, int count) {
		orders.put(menu, count);
	}
	
	public static int getTotalOrderAmount() {
		totalOrderAmount = 0;
		for(Menu menu : orders.keySet()) {
			totalOrderAmount += menu.getPrice() * orders.get(menu);
		}
		return totalOrderAmount;
	}
}
