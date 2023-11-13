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
				Validation.validateTotalMenuCount();
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ErrorView.TOO_MUCH.getMessage());
			}
		}
	}
	
	public void applyBenefits() {
		Benefit.discountForChristmas(customer.getDate());
		Benefit.discountByDayOfWeek(customer.getDate());
		Benefit.discountSpecially(Calendar.isSpecial(customer.getDate()));
		Benefit.givePresent(counter.isSatisfiedForPresent());
		Benefit.giveBadge();
		Benefit.checkBeneficial(counter.isEnoughForEvent());
	}
	
	public void showEventOrderResults() {
		OutputView.printPreviewIntro(customer.getDate());
		OutputView.printOrders(counter.getOrders());
		OutputView.printTotalOrderAmount(counter.getTotalOrderAmount());
		OutputView.printPresentMenu(counter.isSatisfiedForPresent());
		OutputView.printBenefitDetails();
		OutputView.printTotalDiscountAmount();
		OutputView.printAmountForPayment(counter.howMuchForPayment());
		OutputView.printBadge(customer.getBadge());
	}
	
	private void validateInputMenuType() {
		while(true) {
			try {
				validateInputFormat();
				customer.makeOrder();
				Validation.validateNotOnlyBeverage();
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ErrorView.ONLY_BEVERAGE.getMessage());
			}
		}
	}
	
	private void validateInputFormat() {
		while(true) {
			try {
				counter.initOrders();
				counter.initOrdersType();
				customer.setOrders(InputView.readOrder());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ErrorView.ORDER_ERROR.getMessage());
			}
		}
	}
	
}
