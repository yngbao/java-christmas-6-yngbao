package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalendarTest {

	@DisplayName("입력된 날짜의 요일을 구한다.")
	@CsvSource(value = {"16:false", "25:true"}, delimiter = ':')
    @ParameterizedTest
    void IsWeekday(Integer date, boolean isWeekday) {
		boolean actual = Calendar.isWeekday(date);
        assertThat(actual).isEqualTo(isWeekday);
    }

}
