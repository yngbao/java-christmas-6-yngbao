package christmas.domain;

public enum Discount {
	
	BASIC_DISCOUNT(1_000),
	INCREASING(100),
	ANNUAL_DISCOUNT(2_023),
	SPECIAL_DISCOUNT(1_000),
	PRESENT_CRITERIA(120_000);
	
	private int amount;

	Discount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public static int getDateDiscount(int date) {
		int more = INCREASING.getAmount() * (date - Event.FIRST_DATE);
		return BASIC_DISCOUNT.getAmount() + more;
	}

}
