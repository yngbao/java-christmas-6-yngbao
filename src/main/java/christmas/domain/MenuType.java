package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;

public enum MenuType {
	
	APPETIZER("애피타이저", new Menu[] {Menu.MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD}),
	MAIN("메인", new Menu[] {Menu.T_BONE_STEAK, Menu.BBQ_RIBS, Menu.SEAFOOD_PASTA, Menu.X_MAS_PASTA}),
	DESSERT("디저트", new Menu[] {Menu.CHOCO_CAKE, Menu.ICECREAM}),
	BEVERAGE("음료", new Menu[] {Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE});
	
	private final String viewName;
	private final Menu[] containMenu;
	private static final int ZERO_COUNT = 0;
	private static Map<MenuType, Integer> ordersType = new HashMap<>();
	
	MenuType(String viewName, Menu[] containMenu){
		this.viewName = viewName;
		this.containMenu = containMenu;
	}
	
	public void initOrdersType() {
		ordersType.put(APPETIZER, ZERO_COUNT);
		ordersType.put(MAIN, ZERO_COUNT);
		ordersType.put(DESSERT, ZERO_COUNT);
		ordersType.put(BEVERAGE, ZERO_COUNT);
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public Menu[] getContainMenu() {
		return containMenu;
	}
	
	public void storeOrdersType(Menu ordermenu) {
		for(MenuType menuType : MenuType.values()) {
			if(Arrays.asList(menuType.containMenu).contains(ordermenu)) {
				int count = ordersType.get(menuType) + 1;
				ordersType.put(menuType, count);
			}
		}
	}
	
	public MenuType findDiscountMenuType(boolean isWeekday) {
		if(isWeekday) {
			return DESSERT;
		}
		return MAIN;
	}

}
