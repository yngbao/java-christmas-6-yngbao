package christmas.view;

public enum ErrorView {
	
	DATE_ERROR("유효하지 않은 날짜입니다."),
	ORDER_ERROR("유효하지 않은 주문입니다.");
	
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
