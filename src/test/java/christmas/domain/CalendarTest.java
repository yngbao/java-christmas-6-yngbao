package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalendarTest {

	@DisplayName("입력된 날짜가 평일(일~목)인지 아닌지 판단한다.")
	@CsvSource(value = {"16:false", "25:true"}, delimiter = ':')
    @ParameterizedTest
    void isWeekdayTest(Integer date, boolean isWeekday) {
		boolean actual = Calendar.isWeekday(date);
        assertThat(actual).isEqualTo(isWeekday);
    }
	
	@DisplayName("입력된 날짜가 크리스마스 디데이 이벤트 대상인지 아닌지 판단한다.")
	@CsvSource(value = {"2:true", "18:true", "26:false"}, delimiter = ':')
    @ParameterizedTest
    void isBeforeChristmasTest(Integer date, boolean isWeekday) {
		boolean actual = Calendar.isBeforeChristmas(date);
        assertThat(actual).isEqualTo(isWeekday);
    }
	
	@DisplayName("입력된 날짜가 특별할인 대상인지 아닌지 판단한다.")
	@CsvSource(value = {"3:true", "10:true", "17:true", "24:true", "25:true", "31:true", "15:false"}, delimiter = ':')
    @ParameterizedTest
    void isSpecialTest(Integer date, boolean isWeekday) {
		boolean actual = Calendar.isSpecial(date);
        assertThat(actual).isEqualTo(isWeekday);
    }

}
