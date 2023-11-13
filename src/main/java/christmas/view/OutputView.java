package christmas.view;

import java.text.DecimalFormat;
import java.util.Map;

import christmas.domain.Calendar;
import christmas.domain.Counter;
import christmas.domain.Benefit;
import christmas.domain.Menu;

public class OutputView {
	
	static DecimalFormat formatter = new DecimalFormat("###,###원");
	private static final int PRESENT_COUNT = 1;
	private static final String NOTHING = "없음";
	
	public static void printHello() {
		System.out.printf("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n", Calendar.EVENT_MONTH);
	}
	
	public static void printPreviewIntro(int date) {
		System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", Calendar.EVENT_MONTH, date);
	}
	
	public static void printOrders(Map<Menu, Integer> orders) {
		System.out.println("\n<주문 메뉴>");
		for(Menu menu : orders.keySet()) {
			System.out.println(menu.getViewName()+" "+orders.get(menu)+"개");
		}
	}
	
	public static void printTotalOrderAmount(int amount) {
		System.out.println("\n<할인 전 총주문 금액>\n"
				+formatter.format(amount));
	}
	
	public static void printPresentMenu(boolean isSatisfied) {
		System.out.println("\n<증정 메뉴>\n" + presentStatus(isSatisfied));
	}
	
	public static void printBenefitDetails() {
		System.out.println("\n<혜택 내역>");
		for(Benefit discount : Benefit.getDiscountResult().keySet()) {
			System.out.println(discount.getViewName()+": -"+formatter.format(discount.getAmount()));
		}
		if (Benefit.getDiscountResult().size() == 0 ) {
			System.out.println("없음");
		}
	}
	
	public static void printTotalDiscountAmount() {
		System.out.println("\n<총혜택 금액>\n"
				+"-"+formatter.format(Benefit.getTotalBenefitAmount()));
	}
	
	public static void printAmountForPayment(int paymentAmount) {
		System.out.println("\n<할인 후 예상 결제 금액>\n"
				+formatter.format(paymentAmount));
	}
	
	public static void printBadge(String badge) {
		System.out.println("\n<"+Calendar.EVENT_MONTH+"월 이벤트 배지>\n"+badge);
	}
	
	private static String presentStatus(boolean isSatisfied) {
		if (isSatisfied) {
			return Counter.PRESENT.getViewName()+" "+PRESENT_COUNT+"개";
		}
		return NOTHING;
	}

}
