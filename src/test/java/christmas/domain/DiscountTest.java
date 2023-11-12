package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountTest {

	@DisplayName("입력된 날짜 할인액에 해당하는 금액을 할인한다.")
	@CsvSource(value = {"2:5000:3900", "12:5000:2900", "25:8000:4600"}, delimiter = ':')
    @ParameterizedTest
    void discountByDateTest(Integer date,Integer amount, Integer expected) {
		int actualValue = Discount.discountByDate(amount, date);
        assertThat(actualValue).isEqualTo(expected);
    }
	
	@DisplayName("할인 적용된 메뉴수만큼 2,023원씩 총 메뉴 할인액을 구한다.")
    @Test
    void discountByMenuTest() {
        int case1 = 1;
        int case2 = 3;

        int result1 = Discount.getWeekdayDiscount(case1);
        int result2 = Discount.getWeekdayDiscount(case2);

        assertThat(result1).isEqualTo(2023);
        assertThat(result2).isEqualTo(6069);
    }
	
	@DisplayName("특별할인 대상이면 1000원을 할인한다.")
	@CsvSource(value = {"5000:false:5000", "15000:true:14000"}, delimiter = ':')
    @ParameterizedTest
    void discountSpeciallyTest(Integer amount,boolean isSpecial, Integer expected) {
		int actualValue = Discount.discountSpecially(amount, isSpecial);
        assertThat(actualValue).isEqualTo(expected);
    }

}
