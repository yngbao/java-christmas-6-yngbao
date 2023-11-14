package christmas.service;

import christmas.domain.Benefit;
import christmas.domain.Calendar;
import christmas.domain.Counter;
import christmas.domain.Customer;
import christmas.domain.Menu;
import christmas.domain.MenuType;
import christmas.validation.Validation;
import christmas.view.ErrorView;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Service {
	private static final int MENU_INDEX = 0;
	private static final int COUNT_INDEX = 1;
	
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
				Validation.validateTotalMenuCount(counter.howManyOrderedMenu());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ErrorView.TOO_MUCH.getMessage());
			}
		}
	}
	
	public void applyBenefits() {
		Benefit.discountForChristmas(customer.getDate());
		int count = counter.howManyDiscountMenu(COUNT_INDEX);
		Benefit.discountByDayOfWeek(customer.getDate(), count);
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
				makeOrder();
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
				counter.initOrders();
				counter.initOrdersType();
				customer.setOrders(InputView.readOrder());
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(ErrorView.ORDER_ERROR.getMessage());
			}
		}
	}
	
	public void makeOrder() {
		for(String[] order : customer.getOrders()) {
			takeOrder(order[MENU_INDEX], Integer.valueOf(order[COUNT_INDEX]));
		}
	}
	
	public void takeOrder(String inputMenu, int count) {
		Menu orderMenu = Menu.findMenu(inputMenu);
		MenuType orderMenuType = MenuType.findMenuType(orderMenu);
		counter.storeOrder(orderMenu, Integer.valueOf(count));
		counter.storeOrderType(orderMenuType, Integer.valueOf(count));
	}
	
}
