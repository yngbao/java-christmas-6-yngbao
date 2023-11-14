package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
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
	
	@DisplayName("주문목록의 총 개수를 구한다.")
    @Test
    void countOrderedMenuTest() {
        int result = counter.howManyOrderedMenu();

        assertThat(result).isEqualTo(2);
    }
	
	@DisplayName("주문받은 메뉴타입들을 구한다.")
    @Test
    void findOrderedMenuTypeTest() {
		List<MenuType> result = counter.findOrderedMenuType();

        assertThat(result).containsOnly(MenuType.APPETIZER, MenuType.MAIN);
    }
	
	@DisplayName("주문된 메뉴를 주문 목록에 저장한다.")
    @Test
    void takeOrdersTest() {
		Counter counter2 = new Counter();
		counter2.takeOrders(Orders2);
		Map<Menu, Integer> result = counter2.getOrders();

        assertThat(result).containsAllEntriesOf(Orders2);
    }

	@DisplayName("주문 목록을 확인한다.")
    @Test
    void getOrdersTest() {
		Map<Menu, Integer> result = counter.getOrders();

        assertThat(result).containsAllEntriesOf(Orders1);
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

}
