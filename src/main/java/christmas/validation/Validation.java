package christmas.validation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import christmas.domain.Event;
import christmas.domain.Menu;

public class Validation {
	
	private static final int MENU_INDEX = 0;
	private static final int COUNT_INDEX = 1;
	private static final int MIN_ORDER_COUNT = 1;
	private static final int MAX_ORDER_COUNT = 20;
	
	public static void validateDateInput(int date) {
		if (date < Event.FIRST_DATE || date > Event.END_DATE) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void validateMenuInputs(String[] inputs) {
		for(String input : inputs)
		if (!input.matches("\\S+-\\d+")) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void validateOrderMenu(List<String[]> splitedInputs) {  //메뉴판에 없는 메뉴일 경우 예외처리
        for (String[] splitedInput : splitedInputs) {
        	if(!Arrays.stream(Menu.values()).anyMatch(menu -> menu.getViewName() == splitedInput[MENU_INDEX])) {
        		throw new IllegalArgumentException();
        	}
        }
	}
	
	public static void validateMoreThanOneMenu(List<String[]> splitedInputs) {
        for (String[] splitedInput : splitedInputs) {
        	if(Integer.valueOf(splitedInput[COUNT_INDEX]) < MIN_ORDER_COUNT) {
        		throw new IllegalArgumentException();
        	}
        }
	}
	
	public static void validateDistinctOrder(List<String[]> splitedInputs) {
		Set<String> menuInput = new HashSet<>();
		for (String[] splitedInput : splitedInputs) {
			menuInput.add(splitedInput[MENU_INDEX]);
		}
		if(menuInput.size() != splitedInputs.size()) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void validateTotalMenuCount(Map<Menu, Integer> orders) {
		int count = 0;
		for (Menu menu : orders.keySet()) {
			count += orders.get(menu);
		}
		if(count > MAX_ORDER_COUNT) {
			throw new IllegalArgumentException();
		}
	}
	

}
