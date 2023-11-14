package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTest {

	@DisplayName("문자열과 일치하는 이름의 메뉴를 찾는다.")
	@CsvSource(value = {"타파스:TAPAS", "초코케이크:CHOCO_CAKE", "레드와인:RED_WINE"}, delimiter = ':')
    @ParameterizedTest
    void findMenuTest(String input, Menu expected) {
		Menu actual = Menu.findMenu(input);
        assertThat(actual).isEqualTo(expected);
    }

}
