package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public enum Discount {
	
	X_MAS_DISCOUNT("크리스마스 디데이 할인", 1_000),
	INCREASING("일일 할인증가액", 100),
	WEEKDAY_DISCOUNT("평일 할인", 2_023),
	WEEKEND_DISCOUNT("주말 할인", 2_023),
	SPECIAL_DISCOUNT("특별 할인", 1_000),
	PRESENT_AMOUNT("증정 이벤트", Event.PRESENT.getPrice()),
	PRESENT_CRITERIA("증정 기준", 120_000),
	DEFAULT("초기화", 0);
	
	private final String viewName;
	private final int amount;
	private static Map<Discount, Integer> discountResult = new HashMap<>();

	Discount(String viewName, int amount) {
		this.viewName = viewName;
		this.amount = amount;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public static Map<Discount, Integer> getDiscountResult(){
		return discountResult;
	}
	
	public static void discountByDate(int date) {
		discountResult.put(X_MAS_DISCOUNT, getDateDiscount(date));
	}
	
	public static void getWeekdayDiscount(int count) {
		discountResult.put(WEEKDAY_DISCOUNT, WEEKDAY_DISCOUNT.getAmount() * count);
	}
	
	public static void getWeekendDiscount(int count) {
		discountResult.put(WEEKEND_DISCOUNT, WEEKEND_DISCOUNT.getAmount() * count);
	}
	
	public static void discountSpecially(boolean isSpecial) {
		if (isSpecial) {
			discountResult.put(SPECIAL_DISCOUNT, SPECIAL_DISCOUNT.getAmount());
		}
	}

	private static int getDateDiscount(int date) {
		int more = INCREASING.getAmount() * (date - Event.FIRST_DATE);
		return X_MAS_DISCOUNT.getAmount() + more;
	}

}
