package christmas.view;

import christmas.domain.Counter;
import christmas.domain.Event;
import christmas.domain.Menu;

public class OutputView {
	
	public void printHello() {
		System.out.printf("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n", Event.EVENT_MONTH);
	}
	
	public void printPreviewIntro(int date) {
		System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n", Event.EVENT_MONTH, date);
	}
	
	public void printOrders() {
		System.out.println("<주문 메뉴>");
		for(Menu menu : Counter.getOrders().keySet()) {
			System.out.println(menu.getViewName()+" "+Counter.getOrders().get(menu)+"개");
		}
	}

}
