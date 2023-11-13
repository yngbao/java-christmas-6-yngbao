package christmas.view;

import java.text.DecimalFormat;

import christmas.domain.Calendar;
import christmas.domain.Counter;
import christmas.domain.Discount;
import christmas.domain.Menu;

public class OutputView {
	
	DecimalFormat formatter = new DecimalFormat("###,###원");
	
	public void printHello() {
		System.out.printf("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n", Calendar.EVENT_MONTH);
	}
	
	public void printPreviewIntro(int date) {
		System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", Calendar.EVENT_MONTH, date);
	}
	
	public void printOrders() {
		System.out.println("\n<주문 메뉴>");
		for(Menu menu : Counter.getOrders().keySet()) {
			System.out.println(menu.getViewName()+" "+Counter.getOrders().get(menu)+"개");
		}
	}
	
	public void printTotalOrderAmount() {
		System.out.println("\n<할인 전 총주문 금액>\n"
				+formatter.format(Counter.getTotalOrderAmount()));
	}
	
	public void printPresentMenu(String presentStatus) {
		System.out.println("\n<증정 메뉴>\n" + presentStatus);
	}
	
	public void printDiscountDetails() {
		System.out.println("\n<혜택 내역>");
		for(Discount discount : Discount.getDiscountResult().keySet()) {
			System.out.println(discount.getViewName()+": -"+formatter.format(discount.getAmount()));
		}
		if (Discount.getDiscountResult().size() == 0 ) {
			System.out.println("없음");
		}
	}
	
	public void printTotalDiscountAmount() {
		System.out.println("\n<총혜택 금액>\n"
				+"-"+formatter.format(Discount.getTotalBenefitAmount()));
	}
	
	public void printAmountForPayment() {
		System.out.println("\n<할인 후 예상 결제 금액>\n"
				+formatter.format(Counter.howMuchForPayment()));
	}

}
