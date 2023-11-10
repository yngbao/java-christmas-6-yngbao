package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountTest {

	@DisplayName("입력된 날짜 별 할인 금액을 구한다.")
	@CsvSource(value = {"2:1100", "12:2100", "25:3400"}, delimiter = ':')
    @ParameterizedTest
    void discountForDate(Integer date, Integer expected) {
		int actualValue = Discount.getDateDiscount(date);
        assertThat(actualValue).isEqualTo(expected);
    }
	
	@DisplayName("할인 적용된 메뉴에서 2,023원을 할인한다.")
    @Test
    void discountForMenu() {
        Menu case1 = Menu.BBQ_RIBS;
        Menu case2 = Menu.ICECREAM;

        int result1 = Discount.getMenuDiscount(case1);
        int result2 = Discount.getMenuDiscount(case2);

        assertThat(result1).isEqualTo(51977);
        assertThat(result2).isEqualTo(2977);
    }

}
