package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public enum Discount {
	
	X_MAS_DISCOUNT("크리스마스 디데이 할인", 1_000),
	INCREASING("일일 할인증가액", 100),
	WEEKDAY_DISCOUNT("평일 할인", 2_023),
	WEEKEND_DISCOUNT("주말 할인", 2_023),
	SPECIAL_DISCOUNT("특별 할인", 1_000),
	PRESENT("증정 이벤트", Counter.PRESENT.getPrice()),
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
	
	public static int getTotalBenefitAmount() {
		int amount = 0;
		for(Discount discount : discountResult.keySet()) {
			amount += discountResult.get(discount);
		}
		return amount;
	}
	
	public static int howMuchDiscountAount() {
		int amount = 0;
		for(Discount discount : discountResult.keySet()) {
			if (discount != PRESENT) {
				amount += discountResult.get(discount);
			}
		}
		return amount;
	}
	
	public static void givePresent(boolean isSatisfied) {
		if (isSatisfied) {
			discountResult.put(PRESENT, PRESENT.getAmount());
		}
	}
	
	public static void discountByDate(int date) {
		discountResult.put(X_MAS_DISCOUNT, getDateDiscount(date));
	}
	
	public static void discountByDayOfWeek(int date) {
		Discount discountType = weeklyDiscountType(Calendar.isWeekday(date));
		int count = Counter.howManyDiscountMenu(date);
		discountResult.put(discountType, discountType.amount * count);
	}
	
	public static Discount weeklyDiscountType(boolean isWeekday) {
		if (isWeekday) {
			return WEEKDAY_DISCOUNT;
		}
		return WEEKEND_DISCOUNT;
	}
	
	public static void discountSpecially(boolean isSpecial) {
		if (isSpecial) {
			discountResult.put(SPECIAL_DISCOUNT, SPECIAL_DISCOUNT.getAmount());
		}
	}

	private static int getDateDiscount(int date) {
		int more = INCREASING.getAmount() * (date - Calendar.FIRST_DATE);
		return X_MAS_DISCOUNT.getAmount() + more;
	}

}
