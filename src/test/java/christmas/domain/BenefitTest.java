package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitTest {
	Benefit benefit = new Benefit();

	@DisplayName("기준 충족 시 증정이벤트를 적용한다.")
    @Test
    void givePresentTest() {
		benefit.givePresent(true);

        assertThat(benefit.getBenefitResult()).contains(Map.entry(Event.PRESENT, Event.PRESENT.getAmount()));
    }
	
	@DisplayName("날짜가 1~25면 날짜별 할인혜택을 적용한다.")
    @Test
    void discountForChristmasTest() {
		benefit.discountForChristmas(15);
		benefit.discountForChristmas(26);

        assertThat(benefit.getBenefitResult()).contains(Map.entry(Event.X_MAS_DISCOUNT, 2400));
    }
	
	@DisplayName("날짜가 해당하는 요일할인 메뉴타입을 주문된 개수만큼 2023원씩 혜택 적용한다.")
    @Test
    void discountByDayOfWeekTest() {
		benefit.discountByDayOfWeek(13, 3);

        assertThat(benefit.getBenefitResult()).contains(Map.entry(Event.WEEKDAY_DISCOUNT, 6069));
    }
	
	@DisplayName("특별할인 대상 날짜인 경우 1000원 할인을 적용한다.")
    @Test
    void specialDiscountTest() {
		benefit.discountSpecially(true);

        assertThat(benefit.getBenefitResult()).contains(Map.entry(Event.SPECIAL_DISCOUNT, 1000));
    }
	
	@DisplayName("기준에 충족되지 않으면 모든 혜택을 적용하지 않는다.")
    @Test
    void checkBeneficialTest() {
		benefit.checkBeneficial(false);

        assertThat(benefit.getBenefitResult()).isEmpty();;
    }
	
	@DisplayName("총 혜택금액을 구한다.")
    @Test
    void totalBenefitAmountTest() {
		int date = 4;
		benefit.checkBeneficial(false);
		benefit.givePresent(true);
		benefit.discountForChristmas(date);
		benefit.discountByDayOfWeek(date, 1);
		benefit.discountSpecially(true);

        assertThat(benefit.getTotalBenefitAmount()).isEqualTo(29323);
    }
	
	@DisplayName("증정메뉴를 제외한 총 할인금액을 구한다.")
    @Test
    void totalDiscountAmountTest() {
		int date = 4;
		benefit.checkBeneficial(false);
		benefit.givePresent(true);
		benefit.discountForChristmas(date);
		benefit.discountByDayOfWeek(date, 1);
		benefit.discountSpecially(true);

        assertThat(benefit.howMuchDiscountAmount()).isEqualTo(4323);
    }

}
