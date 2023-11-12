package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CounterTest {

	@DisplayName("주문목록의 총 합계금액을 구한다.")
    @Test
    void getTotalOrderAmount() {
		Counter.storeOrder(Menu.SEAFOOD_PASTA, 2);
		Counter.storeOrder(Menu.CHOCO_CAKE, 1);
		Counter.storeOrder(Menu.RED_WINE, 1);

        int result = Counter.getTotalOrderAmount();

        assertThat(result).isEqualTo(145000);
    }

}
