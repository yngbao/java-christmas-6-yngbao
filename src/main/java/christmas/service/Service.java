package christmas.service;

import christmas.domain.Counter;
import christmas.domain.Customer;
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

}
