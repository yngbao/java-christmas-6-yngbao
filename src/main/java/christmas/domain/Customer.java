package christmas.domain;

import java.util.Arrays;
import java.util.List;
import christmas.validation.Validation;

public class Customer {
	
	private static final int MENU_INDEX = 0;
	private static final int COUNT_INDEX = 1;
	private final int date;
	private final List<String[]> orders;
	
	
	Customer(int date, List<String[]> orders) {
		validateOrders(orders);
		this.date = date;
		this.orders = orders;
	}
	
	public int getDate() {
		return date;
	}
	
	public List<String[]> getOrders() {
		return orders;
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
