package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public enum Benefit {
	
	X_MAS_DISCOUNT("크리스마스 디데이 할인", 1_000),
	INCREASING("일일 할인증가액", 100),
	WEEKDAY_DISCOUNT("평일 할인", 2_023),
	WEEKEND_DISCOUNT("주말 할인", 2_023),
	SPECIAL_DISCOUNT("특별 할인", 1_000),
	PRESENT("증정 이벤트", Counter.PRESENT.getPrice()),
	DEFAULT("초기화", 0);
	
	private final String viewName;
	private final int amount;
	private static Map<Benefit, Integer> discountResult = new HashMap<>();

	Benefit(String viewName, int amount) {
		this.viewName = viewName;
		this.amount = amount;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public static Map<Benefit, Integer> getDiscountResult(){
		return discountResult;
	}
	
	public static void checkBeneficial(boolean isEnough) {
		if(!isEnough) {
			discountResult.clear();
		}
	}
	
	public static void giveBadge() {
		String badge = Badge.judgeBadge(getTotalBenefitAmount());
		Customer.setBadge(badge);
	}
	
	public static int getTotalBenefitAmount() {
		int amount = 0;
		for(Benefit discount : discountResult.keySet()) {
			amount += discountResult.get(discount);
		}
		return amount;
	}
	
	public static int howMuchDiscountAount() {
		int amount = 0;
		for(Benefit discount : discountResult.keySet()) {
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
	
	public static void discountForChristmas(int date) {
		if(Calendar.isBeforeChristmas(date)) {
			discountResult.put(X_MAS_DISCOUNT, getDateDiscount(date));
		}
	}
	
	public static void discountByDayOfWeek(int date) {
		Benefit discountType = weeklyDiscountType(Calendar.isWeekday(date));
		int count = Counter.howManyDiscountMenu(date);
		discountResult.put(discountType, discountType.amount * count);
	}
	
	private static Benefit weeklyDiscountType(boolean isWeekday) {
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
