package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CustomerTest {
	Customer customer = new Customer();

	@DisplayName("입력값이 1이상 31이하의 숫자가 아닌 경우 예외가 발생한다.")
	@CsvSource(value = {"0", "32"})
    @ParameterizedTest
    void inputDateInRangeTest(int date) {
		assertThatThrownBy(() -> customer.setDate(date))
        		.isInstanceOf(IllegalArgumentException.class);
    }
	
	@DisplayName("올바른 주문 형식이 아닌 경우 예외가 발생한다.")
    @Test
    void orderFormatTest() {
		String[] case1 = {"12"};
		String[] case2 = {"해산물파스타-"};
		
        assertThatThrownBy(() -> customer.setOrders(case1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> customer.setOrders(case2))
        		.isInstanceOf(IllegalArgumentException.class);
    }
	
	@DisplayName("메뉴판에 없는 메뉴일 경우 예외가 발생한다.")
    @Test
    void InMenuListTest() {
		String[] case1 = {"봉골레파스타-1"};
		String[] case2 = {"치즈케이크-2"};
		
        assertThatThrownBy(() -> customer.setOrders(case1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> customer.setOrders(case2))
        		.isInstanceOf(IllegalArgumentException.class);
    }
	
	@DisplayName("특정 메뉴 개수가 0개 이거나, 중복 메뉴가 있으면 예외가 발생한다.")
    @Test
    void ordersTest() {
		String[] case1 = {"해산물파스타-0"};
		String[] case2 = {"초코케이크-1", "초코케이크-2"};
		
        assertThatThrownBy(() -> customer.setOrders(case1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> customer.setOrders(case2))
        		.isInstanceOf(IllegalArgumentException.class);
    }

}
