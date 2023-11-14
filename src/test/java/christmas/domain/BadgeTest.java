package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

	@DisplayName("금액에 따라 배지를 부여한다.")
	@CsvSource(value = {"4000:없음", "8000:별", "17000:트리", "25000:산타"}, delimiter = ':')
    @ParameterizedTest
    void giveBadgeTest(Integer amount, String expected) {
		String badge = Badge.judgeBadge(amount);
		
        assertThat(badge).isEqualTo(expected);
    }

}
