package christmas.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import christmas.utils.Utils;
import christmas.validation.Validation;

public class Customer {
	
	private static final int MENU_INDEX = 0;
	private static final int COUNT_INDEX = 1;
	
	private String badge;
	private int date;
	private List<String[]> orders;
	
	public void setDate(int date) {
		Validation.validateDateInput(date);
		this.date = date;
	}
	
	public void setOrders(String[] inputs) {
		this.orders = validateOrders(inputs);
	}
	
	public int getDate() {
		return date;
	}
	
	public Map<Menu, Integer> getOrders() {
		Map<Menu, Integer> formattedOrder = new HashMap<>();
		for(String[] inputOrder : orders) {
			Menu orderMenu = Menu.findMenu(inputOrder[MENU_INDEX]);
			formattedOrder.put(orderMenu, Integer.valueOf(inputOrder[COUNT_INDEX]));
		}
		return formattedOrder;
	}
	
	public void setBadge(String badge) {
		this.badge = badge;
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
