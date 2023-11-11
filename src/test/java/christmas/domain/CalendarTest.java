package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalendarTest {

	@DisplayName("입력된 날짜의 요일을 구한다.")
	@CsvSource(value = {"11:11:SATURDAY", "12:25:MONDAY"}, delimiter = ':')
    @ParameterizedTest
    void getDayOfWeek(Integer month, Integer date, DayOfWeek expected) {
		DayOfWeek actual = Calendar.findDayOfWeek(month, date);
        assertThat(actual).isEqualTo(expected);
    }

}
