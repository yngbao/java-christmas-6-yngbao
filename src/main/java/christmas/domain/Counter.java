package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Counter {
	
	private static final int ZERO_COUNT = 0;
	private static final int MIN_AMOUNT_FOR_EVENT = 10_000;
	private static final int PRESENT_CRITERIA = 120_000;
	public static final Menu PRESENT = Menu.CHAMPAGNE;
	private Map<Menu, Integer> orders = new HashMap<>();
	private Map<MenuType, Integer> ordersType = new HashMap<>();
	
	public void initOrdersType() {
		ordersType.put(MenuType.APPETIZER, ZERO_COUNT);
		ordersType.put(MenuType.MAIN, ZERO_COUNT);
		ordersType.put(MenuType.DESSERT, ZERO_COUNT);
		ordersType.put(MenuType.BEVERAGE, ZERO_COUNT);
	}
	
	public void initOrders() {
		orders.clear();
	}
	
	public int howMuchForPayment() {
		return getTotalOrderAmount() - Benefit.howMuchDiscountAount();
	}
	
	public int howManyOrderedMenu() {
		int count = 0;
		for(Menu menu : orders.keySet()) {
			count += orders.get(menu);
		}
		return count;
	}
	
	public List<MenuType> findOrderedMenuType(){
		return ordersType.entrySet().stream()
				.filter(entry -> entry.getValue() != 0)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
	
	public void takeOrders(Map<Menu, Integer> orders) {
		storeOrder(orders);
		for(Menu menu : orders.keySet()) {
			storeOrderType(MenuType.findMenuType(menu), orders.get(menu));
		}
	}
	
	public void storeOrderType(MenuType menuType, int count) {
		int totalCount = ordersType.get(menuType) + count;
		ordersType.put(menuType, totalCount);
	}
	
	public void storeOrder(Map<Menu, Integer> order) {
		orders.putAll(order);
	}
	
	public int howManyDiscountMenu(int date) {
		int count = ordersType.get(MenuType.findDiscountMenuType(Calendar.isWeekday(date)));
		return count;
	}
	
	public boolean isEnoughForEvent() {
		return getTotalOrderAmount() >= MIN_AMOUNT_FOR_EVENT;
	}
	
	public boolean isSatisfiedForPresent() {
		return getTotalOrderAmount() >= PRESENT_CRITERIA;
	}
	
	public int getTotalOrderAmount() {
		int totalOrderAmount = 0;
		for(Menu menu : orders.keySet()) {
			totalOrderAmount += menu.getPrice() * orders.get(menu);
		}
		return totalOrderAmount;
	}
	
	public Map<Menu, Integer> getOrders() {
		return orders;
	}
	
	
}
