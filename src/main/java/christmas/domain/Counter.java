package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Counter {
	
	private static final int ZERO_COUNT = 0;
	private static final int MIN_AMOUNT_FOR_EVENT = 1_000;
	private static final int PRESENT_CRITERIA = 120_000;
	public static final Menu PRESENT = Menu.CHAMPAGNE;
	private static Map<Menu, Integer> orders = new HashMap<>();
	private static Map<MenuType, Integer> ordersType = new HashMap<>();
	
	private static void initOrdersType() {
		ordersType.put(MenuType.APPETIZER, ZERO_COUNT);
		ordersType.put(MenuType.MAIN, ZERO_COUNT);
		ordersType.put(MenuType.DESSERT, ZERO_COUNT);
		ordersType.put(MenuType.BEVERAGE, ZERO_COUNT);
	}
	
	public static void initOrders() {
		orders.clear();
	}
	
	public static int howMuchForPayment() {
		return getTotalOrderAmount() - Benefit.howMuchDiscountAount();
	}
	
	public static int howManyOrderedMenu() {
		int count = 0;
		for(Menu menu : orders.keySet()) {
			count += orders.get(menu);
		}
		return count;
	}
	
	public static List<MenuType> findOrderedMenuType(){
		return ordersType.entrySet().stream()
				.filter(entry -> entry.getValue() != 0)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
	
	public static void takeOrder(String inputMenu, int count) {
		initOrders();
		initOrdersType();
		Menu orderMenu = Menu.findMenu(inputMenu);
		MenuType orderMenuType = MenuType.findMenuType(orderMenu);
		storeOrder(orderMenu, Integer.valueOf(count));
		storeOrdersType(orderMenuType);
	}
	
	private static void storeOrdersType(MenuType menuType) {
		int count = ordersType.get(menuType) + 1;
		ordersType.put(menuType, count);
	}
	
	private static void storeOrder(Menu menu, int count) {
		orders.put(menu, count);
	}
	
	public static int howManyDiscountMenu(int date) {
		int count = ordersType.get(MenuType.findDiscountMenuType(Calendar.isWeekday(date)));
		return count;
	}
	
	public static boolean isEnoughForEvent() {
		return getTotalOrderAmount() >= MIN_AMOUNT_FOR_EVENT;
	}
	
	public static boolean isSatisfiedForPresent() {
		return getTotalOrderAmount() >= PRESENT_CRITERIA;
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
