package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountTest {

	@DisplayName("입력된 금액이 1000원 단위가 아니면 예외가 발생한다.")
	@CsvSource(value = {"2:1100", "12:2100", "25:3400"}, delimiter = ':')
    @ParameterizedTest
    void discountForDate(Integer date, Integer expectedAmount) {
		int actualValue = Discount.getDateDiscount(date);

        assertThat(actualValue).isEqualTo(expectedAmount);
    }

}
