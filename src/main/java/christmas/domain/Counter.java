package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Counter {
	
	private static Map<Menu, Integer> orders = new HashMap<>();
	
	public static void storeOrder(Menu menu, int count) {
		orders.put(menu, count);
	}
	
	public static int getTotalOrderAmount() {
		int totalOrderAmount = 0;
		for(Menu menu : orders.keySet()) {
			totalOrderAmount += menu.getPrice() * orders.get(menu);
		}
		return totalOrderAmount;
	}
	
	public static Map<Menu, Integer> getOrders() {
		return orders;
	}
	
	
}
