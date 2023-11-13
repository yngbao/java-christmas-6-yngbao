package christmas.domain;

import java.util.List;
import christmas.validation.Validation;

public class Customer {
	
	private static final int MENU_INDEX = 0;
	private static final int COUNT_INDEX = 1;
	private int date;
	private List<String[]> orders;
	private static String badge;
	
	public void setDate(int date) {
		Validation.validateDateInput(date);
		this.date = date;
	}
	
	public int getDate() {
		return date;
	}
	
	public void setOrders(List<String[]> inputOrders) {
		validateOrders(inputOrders);
		this.orders = inputOrders;
	}
	
	public List<String[]> getOrders() {
		return orders;
	}
	
	public static void setBadge(String badge) {
		Customer.badge = badge;
	}
	
	public static String getBadge() {
		return badge;
	}
	
	public void makeOrder() {
		for(String[] order : orders) {
			Counter.takeOrder(order[MENU_INDEX], Integer.valueOf(order[COUNT_INDEX]));
		}
	}
	
	private void validateOrders(List<String[]> orders) {
		Validation.validateOrderMenu(orders);
		Validation.validateMoreThanOneMenu(orders);
		Validation.validateDistinctOrder(orders);
	}

}
