package christmas.view;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Event;

public class InputView {
	private static final String SPLIT_CHAR = ",";
	
	public int readDate() {  //NumberFormatException 예외처리 필요
		System.out.printf("%d월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)\n", Event.EVENT_MONTH);
		String input = Console.readLine();
		return Integer.valueOf(input);
	}
	
	public List<String> readOrder() {
		System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
		return Stream.of(Console.readLine().split(SPLIT_CHAR)).collect(Collectors.toList());
	}

}
