package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Benefit {
	
	private Map<Event, Integer> benefitResult = new HashMap<>();
	
	public Map<Event, Integer> getBenefitResult(){
		return benefitResult;
	}
	
	public void checkBeneficial(boolean isEnough) {
		if(!isEnough) {
			benefitResult.clear();
		}
	}
	
	public int getTotalBenefitAmount() {
		int amount = 0;
		for(Event discount : benefitResult.keySet()) {
			amount += benefitResult.get(discount);
		}
		return amount;
	}
	
	public int howMuchDiscountAmount() {
		int amount = 0;
		for(Event discount : benefitResult.keySet()) {
			if (discount != Event.PRESENT) {
				amount += benefitResult.get(discount);
			}
		}
		return amount;
	}
	
	public void givePresent(boolean isSatisfied) {
		if (isSatisfied) {
			benefitResult.put(Event.PRESENT, Event.PRESENT.getAmount());
		}
	}
	
	public void discountForChristmas(int date) {
		if(Calendar.isBeforeChristmas(date)) {
			benefitResult.put(Event.X_MAS_DISCOUNT, getDateDiscount(date));
		}
	}
	
	public void discountByDayOfWeek(int date, int menuCount) {
		Event discountType = weeklyDiscountType(Calendar.isWeekday(date));
		benefitResult.put(discountType, discountType.getAmount() * menuCount);
	}
	
	public void discountSpecially(boolean isSpecial) {
		if (isSpecial) {
			benefitResult.put(Event.SPECIAL_DISCOUNT, Event.SPECIAL_DISCOUNT.getAmount());
		}
	}
	
	private Event weeklyDiscountType(boolean isWeekday) {
		if (isWeekday) {
			return Event.WEEKDAY_DISCOUNT;
		}
		return Event.WEEKEND_DISCOUNT;
	}

	private int getDateDiscount(int date) {
		int more = Event.INCREASING.getAmount() * (date - Calendar.FIRST_DATE);
		return Event.X_MAS_DISCOUNT.getAmount() + more;
	}
}
