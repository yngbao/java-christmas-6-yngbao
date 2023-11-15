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
	
	@DisplayName("평일 여부에 따라 할인되는 메뉴타입을 확인한다.")
	@CsvSource(value = {"true:DESSERT", "false:MAIN"}, delimiter = ':')
    @ParameterizedTest
    void findDiscountMenuTypeTest(boolean isWeekday, MenuType expected) {
		MenuType actual = MenuType.findDiscountableType(isWeekday);
		
        assertThat(actual).isEqualTo(expected);
    }

}
