package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import christmas.validation.Validation;

public class Counter {
	
	private static final int ZERO_COUNT = 0;
	private static final int EVENT_CRITERIA = 10_000;
	private static final int PRESENT_CRITERIA = 120_000;
	public static final Menu PRESENT = Menu.CHAMPAGNE;
	
	private Map<Menu, Integer> orders = new HashMap<>();
	private Map<MenuType, Integer> ordersType = new HashMap<>();
	
	public void takeOrders(Map<Menu, Integer> orders) {
		initOrders();
		initOrdersType();
		storeOrder(orders);
		
		for(Menu menu : orders.keySet()) {
			storeOrderType(MenuType.findMenuType(menu), orders.get(menu));
		}
		
		validateTakenOrder();
	}
	
	public int howMuchForPayment(int discountAmount) {
		return getTotalOrderAmount() - discountAmount;
	}
	
	public int howManyDiscountMenu(int date) {
		int count = ordersType.get(MenuType.findDiscountMenuType(Calendar.isWeekday(date)));
		return count;
	}
	
	public boolean isEnoughForEvent() {
		return getTotalOrderAmount() >= EVENT_CRITERIA;
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
	
	private void initOrdersType() {
		for(MenuType menuType : MenuType.values()) {
			ordersType.put(menuType, ZERO_COUNT);
		}
	}
	
	private void initOrders() {
		orders.clear();
	}
	
	private void storeOrderType(MenuType menuType, int count) {
		int totalCount = ordersType.get(menuType) + count;
		ordersType.put(menuType, totalCount);
	}
	
	private void storeOrder(Map<Menu, Integer> order) {
		orders.putAll(order);
	}
	
	private void validateTakenOrder() {
		Validation.validateNotOnlyBeverage(findOrderedMenuType());
		Validation.validateTotalMenuCount(howManyOrderedMenu());
	}
	
	private int howManyOrderedMenu() {
		int count = 0;
		for(Menu menu : orders.keySet()) {
			count += orders.get(menu);
		}
		return count;
	}
	
	private List<MenuType> findOrderedMenuType(){
		
		return ordersType.entrySet().stream()
				.filter(entry -> entry.getValue() != 0)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
	
}
