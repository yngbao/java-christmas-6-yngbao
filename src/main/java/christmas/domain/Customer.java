package christmas.domain;

import java.util.List;

public class Customer {
	
	private final int date;
	private final List<String> orders;
	
	Customer(int date, List<String> orders) {
		this.date = date;
		this.orders = orders;
	}
	
	public int getDate() {
		return date;
	}
	
	public List<String> getOrders() {
		return orders;
	}

}
