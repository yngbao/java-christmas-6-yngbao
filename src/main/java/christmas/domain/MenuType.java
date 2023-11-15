package christmas.domain;

import java.util.stream.Stream;

public enum MenuType {
	
	APPETIZER("애피타이저", new Menu[] {Menu.MUSHROOM_SOUP, Menu.TAPAS, Menu.CAESAR_SALAD}),
	MAIN("메인", new Menu[] {Menu.T_BONE_STEAK, Menu.BBQ_RIBS, Menu.SEAFOOD_PASTA, Menu.X_MAS_PASTA}),
	DESSERT("디저트", new Menu[] {Menu.CHOCO_CAKE, Menu.ICECREAM}),
	BEVERAGE("음료", new Menu[] {Menu.ZERO_COKE, Menu.RED_WINE, Menu.CHAMPAGNE});
	
	private final String viewName;
	private final Menu[] containMenu;
	
	MenuType(String viewName, Menu[] containMenu){
		this.viewName = viewName;
		this.containMenu = containMenu;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public Menu[] getContainMenu() {
		return containMenu;
	}
	
	public static MenuType findMenuType(Menu ordermenu) {
		
		return Stream.of(MenuType.values())
				.filter(type -> hasMenu(type, ordermenu))
				.findAny().get();
	}
	
	public static MenuType findDiscountMenuType(boolean isWeekday) {
		if(isWeekday) {
			return DESSERT;
		}
		return MAIN;
	}
	
	private static boolean hasMenu(MenuType from, Menu ordermenu) {
		
		return Stream.of(from.containMenu)
				.anyMatch(containMenu -> containMenu == ordermenu);
	}

}
