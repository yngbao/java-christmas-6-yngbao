package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CounterTest {

	@DisplayName("주문목록의 총 합계금액을 구한다.")
    @Test
    void getTotalOrderAmount() {
		Counter.takeOrder("해산물파스타", 2);
		Counter.takeOrder("초코케이크", 1);
		Counter.takeOrder("레드와인", 1);

        int result = Counter.getTotalOrderAmount();

        assertThat(result).isEqualTo(145000);
    }

}
