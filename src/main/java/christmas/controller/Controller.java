package christmas.controller;

import christmas.service.Service;

public class Controller {
	
	Service service = new Service();
	
	public void run() {
		reservation();
		checkBenefits();
		resultPreview();
	}
	
	private void reservation() {
		service.getInputDate();
		service.getInputOrder();
	}
	
	private void checkBenefits() {
		service.applyBenefits();
	}
	
	private void resultPreview() {
		service.showEventOrderResults();
	}

}
