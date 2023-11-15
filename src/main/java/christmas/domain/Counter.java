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
	private Map<MenuType, Integer> orderType = new HashMap<>();
	
	public void takeOrders(Map<Menu, Integer> orders) {
		initOrders();
		initOrderType();
		storeOrder(orders);
		
		for(Menu menu : orders.keySet()) {
			storeOrderType(MenuType.findMenuType(menu), orders.get(menu));
		}
		
		validateTakenOrder();
	}
	
	public int howMuchPayment(int discountAmount) {
		return sumTotalAmount() - discountAmount;
	}
	
	public int countDiscountMenu(int date) {
		int count = orderType.get(MenuType.findDiscountableType(Calendar.isWeekday(date)));
		return count;
	}
	
	public boolean isDiscountable() {
		return sumTotalAmount() >= EVENT_CRITERIA;
	}
	
	public boolean isDeserved() {
		return sumTotalAmount() >= PRESENT_CRITERIA;
	}
	
	public int sumTotalAmount() {
		int totalOrderAmount = 0;
		for(Menu menu : orders.keySet()) {
			totalOrderAmount += menu.getPrice() * orders.get(menu);
		}
		return totalOrderAmount;
	}
	
	public Map<Menu, Integer> getOrders() {
		return orders;
	}
	
	private void initOrderType() {
		for(MenuType menuType : MenuType.values()) {
			orderType.put(menuType, ZERO_COUNT);
		}
	}
	
	private void initOrders() {
		orders.clear();
	}
	
	private void storeOrderType(MenuType menuType, int count) {
		int totalCount = orderType.get(menuType) + count;
		orderType.put(menuType, totalCount);
	}
	
	private void storeOrder(Map<Menu, Integer> order) {
		orders.putAll(order);
	}
	
	private void validateTakenOrder() {
		Validation.validateNotOnlyBeverage(findOrderedTypes());
		Validation.validateTotalMenuCount(countOrderedMenu());
	}
	
	private int countOrderedMenu() {
		int count = 0;
		for(Menu menu : orders.keySet()) {
			count += orders.get(menu);
		}
		return count;
	}
	
	private List<MenuType> findOrderedTypes(){
		
		return orderType.entrySet().stream()
				.filter(entry -> entry.getValue() != 0)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
	
}
