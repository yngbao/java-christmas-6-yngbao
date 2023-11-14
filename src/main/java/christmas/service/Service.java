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
				validateInputMenuType();
				Validation.validateTotalMenuCount(counter.howManyOrderedMenu());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ErrorView.TOO_MUCH.getMessage());
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
		OutputView.printBenefitDetails();
		OutputView.printTotalDiscountAmount();
		OutputView.printAmountForPayment(counter.howMuchForPayment(benefit.howMuchDiscountAount()));
		OutputView.printBadge(customer.getBadge());
	}
	
	private void validateInputMenuType() {
		while(true) {
			try {
				validateInputFormat();
				counter.takeOrders(customer.getOrders());
				Validation.validateNotOnlyBeverage(counter.findOrderedMenuType());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ErrorView.ONLY_BEVERAGE.getMessage());
			}
		}
	}
	
	private void validateInputFormat() {
		while(true) {
			try {
				customer.setOrders(InputView.readOrder());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ErrorView.ORDER_ERROR.getMessage());
			}
		}
	}
	
}
