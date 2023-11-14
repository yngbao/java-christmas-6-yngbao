package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CounterTest {

	@DisplayName("주문된 메뉴를 주문 목록에 저장한다.")
    @Test
    void storeOrderTest() {
		Counter counter = new Counter();
		counter.storeOrder(Menu.CAESAR_SALAD, 1);

        assertThat(counter.getOrders()).containsEntry(Menu.CAESAR_SALAD, 1);
    }
	
	@DisplayName("주문목록의 총 합계금액을 구한다.")
    @Test
    void getTotalOrderAmount() {
		Counter counter = new Counter();
		counter.storeOrder(Menu.SEAFOOD_PASTA, 1);
		counter.storeOrder(Menu.ICECREAM, 2);
        int result = counter.getTotalOrderAmount();

        assertThat(result).isEqualTo(45000);
    }

}
