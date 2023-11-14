package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CounterTest {
	private static Map<Menu, Integer> customerOrder = new HashMap<>();
	
	@BeforeAll
	static void setUp() {
		customerOrder.put(Menu.CAESAR_SALAD, 1);
		customerOrder.put(Menu.SEAFOOD_PASTA, 1);
		customerOrder.put(Menu.ICECREAM, 2);
		customerOrder.put(Menu.ZERO_COKE, 1);
	}

	@DisplayName("주문된 메뉴를 주문 목록에 저장한다.")
    @Test
    void takeOrdersTest() {
		Counter counter = new Counter();
		counter.takeOrders(customerOrder);

        assertThat(counter.getOrders()).containsAllEntriesOf(customerOrder);
    }
	
	@DisplayName("주문목록의 총 합계금액을 구한다.")
    @Test
    void getTotalOrderAmount() {
		Counter counter = new Counter();
		counter.takeOrders(customerOrder);
        int result = counter.getTotalOrderAmount();

        assertThat(result).isEqualTo(56000);
    }

}
