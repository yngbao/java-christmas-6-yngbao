package christmas.domain;

public enum Event {
	
	X_MAS_DISCOUNT("크리스마스 디데이 할인", 1_000),
	INCREASING("일일 할인증가액", 100),
	WEEKDAY_DISCOUNT("평일 할인", 2_023),
	WEEKEND_DISCOUNT("주말 할인", 2_023),
	SPECIAL_DISCOUNT("특별 할인", 1_000),
	PRESENT("증정 이벤트", Counter.PRESENT.getPrice()),
	DEFAULT("초기화", 0);
	
	private final String viewName;
	private final int amount;

	Event(String viewName, int amount) {
		this.viewName = viewName;
		this.amount = amount;
	}
	
	public String getViewName() {
		return viewName;
	}
	
	public int getAmount() {
		return amount;
	}
	

}
