package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MenuTypeTest {

	@DisplayName("메뉴가 속한 메뉴타입을 찾는다.")
	@CsvSource(value = {"TAPAS:APPETIZER", "BBQ_RIBS:MAIN", "ICECREAM:DESSERT", "CHAMPAGNE:BEVERAGE"}, delimiter = ':')
    @ParameterizedTest
    void findMenuTypeTest(Menu menu, MenuType expected) {
		MenuType actual = MenuType.findMenuType(menu);
        assertThat(actual).isEqualTo(expected);
    }

}
