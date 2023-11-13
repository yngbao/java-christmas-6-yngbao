package christmas.controller;

import christmas.service.Service;

public class Controller {
	
	Service service = new Service();
	
	public void run() {
		reservation();
		checkEventBenefits();
		reservationPreview();
	}
	
	private void reservation() {
		service.getInputDate();
		service.getInputOrder();
	}
	
	private void checkEventBenefits() {
		service.applyBenefits();
	}
	
	private void reservationPreview() {
		service.showEventOrderResults();
	}

}
