package christmas.domain;

import java.util.List;

import christmas.validation.Validation;

public class Customer {
	
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
	
	private void validateOrders(List<String[]> orders) {
		Validation.validateOrderMenu(orders);
		Validation.validateMoreThanOneMenu(orders);
		Validation.validateDistinctOrder(orders);
	}

}
