package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Benefit {
	
	private Map<Event, Integer> discountResult = new HashMap<>();
	
	public Map<Event, Integer> getDiscountResult(){
		return discountResult;
	}
	
	public void checkBeneficial(boolean isEnough) {
		if(!isEnough) {
			discountResult.clear();
		}
	}

	public void giveBadge() {
		String badge = Badge.judgeBadge(getTotalBenefitAmount());
		Customer.setBadge(badge);
	}
	
	public int getTotalBenefitAmount() {
		int amount = 0;
		for(Event discount : discountResult.keySet()) {
			amount += discountResult.get(discount);
		}
		return amount;
	}
	
	public int howMuchDiscountAount() {
		int amount = 0;
		for(Event discount : discountResult.keySet()) {
			if (discount != Event.PRESENT) {
				amount += discountResult.get(discount);
			}
		}
		return amount;
	}
	
	public void givePresent(boolean isSatisfied) {
		if (isSatisfied) {
			discountResult.put(Event.PRESENT, Event.PRESENT.getAmount());
		}
	}
	
	public void discountForChristmas(int date) {
		if(Calendar.isBeforeChristmas(date)) {
			discountResult.put(Event.X_MAS_DISCOUNT, getDateDiscount(date));
		}
	}
	
	public void discountByDayOfWeek(int date, int menuCount) {
		Event discountType = weeklyDiscountType(Calendar.isWeekday(date));
		int count = menuCount;
		discountResult.put(discountType, discountType.getAmount() * count);
	}
	
	private Event weeklyDiscountType(boolean isWeekday) {
		if (isWeekday) {
			return Event.WEEKDAY_DISCOUNT;
		}
		return Event.WEEKEND_DISCOUNT;
	}
	
	public void discountSpecially(boolean isSpecial) {
		if (isSpecial) {
			discountResult.put(Event.SPECIAL_DISCOUNT, Event.SPECIAL_DISCOUNT.getAmount());
		}
	}

	private int getDateDiscount(int date) {
		int more = Event.INCREASING.getAmount() * (date - Calendar.FIRST_DATE);
		return Event.X_MAS_DISCOUNT.getAmount() + more;
	}
}
