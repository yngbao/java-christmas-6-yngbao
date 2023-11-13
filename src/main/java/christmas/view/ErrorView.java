package christmas.view;

import christmas.validation.Validation;

public enum ErrorView {
	
	DATE_ERROR("유효하지 않은 날짜입니다."),
	ORDER_ERROR("유효하지 않은 주문입니다."),
	ONLY_BEVERAGE("음료만 주문 시, 주문할 수 없습니다."),
	TOO_MUCH("메뉴는 한 번에 최대 "+Validation.MAX_ORDER_COUNT+"개까지만 주문할 수 있습니다.");
	
	private final String errorMessage;
	private static final String ERROR_INTRO = "[ERROR] ";
	private static final String RETRY_MESSAGE = " 다시 입력해 주세요.";

	ErrorView(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getMessage() {
		return ERROR_INTRO + errorMessage + RETRY_MESSAGE;
	}

}
