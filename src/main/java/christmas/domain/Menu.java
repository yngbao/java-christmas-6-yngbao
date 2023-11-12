package christmas.domain;

public enum Menu {
	
	MUSHROOM_SOUP("양송이수프", 6_000),
	TAPAS("타파스", 5_500),
	CAESAR_SALAD("시저샐러드", 8_000),
	T_BONE_STEAK("티본스테이크", 55_000),
	BBQ_RIBS("바비큐립", 54_000),
	SEAFOOD_PASTA("해산물파스타", 35_000),
	X_MAS_PASTA("크리스마스파스타", 25_000),
	CHOCO_CAKE("초코케이크", 15_000),
	ICECREAM("아이스크림", 5_000),
	ZERO_COKE("제로콜라", 3_000),
	RED_WINE("레드와인", 60_000),
	CHAMPAGNE("샴페인", 25_000);
	
	private final String viewName;
	private final int price;

	Menu(String viewName, int price) {
		this.viewName = viewName;
		this.price = price;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public int getPrice() {
		return price;
	}
	
	
}
