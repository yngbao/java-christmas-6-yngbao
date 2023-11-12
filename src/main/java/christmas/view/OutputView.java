package christmas.view;

import java.text.DecimalFormat;

import christmas.domain.Counter;
import christmas.domain.Event;
import christmas.domain.Menu;

public class OutputView {
	
	DecimalFormat formatter = new DecimalFormat("###,###원");
	
	public void printHello() {
		System.out.printf("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n", Event.EVENT_MONTH);
	}
	
	public void printPreviewIntro(int date) {
		System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", Event.EVENT_MONTH, date);
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
	
	

}
