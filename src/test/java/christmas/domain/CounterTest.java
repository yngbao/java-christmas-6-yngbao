package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CounterTest {
	private static Map<Menu, Integer> Orders1 = new HashMap<>();
	private static Map<Menu, Integer> Orders2 = new HashMap<>();
	private static Counter counter = new Counter();
	
	@BeforeAll
	static void setUp() {
		Orders1.put(Menu.CAESAR_SALAD, 1);
		Orders1.put(Menu.SEAFOOD_PASTA, 1);
		Orders2.put(Menu.T_BONE_STEAK, 1);
		Orders2.put(Menu.CHOCO_CAKE, 2);
		Orders2.put(Menu.RED_WINE, 1);
		counter.takeOrders(Orders1);
	}
	
	@DisplayName("주문된 메뉴를 주문 목록에 저장한다.")
    @Test
    void takeOrdersTest() {
		Counter counter2 = new Counter();
		counter2.takeOrders(Orders2);
		Map<Menu, Integer> result = counter2.getOrders();

        assertThat(result).containsAllEntriesOf(Orders2);
    }
	
	@DisplayName("날짜에 따라 할인되는 메뉴의 개수를 구한다.")
	@CsvSource(value = {"2:1", "13:0"}, delimiter = ':')
    @ParameterizedTest
    void countDiscountMenuTest(Integer date, Integer expected) {
		int actual = counter.howManyDiscountMenu(date);
		
        assertThat(actual).isEqualTo(expected);
    }
	
	@DisplayName("총 주문금액이 1000원 이상인지 확인한다.")
    @Test
    void isEnoughForEventTest() {
		boolean result = counter.isEnoughForEvent();

        assertThat(result).isTrue();
    }
	
	@DisplayName("총 주문금액이 증정 이벤트 기준에 맞는지 확인한다.")
    @Test
    void isSatisfiedForPresentTest() {
		Counter counter3 = new Counter();
		counter3.takeOrders(Orders2);
		
		boolean result1 = counter.isSatisfiedForPresent();
		boolean result2 = counter3.isSatisfiedForPresent();

        assertThat(result1).isFalse();
        assertThat(result2).isTrue();
    }
	
	@DisplayName("주문목록의 총 합계금액을 구한다.")
    @Test
    void totalOrderAmountTest() {
        int result = counter.getTotalOrderAmount();

        assertThat(result).isEqualTo(43000);
    }
	
	@DisplayName("음료만 주문한 경우 예외가 발생한다.")
    @Test
    void onlyBeverageTest() {
		Counter counter3 = new Counter();
		Map<Menu, Integer> order = new HashMap<>();
		order.put(Menu.RED_WINE, 1);
		order.put(Menu.ZERO_COKE, 2);
		
        assertThatThrownBy(() -> counter3.takeOrders(order))
                .isInstanceOf(IllegalArgumentException.class);
    }
	
	@DisplayName("총 주문 개수가 20개를 초과한 경우 예외가 발생한다.")
    @Test
    void tooMuchOrderTest() {
		Counter counter4 = new Counter();
		Map<Menu, Integer> order = new HashMap<>();
		order.put(Menu.SEAFOOD_PASTA, 21);
		
        assertThatThrownBy(() -> counter4.takeOrders(order))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
