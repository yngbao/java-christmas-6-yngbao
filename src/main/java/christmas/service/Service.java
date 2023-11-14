package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Calendar;
import christmas.domain.Counter;
import christmas.domain.Customer;
import christmas.validation.Validation;
import christmas.view.ErrorView;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Service {

	Customer customer = new Customer();
	Counter counter = new Counter();
	Benefit benefit = new Benefit();
	
	public void getInputDate() {
		OutputView.printHello();
		while(true) {
			try {
				customer.setDate(InputView.readDate());
				break;
			} catch (NumberFormatException e) {
				System.out.println(ErrorView.DATE_ERROR.getMessage());
			} catch (IllegalArgumentException e) {
				System.out.println(ErrorView.DATE_ERROR.getMessage());
			}
		}
	}
	
	public void getInputOrder() {
		while(true) {
			try {
				customer.setOrders(InputView.readOrder());
				counter.takeOrders(customer.getOrders());
				Validation.validateNotOnlyBeverage(counter.findOrderedMenuType());
				Validation.validateTotalMenuCount(counter.howManyOrderedMenu());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ErrorView.ORDER_ERROR.getMessage());
			}
		}
	}
	
	public void applyBenefits() {
		benefit.discountForChristmas(customer.getDate());
		int count = counter.howManyDiscountMenu(customer.getDate());
		benefit.discountByDayOfWeek(customer.getDate(), count);
		benefit.discountSpecially(Calendar.isSpecial(customer.getDate()));
		benefit.givePresent(counter.isSatisfiedForPresent());
		benefit.giveBadge();
		benefit.checkBeneficial(counter.isEnoughForEvent());
	}
	
	public void showEventOrderResults() {
		OutputView.printPreviewIntro(customer.getDate());
		OutputView.printOrders(counter.getOrders());
		OutputView.printTotalOrderAmount(counter.getTotalOrderAmount());
		OutputView.printPresentMenu(counter.isSatisfiedForPresent());
		OutputView.printBenefitDetails(benefit.getBenefitResult());
		OutputView.printTotalDiscountAmount(benefit.getTotalBenefitAmount());
		OutputView.printAmountForPayment(counter.howMuchForPayment(benefit.howMuchDiscountAmount()));
		OutputView.printBadge(customer.getBadge());
	}
	
}
