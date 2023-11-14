package christmas.domain;

import java.util.List;

import christmas.utils.Utils;
import christmas.validation.Validation;

public class Customer {
	
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
	
	public void setOrders(String[] inputs) {
		this.orders = validateOrders(inputs);
	}
	
	public List<String[]> getOrders() {
		return orders;
	}
	
	public static void setBadge(String badge) {
		Customer.badge = badge;
	}
	
	public String getBadge() {
		return badge;
	}
	
	private List<String[]> validateOrders(String[] inputs) {
		Validation.validateMenuInputs(inputs);
		List<String[]> orders = Utils.splitStringsToList(inputs);
		Validation.validateOrderMenu(orders);
		Validation.validateMoreThanOneMenu(orders);
		Validation.validateDistinctOrder(orders);
		return orders;
	}

}
