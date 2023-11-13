package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CounterTest {

	@DisplayName("주문목록의 총 합계금액을 구한다.")
    @Test
    void getTotalOrderAmount() {
		Counter counter = new Counter();
		Counter.takeOrder("해산물파스타", 2);

        int result = counter.getTotalOrderAmount();

        assertThat(result).isEqualTo(70000);
    }

}
